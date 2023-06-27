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
@Table(name = "post")
@NoArgsConstructor
@Data
public class Post {

	@Id
	@GeneratedValue
	private UUID id;
	
	private String title;
	private String bodyText;
	private LocalDate publicationDate;
	private String city;
	
	
	public Post(String title, String bodyText, LocalDate publicationDate, String city) {
		super();
		this.title = title;
		this.bodyText = bodyText;
		this.publicationDate = publicationDate;
		this.city = city;
	}
}
