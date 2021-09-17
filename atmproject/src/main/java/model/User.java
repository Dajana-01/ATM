package model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "userId")
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Card> cards = new HashSet<>();

}