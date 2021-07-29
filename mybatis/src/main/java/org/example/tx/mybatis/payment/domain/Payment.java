package org.example.tx.mybatis.payment.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.tx.mybatis.member.domain.Member;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter @Setter @ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    Long id;
    Long amount;
    Member payer;
    Timestamp paidDate;

}
