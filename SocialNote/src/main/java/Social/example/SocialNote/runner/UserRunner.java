package Social.example.SocialNote.runner;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import Social.example.SocialNote.payload.RegisterPayload;
import Social.example.SocialNote.service.UserService;
import lombok.extern.java.Log;

@Log
@Component
public class UserRunner implements CommandLineRunner {

	@Autowired
	UserService uService;
	
	@Autowired
	PasswordEncoder crypt;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Faker faker = new Faker();
		
		for (int i = 0; i <= 40; i++) {
			try {
				
				String name = faker.name().firstName();
				String lastName = faker.name().lastName();
				String username = faker.name().username();
				String email = faker.internet().emailAddress();
				String password = crypt.encode("1234");
				String imageProfile = "http://image.prifile.com";
				int age = 20;
				LocalDate dayOfHiding = LocalDate.now();
				
				RegisterPayload rPayload = new RegisterPayload(name, lastName, username, email, password, imageProfile, age, dayOfHiding);
		//		uService.create(rPayload);
				
			} catch (Exception e) {
				log.info("" + e);
			}
		}
		
	}

}
