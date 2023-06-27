package Social.example.SocialNote.exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}
