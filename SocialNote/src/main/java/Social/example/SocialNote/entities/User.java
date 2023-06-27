package Social.example.SocialNote.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private String name;
	private String lastName;
	private String username;
	private String email;
	private String password;
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
		this.imageProfile = imageProfile;
		this.age = age;
		this.dayOfHiding = dayOfHiding;
	}
}
