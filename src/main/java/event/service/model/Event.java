package event.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Event {
  private String eventName;
  private String description;
  private String duration;
  private String location;
  private Long fees;
  private String[] tags;
  private Integer maxParticipants;
  private User createdBy;
  private EventRegistrationField[] eventRegistrationFields;
  private Participant[] participants;
}
