package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MedicationController {
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicationReminderRepository medicationReminderRepository;
    @Autowired
    private SideEffectRepository sideEffectRepository;

    @PostMapping("/medications")
    public Medication createMedication(@RequestBody Medication medication) {
        return medicationRepository.save(medication);
    }

    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @GetMapping("/medications/{id}")
    public Medication getMedicationById(@PathVariable Long id) throws Exception {
        return medicationRepository.findById(id).orElseThrow(() -> new Exception("Medication not found"));
    }


    // Custom method to schedule medication reminders for a patient
    @PostMapping("/patients/{patientId}/medication-reminders")
    public MedicationReminder scheduleMedicationReminder(@PathVariable Long patientId, @RequestBody MedicationReminder medicationReminder) throws Exception {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new Exception("Patient not found"));
        medicationReminder.setPatient(patient);
        return medicationReminderRepository.save(medicationReminder);
    }

    // Custom method to monitor side effects for a patient's medication
    @PostMapping("/patients/{patientId}/medications/{medicationId}/side-effects")
    public SideEffect monitorSideEffect(@PathVariable Long patientId, @PathVariable Long medicationId, @RequestBody SideEffect sideEffect) throws Exception {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new Exception("Patient not found"));
        Medication medication = medicationRepository.findById(medicationId).orElseThrow(() -> new Exception("Medication not found"));
        sideEffect.setPatient(patient);
        sideEffect.setMedication(medication);
        return sideEffectRepository.save(sideEffect);
    }



    // add any other relevant methods
}

