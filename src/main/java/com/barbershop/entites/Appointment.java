package com.barbershop.entites;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Appointment")
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "APPOINTMENT_TYPESERVICE",
            joinColumns = {
                    @JoinColumn(name = "id_APPOINTMENT")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_TYPESERVICE")})
    private Set<TypeService> typeService = new HashSet<>();


    @ManyToOne()
    private Customer customer;

    public Appointment() { }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<TypeService> getTypeService() {
        return typeService;
    }

    public void setTypeService(Set<TypeService> typeService) {
        this.typeService = typeService;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", typeService=" + typeService +
                ", customer=" + customer +
                '}';
    }
}