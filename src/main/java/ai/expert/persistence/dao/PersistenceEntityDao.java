package ai.expert.persistence.dao;

import ai.expert.persistence.model.PersistenceEntity;

public interface PersistenceEntityDao extends AbstractDao<PersistenceEntity> {
   public Long getCustomCount();
}
