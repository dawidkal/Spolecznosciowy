package pl.dawid.spolecznosciowy.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{

    //List<User> findByUsername(String username);// lub
    Optional<User> findByUsername(String username); // optional kontener dla obiektu
}
