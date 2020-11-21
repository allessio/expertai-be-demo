package ai.expert.assessment.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.expert.assessment.persistence.entity.EntityTypes;

@Repository
public interface EntityTypesRepository extends JpaRepository<EntityTypes, String> {

   Optional<EntityTypes> findById(String id);
   
}
