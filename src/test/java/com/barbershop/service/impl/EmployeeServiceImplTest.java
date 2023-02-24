package com.barbershop.service.impl;

import com.barbershop.entites.Employee;
import com.barbershop.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

   @InjectMocks
   private EmployeeServiceImpl employeeService;

   private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        employee = new Employee();
        employee.setFirst_name("mario");
        employee.setLast_name("perez");
        employee.setEmail("mario@gmail");
        employee.setPassword("123");
        employee.setSoft_delete(false);

    }

    @Test
    void findAll() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
        assertNotNull(employeeService.findAll());
    }

    @Test
    void save() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        assertNotNull(employeeService.findAll());

    }
}