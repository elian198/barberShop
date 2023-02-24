package com.barbershop.controller;

import com.barbershop.DTO.ResetPasswordDto;
import com.barbershop.entites.Employee;
import com.barbershop.exception.MyException;
import com.barbershop.responseEntity.OutPutEntity;
import com.barbershop.security.jwt.JwtTokenUtil;
import com.barbershop.security.payload.JwtPayload;
import com.barbershop.security.payload.LoginPayload;
import com.barbershop.security.payload.RegisterPayload;
import com.barbershop.service.email.EmailService;
import com.barbershop.service.impl.EmployeeServiceImpl;
import com.barbershop.service.impl.ModuleServiceImpl;
import com.sun.istack.NotNull;
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

import static com.barbershop.util.MessageUtil.*;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    EmailService emailService;

    @Autowired
    private ModuleServiceImpl moduleService;

    private final AuthenticationManager authManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    private String codigo;
    private String email;

    public EmployeeController(EmployeeServiceImpl employeeService, AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil,
                              PasswordEncoder passwordEncoder, ModuleServiceImpl module) {
        this.employeeService = employeeService;
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.moduleService = module;
    }

    @PostMapping("/register")
    @ApiOperation("Crea un nuevo usuario")
    @ApiParam("RegisterPayload registerPayload")
    public ResponseEntity<?> save(@RequestBody @NotNull RegisterPayload registerPayload) throws MyException {
        OutPutEntity<String> out = new OutPutEntity<>();

    try {
        if (registerPayload.error()) {
           throw new MyException(NOTNULL.getKey(), NOTNULL.getCode());

        }

        if (employeeService.findByEmail(registerPayload.getEmail()) != null) {
           out.done(OK.getCode(), OK.getKey(), "El email ingresado ya existe");
            return new ResponseEntity(out, out.getStatus());
        }

    } catch (Exception e) {
        out.error();
        return new ResponseEntity(out, out.getStatus());
    }
        registerPayload.setPassword(passwordEncoder.encode(registerPayload.getPassword()));
        Employee employee = registerPayload.convertToEntities();
        employeeService.Save(employee);
        out = moduleService.insertModule(registerPayload);
        out.done(OK.getCode(), OK.getKey(), registerPayload.getFirst_name());
        return new ResponseEntity(out, out.getStatus());

    }
    @PostMapping("/login")
    @ApiOperation("Comprueba que exista el usuario enviado, si esta todo ok devulve un token, que es el que nos va a servir para navegar por las diferentes end points")
    @ApiParam("LoginPayload loginPayload")
    public ResponseEntity<JwtPayload> login(@RequestBody LoginPayload loginPayload) throws MessagingException {
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

        emailService.sendWithAttach( employee1.getEmail(), "RESETEO DE PASSWORD", emailService.codigo(employee1.getFirst_name(), codigo) );
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

