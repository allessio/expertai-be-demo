package ai.expert.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.expert.assessment.persistence.entity.Knowledge;
import ai.expert.assessment.persistence.repository.KnowledgeRepository;
import ai.expert.assessment.service.interfaces.IKnowledgeService;

@Service
public class KnowledgeService implements IKnowledgeService {
   
   @Autowired
   KnowledgeRepository repo;

   @Override
   public List<Knowledge> getAll() {
      return repo.findAll();
   }
   
   @Override
   public Page<Knowledge> getAllPageable(Pageable pageable) {
      return repo.findAll(pageable);
   }

   @Override
   public Knowledge create(Knowledge entity) {
      return repo.saveAndFlush(entity);
   }

   @Override
   public Knowledge update(Knowledge entity) {
      return repo.save(entity);
   }

   @Override
   public Optional<Knowledge> read(Long id) {
      return repo.findById(id);
   }

   @Override
   public void delete(Long id) {
      repo.deleteById(id);
   }

}
