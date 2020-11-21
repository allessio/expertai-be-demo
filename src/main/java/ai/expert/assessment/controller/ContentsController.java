package ai.expert.assessment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.expert.assessment.persistence.entity.Contents;
import ai.expert.assessment.service.interfaces.IContentsService;
import ai.expert.assessment.utils.Globals;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(Globals.CONTENTS_ENDPOINT)
public class ContentsController extends BaseController<Contents> {

   @Autowired
   private IContentsService contentsService;

   @GetMapping("/all")
   @ApiOperation(value = "List of uploaded documents", notes = "Entry point REST for getting the list of uploaded documents")
   public Page<Contents> getAll(Pageable pageable, HttpServletRequest request) {
       return contentsService.getAllPageable(pageable);
   }


}
