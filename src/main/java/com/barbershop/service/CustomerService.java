package com.barbershop.service;

import com.barbershop.DTO.CustomerDto;
import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;

import java.io.FileNotFoundException;
import java.util.List;

public interface CustomerService {
    CustomerDto findByEmail(String email);
    void save(Customer customer);
    void delete(Long id);
    void addAppointment(Long id, Appointment appointment);
    Customer  deleteAppointment(Long id, Long idAppointment);
    Customer update(Customer customer);
    List<Customer> findAll();
    Customer findById(Long id) throws FileNotFoundException;
}
