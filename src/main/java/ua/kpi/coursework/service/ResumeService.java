package ua.kpi.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.coursework.model.Resume;
import ua.kpi.coursework.repository.ResumeRepository;

import java.util.List;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume findById(Long id){
        return resumeRepository.getById(id);
    }
    public List<Resume> findAll(){
        return resumeRepository.findAll();
    }
    public Resume saveUnemployed(Resume resume){
        return resumeRepository.save(resume);
    }
    public void deleteById(Long id){
        resumeRepository.deleteById(id);
    }

}
