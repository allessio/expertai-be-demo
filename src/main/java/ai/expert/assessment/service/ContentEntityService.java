package ai.expert.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ai.expert.assessment.persistence.entity.ContentEntity;
import ai.expert.assessment.persistence.repository.ContentEntityRepository;
import ai.expert.assessment.service.interfaces.IContentEntityService;

@Service
public class ContentEntityService implements IContentEntityService {
   
   @Autowired
   ContentEntityRepository repo;

   @Override
   public List<ContentEntity> getAll() {
      return repo.findAll();
   }
   
   @Override
   public Page<ContentEntity> getAllPageable(Pageable pageable) {
      return repo.findAll(pageable);
   }

   @Override
   public ContentEntity create(ContentEntity entity) {
      return repo.saveAndFlush(entity);
   }

   @Override
   public ContentEntity update(ContentEntity entity) {
      return repo.save(entity);
   }

   @Override
   public Optional<ContentEntity> read(Long id) {
      return repo.findById(id);
   }

   @Override
   public void delete(Long id) {
      repo.deleteById(id);
   }

}
