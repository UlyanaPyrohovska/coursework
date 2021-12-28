package ua.kpi.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kpi.coursework.model.Unemployed;

import java.util.List;

public interface UnemployedRepository extends JpaRepository<Unemployed, Long> {
}
