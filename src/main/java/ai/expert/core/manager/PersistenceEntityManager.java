package ai.expert.core.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ai.expert.persistence.dao.DaoAccessor;

@Component
public class PersistenceEntityManager {
   @Autowired
   private DaoAccessor daoAccessor;

   public Double getCustomRatio() {
//      this.daoAccessor.persistenceEntityDao.getCustomCount();
      return null;
   }
}