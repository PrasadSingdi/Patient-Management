package com.cerner.hcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/save")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
		Patient savedPatient = patientService.createPatient(patient);
		return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
		
	}
	
	
}
