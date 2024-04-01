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

import freitas.dev.msuser.domain.User;
import freitas.dev.msuser.domain.UserService;
import freitas.dev.msuser.web.dto.UserDto;
import freitas.dev.msuser.web.dto.UserUpdateRequest;
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

	@PostMapping()
	public ResponseEntity<UserDto> insert(@RequestBody @Valid User entity) {
		log.info("POST /api/users - new user");
		return new ResponseEntity<>(new UserDto(userService.insert(entity)), HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable final UUID userId) {
		log.info("GET /api/users - acessed by user with ID {}", userId);
		return new ResponseEntity<>(new UserDto(userService.findById(userId)), HttpStatus.OK);
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<UserDto> updateUserWithPatch(@PathVariable UUID userId,
			@RequestBody @Valid UserUpdateRequest request) {
		log.info("PATCH /api/users - acessed by user with ID {}", userId);
		return new ResponseEntity<>(new UserDto(userService.udpate(userId, request)), HttpStatus.OK);
	}
}
