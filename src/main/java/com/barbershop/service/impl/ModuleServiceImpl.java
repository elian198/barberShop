package com.barbershop.service.impl;
import com.barbershop.entites.Module;
import static com.barbershop.util.MessageUtil.*;
import com.barbershop.repository.ModuleRepositoty;
import com.barbershop.responseEntity.OutPutEntity;
import com.barbershop.security.payload.RegisterPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl {

    @Autowired
    private ModuleRepositoty moduleRepositoty;

    public ModuleServiceImpl(ModuleRepositoty moduleRepositoty) {
        this.moduleRepositoty = moduleRepositoty;
    }

    public OutPutEntity<String> insertModule(RegisterPayload registerPayload){
        OutPutEntity<String> out = new OutPutEntity<>();

        try{
            Module module = new Module(registerPayload);
            moduleRepositoty.save(module);

            return out.done(CREATED.getCode(), CREATED.getKey(), null);
        }catch (DataIntegrityViolationException e){
            return out.failed(BADREQUEST.getCode(), e.getMessage(),null);
        }catch (Exception e){
          return   out.error();

        }
    }
}
