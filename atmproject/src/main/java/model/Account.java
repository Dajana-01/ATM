package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "accountId")
    private Integer id;
    private String accountNo;
    private String type;
    private Integer balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Bank_bankId")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Bank bank;

    @OneToMany(mappedBy = "account")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Transaction> transactions= new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="Card_cardId")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Card card;

}