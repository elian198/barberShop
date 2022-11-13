package com.barbershop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailExist extends ResponseStatusException {
    public EmailExist(HttpStatus status, String  message) {
        super(status, message);
    }
}
