package ai.expert.assessment.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "content_analysis")
public class ContentAnalysis implements Serializable {

   private static final long serialVersionUID = -7189871914469337082L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(columnDefinition = "serial")
   private Long analysis_id;
   
   @JsonIgnore
   private String analysis_type;

   @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
   @JoinColumn(name = "content_id", referencedColumnName = "content_id")
   private Content content;

   @JsonIgnore
   private Long content_part_id;
   @JsonIgnore
   private LocalDateTime analyzed_at;
   @JsonIgnore
   private String content_language;
   @JsonIgnore
   private String ws_checker_version;
   @JsonIgnore
   private Boolean ws_checker_success;
   @JsonIgnore
   private byte[] ws_checker_response;

   public Long getAnalysis_id() {
      return analysis_id;
   }

   public void setAnalysis_id(Long analysis_id) {
      this.analysis_id = analysis_id;
   }

   public String getAnalysis_type() {
      return analysis_type;
   }

   public void setAnalysis_type(String analysis_type) {
      this.analysis_type = analysis_type;
   }

   public Content getContent() {
      return content;
   }

   public void setContent(Content content) {
      this.content = content;
   }

   public LocalDateTime getAnalyzed_at() {
      return analyzed_at;
   }

   public void setAnalyzed_at(LocalDateTime analyzed_at) {
      this.analyzed_at = analyzed_at;
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

   public Boolean getWs_checker_success() {
      return ws_checker_success;
   }

   public void setWs_checker_success(Boolean ws_checker_success) {
      this.ws_checker_success = ws_checker_success;
   }

   public Long getContent_part_id() {
      return content_part_id;
   }

   public void setContent_part_id(Long content_part_id) {
      this.content_part_id = content_part_id;
   }

   public byte[] getWs_checker_response() {
      return ws_checker_response;
   }

   public void setWs_checker_response(byte[] ws_checker_response) {
      this.ws_checker_response = ws_checker_response;
   }

}
