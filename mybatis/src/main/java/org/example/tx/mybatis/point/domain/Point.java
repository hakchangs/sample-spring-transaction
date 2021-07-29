package org.example.tx.mybatis.point.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter @Setter @ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Point {

    Long id;
    Long amount;

}
