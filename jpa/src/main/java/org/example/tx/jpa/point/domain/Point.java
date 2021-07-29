package org.example.tx.jpa.point.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.tx.jpa.member.domain.Member;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Point {

    @Id
    Long id;
    Long amount;

    @JsonIgnore
    @OneToOne(mappedBy = "point")
    Member member;

}
