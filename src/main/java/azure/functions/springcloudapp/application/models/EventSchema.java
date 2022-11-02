package azure.functions.springcloudapp.application.models;

import azure.functions.springcloudapp.domain.models.TodoDto;
import lombok.Data;

@Data
public class EventSchema {

  public String topic;
  public String subject;
  public String eventType;
  public String eventTime;
  public String id;
  public String dataVersion;
  public String metadataVersion;
  public TodoDto data;
}
