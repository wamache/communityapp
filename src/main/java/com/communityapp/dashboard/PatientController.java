package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

//    @GetMapping("/patients")
//    public List<Patient> getPatients() {
//        return (List<Patient>) patientService.findAll();
//    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public Patient addPatient(@RequestBody Patient patient) throws Exception {

       return patientService.addPatients(patient);

    }

    @PostMapping("/patient")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) throws Exception {
        return patientRepository.findById(id).orElseThrow(() -> new Exception("Patient not found"));
    }

    @PutMapping("/patient/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) throws Exception {
        Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new Exception("Patient not found"));
        existingPatient.setName(patient.getName());
        // set any other relevant fields
        return patientRepository.save(existingPatient);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

