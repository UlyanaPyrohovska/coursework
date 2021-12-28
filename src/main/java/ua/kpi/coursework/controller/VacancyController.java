package ua.kpi.coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kpi.coursework.model.*;
import ua.kpi.coursework.service.CompanyService;
import ua.kpi.coursework.service.PositionService;
import ua.kpi.coursework.service.SpecialityService;
import ua.kpi.coursework.service.VacancyService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class VacancyController {
    private final VacancyService vacancyService;
    private final CompanyService companyService;
    private final PositionService positionService;
    private final SpecialityService specialityService;

    @Autowired
    public VacancyController(VacancyService vacancyService, CompanyService companyService, PositionService positionService, SpecialityService specialityService) {
        this.vacancyService = vacancyService;
        this.companyService = companyService;
        this.positionService = positionService;
        this.specialityService = specialityService;
    }

    @DeleteMapping("/vacancy-delete/{id}")
    public String deleteVacancy(@PathVariable("id") Long id){
        vacancyService.deleteById(id);
        return "redirect:/vacancies";
    }

    @GetMapping("/vacancy-update/{id}")
    public String getVacancyForm(@PathVariable("id") Long id, Model model){
        Vacancy vacancy = vacancyService.findById(id);
        List<Position> positions = positionService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        List<Company> companies = companyService.findAll();
        model.addAttribute("specialities", specialities);
        model.addAttribute("positions", positions);
        model.addAttribute("companies", companies);
        model.addAttribute("vacancy", vacancy);
        return "edit_vacancy";
    }

    @PostMapping("/vacancy-update")
    public String updateVacancy(@Valid Vacancy vacancy, BindingResult bindingResult,
                                @RequestParam Long position, @RequestParam Long company,
                                @RequestParam Long speciality, Model model){
        List<Position> positions = positionService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        List<Company> companies = companyService.findAll();
        model.addAttribute("specialities", specialities);
        model.addAttribute("positions", positions);
        model.addAttribute("companies", companies);
        Vacancy oldOne = vacancyService.findById(vacancy.getId());
        if(bindingResult.hasFieldErrors("salary")){
            model.addAttribute("vacancy", oldOne);
            return "edit_vacancy";
        }
        boolean err1 = false;
        boolean err3 = false;
        boolean err2 = false;
        if(position == 0 || speciality == 0 || company == 0){
            if(position == 0){
                err1 =true;
                model.addAttribute("err1", err1);
            }
            if(speciality == 0){
                err2 =true;
                model.addAttribute("err2", err2);
            }
            if(company == 0){
                err3 = true;
                model.addAttribute("err3", err3);
            }
            model.addAttribute("vacancy", oldOne);
            return "edit_vacancy";
        }
        vacancy.setCompany(companyService.findById(company));
        vacancy.setPosition(positionService.findById(position));
        vacancy.setSpeciality(specialityService.findById(speciality));
        vacancyService.saveUnemployed(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/add-vacancy")
    public String addVacancy(Model model){
        List<Position> positions = positionService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        List<Company> companies = companyService.findAll();
        model.addAttribute("specialities", specialities);
        model.addAttribute("positions", positions);
        model.addAttribute("companies", companies);
        model.addAttribute("vacancy", new Vacancy());
        return "add_vacancy";
    }

    @PostMapping("/create-vacancy")
    public String createVacancy(@Valid Vacancy vacancy, BindingResult bindingResult, @RequestParam Long position, @RequestParam Long company,
                                @RequestParam Long speciality, Model model){
        List<Position> positions = positionService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        List<Company> companies = companyService.findAll();
        if(bindingResult.hasFieldErrors("salary")){
            model.addAttribute("specialities", specialities);
            model.addAttribute("positions", positions);
            model.addAttribute("companies", companies);
            model.addAttribute("vacancy", vacancy);
            return "add_vacancy";
        }
        boolean err1 = false;
        boolean err3 = false;
        boolean err2 = false;
        if(position == 0 || speciality == 0 || company == 0){
            if(position == 0){
                err1 =true;
                model.addAttribute("err1", err1);
            }
            if(speciality == 0){
                err2 =true;
                model.addAttribute("err1", err2);
            }
            if(company == 0){
                err3 = true;
                model.addAttribute("err3", err3);
            }
            return "add_vacancy";
        }
        vacancy.setCompany(companyService.findById(company));
        vacancy.setPosition(positionService.findById(position));
        vacancy.setSpeciality(specialityService.findById(speciality));
        vacancyService.saveUnemployed(vacancy);
        return "redirect:/vacancies";
    }
}
