package Social.example.SocialNote.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Social.example.SocialNote.auth.authPayload.AuthenticationTokenPayload;
import Social.example.SocialNote.entities.User;
import Social.example.SocialNote.exceptions.NotFoundException;
import Social.example.SocialNote.payload.LoginPayload;
import Social.example.SocialNote.payload.RegisterPayload;
import Social.example.SocialNote.service.UserService;
import Social.example.SocialNote.exceptions.UnauthorizedException;

@RestController
@RequestMapping("/authorization")
public class AuthController {

	@Autowired
	UserService uService;
	
	@Autowired
	private PasswordEncoder crypt;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated RegisterPayload rp) {
		
		rp.setPassword(crypt.encode(rp.getPassword()));
		User createU = uService.create(rp);
		return new ResponseEntity<>(createU, HttpStatus.CREATED);
	};
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationTokenPayload> login(@RequestBody LoginPayload lp) throws NotFoundException{
		
		User u = uService.findByUsername(lp.getUsername());
		
		String password = lp.getPassword();
		String hashedPassword = u.getPassword();
		
		if(!crypt.matches(password, hashedPassword)) {
			throw new UnauthorizedException("invalid credentials");
		};
		
		String token = JWTools.createToken(u);
		
		return new ResponseEntity<>( new AuthenticationTokenPayload(token), HttpStatus.OK);
	};
}
