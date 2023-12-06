package br.com.testeprojedata.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Person extends AbstractModel {

    @Column
    String name;

    @Column
    LocalDate birthDate;
}
