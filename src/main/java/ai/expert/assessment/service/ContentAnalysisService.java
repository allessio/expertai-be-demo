package ai.expert.assessment.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.expert.assessment.persistence.entity.Content;
import ai.expert.assessment.persistence.entity.ContentAnalysis;
import ai.expert.assessment.persistence.repository.ContentAnalysisRepository;
import ai.expert.assessment.service.interfaces.IContentAnalysisService;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.Globals;
import ai.expert.assessment.utils.ResourceUtils;
import ai.expert.nlapi.security.Authentication;
import ai.expert.nlapi.v1.API;
import ai.expert.nlapi.v1.Analyzer;
import ai.expert.nlapi.v1.AnalyzerConfig;
import ai.expert.nlapi.v1.Categorizer;
import ai.expert.nlapi.v1.CategorizerConfig;
import ai.expert.nlapi.v1.message.ResponseDocument;

@Service
public class ContentAnalysisService implements IContentAnalysisService {

   @Autowired
   ContentAnalysisRepository repo;

   @Autowired
   IContentsService contentsService;

   private static final Logger logger = LogManager.getLogger();

   public void analyzeAllTypesAllPersistedDocs(Authentication authenticator, IContentsService contentsService) {
//      analyzeCategoriesAllPersistedDocs(authenticator, contentsService);
      analyzeFullAllPersistedDocs(authenticator, contentsService);
   }

   public void analyzeCategoriesAllPersistedDocs(Authentication authenticator, IContentsService contentsService) {
      try {
         Categorizer categorizer = createCategorizer(authenticator);

         // for all contents in DB
         for (Content content : contentsService.getAll()) {
            String contentParts[] = contentSplitter(content);
            List<ContentAnalysis> contAnalysisList = new ArrayList<>();

            // for all parts of each content
            for (int i = 0; i < contentParts.length; i++) {
               ResponseDocument categorization = categorizer.categorize(contentParts[i]);
               ContentAnalysis contAn = new ContentAnalysis();

               contAn.setAnalysis_type(Globals.ANALYSIS_TYPE_CAT);
               contAn.setAnalyzed_at(LocalDateTime.now());
               contAn.setContent(content);
               contAn.setContent_language(getLanguageFromWSResponse(categorization));
               contAn.setContent_part_id(Long.valueOf(i));
               contAn.setWs_checker_success(categorization.isSuccess());
               contAn.setWs_checker_version(categorization.getData().getVersion());
               contAn.setWs_checker_response(categorization.toJSON().getBytes());

               contAnalysisList.add(contAn);
            }

            content.setContentAnalysis(contAnalysisList);
            contentsService.update(content);
         }
      } catch (Exception e) {
         logger.info("Error in getting categorizer or saving results of analysis");
         e.printStackTrace();
      }
   }

   public void analyzeFullAllPersistedDocs(Authentication authenticator, IContentsService contentsService) {
      try {
         Analyzer analyzer = createAnalyzer(authenticator);

         // for all contents in DB
         for (Content content : contentsService.getAll()) {
            String contentParts[] = contentSplitter(content);
            List<ContentAnalysis> contAnalysisList = new ArrayList<>();
//            Content cont = ResourceUtils.checkFound(contentsService.read(content.getContent_id()));

            // for all parts of each content
            for (int i = 0; i < contentParts.length; i++) {
               ResponseDocument analysis = analyzer.analyze(contentParts[i]);
               ContentAnalysis contAn = new ContentAnalysis();

               contAn.setAnalysis_type(Globals.ANALYSIS_TYPE_EXT_FULL);
               contAn.setAnalyzed_at(LocalDateTime.now());
               contAn.setContent(content);
               contAn.setContent_language(getLanguageFromWSResponse(analysis));
               contAn.setContent_part_id(Long.valueOf(i));
               contAn.setWs_checker_success(analysis.isSuccess());
               contAn.setWs_checker_version(analysis.getData().getVersion());
               contAn.setWs_checker_response(analysis.toJSON().getBytes());

               contAnalysisList.add(contAn);
            }

            content.setContentAnalysis(contAnalysisList);
            contentsService.update(content);
         }
      } catch (Exception e) {
         logger.info("Error in getting full analizer or saving results of analysis");
         e.printStackTrace();
      }
   }

   public void analyzeDisambiguationAllPersistedDocs() {
      // TODO: to implement
   }

   public void analyzeRelevantsAllPersistedDocs() {
      // TODO: to implement
   }

   public void analyzeEntitiesAllPersistedDocs() {
      // TODO: to implement
   }

   private String getLanguageFromWSResponse(ResponseDocument response) {
      // TODO: parse response & extract language
      return "en"; // stub
   }

   private String[] contentSplitter(Content content) {
      List<String> contentParts = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      String contentParagraphs[] = new String(content.getContent(), StandardCharsets.UTF_8).split("\\r?\\n");

      for (int i = 0; i < contentParagraphs.length; i++) {

         // split by paragraphs
         if (contentParagraphs[i].length() < Globals.WS_CONTENT_LIMIT) {
            if (sb.length() + contentParagraphs[i].length() < Globals.WS_CONTENT_LIMIT) {
               sb.append(contentParagraphs[i]);
               sb.append(Globals.PARAGRAPH_DELIMITER_FOR_WS);
            } else {
               contentParts.add(sb.toString());
               sb.setLength(0);
               sb.append(contentParagraphs[i]);
               sb.append(Globals.PARAGRAPH_DELIMITER_FOR_WS);
            }
         } else { // split by sentences
            if (sb.length() > 0) { // flush buffer to resultset
               contentParts.add(sb.toString());
               sb.setLength(0);
            }
            String contentSentences[] = contentParagraphs[i].split("(?<=[a-z])\\.\\s+");
            for (int j = 0; j < contentSentences.length; j++) {
               if (contentSentences[j].length() < Globals.WS_CONTENT_LIMIT) {
                  if (sb.length() + contentSentences[j].length() < Globals.WS_CONTENT_LIMIT) {
                     sb.append(contentSentences[j]);
                     sb.append(Globals.SENTENCE_DELIMITER_FOR_WS);
                  } else {
                     contentParts.add(sb.toString());
                     sb.setLength(0);
                     sb.append(contentSentences[j]);
                     sb.append(Globals.SENTENCE_DELIMITER_FOR_WS);
                  }
               } else {
                  // TODO break sentence into logical parts, if arrives a really huge one
               }
            }
         }
      }
      if (sb.length() > 0) { // flush buffer to resultset
         contentParts.add(sb.toString());
      }

      return contentParts.toArray(new String[0]);
   }

   private Categorizer createCategorizer(Authentication authenticator) throws Exception {
      return new Categorizer(CategorizerConfig.builder().withVersion(API.Versions.V1).withTaxonomy(API.Taxonomies.IPTC)
            .withLanguage(API.Languages.en).withAuthentication(authenticator).build());
   }
   
   private Analyzer createAnalyzer(Authentication authenticator) throws Exception {
      return new Analyzer(AnalyzerConfig.builder()
            .withVersion(API.Versions.V1)
            .withContext(API.Contexts.STANDARD)
            .withLanguage(API.Languages.en)
            .withAuthentication(authenticator)
            .build());
   }

   @Override
   public List<ContentAnalysis> getAll() {
      return repo.findAll();
   }

   @Override
   public Page<ContentAnalysis> getAllPageable(Pageable pageable) {
      return repo.findAll(pageable);
   }

   @Override
   public ContentAnalysis create(ContentAnalysis entity) {
//      return repo.saveAndFlush(entity);
      return repo.save(entity);
   }

   @Override
   public ContentAnalysis update(ContentAnalysis entity) {
      return repo.save(entity);
   }

   @Override
   public Optional<ContentAnalysis> read(Long id) {
      return repo.findById(id);
   }

   @Override
   public void delete(Long id) {
      repo.deleteById(id);
   }

}
