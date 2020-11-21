package ai.expert.assessment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.expert.assessment.persistence.entity.EntityTypes;
import ai.expert.assessment.service.interfaces.IEntityTypesService;
import ai.expert.assessment.utils.Globals;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Globals.ENTITYTYPES_ENDPOINT)
public class EntityTypesController extends BaseController<EntityTypes> {

   @Autowired
   private IEntityTypesService entTypesService;

   @GetMapping("/all")
   @ApiOperation(value = "List of available entity types", notes = "Entry point REST for getting the list of available entity types")
   public List<EntityTypes> getAll(HttpServletRequest request) {
      return entTypesService.getAll();
   }

}
