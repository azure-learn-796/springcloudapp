package azure.functions.springcloudapp.application.functions;

import com.microsoft.azure.functions.ExecutionContext;
import java.util.function.Function;
import org.springframework.messaging.Message;

public interface AzureFunction<I, O> extends Function<Message<I>, O> {
  public static final String CONTEXT_HEADER_KEY = "executionContext";

  // @Override
  // default O apply(Message<I> message) {
  //   ExecutionContext context =
  //     ExecutionContext.class.cast(
  //         message.getHeaders().get((CONTEXT_HEADER_KEY))
  //       );

  //   O output = apply(message.getPayload(), context);

  //   Class<?> outputType = output.getClass();

  //   if (Message.class.isAssignableFrom(outputType)) {
  //     return Message.class.cast(output).getPayload();
  //   }

  //   if (Publisher.class.isAssignableFrom(outputType)) {
  //     return Flux.class.isAssignableFrom(outputType)
  //       ? Flux.class.cast(output).collectList().block()
  //       : Mono.class.cast(output).block();
  //   } else {
  //     return output;
  //   }
  // }

  @Override
  default O apply(Message<I> message) {
    ExecutionContext context =
      ExecutionContext.class.cast(
          message.getHeaders().get((CONTEXT_HEADER_KEY))
        );

    return apply(message.getPayload(), context);
  }

  O apply(I payload, ExecutionContext context);
}
