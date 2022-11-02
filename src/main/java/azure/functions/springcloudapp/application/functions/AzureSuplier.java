package azure.functions.springcloudapp.application.functions;

import com.microsoft.azure.functions.ExecutionContext;
import java.util.function.Function;
import org.springframework.messaging.Message;

public interface AzureSuplier<O> extends Function<Message<Object>, O> {
  public static final String CONTEXT_HEADER_KEY = "executionContext";

  @Override
  default O apply(Message<Object> message) {
    ExecutionContext context =
      ExecutionContext.class.cast(
          message.getHeaders().get((CONTEXT_HEADER_KEY))
        );

    return apply(context);
  }

  O apply(ExecutionContext context);
}
