package Social.example.SocialNote.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Social.example.SocialNote.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
