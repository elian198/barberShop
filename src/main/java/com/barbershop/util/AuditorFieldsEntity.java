package com.barbershop.util;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class AuditorFieldsEntity {

    @Column(name = "id_employee_created", nullable = false)
    private  Long idEmployeeCreated;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "id_employee_update", nullable = false)
    private  Long idEmployeeUpdate;

    @Column(name = "date_Update", nullable = false)
    private LocalDateTime dateUpdate;

    @Column(name = "id_employee_delete", nullable = false)
    private  Long idEmployeeDelete;

    @Column(name = "date_Update", nullable = false)
    private LocalDateTime dateDelete;

    @Column(name = "status", nullable = true)
    private  String estado;

}
