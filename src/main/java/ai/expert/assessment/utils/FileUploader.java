package ai.expert.assessment.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ai.expert.assessment.persistence.entity.Content;
import ai.expert.assessment.service.interfaces.IContentsService;

public class FileUploader {

   private static final Logger logger = LogManager.getLogger();

   private List<Content> listCont = null; 
         
   public FileUploader() {
   }

   public void uploadAllDocs(String inputFolder, List<String> doctypeList, IContentsService contentsService) {
      if (inputFolder != null) {
         logger.info("Reading files from " + inputFolder);

         FileReader fr = new FileReader();
         fr.readFiles(inputFolder);

         logger.info("Uploading files to DB...");

         for (File file : fr.getFiles()) {
            if (doctypeList.contains(FileUtils.getFileExtension(file))) {
               try {
                  uploadDocument(file, contentsService);
               } catch (Exception exc) {
                  logger.info("File " + file.getAbsolutePath() + " not uploaded into DB due to IO errors:");
                  logger.info(exc.getMessage());
                  exc.printStackTrace();
               }
            } else {
               logger.info("Skipping file " + file.getAbsolutePath() + " due to disallowed extension");
            }
         }
         logger.info("Uploading files to DB finished");
      } else {
         logger.info("Error: input folder is undefined");
      }
   }

   private void uploadDocument(File file, IContentsService contentsService) throws IOException, NoSuchAlgorithmException {
      byte[] fileBytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
      byte[] hash = MessageDigest.getInstance("MD5").digest(fileBytes);

      String fileHash = DatatypeConverter.printHexBinary(hash);

      logger.info("File: " + file.getAbsolutePath());
      logger.info("Hash: " + fileHash);

      if (listCont == null) {
         listCont = contentsService.getAll();   
      }

      boolean filePresentInDB = false;
      
      for (Content cont : listCont) {
         if (cont.getDocument_checksum().equals(fileHash)) {
            filePresentInDB = true;
            logger.info("Is already present in DB. Skipping.");
            break;
         }
      }
      
      if (!filePresentInDB) {
         Content doc = new Content();
         doc.setDocument_name(file.getName());
         doc.setDocument_checksum(fileHash);
         doc.setContent(fileBytes);
         doc.setCreated_at(LocalDateTime.now());

         contentsService.create(doc);
      }
   }
}
