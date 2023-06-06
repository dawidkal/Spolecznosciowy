package pl.dawid.spolecznosciowy.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.dawid.spolecznosciowy.users.User;
import pl.dawid.spolecznosciowy.users.UserRepository;

import java.util.Optional;

@RestController
public class PostService {
    @Autowired //wystrzykujemy komponenty
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/posts")  //met dodawania postow
    public ResponseEntity addPost(@RequestHeader("username") String username, @RequestBody String postBody) {//nazwa przekazana w naglowku
   Optional<User> userFromDb = userRepository.findByUsername(username); //pobieranie uzytkownika z bazy
   if (userFromDb.isEmpty()) {
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }
   Post post = new Post(userFromDb.get(), postBody);
   Post savedPost= postRepository.save(post);
   return ResponseEntity.ok(savedPost);
    }
}