package com.barbershop.entites;

import com.barbershop.security.payload.RegisterPayload;
import com.barbershop.util.AuditorFieldsEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sgi_module")
public class Module extends AuditorFieldsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String last_name;

    @Column(name = "EMAIL")
    private  String email;

    @Column (name = "description")
    private String description;


    public Module() {
    }

    public Module(RegisterPayload registerPayload) {
    this.name = registerPayload.getFirst_name();
    this.description = "Modulo pertinente a la seguridad";
    this.last_name = registerPayload.getLast_name();
    this.email = registerPayload.getEmail();
    this.setEstado("A");
    this.setDateCreated(LocalDateTime.now());
    this.setIdEmployeeCreated(registerPayload.convertToEntities().getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
