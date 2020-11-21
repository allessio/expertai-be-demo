package ai.expert.assessment.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "knowledge_properties")
public class KnowledgeProperties implements Serializable {

   private static final long serialVersionUID = 5013746894897611189L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(columnDefinition = "serial")
   private Long property_id;

   private String property_type;
   private String property_value;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "syncon", nullable = false)
   private Knowledge knowledge;

   public Long getProperty_id() {
      return property_id;
   }

   public void setProperty_id(Long property_id) {
      this.property_id = property_id;
   }

   public String getProperty_type() {
      return property_type;
   }

   public void setProperty_type(String property_type) {
      this.property_type = property_type;
   }

   public String getProperty_value() {
      return property_value;
   }

   public void setProperty_value(String property_value) {
      this.property_value = property_value;
   }

   public Knowledge getKnowledge() {
      return knowledge;
   }

   public void setKnowledge(Knowledge knowledge) {
      this.knowledge = knowledge;
   }

}
