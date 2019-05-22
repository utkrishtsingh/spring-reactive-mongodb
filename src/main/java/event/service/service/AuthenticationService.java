package event.service.service;

import event.service.model.User;

import java.util.List;

public interface AuthenticationService {
  boolean authenticateUser(String userName, String password);
  boolean createNewUser(User user);
  boolean checkIfUserExists(String userName);

    List<User> getAllUsers();
}
