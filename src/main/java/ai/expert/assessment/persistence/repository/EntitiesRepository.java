package ai.expert.assessment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.expert.assessment.persistence.entity.Entities;

@Repository
public interface EntitiesRepository extends JpaRepository<Entities, Long> {

   Optional<Entities> findById(Long id);
   
}
