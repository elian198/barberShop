package com.barbershop.service.impl;

import com.barbershop.entites.HairAssistance;
import com.barbershop.repository.HairAssistanceRepository;
import com.barbershop.service.HairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class HairAssistanceServiceImpl implements HairService {

    @Autowired
    private HairAssistanceRepository hairAssistanceRepository;

    public HairAssistanceServiceImpl(HairAssistanceRepository hairAssistanceRepository) {
        this.hairAssistanceRepository = hairAssistanceRepository;
    }

    @Override
    public void save(HairAssistance hairAssistance) throws FileNotFoundException {
        if(!hairAssistanceRepository.existsById(hairAssistance.getId())){
            hairAssistanceRepository.save(hairAssistance);
        }
        throw new FileNotFoundException("El id ya existe");
    }

    @Override
    public void delete(Long id) throws Exception {
     if(!hairAssistanceRepository.existsById(id)){
         throw new Exception("El id ingresado no existe!!");
     }
    }

    @Override
    public HairAssistance update(HairAssistance hairAssistance) throws Exception {
        if(hairAssistanceRepository.existsById(hairAssistance.getId())){
            hairAssistanceRepository.save(hairAssistance);
            return hairAssistance;
        }
        throw new Exception("No se pudo actualizar porque el ID no existe");
    }

    @Override
    public List<HairAssistance> findAll() {
        return hairAssistanceRepository.findAll();
    }

    @Override
    public HairAssistance findById(Long id) throws FileNotFoundException {
        if(hairAssistanceRepository.existsById(id)){
            return hairAssistanceRepository.findById(id).get();
        }
        throw new FileNotFoundException("No se encontro el ID");
    }
}
