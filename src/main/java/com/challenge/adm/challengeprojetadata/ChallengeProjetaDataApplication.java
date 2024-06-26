package com.challenge.adm.challengeprojetadata;

import com.challenge.adm.challengeprojetadata.application.repository.EmployeeRepository;
import com.challenge.adm.challengeprojetadata.application.service.EmployeeService;
import com.challenge.adm.challengeprojetadata.domain.Employee;
import com.challenge.adm.challengeprojetadata.domain.Person;
import com.challenge.adm.challengeprojetadata.utils.FormatterBigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

@SpringBootApplication
public class ChallengeProjetaDataApplication implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeProjetaDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 3.1 - Inserir todos os funcionários na mesma ordem e informações da tabela
        employeeService.save(new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        employeeService.save(new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        employeeService.save(new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        employeeService.save(new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        employeeService.save(new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        employeeService.save(new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        employeeService.save(new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        employeeService.save(new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        employeeService.save(new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        employeeService.save(new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        var listAllEmployees = employeeService.getAllEmployees();

        //3.2 - Remover o funcionário “João” da lista
        employeeService.deleteByName("João");

        //3.3 - Imprimir todos os funcionários com todas suas informações
        System.out.println("______________3.3- Lista de funcionários_______________");
        listAllEmployees.forEach(System.out::println);
        System.out.println();


        // 3.4 - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor
        employeeService.increaseSalary(10);
        System.out.println("______________3.4- Novos salários_______________");
        employeeService.getAllEmployees().forEach(System.out::println);
        System.out.println();


        // 3.5 - Agrupar os funcionários por função
        var listEmployees = employeeService.groupingByPosition();

        // 3.6 - Imprimir os funcionários, agrupados por função
        System.out.println("______________3.6 -Agrupados por função_______________");
        listEmployees.forEach((function, employeeList) -> {
            System.out.println("\nFunção: " + function);
            employeeList.forEach(System.out::println);
        });
        System.out.println();


        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("______________3.8 - Aniversáriamtes do mês_______________");
        employeeService.getBirthDateMoths(10, 12).forEach(System.out::println);
        System.out.println();


        // 3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade
        System.out.println("______________3.9 -Funcionário com maior idade_______________");
        var employeeOld = listAllEmployees.stream()
                .min(Comparator.comparing(Person::getBirthDate))
                .orElseThrow(Exception::new);

        int old = LocalDate.now().getYear() - employeeOld.getBirthDate().getYear();
        System.out.println("\nFuncionário mais velho: " + employeeOld.getName() + ", Idade: " + old);
        System.out.println();

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("______________3.10- Ordenado por ordem alfabética_______________");
        employeeService.sortAlphabetically().forEach(System.out::println);
        System.out.println();


        // 3.11 - Imprimir o total dos salários dos funcionários
        System.out.println("______________3.11- Total dos salários_______________");
        System.out.println("Soma de todos os salários:" + employeeService.totalAverageSalary());
        System.out.println();

        System.out.println("______________3.12- Salários mínimos por funcionários_______________");
        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00
        listAllEmployees.stream()
                .map(employees -> employees.getName() + " ganha " +
                        employees.getSalary().divide(
                                new BigDecimal("1212.00"), 2, BigDecimal.ROUND_HALF_UP)
                        + " salários mínimos.")
                .toList().forEach(System.out::println);
    }

}
