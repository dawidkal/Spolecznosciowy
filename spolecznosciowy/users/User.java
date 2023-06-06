package pl.dawid.spolecznosciowy.users;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="users")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // tu byl by blad mozna by tworzyc pustych uzytkownikow
@RequiredArgsConstructor
@ToString

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id; //takie nazwu powinny sie znalesc w tabeli, raz byla literowka i sie odpalalo ale wys blad
   @NonNull//wymagane
   private  String username;
   @NonNull
   private String password;


}
