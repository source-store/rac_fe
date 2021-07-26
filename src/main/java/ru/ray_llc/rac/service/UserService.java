package ru.ray_llc.rac.service;

import static ru.ray_llc.rac.util.UserUtil.prepareToSave;
import static ru.ray_llc.rac.util.ValidationUtil.checkNotFound;
import static ru.ray_llc.rac.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ray_llc.rac.AuthorizedUser;
import ru.ray_llc.rac.model.User;
import ru.ray_llc.rac.repository.UserRepository;
import ru.ray_llc.rac.to.UserTo;
import ru.ray_llc.rac.util.UserUtil;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @CacheEvict(value = "users", allEntries = true)
  public User create(User user) {
    Assert.notNull(user, "user must not be null");
    return prepareAndSave(user);
  }

  @CacheEvict(value = "users", allEntries = true)
  public void delete(int id) {
    checkNotFoundWithId(repository.delete(id), id);
  }

  public User get(int id) {
    return checkNotFoundWithId(repository.get(id), id);
  }

  public User getByEmail(String email) {
    Assert.notNull(email, "email must not be null");
    return checkNotFound(repository.getByEmail(email), "email=" + email);
  }

  @Cacheable("users")
  public List<User> getAll() {
    return repository.getAll();
  }

  @CacheEvict(value = "users", allEntries = true)
  public void update(User user) {
    Assert.notNull(user, "user must not be null");
//      checkNotFoundWithId : check works only for JDBC, disabled
    prepareAndSave(user);
  }

  @CacheEvict(value = "users", allEntries = true)
  @Transactional
  public void update(UserTo userTo) {
    User user = get(userTo.id());
    prepareAndSave(UserUtil.updateFromTo(user, userTo));
  }

  @CacheEvict(value = "users", allEntries = true)
  @Transactional
  public void enable(int id, boolean enabled) {
    User user = get(id);
    user.setEnabled(enabled);
    repository.save(user);  // !! need only for JDBC implementation
  }

  @Override
  public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = repository.getByEmail(email.toLowerCase());
    if (user == null) {
      throw new UsernameNotFoundException("User " + email + " is not found");
    }
    return new AuthorizedUser(user);
  }

  private User prepareAndSave(User user) {
    return repository.save(prepareToSave(user, passwordEncoder));
  }

}