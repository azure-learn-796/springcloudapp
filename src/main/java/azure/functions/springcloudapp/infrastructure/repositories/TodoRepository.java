package azure.functions.springcloudapp.infrastructure.repositories;

import azure.functions.springcloudapp.infrastructure.entities.Todo;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository
  extends ReactiveCosmosRepository<Todo, Integer> {}
