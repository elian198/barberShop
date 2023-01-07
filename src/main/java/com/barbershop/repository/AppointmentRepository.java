package com.barbershop.repository;

import com.barbershop.DTO.AppointmentDto;
import com.barbershop.DTO.Time;
import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT  FROM appointment WHERE appointment.customer_id = :filtro" ,nativeQuery = true)
    Customer findByIdCustomer(@Param("filtro") Long filtro);

    @Query(value = "SELECT * FROM appointment ORDER BY duration" ,nativeQuery = true)
    List<Appointment> orderByDuration();

    @Query(value = "SELECT * FROM appointment ORDER BY local_date" ,nativeQuery = true)
    List<Appointment> orderByLocalDate();

    @Query(value = "SELECT * FROM appointment WHERE appointment.local_date = :filtro" ,nativeQuery = true)
    List<Appointment> findByLocalDate(@Param("filtro")LocalDate date);

    @Query(value = "SELECT * FROM appointment WHERE appointment.local_date = :filtro AND appointment.time= :time" ,nativeQuery = true)
    Appointment existTurn(@Param("filtro") LocalDate date, @Param("time") LocalTime time);



}
