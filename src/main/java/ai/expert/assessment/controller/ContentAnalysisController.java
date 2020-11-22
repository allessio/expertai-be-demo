package ai.expert.assessment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ai.expert.assessment.persistence.entity.Content;
import ai.expert.assessment.persistence.entity.ContentAnalysis;
import ai.expert.assessment.service.interfaces.IContentAnalysisService;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.Globals;
import ai.expert.assessment.utils.ResourceUtils;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Globals.CONTENT_ANALYSIS_ENDPOINT)
public class ContentAnalysisController extends BaseController<ContentAnalysis> {

   @Value("${expertai.username}")
   private String username;

   @Value("${expertai.password}")
   private String password;

   @Autowired
   private IContentAnalysisService contentAnalysisService;

   @Autowired
   private IContentsService contentsService;

   @GetMapping("/all")
   @ApiOperation(value = "List of analyzed parts of content", notes = "Entry point REST for getting the list of analyzed parts of content")
   public Page<ContentAnalysis> getAll(Pageable pageable, HttpServletRequest request) {
      return contentAnalysisService.getAllPageable(pageable);
   }

   @PostMapping(path = { "/category/all" })
   @ResponseStatus(value = HttpStatus.CREATED)
   @ApiOperation(value = "Request to provide category analysis of persisted documents", notes = "Entry point REST to provide category analysis of persisted documents")
   public void analyzeCategoriesAllPersistedDocs(HttpServletRequest request) {

      contentAnalysisService.analyzeCategoriesAllPersistedDocs();
   }
   
   // analysis/category/?docIDFrom=?&docIDTo=?
   @PostMapping(path = { "/category/" })
   @ResponseStatus(value = HttpStatus.CREATED)
   @ApiOperation(value = "Request to provide category analysis of persisted documents within given range of IDs", notes = "Entry point REST to provide category analysis of persisted documents within given range of IDs")
   public void analyzeCategoriesPersistedDocs(Long docIDFrom, Long docIDTo, HttpServletRequest request) {

      contentAnalysisService.analyzeCategoriesPersistedDocs(docIDFrom, docIDTo);
   }

   @PostMapping(path = { "/full/all" })
   @ResponseStatus(value = HttpStatus.CREATED)
   @ApiOperation(value = "Request to provide full analysis of persisted documents", notes = "Entry point REST to provide full analysis of persisted documents")
   public void analyzeFullAllPersistedDocs(HttpServletRequest request) {

      contentAnalysisService.analyzeFullAllPersistedDocs();
   }
   
   // analysis/full/?docIDFrom=?&docIDTo=?
   @PostMapping(path = { "/full/" })
   @ResponseStatus(value = HttpStatus.CREATED)
   @ApiOperation(value = "Request to provide full analysis of persisted documents within given range of IDs", notes = "Entry point REST to provide full analysis of persisted documents within given range of IDs")
   public void analyzeFullPersistedDocs(Long docIDFrom, Long docIDTo, HttpServletRequest request) {

      contentAnalysisService.analyzeFullPersistedDocs(docIDFrom, docIDTo);
   }
   
   @GetMapping("/content/{id}")
   @ApiOperation(value = "Get content analysis by document ID", notes = "Entry point REST to get content analysis by document ID")
   public List<ContentAnalysis> getContentAnalysisByContentID(@PathVariable(value = "id") Long idContent, HttpServletRequest request) {

      Content content = ResourceUtils.checkFound(contentsService.read(idContent));
      return content.getContentAnalysis();
   }
   
}
