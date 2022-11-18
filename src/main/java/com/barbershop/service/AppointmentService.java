package com.barbershop.service;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

   List<Appointment> findAll();
   Customer findByIdCustomer(Long idCustomer) throws Exception;
   List<Appointment> orderByDuration();
   Optional<Appointment> findById(Long id);
   void delete(Long id) throws Exception;
}
