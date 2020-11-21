package ai.expert.assessment.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entity_types")
public class EntityTypes implements Serializable {

   private static final long serialVersionUID = 7976346877614271196L;

   @Id
   private String entity_type;
   
   private String description;

   public String getEntity_type() {
      return entity_type;
   }

   public void setEntity_type(String entity_type) {
      this.entity_type = entity_type;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

}
