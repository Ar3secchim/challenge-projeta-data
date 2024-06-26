package com.challenge.adm.challengeprojetadata.unit.service.employee;

import com.challenge.adm.challengeprojetadata.application.repository.EmployeeRepository;
import com.challenge.adm.challengeprojetadata.application.service.EmployeeService;
import com.challenge.adm.challengeprojetadata.domain.Employee;
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
public class GetEmployeeUnitTest {
  @InjectMocks
  private EmployeeService employeeService;

  @Mock
  private EmployeeRepository employeeRepository;


  @BeforeEach
  public void setUp() throws Exception {
    Employee employeeTestOne = new Employee("employeeTestOne", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
    Employee employeeTestTwo = new Employee("employeeTestTwo", LocalDate.of(2000, 8, 20), new BigDecimal("3259.44"), "Secretaria");

    when(employeeRepository.findAll()).thenReturn(List.of(employeeTestOne, employeeTestTwo));
  }

  @Test
  @DisplayName("should list employee")
  public void getAllEmployee() {
    List<Employee> employeeList = employeeService.getAllEmployees();

    verify(employeeRepository, times(1)).findAll();
    assertEquals(2, employeeList.size());
  }

  @Test
  @DisplayName("should list employee  when date birth moth")
  public void getEmployeeByBirthDateMoths() {
    List<Employee> employeeList = employeeService.getBirthDateMoths(10);

    verify(employeeRepository, times(1)).findAll();
    assertEquals(1, employeeList.size());
    assertEquals("employeeTestOne", employeeList.getFirst().getName());
  }

  @Test
  @DisplayName("should list employee  when date birth moth not existt")
  public void getEmployeeByBirthDateMothsNotExist() {
    List<Employee> employeeList = employeeService.getBirthDateMoths(2);

    verify(employeeRepository, times(1)).findAll();
    assertEquals(0, employeeList.size());
  }
}
