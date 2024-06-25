package com.challenge.adm.challengeprojetadata.application.service;

import com.challenge.adm.challengeprojetadata.application.repository.EmployeeRepository;
import com.challenge.adm.challengeprojetadata.domain.Employee;
import com.challenge.adm.challengeprojetadata.utils.FormatterBigDecimal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public void save(Employee employee) {
        repository.save(employee);
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public void increaseSalary(int percentage) {
        repository.findAll().forEach(employee -> {
            employee.setSalary(
                    employee.getSalary()
                            .multiply(new BigDecimal(percentage).divide(BigDecimal.valueOf(100)))
                            .add(employee.getSalary()));
            repository.save(employee);
        });
    }

    public Map<String, List<Employee>> groupingByPosition() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getPosition));
    }

    public List<Employee> getBirthDateMoths(int... months) {
        List<Employee> listBirthDateMoths = repository.findAll().stream()
                .filter(employee -> {
                    int birthMoths = employee.getBirthDate().getMonthValue();

                    for (int month : months) {
                        if (birthMoths == month) return true;
                    }
                    return false;

                })
                .collect(Collectors.toList());

        return listBirthDateMoths;
    }

    public List<Employee> sortAlphabetically() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());
    }

    public void totalAverageSalary() {
        var totalSalary = repository.findAll().stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Soma de todos os salários:" + FormatterBigDecimal.forFormatBrazil(totalSalary));
    }


}
