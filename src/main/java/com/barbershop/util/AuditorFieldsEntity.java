package com.barbershop.util;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class AuditorFieldsEntity {

    @Column(name = "id_employee_created")
    private  Long idEmployeeCreated;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "id_employee_update")
    private  Long idEmployeeUpdate;

    @Column(name = "date_Update")
    private LocalDateTime dateUpdate;

    @Column(name = "id_employee_delete")
    private  Long idEmployeeDelete;

    @Column(name = "date_delete", nullable = true)
    private LocalDateTime dateDelete;

    @Column(name = "status")
    private  String estado;

    public Long getIdEmployeeCreated() {
        return idEmployeeCreated;
    }

    public void setIdEmployeeCreated(Long idEmployeeCreated) {
        this.idEmployeeCreated = idEmployeeCreated;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getIdEmployeeUpdate() {
        return idEmployeeUpdate;
    }

    public void setIdEmployeeUpdate(Long idEmployeeUpdate) {
        this.idEmployeeUpdate = idEmployeeUpdate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Long getIdEmployeeDelete() {
        return idEmployeeDelete;
    }

    public void setIdEmployeeDelete(Long idEmployeeDelete) {
        this.idEmployeeDelete = idEmployeeDelete;
    }

    public LocalDateTime getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(LocalDateTime dateDelete) {
        this.dateDelete = dateDelete;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
