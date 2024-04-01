package freitas.dev.msuser.mocks;

import java.util.UUID;

import freitas.dev.msuser.domain.User;

public class UserMock {
	
	public static User generateUser() {
		return new User(UUID.fromString("f2278311-a692-47dd-b0dc-2beee2277697"), "Math Turner", "135.356.455-79", "2193332222", "math@mail.com", "password");
	}
}
