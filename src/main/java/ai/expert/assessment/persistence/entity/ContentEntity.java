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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "content_entity")
public class ContentEntity implements Serializable {

   private static final long serialVersionUID = -4543927264640892933L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(columnDefinition = "serial")
   private Long content_entity_id;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "content_id", nullable = false)
   private Contents content_id;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "entity_id", nullable = false)
   @JsonIgnore // avoids infinite recursion
   private Entities entity_id;

   private Long start_position;
   private Long end_position;

   public Long getContent_entity_id() {
      return content_entity_id;
   }

   public void setContent_entity_id(Long content_entity_id) {
      this.content_entity_id = content_entity_id;
   }

   public Contents getContent_id() {
      return content_id;
   }

   public void setContent_id(Contents content_id) {
      this.content_id = content_id;
   }

   public Entities getEntity_id() {
      return entity_id;
   }

   public void setEntity_id(Entities entity_id) {
      this.entity_id = entity_id;
   }

   public Long getStart_position() {
      return start_position;
   }

   public void setStart_position(Long start_position) {
      this.start_position = start_position;
   }

   public Long getEnd_position() {
      return end_position;
   }

   public void setEnd_position(Long end_position) {
      this.end_position = end_position;
   }

}
