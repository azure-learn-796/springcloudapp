package azure.functions.springcloudapp.application.functions;

import azure.functions.springcloudapp.application.models.EventGridEvent;
import azure.functions.springcloudapp.domain.models.TodoDto;
import azure.functions.springcloudapp.utils.DateTimeUtil;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimerFunction {

  @Bean
  public AzureFunction<String, EventGridEvent> timer() {
    return (payload, context) -> {
      context.getLogger().info("Invoke timer: " + payload);
      final EventGridEvent document = new EventGridEvent();
      document.setId(UUID.randomUUID().toString());
      document.setEventType("MyCustomEvent");
      document.setEventTime(DateTimeUtil.formatDateTime(LocalDateTime.now()));
      document.setDataVersion("1.0");
      document.setSubject("EventGridSample");
      document.setData(new TodoDto(null, "Content", false, null));
      System.out.println(document);

      return document;
    };
  }
}
