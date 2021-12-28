package ua.kpi.coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kpi.coursework.model.*;
import ua.kpi.coursework.service.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class ClientController {
    private final UnemployedService unemployedService;
    private final PositionService positionService;
    private final SpecialityService specialityService;
    private final ResumeService resumeService;
    public final VacancyService vacancyService;

    @Autowired
    public ClientController(UnemployedService unemployedService, PositionService positionService, SpecialityService specialityService, ResumeService resumeService, VacancyService vacancyService) {
        this.unemployedService = unemployedService;
        this.positionService = positionService;
        this.specialityService = specialityService;
        this.resumeService = resumeService;
        this.vacancyService = vacancyService;
    }

    @PostMapping("/client-employ")
    public String setVacancy(@RequestParam Long id1,@RequestParam Long id, Model model){
        Unemployed unemployed1 = unemployedService.findById(id1);
        unemployed1.getResume().setVacancy(vacancyService.findById(id));
        vacancyService.findById(id).setResume(unemployed1.getResume());
        unemployedService.saveUnemployed(unemployed1);
        return "redirect:/clients";
    }

    @GetMapping("/client-employ/{id}")
    public String findVacancies(@PathVariable("id") Long id, Model model){
        Unemployed unemployed = unemployedService.findById(id);
        List<Position> positions = unemployed.getResume().getPositions();
        List<Speciality> specialities = unemployed.getClient_specialities();
        List<Vacancy> vacancies = new LinkedList<>();
        for (Position position : positions) {
            for (Speciality speciality : specialities) {
                vacancies.addAll(vacancyService.findVacanciesByPositionAndSpeciality(position, speciality));
            }
        }
        vacancies.removeIf(vacancy -> vacancy.getResume() != null);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("client", unemployed);
        return "employ";
    }
    @DeleteMapping("/client-delete/{id}")
    public String deleteClient(@PathVariable("id") Long id){
        unemployedService.deleteById(id);
        return "redirect:/clients";
    }
    @GetMapping("/client-update/{id}")
    public String getClientForm(@PathVariable("id") Long id, Model model){
        Unemployed unemployed = unemployedService.findById(id);
        Resume resume = unemployed.getResume();
        List<Position> positions = positionService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        model.addAttribute("client", unemployed);
        model.addAttribute("resume", resume);
        model.addAttribute("positions", positions);
        model.addAttribute("specialities", specialities);
        return "edit_unemployed";
    }
    @GetMapping("/add-client")
    public String addClient(Model model){
        List<Position> positions = positionService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        model.addAttribute("client", new Unemployed());
        model.addAttribute("resume", new Resume());
        model.addAttribute("specialities", specialities);
        model.addAttribute("positions", positions);
        return "add_unemployed";
    }
    @PostMapping("/create_unemployed")
    public String createClient(@Valid @ModelAttribute("client") Unemployed unemployed, BindingResult bindingResultUnemployed,
                               @Valid @ModelAttribute("resume") Resume resume, BindingResult bindingResultResume,
                               @RequestParam Long position, @RequestParam Long position2,
                               @RequestParam Long speciality, @RequestParam Long speciality2, Model model){
        List<Position> positions = positionService.findAll();
        List<Speciality> specialities = specialityService.findAll();
        model.addAttribute("specialities", specialities);
        model.addAttribute("positions", positions);
        boolean err1 = false;
        boolean err11 = false;
        boolean err2 = false;
        boolean err22 = false;
        if(position == 0 ){
            err1 =true;
            model.addAttribute("err1", err1);
            if(speciality == 0){
                err2 =true;
                model.addAttribute("err1", err2);
            }
            return "add_unemployed";
        }

        if (bindingResultUnemployed.hasErrors() || bindingResultResume.hasErrors()) {
            return "add_unemployed";
        }
        Speciality speciality_1 = specialityService.findById(speciality);
        LinkedList<Speciality> client_specialities = new LinkedList<>();
        client_specialities.add(speciality_1);
        if(speciality2 != 0){
            Speciality speciality_2 = specialityService.findById(speciality2);
            if (!speciality.equals(speciality2)){
                client_specialities.add(speciality_2);
            }else{
                err22 =true;
                model.addAttribute("err22", err22);
                return "add_unemployed";
            }
        }
        Position position_1 = positionService.findById(position);
        LinkedList<Position> resume_positions = new LinkedList<>();
        resume_positions.add(position_1);
        if(position2 != 0){
            Position position_2 = positionService.findById(position2);
            if(!position.equals(position2)){
                resume_positions.add(position_2);
            }else {
                err11 =true;
                model.addAttribute("err11", err11);
                return "add_unemployed";
            }
        }
        resume.setDate_submit(new Date());
        unemployed.setClient_specialities(client_specialities);
        resume.setPositions(resume_positions);
        positionService.saveUnemployed(position_1);
        unemployedService.saveUnemployed(unemployed);
        specialityService.saveUnemployed(speciality_1);
        resume.setUnemployed(unemployed);
        if(client_specialities.size() == 2) specialityService.saveUnemployed(specialityService.findById(speciality2));
        if(resume_positions.size() == 2) positionService.saveUnemployed(positionService.findById(position2));
        resumeService.saveUnemployed(resume);
        return "redirect:/clients";
    }
    @PostMapping("/edit_unemployed")
    public String updateClient(@Valid Unemployed unemployed, BindingResult bindingResultUnemployed,
                               @RequestParam Long position, @RequestParam Long position2,
                               @RequestParam Long speciality, @RequestParam Long speciality2, Model model){
        if(position == 0 || speciality == 0 || unemployed.getResume().getDesired_salary() == null){
            return "redirect:/client-update/" + unemployed.getId();
        }
        if (bindingResultUnemployed.hasErrors()) {
            return "redirect:/client-update/" + unemployed.getId();
        }
        Speciality speciality_1 = specialityService.findById(speciality);
        LinkedList<Speciality> client_specialities = new LinkedList<>();
        client_specialities.add(speciality_1);
        if(speciality2 != 0){
            Speciality speciality_2 = specialityService.findById(speciality2);
            if (!speciality.equals(speciality2)){
                client_specialities.add(speciality_2);
            }else return "redirect:/client-update/" + unemployed.getId();
        }
        Position position_1 = positionService.findById(position);
        LinkedList<Position> resume_positions = new LinkedList<>();
        resume_positions.add(position_1);
        if(position2 != 0){
            Position position_2 = positionService.findById(position2);
            if(!position.equals(position2)){
                resume_positions.add(position_2);
            }else return "redirect:/client-update/" + unemployed.getId();
        }
        Resume res = resumeService.findById(unemployed.getResume().getId());
        res.setDate_submit(new Date());
        unemployed.setClient_specialities(client_specialities);
        res.setPositions(resume_positions);
        unemployed.setResume(res);
        //resumeService.saveUnemployed(res);
        positionService.saveUnemployed(position_1);
        unemployedService.saveUnemployed(unemployed);
        specialityService.saveUnemployed(speciality_1);
        if(client_specialities.size() == 2) specialityService.saveUnemployed(specialityService.findById(speciality2));
        if(resume_positions.size() == 2) positionService.saveUnemployed(positionService.findById(position2));
        return "redirect:/clients";
    }
}
