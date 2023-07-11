package com.cerner.hcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.exceptions.PatientException;
import com.cerner.hcs.service.UpdatePatientService;

@RestController
@RequestMapping("/api/patient")
public class UpdatePatientController {

	@Autowired
	private UpdatePatientService patientService;

	// Update patient details
	@PutMapping("/update/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long patientId, @RequestBody Patient patient) {
		patient.setId(patientId);
		Patient updatePatient = null;
		updatePatient = patientService.updatePatient(patient);
		return new ResponseEntity<>(updatePatient, HttpStatus.OK);
	}
}
