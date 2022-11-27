package com.barbershop.service.impl;

import com.barbershop.DTO.AppointmentDto;
import com.barbershop.DTO.ConvertDto;
import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.entites.TypeService;
import com.barbershop.repository.AppointmentRepository;
import com.barbershop.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TypeServiceImpl typeService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Customer findByIdCustomer(Long idCustomer) throws Exception {
        if(appointmentRepository.findByIdCustomer(idCustomer) != null){
            throw new Exception("error el id no existe");
        }
        return appointmentRepository.findByIdCustomer(idCustomer);
    }

    @Override
    public List<Appointment> orderByDuration() {
        if(appointmentRepository.orderByDuration() != null){
            return appointmentRepository.orderByDuration();
        }

            return null ;
    }

    @Override
    public Optional<Appointment> findById(Long id)  throws ArithmeticException{
        if(id == null){
            throw new ArithmeticException();
        }
        return appointmentRepository.findById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        if(appointmentRepository.existsById(id)){
            appointmentRepository.deleteById(id);
        }
        throw new Exception("No se encontro el id");
    }

    @Override
    public List<AppointmentDto> turnos() {

        ConvertDto convertDto = new ConvertDto();
        return  convertDto.convertirAppointmentDto(appointmentRepository.orderByLocalDate());

    }

    @Override
    public void addTypeService(Long id, TypeService typeService) {
        if(appointmentRepository.existsById(id)){
          Appointment appointment =   appointmentRepository.findById(id).get();
          appointment.getTypeService().add(typeService);
          appointmentRepository.save(appointment);
        }
    }



    public void deleteTypeService(Long id, Long idRemove) throws FileNotFoundException {
        if(appointmentRepository.existsById(id)){
            Appointment appointment =   appointmentRepository.findById(id).get();
            appointment.getTypeService().remove(typeService.findById(idRemove));
            appointmentRepository.save(appointment);
        }
    }


    public Appointment update(Appointment appointment) throws Exception {
        if(appointmentRepository.findById(appointment.getId()) !=null){
            appointmentRepository.save(appointment);
        }
        throw new Exception("No se encontro el usuario");
    }

    public void save(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public Double total (Long id){
      Appointment  appointment =  appointmentRepository.findById(id).get();
      Double total = 0.0;
      for(TypeService preciofinal : appointment.getTypeService()){
        total += preciofinal.getPrice();

      }
      return total;

    }

}
