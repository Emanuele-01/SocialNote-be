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
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		http.csrf(c -> c.disable());
		
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers("/auth/**").permitAll());
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers("/auth/register").permitAll());
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.DELETE, "/social&note/users/**").hasAnyAuthority("ADMIN", "USER"));
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.PUT, "/social&note/users/**").hasAnyAuthority("USER", "ADMIN"));
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.GET, "/social&note/users/**").hasAnyAuthority("USER", "ADMIN"));
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.GET, "/social&note/users").permitAll());
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers("/post").permitAll());
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.DELETE,"/post/**").hasAnyAuthority("USER", "ADMIN"));
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.PUT,"/post/**").hasAnyAuthority("USER", "ADMIN"));
		http.authorizeHttpRequests(authorization -> authorization.requestMatchers(HttpMethod.GET,"/post/**").hasAnyAuthority("USER", "ADMIN"));
		
		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	};
	
	@Bean
	PasswordEncoder passwordencoder() {
		
		return new BCryptPasswordEncoder(12);
		
	};
}
