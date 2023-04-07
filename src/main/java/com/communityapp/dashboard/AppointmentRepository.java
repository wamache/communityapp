package com.communityapp.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByStartDateTimeAfter(LocalDateTime dateTime);

    List<Appointment> findByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

}

