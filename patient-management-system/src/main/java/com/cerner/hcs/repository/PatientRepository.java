package com.cerner.hcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cerner.hcs.entity.Patient;


public interface PatientRepository extends JpaRepository<Patient, Long> {

}
