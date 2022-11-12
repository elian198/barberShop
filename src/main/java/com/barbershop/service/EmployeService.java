package com.barbershop.service;

import com.barbershop.entites.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
    Employee findByEmail(String name);
    Optional<Employee> findById(Long id);
    List<Employee> findAll();
    void Save(Employee employee);
    List<Employee> delete(Employee employee);
    Employee update(Employee employee);
}
