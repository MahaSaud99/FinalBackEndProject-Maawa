package com.example.maawa.repository;

import com.example.maawa.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface appointmentRepository extends JpaRepository<Appointment,Integer> {
    Appointment findAppointmentById(Integer id);
    List<Appointment> findAllByUserId(Integer id);
}
