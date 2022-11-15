package com.barbershop.service.impl;

import com.barbershop.entites.HairAssistance;
import com.barbershop.repository.HairAssistanceRepository;
import com.barbershop.service.HairAssistanceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HairAssistanceServiceImpl implements HairAssistanceService {

    @Autowired
    private HairAssistanceRepository hairAssistanceRepository;

    public HairAssistanceServiceImpl(HairAssistanceRepository hairAssistanceRepository) {
        this.hairAssistanceRepository = hairAssistanceRepository;
    }


    @Override
    public List<HairAssistance> findAll() {
        return hairAssistanceRepository.findAll();
    }

    @Override
    public void save(HairAssistance hairAssistance) {
        hairAssistanceRepository.save(hairAssistance);

    }

    @Override
    public List<HairAssistance> delete(Long id) {
        if(hairAssistanceRepository.existsById(id)){
            hairAssistanceRepository.deleteById(id);
            return hairAssistanceRepository.findAll();
        }
        return null;
    }

    @Override
    public HairAssistance update(HairAssistance hairAssistance) {
        if(hairAssistanceRepository.existsById(hairAssistance.getId())){
            hairAssistanceRepository.save(hairAssistance);
        }

        return null;
    }
}
