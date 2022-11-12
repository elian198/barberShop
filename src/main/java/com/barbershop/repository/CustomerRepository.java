package com.barbershop.repository;

import com.barbershop.entites.Customer;
import com.barbershop.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT * FROM customer WHERE customer.email = :filtro" ,nativeQuery = true)
    Customer findByEmail(@Param("filtro") String filtro);
}
