package com.barbershop.controller;

import com.barbershop.entites.HairAssistance;
import com.barbershop.service.impl.HairAssistanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class HairAssistanceController {

    @Autowired
    HairAssistanceServiceImpl assistanceService;


    public HairAssistanceController(HairAssistanceServiceImpl assistanceService) {
        this.assistanceService = assistanceService;
    }

    @PostMapping("/hairAssistance")
    public ResponseEntity<?> save(@RequestBody HairAssistance hairAssistance) throws FileNotFoundException {
        if(hairAssistance == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
        }

        assistanceService.save(hairAssistance);
        return ResponseEntity.ok("Usuario creado");
    }
    @PutMapping("/hairAssistance")
    public ResponseEntity<?> update(@RequestBody HairAssistance hairAssistance) throws Exception {
        if(assistanceService.findById(hairAssistance.getId()) == null){
            return ResponseEntity.badRequest().body("El id no se encontro");
        }
        assistanceService.update(hairAssistance);
        return ResponseEntity.ok("  Usuario  " + hairAssistance.getId() + " Actualizado");
    }

    @DeleteMapping("/hairAssistance/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        if(assistanceService.findById(id) == null){
            return ResponseEntity.badRequest().body("El ID no puede ser NULL");
        }
        assistanceService.delete(id);
           return ResponseEntity.ok("Usuario eliminado");

    }
    @GetMapping("/hiarAssistance")
    public List<HairAssistance> findAll(){
        return assistanceService.findAll();
    }
}
