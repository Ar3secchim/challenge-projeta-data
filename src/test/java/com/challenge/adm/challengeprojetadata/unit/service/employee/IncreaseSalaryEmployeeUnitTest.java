package com.challenge.adm.challengeprojetadata.unit.service.employee;

import com.challenge.adm.challengeprojetadata.application.repository.EmployeeRepository;
import com.challenge.adm.challengeprojetadata.application.service.EmployeeService;
import com.challenge.adm.challengeprojetadata.domain.Employee;
import com.challenge.adm.challengeprojetadata.utils.FormatterBigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncreaseSalaryEmployeeUnitTest {
  @InjectMocks
  private EmployeeService employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  @BeforeEach
  public void setUp() {
    Employee employeeTestOne = new Employee("FEmployee", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
    Employee employeeTestTwo = new Employee("CEmployee", LocalDate.of(2000, 8, 20), new BigDecimal("3259.44"), "Secretaria");

    when(employeeRepository.findAll()).thenReturn(List.of(employeeTestOne, employeeTestTwo));
  }


  @Test
  @DisplayName("should increase salary by 10%")
  public void increaseSalaryEmployee() {
    employeeService.increaseSalary(10);

    verify(employeeRepository, times(1)).findAll();

    List<Employee> employeeList = employeeService.getAllEmployees();
    assertEquals("2.210,38", FormatterBigDecimal.forFormatBrazil(employeeList.getFirst().getSalary()));
    assertEquals("3.585,38", FormatterBigDecimal.forFormatBrazil(employeeList.get(1).getSalary()));
  }

  @Test
  @DisplayName("should sum the salaries of all employees")
  public void totalSumSalaryEmployee() {
    var totalSalary = employeeService.totalAverageSalary();

    verify(employeeRepository, times(1)).findAll();
    assertEquals("5.268,88", totalSalary);
  }
}
