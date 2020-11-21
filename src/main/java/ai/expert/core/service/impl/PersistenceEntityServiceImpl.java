package ai.expert.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ai.expert.core.manager.PersistenceEntityManager;
import ai.expert.core.service.PersistenceEntityService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PersistenceEntityServiceImpl implements PersistenceEntityService {
   @Autowired
   private PersistenceEntityManager persistenceEntityManager;

   @Override
   public Double getCustomRatio() {
      return this.persistenceEntityManager.getCustomRatio();
   }
}
