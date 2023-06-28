package Social.example.SocialNote.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecuriryConfig {

	@Autowired
	AuthFilter authFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
		
	//	https.cors(c -> c.disable());
		https.csrf(c -> c.disable());
		
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers("/auth/**").permitAll());
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers("/user/{:id}").authenticated());
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers("/post").permitAll());
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers("/post/{:id}").authenticated());
		
		
		https.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		
		https.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return https.build();
	};
	
	@Bean
	PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder(12);
	};
}
