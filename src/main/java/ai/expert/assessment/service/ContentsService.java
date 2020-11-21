package ai.expert.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.expert.assessment.persistence.entity.Contents;
import ai.expert.assessment.persistence.repository.ContentsRepository;
import ai.expert.assessment.service.interfaces.IContentsService;

@Service
public class ContentsService implements IContentsService {
   
   @Autowired
   ContentsRepository repo;

   @Override
   public List<Contents> getAll() {
      return repo.findAll();
   }

   @Override
   public Page<Contents> getAllPageable(Pageable pageable) {
      return repo.findAll(pageable);
   }

   @Override
   public Contents create(Contents entity) {
      return repo.saveAndFlush(entity);
   }

   @Override
   public Contents update(Contents entity) {
      return repo.save(entity);
   }

   @Override
   public Optional<Contents> read(Long id) {
      return repo.findById(id);
   }

   @Override
   public void delete(Long id) {
      repo.deleteById(id);
   }

}
