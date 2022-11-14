package com.barbershop.service.impl;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

   List<Appointment> findAll();
   Customer findByIdCustomer(Long idCustomer);
   List<Appointment> orderByDuration();
   Optional<Appointment> findById(Long id);
}