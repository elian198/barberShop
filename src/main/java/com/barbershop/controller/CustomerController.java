package com.barbershop.controller;


import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.service.impl.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    @ApiOperation("Crea un nuevo cliente")
    @ApiParam("Customer")
    public ResponseEntity<?> save(@RequestBody Customer customer){
        if(customer == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
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
    public ResponseEntity<String> addAppointment(@PathVariable Long id , @RequestBody Appointment appointment) throws FileNotFoundException {

        if(customerService.findById(id) == null){
            return ResponseEntity.badRequest().body("El id  no existe");
        }
        customerService.addAppointment(id, appointment);
        return ResponseEntity.ok("TURNO " + appointment.getId() + " AGREGADO CORRECTAMENTE");
    }
    @DeleteMapping("/customer/deleteAppointment/{id}/{idAppointment}")
    @ApiOperation("Eliminar un turno de un cliente")
    @ApiParam("id, idAppointment")
    public  ResponseEntity<String> deleteAppointment(@PathVariable Long id, @PathVariable Long idAppointment) throws FileNotFoundException {
        if(customerService.findById(id) == null){
            return ResponseEntity.badRequest().body("No existe el id");
        }
        customerService.deleteAppointment(id, idAppointment);
        return ResponseEntity.ok("EL turno: " + idAppointment + " del cliente: " + customerService.findById(id) + " Eliminado correctamente") ;
    }


}
