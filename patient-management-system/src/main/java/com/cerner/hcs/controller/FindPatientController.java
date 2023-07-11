package com.cerner.hcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.service.FindPatientService;

@RestController
@RequestMapping("/api/patient")
public class FindPatientController {
	
	@Autowired
	private FindPatientService  findPatientService;
	
	//Find patient by iD and return it
		@GetMapping("/get/{id}")
		public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long patientID){
				Patient patient = null;
				patient = findPatientService.getPatientById(patientID);
				return new ResponseEntity<>(patient, HttpStatus.OK);
		}

}
