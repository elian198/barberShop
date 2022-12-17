package com.barbershop.DTO;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.sun.istack.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConvertDto {

    public ConvertDto() {
    }

    public CustomerDto convertirCustomeraDto(@NotNull Customer customer){
         CustomerDto dto  = new CustomerDto();
         dto.setFirst_name(customer.getFirst_name());
         dto.setLast_name(customer.getLast_name());
         dto.setEmail(customer.getEmail());
         dto.setAppointment(customer.getAppointment());
        return dto;
    }

    public List<AppointmentDto> convertirAppointmentDto ( @NotNull List<Appointment> appointment){
        List<AppointmentDto> appointmentDto = new ArrayList<>();
        Long restantes = 0L;

        for(Appointment list : appointment){
        if(list.getDate().compareTo(LocalDate.now()) >-1){
             restantes = ChronoUnit.DAYS.between( LocalDate.now(), list.getDate());

        }

            appointmentDto.add(new AppointmentDto(list.getId(), list.getDate(),restantes ));
        }

        return appointmentDto;

    }

    public List<Time> convertirTime ( @NotNull List<Appointment> appointment) {
        List<Time> appointmentTime = new ArrayList<>();


        for (Appointment list : appointment) {
            appointmentTime.add(new Time(list.getTime()));
        }


        return appointmentTime;
    }
}
