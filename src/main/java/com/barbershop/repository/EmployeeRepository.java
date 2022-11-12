package com.barbershop.repository;

import com.barbershop.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(value = "SELECT * FROM employee WHERE employee.email = :filtro" ,nativeQuery = true)
    Employee findByEmail(@Param("filtro") String filtro);
}
