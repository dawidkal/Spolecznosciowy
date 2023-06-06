package pl.dawid.spolecznosciowy.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserService { //odpowiada za dodawaie i wyswietlanie uzytkowniow
    @Autowired //wstrzykiwanie, bedzie mozna uzyc klasy
            UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper; // jako Json, objekt z biblioteki Json


    @GetMapping("/users")
    public ResponseEntity getUsers() throws JsonProcessingException {//metoda zwraca wszytkich z bazy
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) {
        //spr czy taki uzytkownik znajduje sie w bazie
        //  List<User> userFromDB = userRepository.findByUsername(user.getUsername());
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());//czy uzytkownik juz istnieje
        //   if (!userFromDB.isEmpty()) // dla listy
        if (userFromDB.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();  //422 kody odpowiedzi w razie dubla uzytkwnika w bazie
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }


    @PostMapping("/login")
    public ResponseEntity login (@RequestBody User user){
    Optional<User>userFromDb=userRepository.findByUsername(user.getUsername());
if (userFromDb.isEmpty() || wrongPassword (userFromDb,user))
{ return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();//spr czy jest w bazie i zwr status
}
return ResponseEntity.ok().build();
}
    private boolean wrongPassword(Optional<User> userFromDb, User user) {
     return !userFromDb.get().getPassword().equals(user.getPassword());
    }
    }
