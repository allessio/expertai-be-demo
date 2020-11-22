package ai.expert.assessment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.expert.assessment.persistence.entity.ContentAnalysis;

@Repository
public interface ContentAnalysisRepository extends JpaRepository<ContentAnalysis, Long> {

   Optional<ContentAnalysis> findById(Long id);
   
}
