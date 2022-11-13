package com.barbershop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PhoneExist extends ResponseStatusException {
    public PhoneExist(HttpStatus status, String  message) {
        super(status, message);
    }
    }