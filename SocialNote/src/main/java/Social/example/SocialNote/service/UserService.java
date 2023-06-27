package Social.example.SocialNote.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Social.example.SocialNote.entities.User;
import Social.example.SocialNote.payload.UserPayload;
import Social.example.SocialNote.exceptions.NotFoundException;
import Social.example.SocialNote.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User create(UserPayload up) {
		
	 User newUser = new User();	
	 
	 newUser.setName(up.getName());
	 newUser.setLastName(up.getLastName());
	 newUser.setUsername(up.getUsername());
	 newUser.setEmail(up.getEmail());
	 newUser.setPassword(up.getPassword());
	 newUser.setImageProfile(up.getImageProfile());
	 newUser.setAge(up.getAge());
	 newUser.setDayOfHiding(up.getDayOfHiding());
	 
	 return userRepo.save(newUser);
	};
	
// -----------------------------------------------------------------------
	
	public User findById(UUID id) throws NotFoundException{
	return 	userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
	};
	
// -----------------------------------------------------------------------
	
	public Page<User> find(int page, int size, String sortedBy) {
		
		if (size < 0) {
			size = 10;
		}
		
		if (size > 50) {
			size = 50;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));

		return userRepo.findAll(pageable);
	};
	
// -------------------------------------------------------------------------
	
	public User findByIdAndUpdate(UUID id, UserPayload up) {
		
		User u = this.findById(id);
		
		u.setName(up.getName());
		u.setLastName(up.getLastName());
		u.setUsername(up.getUsername());
		u.setEmail(up.getEmail());
		u.setPassword(up.getPassword());
		u.setImageProfile(up.getImageProfile());
		u.setAge(up.getAge());
		u.setDayOfHiding(up.getDayOfHiding());
		
		return userRepo.save(u);
	};
	
// ------------------------------------------------------------------------
	
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		
		User u = this.findById(id);
		userRepo.delete(u);
	};
}
