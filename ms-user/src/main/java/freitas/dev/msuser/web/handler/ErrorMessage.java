package freitas.dev.msuser.web.handler;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ErrorMessage(
						@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC") 
						Instant timestamp, 
						Integer status,
						String error,
						String path) {

}
