package ru.ray_llc.rac.web.tasks;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ray_llc.rac.View;
import ru.ray_llc.rac.model.Task;
import ru.ray_llc.rac.util.ValidationUtil;

@RestController
@RequestMapping(value = "/profile/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskUIController extends AbstractTaskController {

  @Override
  @GetMapping
  @JsonView(View.JsonUI.class)
  public List<Task> getAll() {
    return super.getAll();
  }

  @Override
  @GetMapping("/{id}")
  @JsonView(View.JsonUI.class)
  public Task get(@PathVariable int id) {
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
  public ResponseEntity<String> createOrUpdate(Task task, BindingResult result) {
    if (result.hasErrors()) {
      return ValidationUtil.getErrorResponse(result);
    }
    if (task.isNew()) {
      super.create(task);
    } else {
      super.update(task, task.getId());
    }
    return ResponseEntity.ok().build();
  }
}
