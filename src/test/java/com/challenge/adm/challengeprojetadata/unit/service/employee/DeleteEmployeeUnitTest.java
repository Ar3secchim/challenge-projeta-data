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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteEmployeeUnitTest {
  @InjectMocks
  private EmployeeService employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  private Employee employee;

  @BeforeEach
  void setUp() {
    employee = new Employee();
    employee.setName("Joe Test");
    employee.setBirthDate(LocalDate.of(2000, 10, 18));
    employee.setSalary(new BigDecimal("100.00"));
    employee.setPosition("Position Test");
  }


  @Test
  @DisplayName("should delete employee when everything success")
  public void deleteEmployeeWithSuccess() throws Exception {
    when(employeeRepository.findByName(employee.getName())).thenReturn(employee);

    employeeService.deleteByName("Joe Test");

    verify(employeeRepository, times(1)).deleteByName(any());
  }

  @Test
  @DisplayName("should throw exception employee when employee not exist")
  public void deleteEmployeeNotExist() throws Exception {
    Exception exception = assertThrows(
      Exception.class, () -> {
        employeeService.deleteByName("Not exist");
      }
    );

    assertEquals("Employee not found with NAME: Not exist", exception.getMessage());
  }
}
