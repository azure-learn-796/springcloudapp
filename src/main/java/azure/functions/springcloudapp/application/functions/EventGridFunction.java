package azure.functions.springcloudapp.application.functions;

import azure.functions.springcloudapp.application.models.EventSchema;
import azure.functions.springcloudapp.domain.models.TodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class EventGridFunction {

  private final RestTemplate restTemplate;

  @Value("${FUNCTION_ENDPOINT}")
  private String baseEndpoint;

  @Value("${FUNCTION_KEY}")
  private String functionKey;

  @Bean
  public AzureFunction<EventSchema, TodoDto> eventGrid() {
    return (event, context) -> {
      context.getLogger().info("Event: " + event);

      // Functions API 呼び出し
      RequestEntity<TodoDto> request = RequestEntity
        .post(baseEndpoint + "/todos?code={code}", functionKey)
        .contentType(MediaType.APPLICATION_JSON)
        .body(event.getData());
      ResponseEntity<TodoDto> response = restTemplate.exchange(
        request,
        TodoDto.class
      );

      return response.getBody();
    };
  }
}
