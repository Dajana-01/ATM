package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "atms")
@Data
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "atmId")
    private Integer id;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Bank_bankId")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Bank bank;


}