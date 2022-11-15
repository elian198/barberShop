package com.barbershop.controller;

import com.barbershop.entites.Employee;
import com.barbershop.service.EmployeService;
import com.barbershop.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.badRequest().body("No puede enviar campos vacios");
        }
        return ResponseEntity.ok("Usuario creado");
    }
}
