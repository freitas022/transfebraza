package freitas.dev.msuser.domain;

import java.util.UUID;
import java.util.function.Consumer;

import freitas.dev.msuser.web.dto.UserUpdateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String name;
	private String cpf;
	private String phone;
	private String email;
	private String password;

	public void updateData(UserUpdateRequest request) {
		verifyIfNotNull(request.phone(), this::setPhone);
		verifyIfNotNull(request.email(), this::setEmail);
		verifyIfNotNull(request.password(), this::setPassword);
	}

	private void verifyIfNotNull(String value, Consumer<String> setter) {
		if (value != null) {
			setter.accept(value);
		}
	}
}
