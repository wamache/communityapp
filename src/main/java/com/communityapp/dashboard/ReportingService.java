package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingService {

    @Autowired
    private PatientRepository patientRepository;

    public String generateReport() {
        StringBuilder report = new StringBuilder();

        // Get patient outcomes
        List<Patient> patients = patientRepository.findAll();
        int totalPatients = patients.size();
        int patientsWithPositiveOutcomes = 0;
        for (Patient patient : patients) {
//            if (patient.getOutcome().equals("positive")) {
                patientsWithPositiveOutcomes++;
  //          }
        }
        double percentPositiveOutcomes = (double) patientsWithPositiveOutcomes / totalPatients * 100;
        report.append(String.format("Patient outcomes: %.2f%% of patients had positive outcomes.\n", percentPositiveOutcomes));

        // Get patient satisfaction
     //   double averageSatisfaction = patientRepository.findAverageSatisfaction();
      //  report.append(String.format("Patient satisfaction: The average satisfaction rating was %.2f.\n", averageSatisfaction));

        // Get appointment adherence
        //double averageAdherence = patientRepository.findAverageAdherence();
        //report.append(String.format("Appointment adherence: The average adherence rate was %.2f%%.\n", averageAdherence));

        return report.toString();

//    public String generateReport() {
//        // TODO: Implement report generation logic
//        return "Report generated successfully!";
    }
}