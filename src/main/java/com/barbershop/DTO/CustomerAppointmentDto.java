package com.barbershop.DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerAppointmentDto {

    private Long id;
    private  String email;

    List<AppointmentDto> appointmentDtos = new ArrayList();

    public CustomerAppointmentDto() {}

    public CustomerAppointmentDto(Long id, String email, List<AppointmentDto> appointmentDtos) {
        this.id = id;
        this.email = email;
        this.appointmentDtos = appointmentDtos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AppointmentDto> getAppointmentDtos() {
        return appointmentDtos;
    }

    public void setAppointmentDtos(List<AppointmentDto> appointmentDtos) {
        this.appointmentDtos = appointmentDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CustomerAppointmentDto{" +
                "email='" + email + '\'' +
                ", appointmentDtos=" + appointmentDtos +
                '}';
    }
}
