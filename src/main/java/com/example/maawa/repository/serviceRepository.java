package com.example.maawa.repository;

import com.example.maawa.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface serviceRepository extends JpaRepository<Service,Integer> {
    Service findServiceById(Integer id);
    List<Service> findAllByClinicId(Integer id);
}
