package Social.example.SocialNote.payload;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPayload {

	@NotNull(message = "enter a valid name")

	String name;
	
// ---------------------------------------------------------------	
	
	@NotNull(message = "enter a valid last name")

	String lastName;
	
// ---------------------------------------------------------------	
	
	@NotNull(message = "enter a valid username")

	String username;
		
// ---------------------------------------------------------------	
	
	@NotNull(message = "enter a valid email")

	String email;
	
// ---------------------------------------------------------------
	
	@NotNull(message = "enter a valid password")

	String password;
	
// ---------------------------------------------------------------
	
	@NotNull(message = "enter a valid image Profile")
	String imageProfile;
	
// ---------------------------------------------------------------
	
	@NotNull(message = "enter a valid age")

	int age;
	
// ---------------------------------------------------------------
	
	@NotNull(message = "enter a valid day of hiding")
	LocalDate dayOfHiding;
	
// ---------------------------------------------------------------
	
	public RegisterPayload(
			@NotNull(message = "enter a valid name") @Size(min = 3, max = 14, message = "There must be a minimum 3 characters and a maximum of 14") String name,
			@NotNull(message = "enter a valid last name") @Size(min = 3, max = 14, message = "There must be a minimum 3 characters and a maximum of 14") String lastName,
			@NotNull(message = "enter a valid username") @Size(min = 3, max = 15, message = "There must be a minimum 3 characters and a maximum of 15") String username,
			@NotNull(message = "enter a valid email") @Size(min = 10, max = 25, message = "There must be a minimum 10 characters and a maximum of 25") String email,
			@NotNull(message = "enter a valid password") String password,
			@NotNull(message = "enter a valid image Profile") String imageProfile,
			@NotNull(message = "enter a valid age") @Size(min = 1, max = 3, message = "There must be a minimum 1 characters and a maximum of 3") int age,
			@NotNull(message = "enter a valid day of hiding") LocalDate dayOfHiding) {
		
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
