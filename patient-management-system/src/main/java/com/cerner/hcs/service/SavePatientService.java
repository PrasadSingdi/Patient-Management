package com.cerner.hcs.service;

import org.springframework.stereotype.Component;

import com.cerner.hcs.entity.Patient;

@Component
public interface SavePatientService {
	Patient savePatient(Patient patient);
}

