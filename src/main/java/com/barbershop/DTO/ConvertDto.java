package com.barbershop.DTO;

import com.barbershop.entites.Appointment;
import com.barbershop.entites.Customer;
import com.sun.istack.NotNull;

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


}
