package com.barbershop.entites;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String first_name;

    @Column(name = "LAST_NAME")
    private String last_name;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PHONE", unique = true)
    private Integer phone;

    @Column(name = "BIRTH_NAME")
    private Date birth_name;
;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTOMER_APPOINTMENT",
            joinColumns = {
                    @JoinColumn(name = "id_CUSTOMER")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_APPOINTMENT") })
    private Set<Appointment> appointment = new HashSet<>();

    public Customer() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Date getBirth_name() {
        return birth_name;
    }

    public void setBirth_name(Date birth_name) {
        this.birth_name = birth_name;
    }


    public Set<Appointment> getAppointment() {
        return appointment;
    }
    public void setAppointment(Set<Appointment> appointment) {
        this.appointment = appointment;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", birth_name=" + birth_name +
                ", appointment=" + appointment +
                '}';
    }
}
