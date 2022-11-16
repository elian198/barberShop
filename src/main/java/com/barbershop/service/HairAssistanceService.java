package com.barbershop.service;

import com.barbershop.entites.HairAssistance;

import java.util.List;

public interface HairAssistanceService {

    List<HairAssistance> findAll();
    void save(HairAssistance hairAssistance);
    List<HairAssistance> delete(Long id);
    HairAssistance update(HairAssistance hairAssistance);
}
