package br.com.testeprojedata.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee extends Person {

    @Column
    BigDecimal salary;

    @Column
    String jobRole;

    public Employee(String name, LocalDate birthDate, String jobRole, BigDecimal salary) {
        super(name, birthDate);
        this.jobRole = jobRole;
        this.salary = salary;
    }
}
