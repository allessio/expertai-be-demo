package ai.expert.assessment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.expert.assessment.persistence.entity.ContentEntity;

@Repository
public interface ContentEntityRepository extends JpaRepository<ContentEntity, Long> {

   Optional<ContentEntity> findById(Long id);
   
}
