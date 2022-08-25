package com.example.maawa.repository;

import com.example.maawa.model.timeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface timeSlotRepository extends JpaRepository<timeSlot,Integer> {
    timeSlot findTimeSlotById(Integer id);
    List<timeSlot> findAllByServiceId(Integer serviceId);
}
