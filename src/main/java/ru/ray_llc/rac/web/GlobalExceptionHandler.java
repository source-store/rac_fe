package ru.ray_llc.rac.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.ray_llc.rac.AuthorizedUser;
import ru.ray_llc.rac.util.ValidationUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    log.error("Exception at request " + req.getRequestURL(), e);
    Throwable rootCause = ValidationUtil.getRootCause(e);

    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    ModelAndView mav = new ModelAndView("exception",
        Map.of("exception", rootCause, "message", rootCause.toString(), "status", httpStatus));
    mav.setStatus(httpStatus);

    // Interceptor is not invoked, put userTo
    AuthorizedUser authorizedUser = SecurityUtil.safeGet();
    if (authorizedUser != null) {
      mav.addObject("userTo", authorizedUser.getUserTo());
    }
    return mav;
  }
}
