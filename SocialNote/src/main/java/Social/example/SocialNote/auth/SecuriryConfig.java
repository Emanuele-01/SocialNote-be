package Social.example.SocialNote.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecuriryConfig {

	@Autowired
	AuthFilter authFilter;
	
	@Autowired
	CorsFilter corsFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
		
		
		https.csrf(c -> c.disable());
		
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers("/auth/**").permitAll());
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("ADMIN", "USER"));
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority("USER", "ADMIN"));
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("USER", "ADMIN"));
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers("/users").permitAll());
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers("/post").permitAll());
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.DELETE,"/post/**").hasAnyAuthority("USER", "ADMIN"));
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.PUT,"/post/**").hasAnyAuthority("USER", "ADMIN"));
		https.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.GET,"/post/**").hasAnyAuthority("USER", "ADMIN"));
		
		https.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		https.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
		
		https.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return https.build();
	};
	
	@Bean
	PasswordEncoder passwordencoder() {
		
		return new BCryptPasswordEncoder(12);
		
	};
}
