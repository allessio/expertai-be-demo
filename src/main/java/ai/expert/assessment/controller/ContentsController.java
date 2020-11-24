package ai.expert.assessment.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.expert.assessment.controller.dto.ContentDto;
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
   @CrossOrigin
   @ApiOperation(value = "List of uploaded documents", notes = "Entry point REST for getting the list of uploaded documents")
   public Page<ContentDto> getAll(Pageable pageable, HttpServletRequest request) {

      Page<Content> pContent = contentsService.getAllPageable(pageable);

      Page<ContentDto> pagedResult = new PageImpl<>(
            pContent.stream()
               .map(c -> new ContentDto(c,true))
               .collect(Collectors.toList())
            );
      return pagedResult;
   }
   
   // TODO: alternative pagination with links prev/next/self
//   @RepositoryRestResource(collectionResourceRel = "content", path = "")
//   public interface ContentsRepository extends PagingAndSortingRepository<Content, Long> {
//
//     List<ContentDto> findByContentId(@Param("id") String id);
//   }

   @GetMapping("/{id}")
   @CrossOrigin
   @ApiOperation(value = "Get document by its ID", notes = "Entry point REST to get document by its ID")
   public ContentDto getContentByID(@PathVariable(value = "id") Long idContent, HttpServletRequest request) {

      return new ContentDto(ResourceUtils.checkFound(contentsService.read(idContent)));
   }

}
