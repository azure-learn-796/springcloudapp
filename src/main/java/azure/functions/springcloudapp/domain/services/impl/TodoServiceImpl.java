package azure.functions.springcloudapp.domain.services.impl;

import azure.functions.springcloudapp.domain.dto.TodoDto;
import azure.functions.springcloudapp.domain.services.TodoService;
import azure.functions.springcloudapp.infrastructure.entities.Todo;
import azure.functions.springcloudapp.infrastructure.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

  private final TodoRepository todoRepository;

  @Override
  public Flux<TodoDto> getTodos() {
    Flux<Todo> todos = todoRepository.findAll();

    return todos.map(TodoDto::fromEntity);
  }

  @Override
  public Mono<TodoDto> saveTodo(Mono<TodoDto> dto) {
    Mono<Todo> todoMono = todoRepository.save(
      dto.map(TodoDto::toCreateEntity).block()
    );

    return todoMono.map(TodoDto::fromEntity);
  }
}
