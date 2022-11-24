package com.barbershop.entites;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Apointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LOCAL_DATE")
    private LocalDate date;

    @Column(name = "DURATION")
    private Integer duration;


    @ManyToOne()
    private HairAssistance hairAssistance;


    @ManyToOne()
    private Customer customer;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public HairAssistance getHairAssistance() {
        return hairAssistance;
    }

    public void setHairAssistance(HairAssistance hairAssistance) {
        this.hairAssistance = hairAssistance;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", hairAssistance=" + hairAssistance +
                '}';
    }
}
