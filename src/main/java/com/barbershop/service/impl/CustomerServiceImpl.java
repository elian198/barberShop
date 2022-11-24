package com.barbershop.service.impl;

import com.barbershop.DTO.ConvertDto;
import com.barbershop.DTO.CustomerDto;
import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.repository.CustomerRepository;
import com.barbershop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto findByEmail(String email) {
        if(customerRepository.findByEmail(email) != null){
            ConvertDto convertDto = new ConvertDto();
            return  convertDto.convertirCustomeraDto(customerRepository.findByEmail(email));
        }
        return null;
    }

    @Override
    public void save(Customer customer) {
        if(customerRepository.findByEmail(customer.getEmail()) != null){
            //throw new EmailExist()
        }
        customer.setSoft_delete(false);
        customerRepository.save(customer);

    }

    @Override
    public void delete(Long id) {
       if(id > 0){
           customerRepository.deleteById(id);
       }
       //throw new error
    }

    @Override
    public void addAppointment(Long id, Appointment appointment) {
         if(customerRepository.existsById(id)){
             Customer customer = customerRepository.findById(id).get();
             customer.getAppointment().add(appointment);
             customerRepository.save(customer);
         }
         //throw new noExist()
    }

    @Override
    public void deleteAppointment(Long id, Long idAppointment) {
        if(customerRepository.existsById(id)){
            Customer customer = customerRepository.findById(id).get();
            customer.getAppointment().remove(idAppointment);
        }
        //throw new noExist()
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public Customer updateAppointment(Appointment appointment) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public CustomerDto lookAppointMent(Long id){
         ConvertDto convertDto = new ConvertDto();
         return convertDto.convertirCustomeraDto(customerRepository.findById(id).get());
    }
}
