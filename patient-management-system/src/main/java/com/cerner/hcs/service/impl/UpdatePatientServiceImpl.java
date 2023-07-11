package com.cerner.hcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.repository.PatientRepository;
import com.cerner.hcs.service.UpdatePatientService;

@Service
public class UpdatePatientServiceImpl implements UpdatePatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient updatePatient(Patient patient) {
		Patient updatedPatient = null;
		Patient existingPatient = patientRepository.findById(patient.getId()).get();

		if (patient.getName() != null) {
			existingPatient.setName(patient.getName());
		}
		if (patient.getDob() != null) {
			existingPatient.setDob(patient.getDob());
		}
		if (patient.getGender() != null) {
			existingPatient.setGender(patient.getGender());
		}
		if (patient.getAddress() != null) {
			existingPatient.setAddress(patient.getAddress());
		}
		if (patient.getPhonenumber() != null) {
			existingPatient.setPhonenumber(patient.getPhonenumber());
		}

		updatedPatient = patientRepository.save(existingPatient);
		return updatedPatient;

	}

}
