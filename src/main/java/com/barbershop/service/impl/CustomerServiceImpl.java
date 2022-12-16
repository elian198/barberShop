package com.barbershop.service.impl;

import com.barbershop.DTO.ConvertDto;
import com.barbershop.DTO.CustomerDto;
import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.exception.EmailExist;
import com.barbershop.exception.NoExist;
import com.barbershop.repository.CustomerRepository;
import com.barbershop.service.CustomerService;
import com.barbershop.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Autowired
    EmailService emailService;

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
    public void save(Customer customer) throws EmailExist{
        if(customerRepository.findByEmail(customer.getEmail()) != null){
            throw new EmailExist(HttpStatus.NOT_FOUND, "El email ya existe");
        }

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
    public Customer deleteAppointment(Long id, Long idAppointment) throws NoExist {
        if(customerRepository.existsById(id)){
            Customer customer = customerRepository.findById(id).get();
            Appointment appointment = appointmentService.findById(idAppointment).get();
            customer.getAppointment().remove(appointment);
             return customerRepository.save(customer);
        }
        throw new NoExist(HttpStatus.NOT_FOUND, "No existe el id " + id + " ingresado");
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) throws FileNotFoundException {
        if(customerRepository.existsById(id)){
            return customerRepository.findById(id).get();
        }
         throw  new FileNotFoundException("No existe el id");
    }

    public Boolean findByPhone(Integer phone){
        if(customerRepository.findByPhone(phone)!= null){
            return true;
        }
        return false;
    }

    public void sendMessage() throws MessagingException {
        for(Customer listCustomer: customerRepository.findAll()){
            for(Appointment listAppointment : listCustomer.getAppointment()){
                if(appointmentService.total(listAppointment.getId()) >0  ) {
                    if(listAppointment.getDate().compareTo(LocalDate.now()) >0 && listAppointment.getDate().compareTo(LocalDate.now()) <4);
                    emailService.sendWithAttach("elianpareja5@gmail.com", listCustomer.getEmail(), "Aviso turno pendiente", emailService.avisoTurnoPendiente(listCustomer.getFirst_name(), listCustomer.getLast_name(), listAppointment.getDate(),listAppointment.getTime(), appointmentService.total(listAppointment.getId())));
                }
            }
        }
    }
}
