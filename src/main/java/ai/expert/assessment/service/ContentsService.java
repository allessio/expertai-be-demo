package ai.expert.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.expert.assessment.persistence.entity.Content;
import ai.expert.assessment.persistence.repository.ContentsRepository;
import ai.expert.assessment.service.interfaces.IContentsService;

@Service
public class ContentsService implements IContentsService {
   
   @Autowired
   ContentsRepository repo;

   @Override
   public List<Content> getAll() {
      return repo.findAll();
   }

   @Override
   public Page<Content> getAllPageable(Pageable pageable) {
      return repo.findAll(pageable);
   }

   @Override
   public Content create(Content entity) {
      return repo.saveAndFlush(entity);
   }

   @Override
   public Content update(Content entity) {
      return repo.save(entity);
   }

   @Override
   public Optional<Content> read(Long id) {
      return repo.findById(id);
   }

   @Override
   public void delete(Long id) {
      repo.deleteById(id);
   }

}
