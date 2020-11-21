package ai.expert.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.expert.assessment.persistence.entity.Entities;
import ai.expert.assessment.persistence.repository.EntitiesRepository;
import ai.expert.assessment.service.interfaces.IEntitiesService;

@Service
public class EntitiesService implements IEntitiesService {
   
   @Autowired
   EntitiesRepository repo;

   @Override
   public List<Entities> getAll() {
      return repo.findAll();
   }
   
   @Override
   public Page<Entities> getAllPageable(Pageable pageable) {
      return repo.findAll(pageable);
   }

   @Override
   public Entities create(Entities entity) {
      return repo.saveAndFlush(entity);
   }

   @Override
   public Entities update(Entities entity) {
      return repo.save(entity);
   }

   @Override
   public Optional<Entities> read(Long id) {
      return repo.findById(id);
   }

   @Override
   public void delete(Long id) {
      repo.deleteById(id);
   }

}
