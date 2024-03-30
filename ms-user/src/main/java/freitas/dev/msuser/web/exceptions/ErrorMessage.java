package freitas.dev.msuser.web.exceptions;

import java.time.Instant;

public record ErrorMessage(Instant timestamp, Integer status, String error, String path) {

}
