package azure.functions.springcloudapp.application.models;

import azure.functions.springcloudapp.domain.models.TodoDto;
import lombok.Data;

@Data
public class EventGridEvent {

  private String id;
  private String eventType;
  private String subject;
  private String eventTime;
  private String dataVersion;
  private TodoDto data;
}
