package event.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class User {
  private String userName;
  private String password;
  private String firstName;
  private String lastName;
}
