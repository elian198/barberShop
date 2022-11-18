package com.barbershop.service;

import com.barbershop.entites.HairAssistance;

import java.io.FileNotFoundException;
import java.util.List;

public interface HairService {

     void save(HairAssistance hairAssistance) throws FileNotFoundException;
     void delete(Long id) throws Exception;
     HairAssistance update(HairAssistance hairAssistance) throws Exception;
     List<HairAssistance> findAll();
     HairAssistance findById(Long id) throws FileNotFoundException;
}
