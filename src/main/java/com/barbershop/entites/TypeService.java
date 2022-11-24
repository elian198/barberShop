package com.barbershop.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "typeService")
public class TypeService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Double price;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "TYPESERVICE_APPOINTMENT",
            joinColumns = {
                    @JoinColumn(name = "id_TYPESERVICE")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_APPOINTMENT")})
    private Set<TypeService> appointment = new HashSet<>();

    public TypeService() { }

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



    public Set<TypeService> getAppointment() {
        return appointment;
    }

    public void setAppointment(Set<TypeService> appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "TypeService{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", appointment=" + appointment +
                '}';
    }
}
