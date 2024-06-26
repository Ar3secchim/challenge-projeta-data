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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShortAndGroupingEmployeeUniTest {
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
  @DisplayName("should list employee sort Alphabetically")
  public void getAllEmployeeSortAlphabetically() {
    List<Employee> employeeList = employeeService.sortAlphabetically();

    verify(employeeRepository, times(1)).findAll();
    assertEquals(2, employeeList.size());
    assertEquals("CEmployee", employeeList.getFirst().getName());
  }

  @Test
  @DisplayName("should list employee grouping By Position")
  public void getEmployeeByBirthDateMoths() {
    Map<String, List<Employee>> employeeList = employeeService.groupingByPosition();

    verify(employeeRepository, times(1)).findAll();

    assertEquals(2, employeeList.size());
    assertEquals(2, employeeList.keySet().size());

    List<Employee> listEmployeePositionOperator = employeeList.get("Operador");
    assertTrue(employeeList.containsKey("Operador"));
    assertEquals("FEmployee", listEmployeePositionOperator.getFirst().getName());

    List<Employee> listEmployeePositionSecretary = employeeList.get("Secretaria");
    assertTrue(employeeList.containsKey("Secretaria"));
    assertEquals("CEmployee", listEmployeePositionSecretary.getFirst().getName());
  }
}
