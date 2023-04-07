package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public List<Patient>  findAll() {
        return patientRepository.findAll();

    }

    public Patient addPatients(Patient patient) {
        return patientRepository.save(patient);
    }
}
