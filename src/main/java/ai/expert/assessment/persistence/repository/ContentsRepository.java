package ai.expert.assessment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.expert.assessment.persistence.entity.Content;

@Repository
public interface ContentsRepository extends JpaRepository<Content, Long> {

   Optional<Content> findById(Long id);
   
}
