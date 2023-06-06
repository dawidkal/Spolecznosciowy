package pl.dawid.spolecznosciowy.posts;

import lombok.*;
import pl.dawid.spolecznosciowy.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="posts")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    @NotNull
    private User user;
    @NonNull
    private String body;

    public Post(User user, String postBody) {
    }
}
