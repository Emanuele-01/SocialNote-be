package Social.example.SocialNote.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Social.example.SocialNote.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{

}
