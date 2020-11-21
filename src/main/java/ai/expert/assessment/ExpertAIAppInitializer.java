package ai.expert.assessment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ai.expert.assessment.persistence.entity.Contents;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.FileReader;
import ai.expert.assessment.utils.FileUtils;

@Component
public class ExpertAIAppInitializer {

   @Autowired
   private IContentsService contentsService;
   
   private static final Logger logger = LogManager.getLogger();
   
   @Value("${expertai.inputFolder}")
   private String inputFolder;
   
   @Value("#{'${expertai.doctypelist}'.split(',')}")
   private List<String> doctypeList;

   @PostConstruct
   private void init() {
      logger.info("Initialization  of ExpertAI application");

      uploadAllDocs();
      
   }

   private void uploadAllDocs() {
      FileReader fr = new FileReader();
      
      logger.info("Reading files from " + inputFolder);
      
      fr.readFiles(inputFolder);
      
      logger.info("Uploading files to DB...");
      
      for (File f : fr.getFiles()) {
         if (doctypeList.contains(FileUtils.getFileExtension(f))) {
            try {
               uploadDocument(f);
            }
            catch (Exception exc) {
               logger.info("File " + f.getAbsolutePath() + " not uploaded into DB due to IO errors:");
               logger.info(exc.getMessage());
               exc.printStackTrace();
            }
         }
         else { 
            logger.info("Skipping file " + f.getAbsolutePath() + " due to disallowed extension");
         }
      }
      logger.info("Uploading files to DB finished");
   }
   
   private void uploadDocument(File file) throws IOException, NoSuchAlgorithmException {
      byte[] fileBytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
      byte[] hash = MessageDigest.getInstance("MD5").digest(fileBytes);
      
      String fileHash = DatatypeConverter.printHexBinary(hash);
      
      logger.info("File: " + file.getAbsolutePath());
      logger.info("Hash: " + fileHash);
      
      Contents doc = new Contents();
      doc.setDocument_name(file.getName());
      doc.setDocument_checksum(fileHash);
      doc.setContent(fileBytes);
      doc.setCreated_at(LocalDateTime.now());
      
      contentsService.create(doc);
   }
}
