package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getUpcomingAppointments(LocalDateTime dateTime) {
        return appointmentRepository.findByStartDateTimeAfter(dateTime);
    }

    public List<Appointment> getAppointmentsBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return appointmentRepository.findByStartDateTimeBetween(startDateTime, endDateTime);
    }

    public Appointment updateAppointment(Long id, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id " + id));
        existingAppointment.setStartDateTime(appointment.getStartDateTime());
        existingAppointment.setEndDateTime(appointment.getEndDateTime());
        existingAppointment.setPatientName(appointment.getPatientName());
        existingAppointment.setNotes(appointment.getNotes());
        return appointmentRepository.save(existingAppointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

}
