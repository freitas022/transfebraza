package freitas.dev.msuser.web.dto;

import java.util.UUID;

import freitas.dev.msuser.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
	
	private UUID id;
	private String name;
	private String cpf;
	private String phone;
	private String email;
	
	public UserDto(User entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		phone = entity.getPhone();
		email = entity.getEmail();
	}
}
