package com.barbershop.service.impl;

import com.barbershop.DTO.AppointmentDto;
import com.barbershop.DTO.ConvertDto;
import com.barbershop.DTO.Time;
import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.barbershop.entites.TypeService;
import com.barbershop.repository.AppointmentRepository;
import com.barbershop.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

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

    public List<Time> findByLocalDate(LocalDate localDate){
        ConvertDto convertDto =  new ConvertDto();
        if(appointmentRepository.findByLocalDate(localDate) == null){
            return null;
        }

        return  convertDto.convertirTime(appointmentRepository.findByLocalDate(localDate));
    }


       public boolean existTurn(LocalDate localDate, LocalTime localTime){

        if(appointmentRepository.existTurn(localDate, localTime) != null){
            return true;
        }
        return false;
       }
     public List<LocalTime> availableTurn(){

        List<LocalTime> list =  new ArrayList<>();

        for(int i = 9; i <=21; i++){
            for(int m = 0; m <= 30; m += 30) {
                if (i == 21 && m == 30) {
                   break;

                }
                list.add(LocalTime.of(i, m));

            }
         }
       return list;

            }

            public TreeSet<LocalTime> shirt(LocalDate localDate){
             List<LocalTime> list = availableTurn();
               for(Time listTime : findByLocalDate(localDate)){
                   list.remove(listTime.getTime());
                  }

                TreeSet<LocalTime> treeSet = new TreeSet<LocalTime>(list);
                return treeSet;
            }
         }


