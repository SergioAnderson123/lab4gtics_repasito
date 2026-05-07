package repository;

import entity.ClinicDoctor;
import entity.ClinicDoctorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicDoctorRepository extends JpaRepository<ClinicDoctor, ClinicDoctorId> {
}