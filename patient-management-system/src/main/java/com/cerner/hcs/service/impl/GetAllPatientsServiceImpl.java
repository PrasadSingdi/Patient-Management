package com.cerner.hcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.exceptions.PatientException;
import com.cerner.hcs.repository.PatientRepository;
import com.cerner.hcs.service.GetAllPatientsService;

@Service
public class GetAllPatientsServiceImpl implements GetAllPatientsService {
	
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<Patient> getAllPatients(){
			return patientRepository.findAll();
	}

}
