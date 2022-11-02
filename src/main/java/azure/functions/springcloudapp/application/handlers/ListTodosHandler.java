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
import java.util.List;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ListTodosHandler extends FunctionInvoker<Object, List<TodoDto>> {

  public static final String FUNCTION_NAME = "listTodos";

  @FunctionName(FUNCTION_NAME)
  public HttpResponseMessage listTodos(
    @HttpTrigger(
      name = "req",
      methods = { HttpMethod.GET },
      route = TodoFunction.BASE_ROUTE,
      authLevel = AuthorizationLevel.FUNCTION
    ) HttpRequestMessage<Object> request,
    ExecutionContext context
  ) {
    return request
      .createResponseBuilder(HttpStatus.OK)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(handleRequest("", context))
      .build();
  }
}
