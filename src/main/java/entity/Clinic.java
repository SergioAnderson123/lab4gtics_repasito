package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 150)
    @NotNull
    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @Size(max = 9)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 9)
    private String phoneNumber;


}