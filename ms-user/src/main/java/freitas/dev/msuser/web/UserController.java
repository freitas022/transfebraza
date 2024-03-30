package freitas.dev.msuser.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freitas.dev.msuser.domain.UserService;
import freitas.dev.msuser.dto.UserRequest;
import freitas.dev.msuser.dto.UserResponse;
import freitas.dev.msuser.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Validated
@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	private final UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable final UUID userId) {
		log.info("GET /api/users - acessed by user with ID {}", userId);
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserResponse> insertUser(@RequestBody @Valid UserRequest request) {
		log.info("POST /api/users - User registration request received");
		return new ResponseEntity<>(userService.insertUser(request), HttpStatus.CREATED);
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<UserResponse> updateUserWithPatch(@RequestBody @Valid UserUpdateRequest request,
			@PathVariable UUID userId) {
		log.info("PATCH /api/users - acessed by user with ID {}", userId);
		return new ResponseEntity<>(userService.updateUserWithPatch(request, userId), HttpStatus.OK);
	}
}
