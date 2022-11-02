package azure.functions.springcloudapp.application.handlers;

import azure.functions.springcloudapp.application.functions.TodoFunction;
import azure.functions.springcloudapp.domain.models.TodoDto;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

public class CreateTodoHandler extends FunctionInvoker<TodoDto, TodoDto> {

  public static final String FUNCTION_NAME = "saveTodo";

  @FunctionName(FUNCTION_NAME)
  public HttpResponseMessage saveTodo(
    @HttpTrigger(
      name = "req",
      methods = { HttpMethod.POST },
      route = TodoFunction.BASE_ROUTE,
      authLevel = AuthorizationLevel.FUNCTION
    ) HttpRequestMessage<TodoDto> request,
    ExecutionContext context
  ) {
    final TodoDto payload = request.getBody();

    if (payload == null) {
      return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
    }

    final TodoDto todoCreated = handleRequest(payload, context);

    return request
      .createResponseBuilder(HttpStatus.CREATED)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .header(
        HttpHeaders.LOCATION,
        UriComponentsBuilder
          .fromUri(request.getUri())
          .path("/{id}")
          .buildAndExpand(todoCreated.getId())
          .toString()
      )
      .body(todoCreated)
      .build();
  }
}
