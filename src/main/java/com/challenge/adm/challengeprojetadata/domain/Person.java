package com.challenge.adm.challengeprojetadata.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Setter
@Getter
@MappedSuperclass
public abstract class Person {
  @Getter
  private String name;
  private LocalDate birthDate;

  protected Person() {
  }

  public Person(String name, LocalDate birthDate) {
    this.name = name;
    this.birthDate = birthDate;
  }
}
