package model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "cards")
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "cardId")
    private Integer id;
    private String cardNo;
    private Integer cvv;
    private LocalDateTime expirationDate;
    private String pin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Users_userId")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Bank_bankId")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Bank bank;

//    @OneToMany(mappedBy = "card")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Set<Account> accounts = new HashSet<>();
}