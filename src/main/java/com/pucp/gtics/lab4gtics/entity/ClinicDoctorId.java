package com.pucp.gtics.lab4gtics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ClinicDoctorId implements Serializable {

    private static final long serialVersionUID = -2318266912390436150L;
    @NotNull
    @Column(name = "clinic_id", nullable = false)
    private Integer clinicId;

    @NotNull
    @Column(name = "doctor_id", nullable = false)
    private Integer doctorId;


}