package Social.example.SocialNote.payload;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPayload {


	@NotNull(message = "enter a valid title")
	@Size(min = 1 , max = 25, message = "maximum number of characters 25")
	String title;
	
// -------------------------------------------------------------------------------
	
	@NotNull(message = "enter a valid body Text")
	@Size(min = 1, max = 150, message = "maximum number of characters 150")
	String bodyText;
	
// -------------------------------------------------------------------------------- 
	@NotNull(message = "enter a valid date")
	LocalDate publicationDate;
	
// --------------------------------------------------------------------------------
	
	@NotNull(message = "enter a valid city")
	@Size(min =2 , max = 25, message = "There must be a minimum 2 characters and a maximum of 25")
	String city;
	
// ----------------------------------------------------------------------------------
	
	public PostPayload(
			@NotNull(message = "enter a valid title") @Size(min = 1, max = 25, message = "maximum number of characters 25") String title,
			@NotNull(message = "enter a valid body Text") @Size(min = 1, max = 150, message = "maximum number of characters 150") String bodyText,
			@NotNull(message = "enter a valid date") LocalDate publicationDate,
			@NotNull(message = "enter a valid city") @Size(min = 2, max = 25, message = "There must be a minimum 2 characters and a maximum of 25") String city) {
		super();
		this.title = title;
		this.bodyText = bodyText;
		this.publicationDate = publicationDate;
		this.city = city;
	};
}
