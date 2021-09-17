package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "banks")
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankId")
    private Integer id;
    private String name;
    private String address;
    private Integer code;

    @OneToMany(mappedBy = "bank")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "bank")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Atm> atms = new HashSet<>();

    @OneToMany(mappedBy = "bank")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Account> accounts = new HashSet<>();
}