package Social.example.SocialNote.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import Social.example.SocialNote.service.PostService;

@Component
public class PostRunner implements CommandLineRunner{

	@Autowired
	PostService pService;

	
	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
