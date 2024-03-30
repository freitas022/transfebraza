/**
 * 
 */
package freitas.dev.msuser.domain;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Consumer;

import freitas.dev.msuser.dto.UserUpdateRequest;
import freitas.dev.msuser.utils.FormaterUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String cpf;
	private LocalDate birthdate;
	private String phone;
	private String email;
	private String password;

	public void updateData(UserUpdateRequest request) {
		String phoneFormated = FormaterUtils.formatPhone(request.phone());
		verifyIfNotNull(phoneFormated, this::setPhone);
		verifyIfNotNull(request.email(), this::setEmail);
		verifyIfNotNull(request.password(), this::setPassword);
	}

	private void verifyIfNotNull(String value, Consumer<String> setter) {
		if (value != null) {
			setter.accept(value);
		}
	}
}
