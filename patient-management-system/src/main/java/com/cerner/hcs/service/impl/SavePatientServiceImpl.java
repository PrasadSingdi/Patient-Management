package com.cerner.hcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.repository.PatientRepository;
import com.cerner.hcs.service.SavePatientService;

@Service
public class SavePatientServiceImpl implements SavePatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public Patient savePatient(Patient patient){
			return patientRepository.save(patient);
	}	

}
