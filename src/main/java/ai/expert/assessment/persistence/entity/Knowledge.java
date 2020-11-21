package ai.expert.assessment.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "knowledge")
public class Knowledge implements Serializable {

   private static final long serialVersionUID = -871778029284900897L;

   @Id
   private Long syncon;
   
   private String label;

   public Long getSyncon() {
      return syncon;
   }

   public void setSyncon(Long syncon) {
      this.syncon = syncon;
   }

   public String getLabel() {
      return label;
   }

   public void setLabel(String label) {
      this.label = label;
   }

}
