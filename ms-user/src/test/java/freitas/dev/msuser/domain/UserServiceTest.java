package freitas.dev.msuser.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import freitas.dev.msuser.domain.exceptions.BusinessException;
import freitas.dev.msuser.domain.exceptions.UserNotFoundException;
import freitas.dev.msuser.mocks.UserMock;
import freitas.dev.msuser.web.dto.UserUpdateRequest;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	private User user;
	private UUID nonExistingId;
	private UUID existingId;

	@BeforeEach
	void setUp() throws Exception {
		existingId = UUID.fromString("f2278311-a692-47dd-b0dc-2beee2277697");
		nonExistingId = UUID.randomUUID();
		user = UserMock.generateUser();
		userRepository.deleteAll();
	}

	@Test
	@DisplayName("Deve cadastrar e retornar um novo usuário.")
	void insertUserTestSuccess() {

		when(userRepository.existsByCpf(anyString())).thenReturn(false);
		when(userRepository.existsByEmail(anyString())).thenReturn(false);
		when(userRepository.save(any(User.class))).thenReturn(user);

		var actual = userService.insert(user);

		assertEquals(user.getId(), actual.getId());
		assertEquals(user.getCpf(), actual.getCpf());
		assertEquals(user.getName(), actual.getName());
		assertEquals(user.getPhone(), actual.getPhone());
		assertEquals(user.getEmail(), actual.getEmail());
		assertEquals(user.getPassword(), actual.getPassword());
	}

	@Test
	@DisplayName("Deve lançar exceção BusinessException")
	void insertUserTestThrowsException1() {

		when(userRepository.existsByCpf(anyString())).thenReturn(true);
		when(userRepository.existsByEmail(anyString())).thenReturn(false);
		when(userRepository.save(any(User.class))).thenReturn(user);

		assertThrows(BusinessException.class, () -> {
			userService.insert(user);
		});
	}

	@Test
	@DisplayName("Deve lançar exceção BusinessException")
	void insertUserTestThrowsException2() {

		when(userRepository.existsByCpf(anyString())).thenReturn(false);
		when(userRepository.existsByEmail(anyString())).thenReturn(true);
		when(userRepository.save(any(User.class))).thenReturn(user);

		assertThrows(BusinessException.class, () -> {
			userService.insert(user);
		});
	}

	@Test
	@DisplayName("Deve buscar um usuário pelo ID")
	void findByIdUserTestSucess() {

		when(userRepository.findById(existingId)).thenReturn(Optional.of(user));

		var actual = userService.findById(existingId);

		assertEquals(user.getId(), actual.getId());
	}

	@Test
	@DisplayName("Deve lançar exceção UserNotFoundException")
	void findByIdUserTestThrowsException() {

		assertThrows(UserNotFoundException.class, () -> {
			userService.findById(nonExistingId);
		});
	}
	
	@Test
	@DisplayName("Deve atualizar os campos não nulos email, password ou phone")
	void updateWithPathTest() {
		UserUpdateRequest request = new UserUpdateRequest(null, "email@test.com", "12345");
		
		when(userRepository.findById(existingId)).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		userService.udpate(existingId, request);
		
		assertEquals("email@test.com", user.getEmail());
		assertEquals("12345", user.getPassword());
	}
	
	@Test
	@DisplayName("Não deve atualizar se o campo for nulo")
	void doNothingUpdateWithPathTest() {
		UserUpdateRequest request = new UserUpdateRequest(null, null, null);
		
		when(userRepository.findById(existingId)).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		userService.udpate(existingId, request);
		
		assertEquals("math@mail.com", user.getEmail());
		assertEquals("password", user.getPassword());
	}

}
