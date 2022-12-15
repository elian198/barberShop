package com.barbershop.controller;

import com.barbershop.DTO.ResetPasswordDto;
import com.barbershop.entites.Employee;
import com.barbershop.security.jwt.JwtTokenUtil;
import com.barbershop.security.payload.JwtPayload;
import com.barbershop.security.payload.LoginPayload;
import com.barbershop.security.payload.RegisterPayload;
import com.barbershop.service.email.EmailService;
import com.barbershop.service.impl.EmployeeServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    EmailService emailService;

    private final AuthenticationManager authManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    private String codigo;
    private String email;

    public EmployeeController(EmployeeServiceImpl employeeService, AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @ApiOperation("Crea un nuevo usuario")
    @ApiParam("RegisterPayload registerPayload")
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
    @ApiOperation("Comprueba que exista el usuario enviado, si esta todo ok devulve un token, que es el que nos va a servir para navegar por las diferentes end points")
    @ApiParam("LoginPayload loginPayload")
    public ResponseEntity<JwtPayload> login(@RequestBody LoginPayload loginPayload){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginPayload.getFirst_name(), loginPayload.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtPayload(jwt));
    }

    @DeleteMapping("/employee/{id}")
    @ApiOperation("Elimina un usuario")
    @ApiParam("Long id")
    public List<Employee> delete(@PathVariable Long id){
        if(employeeService.findById(id) == null){

        }
        return employeeService.delete(id);
    }
    @GetMapping("/employee")
   public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @PostMapping("/login/password/{email}")
    public ResponseEntity forguetPassowrd(@PathVariable String email) throws MessagingException {

        if(employeeService.findByEmail(email) == null){
            return ResponseEntity.badRequest().body("El email ingresado no existe");
        }
        Employee employee1 = employeeService.findByEmail(email);
        this.email = email;
        codigo = employeeService.aleatorio();

        emailService.sendWithAttach("elianpareja5@gmail.com", employee1.getEmail(), "RESETEO DE PASSWORD", emailService.codigo(employee1.getFirst_name(), codigo) );
        return ResponseEntity.ok("Codigo enviado a su email");
    }

    @PostMapping("/login/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){

        if(resetPasswordDto.getPassword().equalsIgnoreCase(resetPasswordDto.getRepeatPassword())) {
            if (resetPasswordDto.getCodigo().equalsIgnoreCase(codigo)) {
                Employee employee = employeeService.findByEmail(email);
                employee.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
                employeeService.update(employee);
            } else {
                return ResponseEntity.badRequest().body("El codigo ingresado no es correcto!!");
            }
            return ResponseEntity.ok("Contraseña cambiada");
        }
            return ResponseEntity.badRequest().body("Las contraseñas no coinciden!!");


    }

}

