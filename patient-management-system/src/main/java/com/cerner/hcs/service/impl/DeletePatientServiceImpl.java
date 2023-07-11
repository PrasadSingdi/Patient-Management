package com.cerner.hcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.hcs.exceptions.PatientException;
import com.cerner.hcs.repository.PatientRepository;
import com.cerner.hcs.service.DeletePatientService;

@Service
public class DeletePatientServiceImpl implements DeletePatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public void deletePatient(Long patientId) {
			patientRepository.deleteById(patientId);
	}
}
