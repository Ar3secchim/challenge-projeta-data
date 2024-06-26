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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SaveEmployeeUnitTest {
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
    @DisplayName("should register employee when everything success")
    public void registerEmployeeWithSuccess() throws Exception {
        employeeService.save(employee);
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("should throw exception employee when name is invalid")
    public void registerEmployeeWithNameLessThanEmpty() {
        employee.setName("");

        Exception exception = assertThrows(
                Exception.class, () -> {
                    employeeService.save(employee);
                }
        );

        assertEquals("length must be between 3 and 35", exception.getMessage());
    }

    @Test
    @DisplayName("should throw exception employee when date birth is invalid")
    public void registerEmployeeWithDateAfterDayToday() {
        employee.setBirthDate(LocalDate.of(2024, 10, 25));

        Exception exception = assertThrows(
                Exception.class, () -> {
                    employeeService.save(employee);
                }
        );

        assertEquals("date must be after current date", exception.getMessage());
    }

    @Test
    @DisplayName("should throw exception employee when salary is invalid")
    public void registerEmployeeWithSalarySmallerThanZero() {
        employee.setSalary(new BigDecimal(-12500));
        Exception exception = assertThrows(
                Exception.class, () -> {
                    employeeService.save(employee);
                }
        );
        assertEquals("salary must be greater than zero", exception.getMessage());
    }

    @Test
    @DisplayName("should throw exception employee when position is invalid")
    public void registerEmployeeWithPositionInvalid() {
        employee.setPosition("");

        Exception exception = assertThrows(
                Exception.class, () -> {
                    employeeService.save(employee);
                }
        );
        assertEquals("position must not be empty or length must be between 3 and 35 ", exception.getMessage());
    }
}
