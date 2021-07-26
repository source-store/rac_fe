package ru.ray_llc.rac.web.user;

import static ru.ray_llc.rac.web.SecurityUtil.authUserId;

import java.net.URI;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.ray_llc.rac.model.User;
import ru.ray_llc.rac.to.UserTo;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractUserController {

  static final String REST_URL = "/rest/profile";

  @GetMapping
  public User get() {
    return super.get(authUserId());
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete() {
    super.delete(authUserId());
  }

  @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
    User created = super.create(userTo);
    URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path(REST_URL).build().toUri();
    return ResponseEntity.created(uriOfNewResource).body(created);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody UserTo userTo) {
    super.update(userTo, authUserId());
  }

  @GetMapping("/text")
  public String testUTF() {
    return "Русский текст";
  }

}