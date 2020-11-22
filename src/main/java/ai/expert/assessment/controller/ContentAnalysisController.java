package ai.expert.assessment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ai.expert.assessment.persistence.entity.ContentAnalysis;
import ai.expert.assessment.service.ContentAnalysisService;
import ai.expert.assessment.service.interfaces.IContentAnalysisService;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.Globals;
import ai.expert.nlapi.security.Authentication;
import ai.expert.nlapi.security.Authenticator;
import ai.expert.nlapi.security.BasicAuthenticator;
import ai.expert.nlapi.security.Credential;
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

   @PostMapping(path = {"/analyzeall"})
   @ResponseStatus(value = HttpStatus.CREATED)
   @ApiOperation(value = "Request to provide analysis of persisted documents", notes = "Entry point REST to provide analysis of persisted documents")
   public void create(
         HttpServletRequest request) {
      
      Authenticator authenticator = new BasicAuthenticator(new Credential(username, password));
      new ContentAnalysisService().analyzeAllTypesAllPersistedDocs(new Authentication(authenticator), contentsService);


   }

}
