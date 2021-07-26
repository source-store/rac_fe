package ru.ray_llc.rac.web;

import static java.util.Objects.requireNonNull;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.ray_llc.rac.AuthorizedUser;

public class SecurityUtil {

  private SecurityUtil() {
  }

  public static AuthorizedUser safeGet() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return null;
    }
    Object principal = auth.getPrincipal();
    return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
  }

  public static AuthorizedUser get() {
    return requireNonNull(safeGet(), "No authorized user found");
  }

  public static int authUserId() {
    return get().getUserTo().id();
  }

}