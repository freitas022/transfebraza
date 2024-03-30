package freitas.dev.msuser.utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class TimestampFormater {
	
	private TimestampFormater() {}
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
            .withZone(ZoneOffset.UTC);
	
	public static Instant formatInstant(Instant timestamp) {
        String formattedString = formatter.format(timestamp);
        return Instant.parse(formattedString);
    }
}
