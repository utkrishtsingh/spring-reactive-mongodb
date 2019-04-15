package event.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventRegistrationField {
  private String fieldName;
  private Boolean optional;
}
