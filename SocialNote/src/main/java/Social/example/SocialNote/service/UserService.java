package Social.example.SocialNote.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import Social.example.SocialNote.entities.User;
import Social.example.SocialNote.entities.UserRole;
import Social.example.SocialNote.payload.RegisterPayload;
import Social.example.SocialNote.exceptions.NotFoundException;
import Social.example.SocialNote.exceptions.UnauthorizedException;
import Social.example.SocialNote.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	 UserRepository userRepo;
	
	public User create(RegisterPayload rp) {
		
	 User newUser = new User();	
	 
	 newUser.setName(rp.getName());
	 newUser.setLastName(rp.getLastName());
	 newUser.setUsername(rp.getUsername());
	 newUser.setEmail(rp.getEmail());
	 newUser.setPassword(rp.getPassword());
	 newUser.setRole(UserRole.USER);
	 newUser.setImageProfile(rp.getImageProfile());
	 newUser.setAge(rp.getAge());
	 newUser.setDayOfHiding(rp.getDayOfHiding());
	 
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
	
	public User findByUsername(String username) throws NotFoundException {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("User not found"));
	}
	
// -------------------------------------------------------------------------
	
	public User findByEmail(String email) throws NotFoundException {
		return userRepo.findByEmail(email)
			.orElseThrow(() -> new NotFoundException("User not found"));
	}
// -------------------------------------------------------------------------
	
	public User findByIdAndUpdate(UUID id, RegisterPayload rp, Authentication auth) {
		
		User u = this.findById(id);
		
		String authEmail = ( (User) auth.getPrincipal()).getEmail();
		
		if(!u.getEmail().equals(authEmail.toString())) {
			throw new UnauthorizedException("Not Valid User for this action");
		};
		
		u.setName(rp.getName());
		u.setLastName(rp.getLastName());
		u.setUsername(rp.getUsername());
		u.setEmail(rp.getEmail());
		u.setPassword(rp.getPassword());
		u.setImageProfile(rp.getImageProfile());
		u.setAge(rp.getAge());
		u.setDayOfHiding(rp.getDayOfHiding());
		
		return userRepo.save(u);
	};
	
// ------------------------------------------------------------------------
	
	public void findByIdAndDelete(UUID id, Authentication auth) throws NotFoundException {
		
		User u = this.findById(id);
		
		String authEmail = auth.getName();
		
		if(!u.getUsername().equals(authEmail)) {
			throw new UnauthorizedException("Not Valid User for this action");
		};
		
		userRepo.delete(u);
	};
}
