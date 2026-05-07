package repository;

import entity.DoctorState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorStateRepository extends JpaRepository<DoctorState, Integer> {
}