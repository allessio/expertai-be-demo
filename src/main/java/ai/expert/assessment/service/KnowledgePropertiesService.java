package ai.expert.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.expert.assessment.persistence.entity.KnowledgeProperties;
import ai.expert.assessment.persistence.repository.KnowledgePropertiesRepository;
import ai.expert.assessment.service.interfaces.IKnowledgePropertiesService;

@Service
public class KnowledgePropertiesService implements IKnowledgePropertiesService {
   
   @Autowired
   KnowledgePropertiesRepository repo;

   @Override
   public List<KnowledgeProperties> getAll() {
      return repo.findAll();
   }

   @Override
   public Page<KnowledgeProperties> getAllPageable(Pageable pageable) {
      return repo.findAll(pageable);
   }

   @Override
   public KnowledgeProperties create(KnowledgeProperties entity) {
      return repo.saveAndFlush(entity);
   }

   @Override
   public KnowledgeProperties update(KnowledgeProperties entity) {
      return repo.save(entity);
   }

   @Override
   public Optional<KnowledgeProperties> read(Long id) {
      return repo.findById(id);
   }

   @Override
   public void delete(Long id) {
      repo.deleteById(id);
   }

}
