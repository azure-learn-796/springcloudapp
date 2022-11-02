package azure.functions.springcloudapp.utils;

import com.azure.cosmos.implementation.Strings;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class DateTimeUtil {

  public static final String DEFAULT_DATETIME_PATTERN = "uuuu/MM/dd HH:mm:ss";
  public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(
    DEFAULT_DATETIME_PATTERN
  );

  public static Instant toInstant(String datetimeStr) {
    if (Strings.isNullOrEmpty(datetimeStr)) {
      return null;
    }

    return LocalDateTime
      .parse(datetimeStr, DEFAULT_DATETIME_FORMATTER)
      .toInstant(ZoneOffset.UTC);
  }

  public static String formatDateTime(Temporal temporal) {
    if (temporal == null) {
      return null;
    }

    if (temporal instanceof Instant) {
      return DEFAULT_DATETIME_FORMATTER.format(
        LocalDateTime.ofInstant(Instant.from(temporal), ZoneId.systemDefault())
      );
    }

    return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.from(temporal));
  }

  public static Instant instantNow() {
    return Instant.now(Clock.systemUTC());
  }
}
