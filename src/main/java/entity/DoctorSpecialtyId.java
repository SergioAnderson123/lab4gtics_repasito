package entity;

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
public class DoctorSpecialtyId implements Serializable {
    private static final long serialVersionUID = -6822051643744250796L;
    @NotNull
    @Column(name = "doctor_id", nullable = false)
    private Integer doctorId;

    @NotNull
    @Column(name = "specialty_id", nullable = false)
    private Integer specialtyId;


}