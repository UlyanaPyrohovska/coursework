package ua.kpi.coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.kpi.coursework.model.Company;
import ua.kpi.coursework.model.Position;
import ua.kpi.coursework.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/add-company")
    public String getCompanyForm(Model model){
        model.addAttribute("company", new Company());
        return "add_company";
    }

    @DeleteMapping("/company-delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id){
        companyService.deleteById(id);
        return "redirect:/companies";
    }

    @GetMapping("/company-update/{id}")
    public String getCompanyForm(@PathVariable("id") Long id, Model model){
        Company company = companyService.findById(id);
        model.addAttribute(company);
        return "edit_company";
    }

    @PostMapping("/update-company")
    public String updateCompany(@Valid Company company, BindingResult bindingResult, Model model){
        model.addAttribute("company", company);
        if(bindingResult.hasErrors()){
            return "edit_company";
        }
        companyService.saveUnemployed(company);
        return "redirect:/companies";
    }

    @PostMapping("/create-company")
    public String createCompany(@Valid Company company, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "add_company";
        }
        companyService.saveUnemployed(company);
        return "redirect:/companies";
    }
}
