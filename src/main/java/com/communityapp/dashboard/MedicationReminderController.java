package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
public class MedicationReminderController {

    @Autowired
    private MedicationReminderRepository medicationReminderRepository;

    @PostMapping("/medication-reminders")
    public MedicationReminder createMedicationReminder(@RequestBody MedicationReminder medicationReminder) {
        return medicationReminderRepository.save(medicationReminder);
    }

    @GetMapping("/medication-reminders")
    public List<MedicationReminder> getAllMedicationReminders() {
        return medicationReminderRepository.findAll();
    }

    @GetMapping("/medication-reminders/{id}")
    public MedicationReminder getMedicationReminderById(@PathVariable Long id) throws Exception {
        return medicationReminderRepository.findById(id).orElseThrow(() -> new Exception("Medication reminder not found"));
    }

    @PutMapping("/medication-reminders/{id}")
    public MedicationReminder updateMedicationReminder(@PathVariable Long id, @RequestBody MedicationReminder medicationReminder) throws Exception {
        MedicationReminder existingMedicationReminder = medicationReminderRepository.findById(id).orElseThrow(() -> new Exception("Medication reminder not found"));
        existingMedicationReminder.setDateTime(medicationReminder.getDateTime());
        // set any other relevant fields
        return medicationReminderRepository.save(existingMedicationReminder);
    }

    @DeleteMapping("/medication-reminders/{id}")
    public ResponseEntity<?> deleteMedicationReminder(@PathVariable Long id) {
        medicationReminderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
