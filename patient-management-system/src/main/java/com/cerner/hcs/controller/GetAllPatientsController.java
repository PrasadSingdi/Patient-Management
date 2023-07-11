package com.cerner.hcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.exceptions.PatientException;
import com.cerner.hcs.service.GetAllPatientsService;

@RestController
@RequestMapping("/api/patient")
public class GetAllPatientsController {
	
	@Autowired
	private GetAllPatientsService patientService;
	
		//Get all the patients
		@GetMapping("/all")
		public ResponseEntity<List<Patient>> getAllPatients(){
			List<Patient> patientsList = null;
			patientsList = patientService.getAllPatients();
			return new ResponseEntity<>(patientsList, HttpStatus.OK);
		}

}
