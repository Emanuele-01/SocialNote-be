package Social.example.SocialNote.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Social.example.SocialNote.entities.Post;
import Social.example.SocialNote.exceptions.NotFoundException;
import Social.example.SocialNote.payload.PostPayload;
import Social.example.SocialNote.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepo;
	
	public Post create(PostPayload post) {
		
		Post newPost = new Post();
		
		newPost.setTitle(post.getTitle());
		newPost.setBodyText(post.getBodyText());
		newPost.setPublicationDate(post.getPublicationDate());
		newPost.setCity(post.getCity());
		
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
	
	public Post findByIdAndUpdate(UUID id, PostPayload post) {
		
		Post p = this.findById(id);
		
		p.setTitle(post.getTitle());
		p.setBodyText(post.getBodyText());
		p.setPublicationDate(post.getPublicationDate());
		p.setCity(post.getCity());
		
		return postRepo.save(p);
	};
	
// -------------------------------------------------------------------------------------------
	
	public void findByIdAndDelete(UUID id) throws NotFoundException{
		
		Post p = this.findById(id);
		postRepo.delete(p);
	};
}
