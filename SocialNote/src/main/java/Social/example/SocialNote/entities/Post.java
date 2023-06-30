package Social.example.SocialNote.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name =" user_id ")
	User user;
	
	
	public Post(String title, String bodyText, LocalDate publicationDate, String city) {
		super();
		this.title = title;
		this.bodyText = bodyText;
		this.publicationDate = publicationDate;
		this.city = city;
	}
	public Post(String title, String bodyText, LocalDate publicationDate, String city, User u) {
		super();
		this.title = title;
		this.bodyText = bodyText;
		this.publicationDate = publicationDate;
		this.city = city;
		this.user = u;
	}
}
