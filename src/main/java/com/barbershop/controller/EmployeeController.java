package com.barbershop.controller;

import com.barbershop.entites.Employee;
import com.barbershop.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<?> save(@RequestBody Employee employee){
        if(employee == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
        }
        employeeService.Save(employee);
        return ResponseEntity.ok("Usuario creado");
    }
    @PutMapping("/employee")
    public ResponseEntity<?> update(@RequestBody Employee employee){
        if(employeeService.findById(employee.getId()) == null){
            return ResponseEntity.badRequest().body("El id no se encontro");
        }
        employeeService.update(employee);
        return ResponseEntity.ok("  Usuario  " + employee.getEmail() + " Actualizado");
    }

    @DeleteMapping("/employee/{id}")
    public List<Employee> delete(@PathVariable Long id){
        if(employeeService.findById(id) == null){

        }
        return employeeService.delete(id);
    }
    @GetMapping("/employee")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
}
