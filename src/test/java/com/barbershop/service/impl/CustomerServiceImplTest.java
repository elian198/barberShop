package com.barbershop.service.impl;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.repository.CustomerRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerServiceImpl customerService;

    @InjectMocks
    Customer customer = new Customer();


    @BeforeEach
    void init(@Mock CustomerRepository customerRepository) throws ParseException {
        MockitoAnnotations.openMocks(this);
    customerService = new CustomerServiceImpl(customerRepository);

    }
    @Test
    void findByEmail() {
    }

    @Test
    void save() throws ParseException {

    }

    @Test
    void delete() {
    }

    @Test
    void addAppointment() {
    }

    @Test
    void deleteAppointment() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }
}