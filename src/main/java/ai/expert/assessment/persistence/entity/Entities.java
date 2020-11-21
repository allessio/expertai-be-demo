package ai.expert.assessment.persistence.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "entities")
public class Entities implements Serializable {

   private static final long serialVersionUID = -2243697536139058944L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(columnDefinition = "serial")
   private Long entity_id;

   @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "entity_id")
   private ContentEntity content_entity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "syncon", nullable = false)
   private Knowledge knowledge;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "entity_type", nullable = false)
   private EntityTypes entity_type;

   private String lemma;

   public Long getEntity_id() {
      return entity_id;
   }

   public void setEntity_id(Long entity_id) {
      this.entity_id = entity_id;
   }

   public ContentEntity getContent_entity() {
      return content_entity;
   }

   public void setContent_entity(ContentEntity content_entity) {
      this.content_entity = content_entity;
   }

   public Knowledge getKnowledge() {
      return knowledge;
   }

   public void setKnowledge(Knowledge knowledge) {
      this.knowledge = knowledge;
   }

   public EntityTypes getEntity_type() {
      return entity_type;
   }

   public void setEntity_type(EntityTypes entity_type) {
      this.entity_type = entity_type;
   }

   public String getLemma() {
      return lemma;
   }

   public void setLemma(String lemma) {
      this.lemma = lemma;
   }

}
