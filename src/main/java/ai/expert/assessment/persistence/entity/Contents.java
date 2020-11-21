package ai.expert.assessment.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contents")
public class Contents {

   @Id
   private Long content_id;

   private String content;

   private LocalDateTime created_at;
   private LocalDateTime ws_checked_at;

   private String content_language;
   private String ws_checker_version;
   private Boolean wschecker_success;

   public Long getContent_id() {
      return content_id;
   }

   public void setContent_id(Long content_id) {
      this.content_id = content_id;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
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
      return wschecker_success;
   }

   public void setWschecker_success(Boolean wschecker_success) {
      this.wschecker_success = wschecker_success;
   }

}
