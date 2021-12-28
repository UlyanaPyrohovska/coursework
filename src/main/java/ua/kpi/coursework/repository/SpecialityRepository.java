package ua.kpi.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.coursework.model.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}
