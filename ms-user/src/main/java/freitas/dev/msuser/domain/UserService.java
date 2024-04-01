package freitas.dev.msuser.domain;

import java.util.UUID;

import org.springframework.stereotype.Service;

import freitas.dev.msuser.domain.exceptions.BusinessException;
import freitas.dev.msuser.domain.exceptions.UserNotFoundException;
import freitas.dev.msuser.web.dto.UserUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public User insert(User request) {
		existsByCpf(request.getCpf());
		existsByEmail(request.getEmail());
		return userRepository.save(request);
	}

	public User findById(UUID id) {
		return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}

	public User udpate(UUID userId, UserUpdateRequest request) {
		return userRepository.findById(userId).map(userToUpdate -> {
			userToUpdate.updateData(request);
			return userRepository.save(userToUpdate);
		}).orElseThrow(UserNotFoundException::new);
	}

	private void existsByCpf(String cpf) {
		if (userRepository.existsByCpf(cpf)) {
			throw new BusinessException("CPF already exists.");
		}
	}

	private void existsByEmail(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new BusinessException("Email already exists.");
		}
	}
}
