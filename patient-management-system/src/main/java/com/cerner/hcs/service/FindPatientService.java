package com.cerner.hcs.service;

import org.springframework.stereotype.Component;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.exceptions.PatientException;

@Component
public interface FindPatientService {
	Patient getPatientById(Long patientId);
}
