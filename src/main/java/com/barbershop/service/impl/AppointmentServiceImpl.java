package com.barbershop.service.impl;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.repository.AppointmentRepository;
import com.barbershop.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Customer findByIdCustomer(Long idCustomer) throws Exception {
        if(appointmentRepository.findByIdCustomer(idCustomer) != null){
            throw new Exception("error el id no existe");
        }
        return appointmentRepository.findByIdCustomer(idCustomer);
    }

    @Override
    public List<Appointment> orderByDuration() {
        return null;
    }

    @Override
    public Optional<Appointment> findById(Long id)  throws ArithmeticException{
        if(id == null){
            throw new ArithmeticException();
        }
        return appointmentRepository.findById(id);
    }
}
