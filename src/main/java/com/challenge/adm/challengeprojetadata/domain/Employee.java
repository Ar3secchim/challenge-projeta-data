package com.challenge.adm.challengeprojetadata.domain;

import com.challenge.adm.challengeprojetadata.utils.FormatterString;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "employee")
@Setter
@Getter
public class Employee extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal salary;
    @Getter
    private String position;

    public Employee() {
    }

    public Employee(String name, LocalDate birthDate, BigDecimal salary, String position) {
        super(name, birthDate);
        this.salary = salary;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Nome: " + getName() +
          ", Data de Nascimento: " + getBirthDate() +
          ", Salário: " + FormatterString.bigDecimalForFormatBrazil(salary) +
                ", Função: " + getPosition();
    }
}
