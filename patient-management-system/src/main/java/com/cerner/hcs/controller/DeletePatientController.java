package com.cerner.hcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerner.hcs.exceptions.PatientException;
import com.cerner.hcs.service.DeletePatientService;


@RestController
@RequestMapping("/api/patient")
public class DeletePatientController {
	
	@Autowired
	private DeletePatientService  patientService;
	 
		//Remove the patient
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deletePatient(@PathVariable("id") Long patientId){
			patientService.deletePatient(patientId);
			return new ResponseEntity<>("Patient Successfully deleted!", HttpStatus.OK);
		}
}
