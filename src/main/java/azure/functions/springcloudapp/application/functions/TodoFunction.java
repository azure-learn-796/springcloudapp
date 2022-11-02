package azure.functions.springcloudapp.application.functions;

import azure.functions.springcloudapp.domain.models.TodoDto;
import azure.functions.springcloudapp.domain.services.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TodoFunction {

  public static final String BASE_ROUTE = "todos";

  private final TodoService todoService;

  @Bean
  public AzureSuplier<List<TodoDto>> listTodos() {
    return context -> {
      context.getLogger().info("Invoke listTodos");
      return todoService.getTodos().collectList().block();
    };
  }

  @Bean
  public AzureFunction<TodoDto, TodoDto> saveTodo() {
    return (payload, context) -> {
      context.getLogger().info("Invoke saveTodo");
      Mono<TodoDto> todoMono = todoService.saveTodo(Mono.just(payload));
      return todoMono.block();
    };
  }
}
