package ua.kpi.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.coursework.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
