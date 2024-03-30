package freitas.dev.msuser.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(	@NotBlank String name,
							@NotNull LocalDate birthdate, 
							@NotBlank@CPF String cpf, 
							@NotBlank String phone, 
							@NotBlank @Email String email,
							@NotBlank String password) {

}
