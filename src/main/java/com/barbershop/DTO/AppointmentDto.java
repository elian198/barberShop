package com.barbershop.DTO;

import com.barbershop.entites.Appointment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AppointmentDto {

    private Long id;
    private LocalDate date;

    private Long diasRestantes;




    public AppointmentDto() {
    }


    public AppointmentDto(Long id, LocalDate date, Long diasRestantes) {
        this.id = id;
        this.date = date;
        this.diasRestantes = diasRestantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(Long diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "id=" + id +
                ", date=" + date +
                ", diasRestantes=" + diasRestantes +
                '}';
    }
}
