package com.cerner.hcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.exceptions.PatientException;
import com.cerner.hcs.service.SavePatientService;

@RestController
@RequestMapping("/api/patient")
public class SavePatientController {
			
	@Autowired
	private SavePatientService  patientService;
	
	//Add patient
	@PostMapping("/save")
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){
		Patient savedPatient = null;
		savedPatient = patientService.savePatient(patient);
		return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
		
	}
	
}
