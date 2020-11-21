package ai.expert.assessment.controller;

import java.util.Optional;

import ai.expert.assessment.exceptions.ResourceNotFoundException;
import ai.expert.assessment.utils.ResourceUtils;

public abstract class BaseController<T> {

   public static <T> T checkFound(Optional<T> resource) throws ResourceNotFoundException{
      return ResourceUtils.checkFound(resource);
  }
  
}
