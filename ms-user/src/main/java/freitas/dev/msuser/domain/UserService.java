package freitas.dev.msuser.domain;

import java.util.UUID;

import org.springframework.stereotype.Service;

import freitas.dev.msuser.dto.UserRequest;
import freitas.dev.msuser.dto.UserResponse;
import freitas.dev.msuser.dto.UserUpdateRequest;
import freitas.dev.msuser.utils.FormaterUtils;
import freitas.dev.msuser.web.exceptions.BusinessException;
import freitas.dev.msuser.web.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserResponse getUserById(final UUID idUser) {
		return userRepository.findById(idUser)
				.map(UserResponse::new)
				.orElseThrow(UserNotFoundException::new);
	}

	@Transactional
	public UserResponse insertUser(final UserRequest request) {
		String cpf = FormaterUtils.formatCpf(request.cpf());
		String phone = FormaterUtils.formatPhone(request.phone());		
		var userToInsert = User.builder()
				.name(request.name())
				.cpf(cpf)
				.birthdate(request.birthdate())
				.phone(phone)
				.email(request.email())
				.password(request.password())
				.build();		
		existsByCpf(userToInsert.getCpf());
		existsByEmail(userToInsert.getEmail());
		return new UserResponse(userRepository.save(userToInsert));
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

	public UserResponse updateUserWithPatch(final UserUpdateRequest request, final UUID userId) {
		return userRepository.findById(userId)
				.map(userToUpdate -> {
					userToUpdate.updateData(request);
					userRepository.save(userToUpdate);
					return new UserResponse(userToUpdate);
				}).orElseThrow(UserNotFoundException::new);
	}

}
