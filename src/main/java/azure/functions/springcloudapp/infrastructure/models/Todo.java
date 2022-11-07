package azure.functions.springcloudapp.infrastructure.models;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "todos")
public class Todo implements Serializable {

  @Id
  @PartitionKey
  private String id;

  private String content;

  private boolean done;

  private Instant createdAt;
}
