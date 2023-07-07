/**
 * 
 */
package com.cerner.hcs.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.repository.PatientRepository;
import com.cerner.hcs.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public Patient createPatient(Patient patient) {
		return patientRepository.save(patient);
	}

}
