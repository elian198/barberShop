package com.barbershop.controller;

import com.barbershop.entites.TypeService;
import com.barbershop.service.impl.TypeServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class TypeServiceController {

    @Autowired
    TypeServiceImpl typeServiceimpl;


    public TypeServiceController(TypeServiceImpl typeService) {
        this.typeServiceimpl= typeService;
    }

    @PostMapping("/typeService")
    @ApiOperation("Agrega servicio")
    @ApiParam("TypeService typeservice")
    public ResponseEntity<?> save(@RequestBody TypeService typeService) throws FileNotFoundException {
        if(typeService == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
        }

        typeServiceimpl.save(typeService);
        return ResponseEntity.ok("Usuario creado");
    }
    @PutMapping("/typeService")
    @ApiOperation("Actualiza un servicio")
    @ApiParam("TypeService typeservice")
    public ResponseEntity<?> update(@RequestBody TypeService  typeService) throws Exception {
        if(typeServiceimpl.findById(typeService.getId()) == null){
            return ResponseEntity.badRequest().body("El id no se encontro");
        }
        typeServiceimpl.update(typeService);
        return ResponseEntity.ok("  Service  " + typeService.getDescription() + " Actualizado");
    }

    @DeleteMapping("/typeService/{id}")
    @ApiOperation("Borra un servicio")
    @ApiParam("Long id")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        if(typeServiceimpl.findById(id) == null){
            return ResponseEntity.badRequest().body("El ID no puede ser NULL");
        }
        typeServiceimpl.delete(id);
           return ResponseEntity.ok("Service eliminado");

    }
    @GetMapping("/typeService")
    @ApiOperation("Muestra los servicios")
    public List<TypeService> findAll(){
        return typeServiceimpl.findAll();
    }

    @GetMapping("/total")
    public double total(){
        return typeServiceimpl.total();
    }
}
