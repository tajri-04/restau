package com.javatech.mail.service.doctor;

import com.javatech.mail.MailService;
import com.javatech.mail.dao.doctor.DoctorRepository;
import com.javatech.mail.model.doctor.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private MailService mailService;

    @PostConstruct
    public void initDoctor(){
        repository.saveAll(Stream.of(new Doctor(101,"john","Cardiac"),
                new Doctor(102,"peter","eye"))
                .collect(Collectors.toList()));
    }


    public List<Doctor> getDoctors() {
        mailService.senEmail();
        return repository.findAll();
    }
}
