package com.barbershop.repository;

import com.barbershop.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(value = "SELECT * FROM employees WHERE employees.email = :filtro" ,nativeQuery = true)
    Employee findByEmail(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM employees WHERE employees.first_name =  :filtro" ,nativeQuery = true)
    Optional<Employee> findByUsername(@Param("filtro") String filtro);

    //@Procedure("existe_lastName")
   // Integer existeLastName(String lastName);

}
