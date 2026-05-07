package com.pucp.gtics.lab4gtics.controller;

import com.pucp.gtics.lab4gtics.entity.Doctor;
import com.pucp.gtics.lab4gtics.entity.DoctorState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pucp.gtics.lab4gtics.repository.DoctorRepository;
import com.pucp.gtics.lab4gtics.repository.DoctorStateRepository;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DoctorStateRepository doctorStateRepository;

    @GetMapping(value = {"", "/"})
    public String listaDoctores(Model model) {
        model.addAttribute("listaDoctores", doctorRepository.findAll());
        return "doctor/list";
    }

    @GetMapping("/new")
    public String nuevoDoctorFrm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("accion", "Crear");
        model.addAttribute("estados", doctorStateRepository.findAll());
        return "doctor/form";
    }

    @GetMapping("/edit")
    public String editarDoctorFrm(@RequestParam("id") int id, Model model) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor == null) return "redirect:/doctor";
        model.addAttribute("doctor", doctor);
        model.addAttribute("accion", "Editar");
        model.addAttribute("estados", doctorStateRepository.findAll());
        return "doctor/form";
    }

    @PostMapping("/save")
    public String guardarDoctor(@Valid @ModelAttribute Doctor doctor,
                                BindingResult result,
                                Model model,
                                RedirectAttributes attr) {
        if (result.hasErrors()) {
            model.addAttribute("accion", doctor.getId() == null ? "Crear" : "Editar");
            model.addAttribute("estados", doctorStateRepository.findAll());
            return "doctor/form";
        }
        doctorRepository.save(doctor);
        attr.addFlashAttribute("mensaje", "Doctor guardado correctamente");
        return "redirect:/doctor";
    }

    @GetMapping("/delete")
    public String eliminarDoctor(@RequestParam("id") int id, RedirectAttributes attr) {
        doctorRepository.deleteById(id);
        attr.addFlashAttribute("mensaje", "Doctor eliminado correctamente");
        return "redirect:/doctor";
    }
}