package ua.kpi.coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kpi.coursework.model.*;
import ua.kpi.coursework.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    private final UnemployedService unemployedService;
    private final PositionService positionService;
    private final CompanyService companyService;
    private final SpecialityService specialityService;
    private final VacancyService vacancyService;

    @Autowired
    public MainController(UnemployedService unemployedService, PositionService positionService, CompanyService companyService, SpecialityService specialityService, VacancyService vacancyService) {
        this.unemployedService = unemployedService;
        this.positionService = positionService;
        this.companyService = companyService;
        this.specialityService = specialityService;
        this.vacancyService = vacancyService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Company> companies = companyService.findAll();
        List<Unemployed> clients = unemployedService.findAll();
        List<Vacancy> vacancies = vacancyService.findAll();
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("clients", clients);
        model.addAttribute("companies", companies);
        return "index";
    }

    @GetMapping("/companies")
    public String companies(Model model){
        List<Company> positions = companyService.findAll();
        model.addAttribute("companies", positions);
        return "companies";
    }
    @GetMapping("/positions")
    public String positions(Model model){
        List<Position> positions = positionService.findAll();
        model.addAttribute("positions", positions);
        model.addAttribute("position", new Position());
        return "positions";
    }
    @GetMapping("/clients")
    public String clients(Model model){
        List<Unemployed> clients = unemployedService.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/vacancies")
    public String vacancies(Model model){
        List<Vacancy> vacancies = vacancyService.findAll();
        model.addAttribute("vacancies", vacancies);
        return "vacancies";
    }

    @GetMapping("/specialities")
    public String specialities(Model model){
        List<Speciality> specialities = specialityService.findAll();
        model.addAttribute("specialities", specialities);
        model.addAttribute("speciality", new Speciality());
        return "specialities";
    }

}
