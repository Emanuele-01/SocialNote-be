package Social.example.SocialNote.auth.authPayload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationTokenPayload {

	private String accessToken;
}
