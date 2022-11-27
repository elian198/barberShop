package com.barbershop.service.impl;

import com.barbershop.entites.Customer;
import com.barbershop.entites.Employee;
import com.barbershop.exception.EmployeeNoExists;
import com.barbershop.repository.EmployeeRepository;
import com.barbershop.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeService , UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findByEmail(String name) {
        if(employeeRepository.findByEmail(name) == null){
            return null;
        }
        return employeeRepository.findByEmail(name);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        if(employeeRepository.existsById(id)){
            return employeeRepository.findById(id);
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void Save(Employee employee)  {
        if(employeeRepository.findByEmail(employee.getFirst_name()) != null){
            /***
             * todo falta crear la exceciones de email
             */

          //throw new EmailExistEmployee();
        }
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> delete(Long id) {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return employeeRepository.findAll();
        }
        //throw  new ;
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        if(employeeRepository.existsById(employee.getId())){
            employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public void addCustomer(Long id, Customer customer) {
      if(employeeRepository.existsById(id)){
         Employee employee = employeeRepository.findById(id).get();

      }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws EmployeeNoExists {
        com.barbershop.entites.Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new EmployeeNoExists(HttpStatus.NOT_FOUND, " El usuario no existe!!!"));

        return new org.springframework.security.core.userdetails.User(
                employee.getFirst_name(), employee.getPassword(),new ArrayList<>());
    }
}
