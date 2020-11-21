package ai.expert.assessment.utils;

import java.util.Optional;

import ai.expert.assessment.exceptions.ResourceNotFoundException;

public class ResourceUtils {
   
   public static <T> T checkFound(Optional<T> resource) throws ResourceNotFoundException {
      if (!resource.isPresent()) {
         throw new ResourceNotFoundException("Resource not found");
      }
      return resource.get();
   }

}
