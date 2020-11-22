package ai.expert.assessment.service;

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
import ai.expert.assessment.service.interfaces.IAppInitService;
import ai.expert.assessment.service.interfaces.IContentAnalysisService;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.Globals;
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
   
   @Autowired 
   IAppInitService appInitService;

   private static final Logger logger = LogManager.getLogger();

   public void analyzeAllTypesAllPersistedDocs() {
//      analyzeCategoriesAllPersistedDocs();
//      analyzeFullAllPersistedDocs();
   }

   /**
    * Category analyzer for all documents persisted in DB.
    */
   @Override
   public void analyzeCategoriesAllPersistedDocs() {
      analyzeCategoriesPersistedDocs(0L, -1L);
   }
   
   /**
    * Category analyzer for certain documents persisted in DB. 
    * Document to analyze are identified by range of ID document docIDFrom to docIDTo.
    * If docIDFrom > docIDFrom, all documents are analyzed
    * 
    * @param docIDFrom
    * @param docIDTo
    */
   public void analyzeCategoriesPersistedDocs(Long docIDFrom, Long docIDTo) {
      try {
         Categorizer categorizer = createCategorizer();

         // for all contents in DB
         for (Content content : contentsService.getAll()) {
            // check if document should be analyzed by provided IDs range
            if (docIDFrom > docIDTo || content.getContent_id() < docIDFrom || content.getContent_id() > docIDTo) {
               continue;
            }
            
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
               
               if (categorization != null) {
                  contAn.setWs_checker_success(categorization.isSuccess());
                  contAn.setWs_checker_version(categorization.getData().getVersion());
                  contAn.setWs_checker_response(categorization.toJSON().getBytes(Globals.DEFAULT_ENCODING));
               }

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

   
   public void analyzeFullAllPersistedDocs() {
      analyzeFullPersistedDocs(0L, -1L);
   }
   
   /**
    * Category analyzer for all documents persisted in DB.
    */
   public void analyzeFullPersistedDocs(Long docIDFrom, Long docIDTo) {
      try {
         Analyzer analyzer = createAnalyzer();

         // for all contents in DB
         for (Content content : contentsService.getAll()) {
            // check if document should be analyzed by provided IDs range
            if (docIDFrom > docIDTo || content.getContent_id() < docIDFrom || content.getContent_id() > docIDTo) {
               continue;
            }
            
            String contentParts[] = contentSplitter(content);
            List<ContentAnalysis> contAnalysisList = new ArrayList<>();

            // for all parts of each content
            for (int i = 0; i < contentParts.length; i++) {
               ResponseDocument analysis = analyzer.analyze(contentParts[i]);
               ContentAnalysis contAn = new ContentAnalysis();

               contAn.setAnalysis_type(Globals.ANALYSIS_TYPE_EXT_FULL);
               contAn.setAnalyzed_at(LocalDateTime.now());
               contAn.setContent(content);
               contAn.setContent_language(getLanguageFromWSResponse(analysis));
               contAn.setContent_part_id(Long.valueOf(i));
               
               if (analysis != null) {
                  contAn.setWs_checker_success(analysis.isSuccess());
                  contAn.setWs_checker_version(analysis.getData().getVersion());
                  contAn.setWs_checker_response(analysis.toJSON().getBytes(Globals.DEFAULT_ENCODING));
               }

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
      // TODO: to implement if needed
   }

   public void analyzeRelevantsAllPersistedDocs() {
      // TODO: to implement if needed
   }

   public void analyzeEntitiesAllPersistedDocs() {
      // TODO: to implement if needed
   }

   private String getLanguageFromWSResponse(ResponseDocument response) {
      // TODO: parse response & extract language
      return "en"; // stub
   }

   /**
    * Splits content into chunks of max size < Globals.WS_CONTENT_LIMIT
    * 
    * @param content
    * @return array of content parts satisfying Globals.WS_CONTENT_LIMIT
    */
   private String[] contentSplitter(Content content) {
      List<String> contentParts = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      String contentParagraphs[] = new String(content.getContent(), Globals.DEFAULT_ENCODING).split("\\r?\\n");

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
                  // TODO if needed, break sentence into logical parts (if arrives a really huge one)
               }
            }
         }
      }
      if (sb.length() > 0) { // flush buffer to resultset
         contentParts.add(sb.toString());
      }

      return contentParts.toArray(new String[0]);
   }

   private Categorizer createCategorizer() throws Exception {
      return new Categorizer(CategorizerConfig.builder().withVersion(API.Versions.V1).withTaxonomy(API.Taxonomies.IPTC)
            .withLanguage(API.Languages.en).withAuthentication(appInitService.getAuthentication()).build());
   }
   
   private Analyzer createAnalyzer() throws Exception {
      return new Analyzer(AnalyzerConfig.builder()
            .withVersion(API.Versions.V1)
            .withContext(API.Contexts.STANDARD)
            .withLanguage(API.Languages.en)
            .withAuthentication(appInitService.getAuthentication())
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
