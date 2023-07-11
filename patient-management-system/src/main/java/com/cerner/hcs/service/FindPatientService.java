package com.cerner.hcs.service;

import org.springframework.stereotype.Component;

import com.cerner.hcs.entity.Patient;

@Component
public interface FindPatientService {
	Patient getPatientById(Long patientId);
}
