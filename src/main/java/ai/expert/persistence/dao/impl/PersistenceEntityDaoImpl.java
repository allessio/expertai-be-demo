package ai.expert.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import ai.expert.persistence.dao.PersistenceEntityDao;
import ai.expert.persistence.model.PersistenceEntity;

@Repository("persistenceEntityDao")
public class PersistenceEntityDaoImpl extends AbstractDaoImpl<PersistenceEntity> implements PersistenceEntityDao {
   @Override
   public Long getCustomCount() {
      super.getSession();
      return null;
   }
}
