package Social.example.SocialNote.payload;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPayload {

	@NotNull(message = "enter a valid name")
	@Size(min = 3, max = 14)
	 String name;
	
// ---------------------------------------------------------------	
	
	@NotNull(message = "enter a valid last name")
	@Size(min = 3, max = 14)
	 String lastName;
	
// ---------------------------------------------------------------	
	
	@NotNull(message = "enter a valid username")
	@Size(min = 3, max = 15)
		String username;
		
// ---------------------------------------------------------------	
	
	@NotNull(message = "enter a valid email")
	@Size(min = 10, max = 25)
		String email;
	
// ---------------------------------------------------------------
	
	@NotNull(message = "enter a valid password")
	@Size(min = 4)
		String password;
	
// ---------------------------------------------------------------
	
	@NotNull(message = "enter a valid image Profile")
	String imageProfile;
	
// ---------------------------------------------------------------
	
	@NotNull(message = "enter a valid age")
	@Size(min = 1, max = 3)
	 int age;
	
// ---------------------------------------------------------------
	
	@NotNull(message = "enter a valid day of hiding")
	LocalDate dayOfHiding;
	
// ---------------------------------------------------------------
}
