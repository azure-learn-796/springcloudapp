package azure.functions.springcloudapp.domain.services;

import azure.functions.springcloudapp.domain.dto.TodoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {
  Flux<TodoDto> getTodos();

  Mono<TodoDto> saveTodo(Mono<TodoDto> todo);
}
