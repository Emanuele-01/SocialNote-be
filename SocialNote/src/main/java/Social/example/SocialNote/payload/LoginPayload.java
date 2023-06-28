package Social.example.SocialNote.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPayload {

	@NotNull
	String email;
	
	@NotNull
	String password;
}
