package freitas.dev.msuser.domain;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);

}
