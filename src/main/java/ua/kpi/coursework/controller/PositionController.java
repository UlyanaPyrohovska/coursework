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
import ua.kpi.coursework.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/position-update/{id}")
    public String getPositionForm(@PathVariable("id") Long id, Model model){
        Position position = positionService.findById(id);
        model.addAttribute("position", position);
        return "edit_position";
    }

    @DeleteMapping("/position-delete/{id}")
    public String deletePosition(@PathVariable("id") Long id){
        positionService.deleteById(id);
        return "redirect:/positions";
    }

    @PostMapping("/position-update")
    public String getClientForm(@Valid Position position, BindingResult bindingResult, Model model){
        model.addAttribute("position", position);
        if(bindingResult.hasErrors()){
            return "edit_position";
        }
        positionService.saveUnemployed(position);
        return "redirect:/positions";
    }

    @PostMapping("/create-position")
    public String createPosition(@Valid Position position, BindingResult bindingResult, Model model){
        List<Position> positions = positionService.findAll();
        if(bindingResult.hasErrors()){
            model.addAttribute("positions", positions);
            model.addAttribute("position", position);
            return "positions";
        }
        model.addAttribute("position", new Position());
        positionService.saveUnemployed(position);
        positions = positionService.findAll();
        model.addAttribute("positions", positions);
        return "positions";
    }
}
