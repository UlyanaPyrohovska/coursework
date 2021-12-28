package ua.kpi.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.coursework.model.Unemployed;
import ua.kpi.coursework.repository.UnemployedRepository;

import java.util.List;
@Service
public class UnemployedService {

    private final UnemployedRepository unemployedRepository;

    @Autowired
    public UnemployedService(UnemployedRepository unemployedRepository){
        this.unemployedRepository = unemployedRepository;
    }

    public Unemployed findById(Long id){
        return unemployedRepository.getById(id);
    }
    public List<Unemployed> findAll(){
        return unemployedRepository.findAll();
    }
    public Unemployed saveUnemployed(Unemployed unemployed){
        return unemployedRepository.save(unemployed);
    }
    public void deleteById(Long id){
        unemployedRepository.deleteById(id);
    }

}
