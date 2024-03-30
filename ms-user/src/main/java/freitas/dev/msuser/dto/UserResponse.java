package freitas.dev.msuser.dto;

import java.time.LocalDate;
import java.util.UUID;

import freitas.dev.msuser.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {
	
	private UUID id;
	private String name;
	private String cpf;
	private LocalDate birthdate;
	private String phone;
	private String email;
	
	public UserResponse(User entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		birthdate = entity.getBirthdate();
		phone = entity.getPhone();
		email = entity.getEmail();
	}
}
