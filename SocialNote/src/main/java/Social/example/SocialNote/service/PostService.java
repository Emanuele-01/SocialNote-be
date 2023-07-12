package Social.example.SocialNote.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import Social.example.SocialNote.entities.Post;
import Social.example.SocialNote.exceptions.NotFoundException;
import Social.example.SocialNote.exceptions.UnauthorizedException;
import Social.example.SocialNote.payload.PostPayload;
import Social.example.SocialNote.repositories.PostRepository;
import Social.example.SocialNote.repositories.UserRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository uRepo;
	
	public Post create(PostPayload post) {
		
		Post newPost = new Post();
		
		newPost.setTitle(post.getTitle());
		newPost.setBodyText(post.getBodyText());
		newPost.setPublicationDate(LocalDate.now());
		newPost.setCity(post.getCity());
		newPost.setUser(uRepo.findById(post.getUser()).orElseThrow(() -> new NotFoundException("User not Found")));
		
		return postRepo.save(newPost);
	};
	
// ------------------------------------------------------------------------------------------
	
	public Post findById(UUID id) throws NotFoundException{
		return postRepo.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
	};
	
// ------------------------------------------------------------------------------------------
	
public Page<Post> find(int page, int size, String sortedBy) {
		
		if (size < 0) {
			size = 10;
		}
		
		if (size > 50) {
			size = 50;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));

		return postRepo.findAll(pageable);
	};
	
// --------------------------------------------------------------------------------------------
	
	public Post findByIdAndUpdate(UUID id, PostPayload post, Authentication auth) {
		
		Post p = this.findById(id);
		
		 if (!p.getUser().getUsername().equals((auth.getName()))) {
	            throw new UnauthorizedException("Not Valid User for this action");
	        }
		
		p.setTitle(post.getTitle());
		p.setBodyText(post.getBodyText());
		p.setPublicationDate(LocalDate.now());
		p.setCity(post.getCity());
		
		return postRepo.save(p);
	};
	
// -------------------------------------------------------------------------------------------
	
	public void findByIdAndDelete(UUID id, Authentication auth) throws NotFoundException{
		
		Post p = this.findById(id);
		
		  if (!p.getUser().getUsername().equals((auth.getName()))) {
	            throw new UnauthorizedException("Not Valid User for this action");
	        }
		
		postRepo.delete(p);
	};
}
