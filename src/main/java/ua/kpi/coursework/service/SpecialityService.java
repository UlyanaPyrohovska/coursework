package ua.kpi.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ua.kpi.coursework.model.Speciality;
import ua.kpi.coursework.repository.SpecialityRepository;

import java.util.List;
@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public Speciality findById(Long id){
        return specialityRepository.getById(id);
    }
    public List<Speciality> findAll(){
        return specialityRepository.findAll();
    }
    public Speciality saveUnemployed(Speciality position){
        return specialityRepository.save(position);
    }
    public void deleteById(Long id){
        specialityRepository.deleteById(id);
    }
}
