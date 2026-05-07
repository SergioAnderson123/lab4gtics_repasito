package com.pucp.gtics.lab4gtics.controller;

import com.pucp.gtics.lab4gtics.entity.Clinic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pucp.gtics.lab4gtics.repository.ClinicRepository;

@Controller
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    ClinicRepository clinicRepository;

    @GetMapping(value = {"", "/"})
    public String listaClinicas(Model model) {
        model.addAttribute("listaClinics", clinicRepository.findAll());
        return "clinic/list";
    }

    @GetMapping("/new")
    public String nuevaClinicaFrm(Model model) {
        model.addAttribute("clinic", new Clinic());
        model.addAttribute("accion", "Crear");
        return "clinic/form";
    }

    @GetMapping("/edit")
    public String editarClinicaFrm(@RequestParam("id") int id, Model model) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        if (clinic == null) return "redirect:/clinic";
        model.addAttribute("clinic", clinic);
        model.addAttribute("accion", "Editar");
        return "clinic/form";
    }

    @PostMapping("/save")
    public String guardarClinica(@Valid @ModelAttribute Clinic clinic,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes attr) {
        if (result.hasErrors()) {
            model.addAttribute("accion", clinic.getId() == null ? "Crear" : "Editar");
            return "clinic/form";
        }
        clinicRepository.save(clinic);
        attr.addFlashAttribute("mensaje", "Clínica guardada correctamente");
        return "redirect:/clinic";
    }

    @GetMapping("/delete")
    public String eliminarClinica(@RequestParam("id") int id, RedirectAttributes attr) {
        clinicRepository.deleteById(id);
        attr.addFlashAttribute("mensaje", "Clínica eliminada correctamente");
        return "redirect:/clinic";
    }
}