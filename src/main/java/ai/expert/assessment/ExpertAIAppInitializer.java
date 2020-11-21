package ai.expert.assessment;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ai.expert.assessment.utils.FileReader;
import ai.expert.assessment.utils.FileUtils;

@Component
public class ExpertAIAppInitializer {

   private static final Logger logger = LogManager.getLogger();
   
   @Value("${expertai.inputFolder}")
   private String inputFolder;
   
   @Value("#{'${expertai.doctypelist}'.split(',')}")
   private List<String> doctypeList;

   @PostConstruct
   private void init() {
      logger.info("Initialization  of ExpertAI application...");

      uploadAllDocs();
   }

   private void uploadAllDocs() {
      FileReader fr = new FileReader();
      
      logger.info("Reading files from " + inputFolder);
      
      fr.readFiles(inputFolder);
      
      logger.info("Uploading files to DB");
      
      for (File f : fr.getFiles()) {
         // TEST
         if (doctypeList.contains(FileUtils.getFileExtension(f))) {
            System.out.println(f.getAbsolutePath());
            
            uploadDocument(f);
         }
         else { 
            System.out.println("Skipping file: " + f.getAbsolutePath());
         }
      }
      
      //TEST
      System.out.println("FINISHED");
   }
   
   private void uploadDocument(File file) {
      
      MessageDigest md = MessageDigest.getInstance("MD5");
      try (InputStream is = Files.newInputStream(Paths.get("file.txt"));
           DigestInputStream dis = new DigestInputStream(is, md)) 
      {
        /* Read decorated stream (dis) to EOF as normal... */
      }
      byte[] digest = md.digest();
   }
}
