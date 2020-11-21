package ai.expert.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.expert.assessment.persistence.entity.EntityTypes;
import ai.expert.assessment.persistence.repository.EntityTypesRepository;
import ai.expert.assessment.service.interfaces.IEntityTypesService;

@Service
public class EntityTypesService implements IEntityTypesService {
   
   @Autowired
   EntityTypesRepository repo;

   @Override
   public List<EntityTypes> getAll() {
      return repo.findAll();
   }
   
   @Override
   public Page<EntityTypes> getAllPageable(Pageable pageable) {
      return repo.findAll(pageable);
   }

   @Override
   public EntityTypes create(EntityTypes entity) {
      return repo.saveAndFlush(entity);
   }

   @Override
   public EntityTypes update(EntityTypes entity) {
      return repo.save(entity);
   }

   @Override
   public Optional<EntityTypes> read(String id) {
      return repo.findById(id);
   }

   @Override
   public void delete(String id) {
      repo.deleteById(id);
   }

}
