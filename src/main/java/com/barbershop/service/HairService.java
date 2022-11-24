package com.barbershop.service;

import com.barbershop.entites.TypeService;

import java.io.FileNotFoundException;
import java.util.List;

public interface HairService {

     void save(TypeService hairAssistance) throws FileNotFoundException;
     void delete(Long id) throws Exception;
     TypeService update(TypeService hairAssistance) throws Exception;
     List<TypeService> findAll();
     TypeService findById(Long id) throws FileNotFoundException;
}
