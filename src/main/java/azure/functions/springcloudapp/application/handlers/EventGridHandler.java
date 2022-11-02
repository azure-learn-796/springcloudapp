package azure.functions.springcloudapp.application.handlers;

import azure.functions.springcloudapp.application.models.EventSchema;
import azure.functions.springcloudapp.domain.models.TodoDto;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

public class EventGridHandler extends FunctionInvoker<EventSchema, TodoDto> {

  public static final String FUNCTION_NAME = "eventGrid";

  @FunctionName(FUNCTION_NAME)
  public void eventGrid(
    @EventGridTrigger(name = "event") EventSchema event,
    ExecutionContext context
  ) {
    TodoDto output = handleRequest(event, context);
    context.getLogger().info(output.toString());
  }
}
