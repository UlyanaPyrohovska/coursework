package ua.kpi.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.coursework.model.Position;
import ua.kpi.coursework.repository.PositionRepository;

import java.util.List;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position findById(Long id){
        return positionRepository.getById(id);
    }
    public List<Position> findAll(){
        return positionRepository.findAll();
    }
    public Position saveUnemployed(Position position){
        return positionRepository.save(position);
    }
    public void deleteById(Long id){
        positionRepository.deleteById(id);
    }
}
