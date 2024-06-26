package com.challenge.adm.challengeprojetadata.utils;

import com.challenge.adm.challengeprojetadata.domain.Employee;

import java.util.List;

public class PrintTable {

  public static void print(List<Employee> employees) {
    System.out.println("-----------------------------------------------------------------------------");
    System.out.printf("%10s %20s %15s %20s", "NOME", "DATA DE NASCIMENTO", "SALARIO", "FUNÇAO");
    System.out.println();
    System.out.println(
      "-----------------------------------------------------------------------------");
    for (Employee employee : employees) {
      System.out.format("%10s %20s %15s %20s",
        employee.getName(),
        centerString(FormatterString.dateTime(employee.getBirthDate())),
        FormatterString.bigDecimalForFormatBrazil(employee.getSalary()),
        employee.getPosition());
      System.out.println();
    }
    System.out.println("-----------------------------------------------------------------------------");
  }

  private static String centerString(String s) {
    return String.format("%-" + 20 + "s",
      String.format("%" + (s.length() + (20 - s.length()) / 2) + "s", s));
  }
}
