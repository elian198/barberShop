package com.barbershop.service;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer findByEmail(String email);
    void save(Customer customer);
    void delete(Long id);
    void addAppointment(Long id, Appointment appointment);
    void  deleteAppointment(Long id, Long idAppointment);
    Customer update(Customer customer);
    Customer updateAppointment(Appointment appointment);
    List<Customer> findAll();
}
