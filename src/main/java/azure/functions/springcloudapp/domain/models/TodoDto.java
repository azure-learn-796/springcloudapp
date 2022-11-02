package azure.functions.springcloudapp.domain.dto;

import azure.functions.springcloudapp.infrastructure.entities.Todo;
import azure.functions.springcloudapp.utils.DateTimeUtil;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto implements Serializable {

  private String id;

  private String content;

  private boolean done;

  private String createdAt;

  public static TodoDto fromEntity(Todo entity) {
    return new TodoDto(
      entity.getId(),
      entity.getContent(),
      entity.isDone(),
      DateTimeUtil.formatDateTime(entity.getCreatedAt())
    );
  }

  public Todo toEntity() {
    return new Todo(
      getId(),
      getContent(),
      isDone(),
      Objects.requireNonNullElse(
        DateTimeUtil.toInstant(getCreatedAt()),
        DateTimeUtil.instantNow()
      )
    );
  }

  public Todo toCreateEntity() {
    return new Todo(
      UUID.randomUUID().toString(),
      getContent(),
      false,
      Objects.requireNonNullElse(
        DateTimeUtil.toInstant(getCreatedAt()),
        DateTimeUtil.instantNow()
      )
    );
  }
}
