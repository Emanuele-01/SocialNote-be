package Social.example.SocialNote.payload;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPayload {


	@NotNull(message = "enter a valid title")
	String title;
	
// -------------------------------------------------------------------------------
	
	@NotNull(message = "enter a valid body Text")
	String bodyText;
	
// -------------------------------------------------------------------------------- 
	@NotNull(message = "enter a valid date")
	LocalDate publicationDate;
	
// --------------------------------------------------------------------------------
	
	@NotNull(message = "enter a valid city")
	String city;
	
// ----------------------------------------------------------------------------------
	@NotNull(message = "enter a valid user")
	UUID user;
	
// ----------------------------------------------------------------------------------
	public PostPayload(
			@NotNull(message = "enter a valid title") String title,
			@NotNull(message = "enter a valid body Text") String bodyText,
			@NotNull(message = "enter a valid date") LocalDate publicationDate,
			@NotNull(message = "enter a valid city") String city,
			@NotNull(message = "enter a valid user") UUID user) {
		super();
		this.title = title;
		this.bodyText = bodyText;
		this.publicationDate = publicationDate;
		this.city = city;
		this.user = user;
	};
}
