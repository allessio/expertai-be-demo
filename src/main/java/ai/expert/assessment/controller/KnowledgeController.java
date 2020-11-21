package ai.expert.assessment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.expert.assessment.persistence.entity.Knowledge;
import ai.expert.assessment.persistence.entity.KnowledgeProperties;
import ai.expert.assessment.service.interfaces.IKnowledgePropertiesService;
import ai.expert.assessment.service.interfaces.IKnowledgeService;
import ai.expert.assessment.utils.Globals;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Globals.KNOWLEDGE_ENDPOINT)
public class KnowledgeController extends BaseController<Knowledge> {

   @Autowired
   private IKnowledgeService knowledgeService;

   @Autowired
   private IKnowledgePropertiesService knowledgePropsService;
   
   @GetMapping("/all")
   @ApiOperation(value = "List of recognized knowledge entries", notes = "Entry point REST for getting the list of recognized knowledge entries")
   public List<Knowledge> getAll(HttpServletRequest request) {
      return knowledgeService.getAll();
   }
   
   @GetMapping("/properties/all")
   @ApiOperation(value = "List of recognized knowledge properties", notes = "Entry point REST for getting the list of recognized knowledge properties")
   public List<KnowledgeProperties> getAllProperties(HttpServletRequest request) {
      return knowledgePropsService.getAll();
   }

}
