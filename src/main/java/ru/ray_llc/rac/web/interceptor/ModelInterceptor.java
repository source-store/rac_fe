package ru.ray_llc.rac.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.ray_llc.rac.AuthorizedUser;
import ru.ray_llc.rac.web.SecurityUtil;

/**
 * This interceptor adds userTo to the model of every requests
 */
public class ModelInterceptor implements HandlerInterceptor {

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    if (modelAndView != null && !modelAndView.isEmpty()) {
      AuthorizedUser authorizedUser = SecurityUtil.safeGet();
      if (authorizedUser != null) {
        modelAndView.getModelMap().addAttribute("userTo", authorizedUser.getUserTo());
      }
    }
  }
}
