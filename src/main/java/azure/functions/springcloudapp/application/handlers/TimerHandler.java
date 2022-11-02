package azure.functions.springcloudapp.application.handlers;

import azure.functions.springcloudapp.application.models.EventGridEvent;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.EventGridOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

public class TimerHandler extends FunctionInvoker<String, EventGridEvent> {

  public static final String FUNCTION_NAME = "timer";

  @FunctionName(FUNCTION_NAME)
  public void timer(
    @TimerTrigger(name = "timer", schedule = "* * * * *") String timerInfo,
    @EventGridOutput(
      name = "outputEvent",
      topicEndpointUri = "MyEventGridTopicUriSetting",
      topicKeySetting = "MyEventGridTopicKeySetting"
    ) OutputBinding<EventGridEvent> outputEvent,
    ExecutionContext context
  ) {
    handleOutput("timer exec", outputEvent, context);
  }
}
