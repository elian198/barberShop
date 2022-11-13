package com.barbershop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoExist extends ResponseStatusException {
    public NoExist(HttpStatus status, String  message) {
        super(status, message);

        }
    }