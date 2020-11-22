package ai.expert.assessment;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ai.expert.assessment.service.ContentAnalysisService;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.FileUploader;
import ai.expert.nlapi.security.Authentication;
import ai.expert.nlapi.security.Authenticator;
import ai.expert.nlapi.security.BasicAuthenticator;
import ai.expert.nlapi.security.Credential;

@Component
public class ExpertAIAppInitializer {
   
   @Value("${expertai.inputFolder}")
   private String inputFolder;
   
   @Value("${expertai.username}")
   private String username;
   
   @Value("${expertai.password}")
   private String password;
   
   @Value("#{'${expertai.doctypelist}'.split(',')}")
   private List<String> doctypeList;
   
   @Autowired
   private IContentsService contentsService;
   
   private static final Logger logger = LogManager.getLogger();

   @PostConstruct
   private void init() {
      logger.info("Initialization of ExpertAI application");

/*
 *  Upload all allowed files from input folder
 */
      //      new FileUploader().uploadAllDocs(inputFolder, doctypeList, contentsService);
   
/*
 * Analyze all persisted documents      
 */
//      Authenticator authenticator = new BasicAuthenticator(new Credential(username, password));
//      new ContentAnalysisService().analyzeAllTypesAllPersistedDocs(new Authentication(authenticator), contentsService);
   }

}
