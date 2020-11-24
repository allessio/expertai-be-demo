package ai.expert.assessment;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ai.expert.assessment.service.interfaces.IAppInitService;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.FileUploader;
import ai.expert.nlapi.security.Authentication;
import ai.expert.nlapi.security.Authenticator;
import ai.expert.nlapi.security.BasicAuthenticator;
import ai.expert.nlapi.security.Credential;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AppInitService implements IAppInitService {

   private Authenticator authenticator;

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
      authenticator = new BasicAuthenticator(new Credential(username, password));

      /*
       * Upload all allowed files from input folder
       * Files already presented in DB (checked by its hash) are skipped
       *   
       */
       new FileUploader().uploadAllDocs(inputFolder, doctypeList, contentsService);

   }
   
   public Authentication getAuthentication() {
      if (true) {       // TODO: if( !authenticator.expired() ) 
         return new Authentication(authenticator);
      }
      else {
         return new Authentication(new BasicAuthenticator(new Credential(username, password)));
      }
   }
}