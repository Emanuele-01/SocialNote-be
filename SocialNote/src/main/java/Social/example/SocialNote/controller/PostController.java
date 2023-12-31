package Social.example.SocialNote.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Social.example.SocialNote.entities.Post;
import Social.example.SocialNote.payload.PostPayload;
import Social.example.SocialNote.service.PostService;

@RestController
@RequestMapping(path = "social&note/post")
public class PostController {

	@Autowired
	PostService pService;
	
	@GetMapping(path = "")
	@ResponseStatus(HttpStatus.OK)
	public Page<Post> postAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "id") String sorted) {
		return pService.find(page, size, sorted);
	};
	
// ------------------------------------------------------------------------------------------
	
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Post getPost(@PathVariable UUID id) {
		return pService.findById(id);
	};
	
// ------------------------------------------------------------------------------------------
	
	@PostMapping(path ="")
	@ResponseStatus(HttpStatus.CREATED)
	public Post Create(@RequestBody PostPayload post) {
		return pService.create(post);
	};
	
// -----------------------------------------------------------------------------------------
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Post findAndUpdate(@PathVariable UUID id, @RequestBody PostPayload post, Authentication auth) {
		return pService.findByIdAndUpdate(id, post, auth);
	};
	
// -----------------------------------------------------------------------------------------
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePost(@PathVariable UUID id, Authentication auth) {
		pService.findByIdAndDelete(id, auth);
	};
}
