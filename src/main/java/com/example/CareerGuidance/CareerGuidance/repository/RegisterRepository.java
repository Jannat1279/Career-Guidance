package com.example.CareerGuidance.CareerGuidance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CareerGuidance.CareerGuidance.model.Register;


@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    Optional<Register> findByEmail(String email);
}
