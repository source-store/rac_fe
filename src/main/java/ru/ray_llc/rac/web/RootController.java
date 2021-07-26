package ru.ray_llc.rac.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

  @GetMapping("/")
  public String root() {
    return "barriers";
  }

  //    @Secured("ROLE_ADMIN")
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/users")
  public String getUsers() {
    return "users";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/barriers")
  public String getBarriers() {
    return "barriers";
  }
}