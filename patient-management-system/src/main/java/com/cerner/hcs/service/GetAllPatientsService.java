package com.cerner.hcs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.exceptions.PatientException;

@Component
public interface GetAllPatientsService {
	List<Patient> getAllPatients();
}
