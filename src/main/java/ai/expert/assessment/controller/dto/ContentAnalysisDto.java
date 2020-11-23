package ai.expert.assessment.controller.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import ai.expert.assessment.persistence.entity.ContentAnalysis;
import ai.expert.assessment.utils.Globals;

public class ContentAnalysisDto implements Serializable {

   private static final long serialVersionUID = -859846377945801756L;

   private Long analysis_id;
   private String analysis_type;
   private Long content_part_id;
   private LocalDateTime analyzed_at;
   private String content_language;
   private String ws_checker_version;
   private Boolean ws_checker_success;
   private String ws_checker_response;

   public ContentAnalysisDto(ContentAnalysis ca) {
      this.analysis_id = ca.getAnalysis_id();
      this.analysis_type = ca.getAnalysis_type();
      this.content_part_id = ca.getContent_part_id();
      this.analyzed_at = ca.getAnalyzed_at();
      this.content_language = ca.getContent_language();
      this.ws_checker_version = ca.getWs_checker_version();
      this.ws_checker_success = ca.getWs_checker_success();
      this.ws_checker_response = ca.getWs_checker_response() != null ? new String(ca.getWs_checker_response(), Globals.DEFAULT_ENCODING) : null;
   }

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

   public Long getContent_part_id() {
      return content_part_id;
   }

   public void setContent_part_id(Long content_part_id) {
      this.content_part_id = content_part_id;
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

   public String getWs_checker_response() {
      return ws_checker_response;
   }

   public void setWs_checker_response(String ws_checker_response) {
      this.ws_checker_response = ws_checker_response;
   }

}