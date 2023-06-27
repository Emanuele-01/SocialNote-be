package Social.example.SocialNote.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Social.example.SocialNote.entities.User;
import Social.example.SocialNote.payload.RegisterPayload;
import Social.example.SocialNote.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService uService;
	
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Page<User> UserAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "id") String sorted){
		return uService.find(page, size, sorted);
	};
	
// ---------------------------------------------------------------------------------------------------
	
	@PutMapping("/{:id}")
	@ResponseStatus(HttpStatus.OK)
	public User findAndUpdate(@PathVariable UUID id, @RequestBody RegisterPayload rp) throws Exception {
		return uService.findByIdAndUpdate(id, rp);
	};
	
// ----------------------------------------------------------------------------------------------------
	
	@DeleteMapping("")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID id) throws Exception{
		uService.findByIdAndDelete(id);
	};
}
