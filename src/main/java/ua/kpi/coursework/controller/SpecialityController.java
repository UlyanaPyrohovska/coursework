package ua.kpi.coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.kpi.coursework.model.Position;
import ua.kpi.coursework.model.Speciality;
import ua.kpi.coursework.service.SpecialityService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SpecialityController {
    private final SpecialityService specialityService;

    @Autowired
    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @DeleteMapping("/speciality-delete/{id}")
    public String deletePosition(@PathVariable("id") Long id){
        specialityService.deleteById(id);
        return "redirect:/specialities";
    }

    @GetMapping("/speciality-update/{id}")
    public String getPositionForm(@PathVariable("id") Long id, Model model){
        Speciality speciality = specialityService.findById(id);
        model.addAttribute("speciality", speciality);
        return "edit_speciality";
    }

    @PostMapping("/speciality-update")
    public String getClientForm(@Valid Speciality speciality, BindingResult bindingResult, Model model){
        model.addAttribute("speciality", speciality);
        if(bindingResult.hasErrors()){
            return "edit_speciality";
        }
        specialityService.saveUnemployed(speciality);
        return "redirect:/specialities";
    }

    @PostMapping("/create-speciality")
    public String createPosition(@Valid Speciality speciality, BindingResult bindingResult, Model model){
        List<Speciality> specialities = specialityService.findAll();
        if(bindingResult.hasErrors()){
            model.addAttribute("specialities", specialities);
            model.addAttribute("speciality", speciality);
            return "specialities";
        }
        model.addAttribute("speciality", new Position());
        specialityService.saveUnemployed(speciality);
        specialities = specialityService.findAll();
        model.addAttribute("specialities", specialities);
        return "specialities";
    }
}
