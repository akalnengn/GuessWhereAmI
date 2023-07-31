package org.engina.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblguess")
public class Guess extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long cityid;
    Long userid;
    String cityName;
    String cityArea;
    String hint;
    String photoWithBlur;
    String photoWithoutBlur;
}
