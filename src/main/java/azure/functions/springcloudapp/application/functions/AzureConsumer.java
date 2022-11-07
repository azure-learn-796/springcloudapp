package azure.functions.springcloudapp.application.functions;

import com.microsoft.azure.functions.ExecutionContext;
import java.util.function.Consumer;
import org.springframework.messaging.Message;

public interface AzureConsumer<I> extends Consumer<Message<I>> {
  public static final String CONTEXT_HEADER_KEY = "executionContext";

  @Override
  default void accept(Message<I> message) {
    ExecutionContext context =
      ExecutionContext.class.cast(
          message.getHeaders().get((CONTEXT_HEADER_KEY))
        );

    apply(message.getPayload(), context);
  }

  void apply(I payload, ExecutionContext context);
}
