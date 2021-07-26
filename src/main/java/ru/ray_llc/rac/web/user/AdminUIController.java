package ru.ray_llc.rac.web.user;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ray_llc.rac.model.User;
import ru.ray_llc.rac.to.UserTo;

@RestController
@RequestMapping(value = "/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUIController extends AbstractUserController {

  @Override
  @GetMapping
  public List<User> getAll() {
    return super.getAll();
  }

  @Override
  @GetMapping("/{id}")
  public User get(@PathVariable int id) {
    return super.get(id);
  }

  @Override
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable int id) {
    super.delete(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void createOrUpdate(@Valid UserTo userTo) {
    if (userTo.isNew()) {
      super.create(userTo);
    } else {
      super.update(userTo, userTo.id());
    }
  }

  @Override
  @PostMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void enable(@PathVariable int id, @RequestParam boolean enabled) {
    super.enable(id, enabled);
  }
}
