package com.cerner.hcs.service;

import org.springframework.stereotype.Component;

import com.cerner.hcs.entity.Patient;

@Component
public interface UpdatePatientService {
	Patient updatePatient(Patient patient);
}
