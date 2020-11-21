package ai.expert.assessment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.expert.assessment.persistence.entity.Entities;
import ai.expert.assessment.service.interfaces.IEntitiesService;
import ai.expert.assessment.utils.Globals;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Globals.ENTITIES_ENDPOINT)
public class EntitiesController extends BaseController<Entities> {

   @Autowired
   private IEntitiesService entitiesService;

   @GetMapping("/all")
   @ApiOperation(value = "List of recognized entities", notes = "Entry point REST for getting the list of recognized entities")
   public List<Entities> getAll(HttpServletRequest request) {
      return entitiesService.getAll();
   }

}
