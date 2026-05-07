package com.pucp.gtics.lab4gtics.controller;

import com.pucp.gtics.lab4gtics.entity.Clinic;
import com.pucp.gtics.lab4gtics.entity.ClinicDoctor;
import com.pucp.gtics.lab4gtics.entity.ClinicDoctorId;
import com.pucp.gtics.lab4gtics.entity.Doctor;
import com.pucp.gtics.lab4gtics.repository.ClinicDoctorRepository;
import com.pucp.gtics.lab4gtics.repository.ClinicRepository;
import com.pucp.gtics.lab4gtics.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clinicDoctor")
public class ClinicDoctorController {

    @Autowired
    ClinicDoctorRepository clinicDoctorRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping(value = {"", "/"})
    public String listar(Model model) {
        model.addAttribute("listaClinicDoctors", clinicDoctorRepository.findAll());
        return "clinicDoctor/list";
    }

    @GetMapping("/new")
    public String nuevaAsociacionFrm(Model model) {
        model.addAttribute("clinicas", clinicRepository.findAll());
        model.addAttribute("doctores", doctorRepository.findAll());
        return "clinicDoctor/form";
    }

    @PostMapping("/save")
    public String guardar(@RequestParam("clinicId") Integer clinicId,
                          @RequestParam("doctorId") Integer doctorId,
                          RedirectAttributes attr) {
        Clinic clinic = clinicRepository.findById(clinicId).orElse(null);
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (clinic == null || doctor == null) {
            attr.addFlashAttribute("error", "Clínica o doctor no encontrado");
            return "redirect:/clinicDoctor";
        }
        ClinicDoctorId id = new ClinicDoctorId();
        id.setClinicId(clinicId);
        id.setDoctorId(doctorId);
        if (clinicDoctorRepository.existsById(id)) {
            attr.addFlashAttribute("error", "La asociación ya existe");
            return "redirect:/clinicDoctor";
        }
        ClinicDoctor clinicDoctor = new ClinicDoctor();
        clinicDoctor.setId(id);
        clinicDoctor.setClinic(clinic);
        clinicDoctor.setDoctor(doctor);
        clinicDoctorRepository.save(clinicDoctor);
        attr.addFlashAttribute("mensaje", "Asociación guardada correctamente");
        return "redirect:/clinicDoctor";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("clinicId") Integer clinicId,
                           @RequestParam("doctorId") Integer doctorId,
                           RedirectAttributes attr) {
        ClinicDoctorId id = new ClinicDoctorId();
        id.setClinicId(clinicId);
        id.setDoctorId(doctorId);
        clinicDoctorRepository.deleteById(id);
        attr.addFlashAttribute("mensaje", "Asociación eliminada correctamente");
        return "redirect:/clinicDoctor";
    }
}
