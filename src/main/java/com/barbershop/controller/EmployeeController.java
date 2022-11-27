package com.barbershop.controller;

import com.barbershop.entites.Employee;
import com.barbershop.security.jwt.JwtTokenUtil;
import com.barbershop.security.payload.JwtPayload;
import com.barbershop.security.payload.LoginPayload;
import com.barbershop.security.payload.RegisterPayload;
import com.barbershop.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    private final AuthenticationManager authManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    public EmployeeController(EmployeeServiceImpl employeeService, AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody RegisterPayload registerPayload){
        if(registerPayload == null){
            return ResponseEntity.badRequest().body("No se puede enviar campos vacios");
        }


        if(employeeService.findByEmail(registerPayload.getEmail())!= null){
            return ResponseEntity.badRequest().body("EL email ya existe");
        }
        registerPayload.setPassword(passwordEncoder.encode(registerPayload.getPassword()));
        Employee employee = registerPayload.convertToEntities();
        employeeService.Save(employee);
        return ResponseEntity.ok("Usuario creado");
    }
    @PostMapping("/login")
    public ResponseEntity<JwtPayload> update(@RequestBody LoginPayload loginPayload){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginPayload.getFirst_name(), loginPayload.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtPayload(jwt));
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

    @GetMapping("/inicio")
    public  String inicio(){
        return "INICIO ";
    }
}

