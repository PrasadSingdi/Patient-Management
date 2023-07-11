package com.cerner.hcs.service;

import org.springframework.stereotype.Component;

import com.cerner.hcs.exceptions.PatientException;

@Component
public interface DeletePatientService {
	void deletePatient(Long patientId);
}
