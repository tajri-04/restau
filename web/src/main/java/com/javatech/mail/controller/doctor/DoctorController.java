package com.javatech.mail.controller.doctor;

import com.javatech.mail.model.doctor.Doctor;
import com.javatech.mail.service.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {
     @Autowired
    private DoctorService doctorService;

     @GetMapping("/doctors")
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }
}
