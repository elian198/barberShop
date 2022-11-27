package com.barbershop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeNoExists  extends ResponseStatusException {

    public EmployeeNoExists(HttpStatus status, String  message) {
        super(status, message);
    }
}
