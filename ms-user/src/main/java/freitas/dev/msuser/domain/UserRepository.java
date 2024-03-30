package freitas.dev.msuser.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User> findByEmail(String email);
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);	
}
