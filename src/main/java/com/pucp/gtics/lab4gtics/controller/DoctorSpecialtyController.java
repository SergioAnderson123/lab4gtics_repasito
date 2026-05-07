package com.pucp.gtics.lab4gtics.controller;

import com.pucp.gtics.lab4gtics.entity.Doctor;
import com.pucp.gtics.lab4gtics.entity.DoctorSpecialty;
import com.pucp.gtics.lab4gtics.entity.DoctorSpecialtyId;
import com.pucp.gtics.lab4gtics.entity.Specialty;
import com.pucp.gtics.lab4gtics.repository.DoctorRepository;
import com.pucp.gtics.lab4gtics.repository.DoctorSpecialtyRepository;
import com.pucp.gtics.lab4gtics.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doctorSpecialty")
public class DoctorSpecialtyController {

    @Autowired
    DoctorSpecialtyRepository doctorSpecialtyRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    SpecialtyRepository specialtyRepository;

    @GetMapping(value = {"", "/"})
    public String listar(Model model) {
        model.addAttribute("listaDoctorSpecialties", doctorSpecialtyRepository.findAll());
        return "doctorSpecialty/list";
    }

    @GetMapping("/new")
    public String nuevaAsociacionFrm(Model model) {
        model.addAttribute("doctores", doctorRepository.findAll());
        model.addAttribute("especialidades", specialtyRepository.findAll());
        return "doctorSpecialty/form";
    }

    @PostMapping("/save")
    public String guardar(@RequestParam("doctorId") Integer doctorId,
                          @RequestParam("specialtyId") Integer specialtyId,
                          RedirectAttributes attr) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        Specialty specialty = specialtyRepository.findById(specialtyId).orElse(null);
        if (doctor == null || specialty == null) {
            attr.addFlashAttribute("error", "Doctor o especialidad no encontrada");
            return "redirect:/doctorSpecialty";
        }
        DoctorSpecialtyId id = new DoctorSpecialtyId();
        id.setDoctorId(doctorId);
        id.setSpecialtyId(specialtyId);
        if (doctorSpecialtyRepository.existsById(id)) {
            attr.addFlashAttribute("error", "La asociación ya existe");
            return "redirect:/doctorSpecialty";
        }
        DoctorSpecialty ds = new DoctorSpecialty();
        ds.setId(id);
        ds.setDoctor(doctor);
        ds.setSpecialty(specialty);
        doctorSpecialtyRepository.save(ds);
        attr.addFlashAttribute("mensaje", "Especialidad asignada correctamente");
        return "redirect:/doctorSpecialty";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("doctorId") Integer doctorId,
                           @RequestParam("specialtyId") Integer specialtyId,
                           RedirectAttributes attr) {
        DoctorSpecialtyId id = new DoctorSpecialtyId();
        id.setDoctorId(doctorId);
        id.setSpecialtyId(specialtyId);
        doctorSpecialtyRepository.deleteById(id);
        attr.addFlashAttribute("mensaje", "Especialidad eliminada correctamente");
        return "redirect:/doctorSpecialty";
    }
}
