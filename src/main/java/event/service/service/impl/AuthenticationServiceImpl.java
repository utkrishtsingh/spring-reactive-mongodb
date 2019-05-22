package event.service.service.impl;

import event.service.model.User;
import event.service.repository.UserRepository;
import event.service.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;

  public AuthenticationServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean authenticateUser(String userName, String password) {
    return userRepository.findUserByUserName(userName)
            .map(user -> user.getPassword().equals(password))
            .orElse(false);
  }

  @Override
  public boolean createNewUser(User user) {
    return userRepository.findUserByUserName(user.getUserName())
            .map(existingUser -> false)
            .orElse(user.getUserName() != null && userRepository.save(user).equals(user));
  }

  @Override
  public boolean checkIfUserExists(String userName) {
    return userRepository.findUserByUserName(userName).isPresent();
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
