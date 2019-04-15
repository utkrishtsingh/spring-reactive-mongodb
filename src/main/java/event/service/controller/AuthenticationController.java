package event.service.controller;

import event.service.model.User;
import event.service.repository.UserRepository;
import event.service.service.AuthenticationService;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class AuthenticationController {
  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
  private final AuthenticationService authenticationService;

  @Autowired
  UserRepository userRepository;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @GetMapping("/check/{userName}")
  public ResponseEntity checkUsernameExistence(@PathVariable("userName") String userName) {
    LOG.info("In /check/{}", userName);
    boolean exists = authenticationService.checkIfUserExists(userName);
    return exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }

  @GetMapping("/authenticate/{userName}/{password}")
  public ResponseEntity authenticateUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
    boolean authenticated = authenticationService.authenticateUser(userName, password);
    return authenticated ?
        ResponseEntity.ok().build() :
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @PostMapping("/create")
  public ResponseEntity createNewUser(@RequestBody @NotNull User user) {
    LOG.info("In /create");
    boolean created = authenticationService.createNewUser(user);
    return created ?
        ResponseEntity.status(HttpStatus.CREATED).build() :
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<> (userRepository.findAll(), HttpStatus.OK);
  }
}
