package org.example.tx.jpa.member.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.tx.jpa.point.domain.Point;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter @Setter @ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member {

    @Id
    Long id;
    String name;

    @OneToOne
    @JoinColumn(name = "id")
    Point point;

}
