package ua.kpi.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kpi.coursework.model.Position;
import ua.kpi.coursework.model.Speciality;
import ua.kpi.coursework.model.Vacancy;

import java.util.LinkedList;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findVacanciesByPositionAndSpeciality(Position position, Speciality speciality);

//    @Query("select v from Vacancy as v where v.resume.vacancy.id is null")
//    List<Vacancy> getFreeVacancies(@Param("position") Position position,
//                                   @Param("speciality") Speciality speciality);
}
