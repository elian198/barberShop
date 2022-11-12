package com.barbershop.entites;

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

    @ManyToOne()
    private Employee employee;

   @OneToMany(mappedBy = "customer")
   private Set<Appointment> listApoint = new HashSet<>();


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

    public Set<Appointment> getListApoint() {
        return listApoint;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setListApoint(Set<Appointment> listApoint) {
        this.listApoint = listApoint;
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
                ", employee=" + employee +
                ", listApoint=" + listApoint +
                '}';
    }
}
