package freitas.dev.msuser.web.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import freitas.dev.msuser.utils.TimestampFormater;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		log.error("Handled Method Argument Not Valid Exception");
		return errors;
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorMessage> handleBusinessException(BusinessException ex, HttpServletRequest request) {
		log.error("Handled Business Exception");
		HttpStatus status = HttpStatus.CONFLICT;
		var error = new ErrorMessage(TimestampFormater.formatInstant(Instant.now()), status.value(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException ex,
			HttpServletRequest request) {
		log.error("Handled User Not Found Exception");
		HttpStatus status = HttpStatus.NOT_FOUND;
		var error = new ErrorMessage(TimestampFormater.formatInstant(Instant.now()), status.value(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(error, status);
	}
}
