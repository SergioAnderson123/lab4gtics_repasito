package repository;

import entity.DoctorSpecialty;
import entity.DoctorSpecialtyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorSpecialtyRepository extends JpaRepository<DoctorSpecialty, DoctorSpecialtyId> {
}