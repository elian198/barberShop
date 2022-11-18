package com.barbershop.controller;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Employee;
import com.barbershop.service.AppointmentService;
import com.barbershop.service.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointment")
    public ResponseEntity<?> save(@RequestBody Appointment appointment){
        if(appointment == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
        }
        appointmentService.save(appointment);
        return ResponseEntity.ok("Usuario creado");
    }
    @PutMapping("/appointment")
    public ResponseEntity<?> update(@RequestBody Appointment appointment) throws Exception {
        if(appointmentService.findById(appointment.getId()) == null){
            return ResponseEntity.badRequest().body("El id no se encontro");
        }
        appointmentService.update(appointment);
        return ResponseEntity.ok("  Usuario " + appointment.getId() + " Actualizado");
    }

    @DeleteMapping("/appointment/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        if(appointmentService.findById(id) == null){

        }
        appointmentService.delete(id);
    }
    @GetMapping("/appointment")
    public List<Appointment> findAll(){
        return appointmentService.findAll();
    }
}
