package ai.expert.ws.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ai.expert.assessment.controller.BaseController;
//import ai.expert.core.exception.UserMessageException;
import ai.expert.core.service.PersistenceEntityService;
//import ai.expert.commons.json.Json;

@Controller
@RequestMapping("management/entities/persistenceController")
public class PersistenceEntityController extends BaseController {
   @Autowired
   private PersistenceEntityService persistenceEntityService;

   @RequestMapping(value = "getCustomRatio", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
   @ResponseBody
   public String getCustomRatio(HttpServletRequest httpServletRequest) {
      return null;
//      try {
//         if (this.log.isInfoEnabled())
//            this.log.info("list requested");
//         return doGetCustomRatio(httpServletRequest);
//      } catch (UserMessageException userMessageException) {
//         this.log.error(userMessageException.toString());
//         return Json.throwableToJson(userMessageException);
//      } catch (Throwable throwable) {
//         this.log.error(throwable.toString(), throwable);
//         return Json.throwableToJson(throwable);
//      }
   }

   public String doGetCustomRatio(HttpServletRequest httpServletRequest) throws Exception {
      return null;
//      return Json.toJson(this.persistenceEntityService.getCustomRatio());
   }
}
