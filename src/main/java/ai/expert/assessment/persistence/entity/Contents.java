package ai.expert.assessment.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contents")
public class Contents {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long content_id;

   private String document_name;
   private String document_checksum;
   private byte[] content;

   private LocalDateTime created_at;
   private LocalDateTime ws_checked_at;

   private String content_language;
   private String ws_checker_version;
   private Boolean ws_checker_success;

   public Long getContent_id() {
      return content_id;
   }

   public void setContent_id(Long content_id) {
      this.content_id = content_id;
   }

   public String getDocument_name() {
      return document_name;
   }

   public void setDocument_name(String document_name) {
      this.document_name = document_name;
   }

   public String getDocument_checksum() {
      return document_checksum;
   }

   public void setDocument_checksum(String document_checksum) {
      this.document_checksum = document_checksum;
   }

   public byte[] getContent() {
      return content;
   }

   public void setContent(byte[] content) {
      this.content = content;
   }

   public LocalDateTime getCreated_at() {
      return created_at;
   }

   public void setCreated_at(LocalDateTime created_at) {
      this.created_at = created_at;
   }

   public LocalDateTime getWs_checked_at() {
      return ws_checked_at;
   }

   public void setWs_checked_at(LocalDateTime ws_checked_at) {
      this.ws_checked_at = ws_checked_at;
   }

   public String getContent_language() {
      return content_language;
   }

   public void setContent_language(String content_language) {
      this.content_language = content_language;
   }

   public String getWs_checker_version() {
      return ws_checker_version;
   }

   public void setWs_checker_version(String ws_checker_version) {
      this.ws_checker_version = ws_checker_version;
   }

   public Boolean getWschecker_success() {
      return ws_checker_success;
   }

   public void setWschecker_success(Boolean ws_checker_success) {
      this.ws_checker_success = ws_checker_success;
   }

}
