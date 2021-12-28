package ua.kpi.coursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.coursework.model.Company;
import ua.kpi.coursework.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findById(Long id){
        return companyRepository.getById(id);
    }
    public List<Company> findAll(){
        return companyRepository.findAll();
    }
    public Company saveUnemployed(Company company){
        return companyRepository.save(company);
    }
    public void deleteById(Long id){
        companyRepository.deleteById(id);
    }
}
