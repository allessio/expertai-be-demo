package ai.expert.assessment.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "contents")
public class Content  implements Serializable {

   private static final long serialVersionUID = 6200599842291806346L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long content_id;

   private String document_name;
   private String document_checksum;
   private byte[] content;

   private LocalDateTime created_at;

   @OneToMany(targetEntity=ContentAnalysis.class, fetch = FetchType.LAZY, mappedBy = "content", cascade = { CascadeType.ALL })
   @JsonIgnore
   private List<ContentAnalysis> contentAnalysis = new ArrayList<>();

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

   public List<ContentAnalysis> getContentAnalysis() {
      return contentAnalysis;
   }

   public void setContentAnalysis(List<ContentAnalysis> contentAnalysis) {
      this.contentAnalysis = contentAnalysis;
   }

}
