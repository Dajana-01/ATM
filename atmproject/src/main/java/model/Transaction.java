package model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "transactionId")
    private Integer id;
    private Integer transactionNo;
    private String status;
    private Integer amount;
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Account_accountId")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Account account;
}