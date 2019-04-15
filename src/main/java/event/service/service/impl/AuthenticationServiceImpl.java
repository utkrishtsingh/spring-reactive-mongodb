package event.service.service.impl;

import event.service.model.User;
import event.service.repository.UserRepository;
import event.service.service.AuthenticationService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;

  public AuthenticationServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean authenticateUser(String userName, String password) {
    boolean authenticated = false;
    Optional<User> user = userRepository.findUserByUserName(userName);
    if (user.isPresent()) {
      authenticated = user.get().getPassword().equals(password);
    }
    return authenticated;
  }

  @Override
  public boolean createNewUser(User user) {
    boolean created = false;
    if (!checkIfUserExists(user.getUserName())) {
      if (user.getUserName() != null) {
        User savedUser = userRepository.save(user);
        created = user.equals(savedUser);
      }
    }
    return created;
  }

  @Override
  public boolean checkIfUserExists(String userName) {
    return userRepository.findUserByUserName(userName).isPresent();
  }
}
