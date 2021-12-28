package ua.kpi.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.coursework.model.Position;
import ua.kpi.coursework.model.Speciality;
import ua.kpi.coursework.model.Vacancy;
import ua.kpi.coursework.repository.VacancyRepository;

import java.util.List;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository){
        this.vacancyRepository = vacancyRepository;
    }

    public Vacancy findById(Long id){
        return vacancyRepository.getById(id);
    }
    public List<Vacancy> findAll(){
        return vacancyRepository.findAll();
    }
    public Vacancy saveUnemployed(Vacancy vacancy){
        return vacancyRepository.save(vacancy);
    }
    public void deleteById(Long id){
        vacancyRepository.deleteById(id);
    }
    public List<Vacancy> findVacanciesByPositionAndSpeciality(Position position, Speciality speciality){
        return vacancyRepository.findVacanciesByPositionAndSpeciality(position, speciality);
    }
}
