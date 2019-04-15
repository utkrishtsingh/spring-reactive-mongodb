package event.service.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Participant {
  private User user;
  private Map<String, String> participantInformation;
}
