package com.barbershop.repository;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT  FROM appointment WHERE appointment.customer_id = :filtro" ,nativeQuery = true)
    Customer findByIdCustomer(@Param("filtro") Long filtro);
}
