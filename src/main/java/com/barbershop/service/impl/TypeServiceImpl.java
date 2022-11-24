package com.barbershop.service.impl;

import com.barbershop.entites.TypeService;
import com.barbershop.repository.TypeServiceRepository;
import com.barbershop.service.HairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class TypeServiceImpl implements HairService {

    @Autowired
    private TypeServiceRepository typeServiceRepository;

    public TypeServiceImpl(TypeServiceRepository typeServiceRepository) {
        this.typeServiceRepository = typeServiceRepository;
    }

    @Override
    public void save(TypeService hairAssistance) throws FileNotFoundException {
        if(!typeServiceRepository.existsById(hairAssistance.getId())){
            typeServiceRepository.save(hairAssistance);
        }
        throw new FileNotFoundException("El id ya existe");
    }

    @Override
    public void delete(Long id) throws Exception {
     if(!typeServiceRepository.existsById(id)){
         throw new Exception("El id ingresado no existe!!");
     }
    }

    @Override
    public TypeService update(TypeService hairAssistance) throws Exception {
        if(typeServiceRepository.existsById(hairAssistance.getId())){
            typeServiceRepository.save(hairAssistance);
            return hairAssistance;
        }
        throw new Exception("No se pudo actualizar porque el ID no existe");
    }

    @Override
    public List<TypeService> findAll() {
        return typeServiceRepository.findAll();
    }

    @Override
    public TypeService findById(Long id) throws FileNotFoundException {
        if(typeServiceRepository.existsById(id)){
            return typeServiceRepository.findById(id).get();
        }
        throw new FileNotFoundException("No se encontro el ID");
    }
}
