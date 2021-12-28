package ua.kpi.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.coursework.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
