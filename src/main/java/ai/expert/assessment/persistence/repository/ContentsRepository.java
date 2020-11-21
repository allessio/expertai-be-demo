package ai.expert.assessment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.expert.assessment.persistence.entity.Contents;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {

   Optional<Contents> findById(Long id);
   
}
