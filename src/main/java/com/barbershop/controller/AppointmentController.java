package com.barbershop.controller;

import com.barbershop.DTO.AppointmentDto;
import com.barbershop.entites.Appointment;
import com.barbershop.entites.Employee;
import com.barbershop.entites.TypeService;
import com.barbershop.service.AppointmentService;
import com.barbershop.service.impl.AppointmentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointment")
    @ApiOperation("Agrega un turno")
    @ApiParam("Appointment")
    public ResponseEntity<?> save(@RequestBody Appointment appointment){
        if(appointment == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
        }
        appointmentService.save(appointment);
        return ResponseEntity.ok("TURNO AGREGADO");
    }
    @PutMapping("/appointment")
    @ApiOperation("Actualiza un turno")
    @ApiParam("Appointment appointment")
    public ResponseEntity<?> update(@RequestBody Appointment appointment) throws Exception {
        if(appointmentService.findById(appointment.getId()) == null){
            return ResponseEntity.badRequest().body("El id no se encontro");
        }
        appointmentService.update(appointment);
        return ResponseEntity.ok("  Turno" + appointment.getId() + " Actualizado");
    }

    @DeleteMapping("/appointment/{id}")
    @ApiOperation("Borra un turno")
    @ApiParam("Long id")
    public void delete(@PathVariable Long id) throws Exception {
        if(appointmentService.findById(id) == null){

        }
        appointmentService.delete(id);
    }
    @GetMapping("/appointment")
    @ApiOperation("Muestra todos los turnos")
    public List<Appointment> findAll(){
        return appointmentService.findAll();
    }

    @PostMapping("/appointment/{id}")
    @ApiOperation("Agrega un servicio a un turno")
    @ApiParam("Long id,  TypeService typeService")
    public ResponseEntity<?> addTypeService(@PathVariable Long id, @RequestBody TypeService typeService){
        if(appointmentService.findById(id) == null){
            return ResponseEntity.badRequest().body("No se encontro el id ingresado");
        }
        appointmentService.addTypeService(id, typeService);
        return ResponseEntity.ok("Servicio agregado");
    }

    @DeleteMapping("/appointment/deleteTypeService/{id}/{idRemove}")
    @ApiOperation("borra el servicio de un turno")
    @ApiParam(" Long id,   Long idRemove")
    public ResponseEntity<?> deleteTypeService(@PathVariable Long id, @PathVariable Long idRemove) throws FileNotFoundException {
        if(appointmentService.findById(id) == null){
            return ResponseEntity.badRequest().body("No se encontro el id ingresado");
        }
        appointmentService.deleteTypeService(id, idRemove);
        return ResponseEntity.ok("Servicio eliminado");
    }

    @GetMapping("/appointment/precioFinal/{id}")
    @ApiOperation("Muestra el total a pagar de un turno de un cliente")
    @ApiParam("id")
    public String precioTotal(@PathVariable Long id){

        return "El precio total es : " + appointmentService.total(id);
    }



    @GetMapping("/appointment/turnos")
    @ApiOperation("Muestra la fecha del turno y cuantos dias faltan")
    public List<AppointmentDto> diasParaTurnos(){

        return appointmentService.turnos();
    }

}
