package Social.example.SocialNote.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
public class User implements UserDetails{
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private String name;
	private String lastName;
	private String username;
	private String email;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	private String imageProfile;
	private int age;
	private LocalDate dayOfHiding;

	
	public User(String name, String lastName, String username, String email, String password, String imageProfile, int age,
			LocalDate dayOfHiding) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = UserRole.USER;
		this.imageProfile = imageProfile;
		this.age = age;
		this.dayOfHiding = dayOfHiding;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority(role.name()));
	}


	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}


	@Override
	public boolean isEnabled() {
		
		return true;
	}
}
