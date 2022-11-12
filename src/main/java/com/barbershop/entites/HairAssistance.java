package com.barbershop.entites;

import javax.persistence.*;

@Entity
@Table(name = "HairAssistances")
public class HairAssistance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Double price;

   @ManyToOne
    private Appointment appointments;

    public HairAssistance() { }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Appointment getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "HairAssistance{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", appointments=" + appointments +
                '}';
    }
}
