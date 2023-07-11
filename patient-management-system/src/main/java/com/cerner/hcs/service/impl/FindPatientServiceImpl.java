package com.cerner.hcs.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.repository.PatientRepository;
import com.cerner.hcs.service.FindPatientService;

@Service
public class FindPatientServiceImpl implements FindPatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public Patient getPatientById(Long patientId){
		Optional<Patient> optionalPatient = patientRepository.findById(patientId);
		return optionalPatient.get();
	}
}
