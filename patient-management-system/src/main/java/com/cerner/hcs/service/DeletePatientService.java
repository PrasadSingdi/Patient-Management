package com.cerner.hcs.service;

import org.springframework.stereotype.Component;

@Component
public interface DeletePatientService {
	void deletePatient(Long patientId);
}
