package ai.expert.assessment.controller.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ai.expert.assessment.persistence.entity.Content;
import ai.expert.assessment.utils.Globals;

public class ContentDto implements Serializable {

   private static final long serialVersionUID = -812996622180996664L;

   private Long content_id;
   private String document_name;
   private String document_checksum;
   private String content;
   private LocalDateTime created_at;
   private List<ContentAnalysisDto> contentAnalysis;

   public ContentDto(Content c) {
      this.content_id = c.getContent_id();
      this.document_name = c.getDocument_name();
      this.document_checksum = c.getDocument_checksum();
      this.content = new String(c.getContent(), Globals.DEFAULT_ENCODING);
      this.created_at = c.getCreated_at();
      this.contentAnalysis = c.getContentAnalysis()
                                 .stream()
                                 .map(ca -> new ContentAnalysisDto(ca))
                                 .collect(Collectors.toList());
   }
   
   public ContentDto(Content c, boolean summary) {
      this.content_id = c.getContent_id();
      this.document_name = c.getDocument_name();
      this.document_checksum = c.getDocument_checksum();
      this.created_at = c.getCreated_at();
      if (!summary) {
         this.content = new String(c.getContent(), Globals.DEFAULT_ENCODING);
         this.contentAnalysis = c.getContentAnalysis()
                                    .stream()
                                    .map(ca -> new ContentAnalysisDto(ca))
                                    .collect(Collectors.toList());
      }
   }

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

   public List<ContentAnalysisDto> getContentAnalysis() {
      return contentAnalysis;
   }

   public void setContentAnalysis(List<ContentAnalysisDto> contentAnalysis) {
      this.contentAnalysis = contentAnalysis;
   }

}