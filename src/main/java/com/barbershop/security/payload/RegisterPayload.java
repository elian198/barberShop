package com.barbershop.security.payload;

import com.barbershop.entites.Employee;

public class RegisterPayload {

    private String first_name;

    private String last_name;
    private String email;
    private String password;

    public RegisterPayload(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public RegisterPayload() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Employee convertToEntities() {
        Employee employee = new Employee();
        employee.setFirst_name(first_name);
        employee.setLast_name(last_name);
        employee.setEmail(email);
        employee.setPassword(password);
        return employee;
    }

    public Boolean error() {
        if (getFirst_name().length() == 0 || getLast_name().length() == 0 || getEmail().length() == 0 || getPassword().length() == 0){

            return true;
           }
        return false;
}
}


