package ua.kpi.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.coursework.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
