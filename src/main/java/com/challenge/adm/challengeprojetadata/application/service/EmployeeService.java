package com.challenge.adm.challengeprojetadata.application.service;

import com.challenge.adm.challengeprojetadata.application.repository.EmployeeRepository;
import com.challenge.adm.challengeprojetadata.domain.Employee;
import com.challenge.adm.challengeprojetadata.infra.expection.BadRequestClient;
import com.challenge.adm.challengeprojetadata.utils.FormatterString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public void save(Employee employee) throws Exception {
        if (employee.getName() == null || employee.getName().trim().isEmpty() || employee.getName().trim().length() < 3)
            throw new Exception("length must be between 3 and 35");

        if (employee.getBirthDate() == null || employee.getBirthDate().isAfter(LocalDate.now()))
            throw new Exception("date must be after current date");

        if (employee.getSalary() == null || employee.getSalary().compareTo(BigDecimal.ZERO) < 0)
            throw new Exception("salary must be greater than zero");

        if (employee.getPosition().trim().isEmpty() || employee.getPosition().trim().length() < 3) {
            throw new Exception("position must not be empty or length must be between 3 and 35 ");
        }

        repository.save(employee);
    }

    public void deleteByName(String name) throws BadRequestClient {
        Employee Employee = repository.findByName(name);
        if (Employee == null) throw new BadRequestClient("Employee not found with NAME: " + name);
        repository.deleteByName(name);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
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

    public Map<String, List<Employee>> groupingByPosition() {
        return repository.findAll().stream()
          .collect(Collectors.groupingBy(Employee::getPosition));
    }

    public List<Employee> sortAlphabetically() {
        return repository.findAll().stream()
          .sorted(Comparator.comparing(Employee::getName))
          .collect(Collectors.toList());
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

    public String totalAverageSalary() {
        var totalSalary = repository.findAll().stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return FormatterString.bigDecimalForFormatBrazil(totalSalary);
    }
}
