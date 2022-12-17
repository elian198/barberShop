package com.barbershop.controller;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.service.email.EmailService;
import com.barbershop.service.impl.AppointmentServiceImpl;
import com.barbershop.service.impl.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    public CustomerController(CustomerServiceImpl customerService, EmailService emailService, AppointmentServiceImpl appointmentService) {
        this.customerService = customerService;
        this.emailService = emailService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/customer")
    @ApiOperation("Crea un nuevo cliente")
    @ApiParam("Customer")
    public ResponseEntity<?> save(@RequestBody Customer customer){
        if(customer == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
        }
        if(customerService.findByEmail(customer.getEmail()) != null){
            return ResponseEntity.badRequest().body("El email ya existe!!\nElija otro por favor.");
        }
        if(customerService.findByPhone(customer.getPhone())){
            return ResponseEntity.badRequest().body("El numero de telfono ya existe!!!\nElija otro por favor");
        }
        customerService.save(customer);
        return ResponseEntity.ok("Cliente creado");
    }
    @PutMapping("/customer")
    @ApiOperation("Actualiza un cliente")
    @ApiParam("Customer")
    public ResponseEntity<?> update(@RequestBody Customer customer){

        customerService.update(customer);
        return ResponseEntity.ok("  Usuario  " + customer.getId() + " Actualizado");
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        customerService.delete(id);
        return ResponseEntity.ok("cliente eliminado");
    }
    @GetMapping("/customer")
    @ApiOperation("Muestra la lista de clientes")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping( value = "/customer/", params = "email")
    public ResponseEntity<?> findByEmail(@RequestParam String email){

        return ResponseEntity.ok(  customerService.findByEmail(email));
    }
    @PostMapping("/customer/addAppointment/{id}")
    @ApiOperation("Agrega turnos apartir del id de un cliente")
    @ApiParam("id, Appoinment")
    public ResponseEntity<String> addAppointment(@PathVariable Long id , @RequestBody Appointment appointment) throws FileNotFoundException, MessagingException {

        if(customerService.findById(id) == null){
            return ResponseEntity.badRequest().body("El id  no existe");
        }
        if(appointment.getDate().compareTo(LocalDate.now()) < 0){
            System.out.println(appointment.getDate().compareTo(LocalDate.now()));
           return ResponseEntity.badRequest().body("La fecha del turno ya paso!!\nFecha actual: " +LocalDate.now());
        }

       int minutes = (int) ChronoUnit.MINUTES.between(appointment.getTime(),LocalTime.of(9,00)) * (-1);
       if(minutes > (60 * 12)  ){
         return   ResponseEntity.badRequest().body("Los turnos no pueden ser fuera de horario\nHorarios de 9hs a 21hs");
       }

        String dia = String.valueOf(appointment.getDate().getDayOfWeek());
        if(dia.equalsIgnoreCase("monday")){
           return ResponseEntity.badRequest().body("LOS DIAS LUNES NO ABRIMOS!!");
       }

        Customer customer = customerService.findById(id);
        if(appointmentService.existTurn(appointment.getDate(), appointment.getTime())){
            return ResponseEntity.badRequest().body("Turno ocupado elija otro\nTurnos disponibles del dia " + appointment.getDate() + " son : \n" + appointmentService.shirt(appointment.getDate() ));
        }
        emailService.sendWithAttach(customer.getEmail(), "Turno", emailService.Turno(customer.getFirst_name(), appointment.getDate(),appointment.getTime(), "turno"));
        customerService.addAppointment(id, appointment);
        return ResponseEntity.ok("TURNO AGREGADO CORRECTAMENTE");
    }
    @DeleteMapping("/customer/deleteAppointment/{id}/{idAppointment}")
    @ApiOperation("Eliminar un turno de un cliente")
    @ApiParam("id, idAppointment")
    public  ResponseEntity<String> deleteAppointment(@PathVariable Long id, @PathVariable Long idAppointment) throws FileNotFoundException {
        if(customerService.findById(id) == null){
            return ResponseEntity.badRequest().body("No existe el id");
        }
        Customer customer = customerService.findById(id);
        emailService.send("elianpareja5@gmail.com",customer.getEmail(), "Turno cancelado", emailService.Turno(customer.getFirst_name(), appointmentService.findById(idAppointment).get().getDate(),appointmentService.findById(idAppointment).get().getTime(), "cancelado"));
        customerService.deleteAppointment(id, idAppointment);
        return ResponseEntity.ok("EL turno: " + idAppointment + " del cliente: " + customerService.findById(id) + " Eliminado correctamente") ;
    }

    @GetMapping("/customer/sendMessage")
    public void sendMessage() throws MessagingException {
        customerService.sendMessage();
    }

}
