package ai.expert.assessment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.expert.assessment.persistence.entity.KnowledgeProperties;

@Repository
public interface KnowledgePropertiesRepository extends JpaRepository<KnowledgeProperties, Long> {

   Optional<KnowledgeProperties> findById(Long id);
   
}
