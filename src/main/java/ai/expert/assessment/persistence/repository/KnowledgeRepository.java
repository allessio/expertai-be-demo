package ai.expert.assessment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.expert.assessment.persistence.entity.Knowledge;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {

   Optional<Knowledge> findById(Long id);
   
}
