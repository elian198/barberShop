package com.barbershop.controller;


import com.barbershop.DTO.CustomerDto;
import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<?> save(@RequestBody Customer customer){
        if(customer == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
        }
        customerService.save(customer);
        return ResponseEntity.ok("Cliente creado");
    }
    @PutMapping("/customer")
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
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping( value = "/customer/appoint", params = "email")
    public ResponseEntity<?> findAppoint(@RequestParam String email){

        return ResponseEntity.ok(  customerService.findByEmail(email));
    }
    @PostMapping("/customer/addAppointment/{id}")
    public void addAppointment(@PathVariable Long id , @RequestBody Appointment appointment){
        customerService.addAppointment(id, appointment);
    }

    @GetMapping("/customer/appoint/{id}")
    public CustomerDto mostrarFindById(@PathVariable Long id){

        return customerService.lookAppointMent(id);
    }
}
