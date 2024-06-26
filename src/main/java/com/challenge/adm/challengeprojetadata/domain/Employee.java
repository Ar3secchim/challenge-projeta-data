package com.challenge.adm.challengeprojetadata.domain;

import com.challenge.adm.challengeprojetadata.utils.FormatterBigDecimal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private BigDecimal salary;
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
                ", Data de Nascimento: " + formatterDateTime(getBirthDate()) +
                ", Salário: " + FormatterBigDecimal.forFormatBrazil(getSalary()) +
                ", Função: " + getPosition();
    }

    private String formatterDateTime(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dateFormatter);
    }


}
