package com.cerner.hcs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cerner.hcs.entity.Patient;

@Component
public interface GetAllPatientsService {
	List<Patient> getAllPatients();
}
