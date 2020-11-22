package ai.expert.assessment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.expert.assessment.persistence.entity.Content;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.Globals;
import ai.expert.assessment.utils.ResourceUtils;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Globals.CONTENTS_ENDPOINT)
public class ContentsController extends BaseController<Content> {

   @Autowired
   private IContentsService contentsService;

   @GetMapping("/all")
   @ApiOperation(value = "List of uploaded documents", notes = "Entry point REST for getting the list of uploaded documents")
   public Page<Content> getAll(Pageable pageable, HttpServletRequest request) {

      return contentsService.getAllPageable(pageable);
   }

   @GetMapping("/{id}")
   @ApiOperation(value = "Get document by its ID", notes = "Entry point REST to get document by its ID")
   public Content getContentByID(@PathVariable(value = "id") Long idContent, HttpServletRequest request) {

      return ResourceUtils.checkFound(contentsService.read(idContent));
   }

}
