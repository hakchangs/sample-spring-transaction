package org.example.tx.jpa.payment.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.tx.jpa.member.domain.Member;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long amount;
    @ManyToOne
    Member payer;
    Timestamp paidDate;

}
