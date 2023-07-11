	/**
	 * 
	 */
	package com.cerner.hcs;
	


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.exceptions.PatientException;
import com.cerner.hcs.repository.PatientRepository;
import com.cerner.hcs.service.impl.SavePatientServiceImpl;
import com.cerner.hcs.service.impl.DeletePatientServiceImpl;
import com.cerner.hcs.service.impl.FindPatientServiceImpl;
import com.cerner.hcs.service.impl.GetAllPatientsServiceImpl;
import com.cerner.hcs.service.impl.UpdatePatientServiceImpl;
	
	/**
	 * @author Mahesh
	 *
	 */
	@ExtendWith(MockitoExtension.class)
	public class PatientServiceTests {
	
		@Mock
		private PatientRepository patientRepository;
	
		@InjectMocks
		private SavePatientServiceImpl savePatientService;
	
		@InjectMocks
		private DeletePatientServiceImpl deletePatientService;
	
		@InjectMocks
		private FindPatientServiceImpl findPatientService;
	
		@InjectMocks
		private UpdatePatientServiceImpl updatePatientService;
	
		@InjectMocks
		private GetAllPatientsServiceImpl getAllPatientsService;
	
		private Patient patient1;
		private Patient patient2;
		List<Patient> patientList;
	
		@BeforeEach
		public void setup() {
			// employeeRepository = Mockito.mock(EmployeeRepository.class);
			// employeeService = new EmployeeServiceImpl(employeeRepository);
			patient1 = Patient.builder().id(1L).name("Ramesh").dob(LocalDate.of(1988, 01, 02)).gender("Male")
					.address("Hyderabad").phonenumber("9845638940").build();
			patient2 = Patient.builder().id(2L).name("Rosy").dob(LocalDate.of(1987, 04, 03)).gender("Female")
					.address("Bengalore").phonenumber("8954762890").build();
			
			patientList = new ArrayList();
			patientList.add(patient1);
			patientList.add(patient2);
		}
	
	    @DisplayName("JUnit test for savePatient method")
	    @Test
	    public void testSavePatient(){
	    	when(patientRepository.save(any())).thenReturn(patient1);
	    	savePatientService.savePatient(patient1);
	    	verify(patientRepository,times(1)).save(any());

	    }
	    @DisplayName("JUnit test for getAllPatients method")
	    @Test
	    public void testGetAllPatients() {
	    	patientRepository.save(patient1);
	    	//stubbing mock to return specific data
	    	when(patientRepository.findAll()).thenReturn(patientList);
	    	List<Patient> patientList1 = getAllPatientsService.getAllPatients();
	    	assertEquals(patientList1, patientList);
	    	verify(patientRepository,times(1)).save(patient1);
	    	verify(patientRepository,times(1)).findAll();
	    	
	    }
	    @DisplayName("JUnit test for getPatientById method")
	    @Test
	    public void testGetPatientById() {
	       when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(patient1));
	       assertThat(findPatientService.getPatientById(patient1.getId())).isEqualTo(patient1);
	    }
	    @DisplayName("JUnit test for updatePatient method")
	    @Test
	    public void testUpdatePatient() {
	    	when(patientRepository.save(patient2)).thenReturn(patient2);
	    	when(patientRepository.findById(2L)).thenReturn(Optional.ofNullable(patient2));
	    	patient2.setAddress("Chennai");
	    	patient2.setPhonenumber("7654743216");
	    	Patient updatePatient = updatePatientService.updatePatient(patient2);
	    	assertEquals(updatePatient.getAddress(), patient2.getAddress());
	    	assertEquals(updatePatient.getPhonenumber(), patient2.getPhonenumber());
	    	verify(patientRepository,times(1)).save(patient2);
	    	
	    }
	    @DisplayName("JUnit test for deletePatient method")
	    @Test
	    public void testDeletePatientById() {
	    	Long patientId = 1L;
	    	doNothing().when(patientRepository).deleteById(patientId);
	    	deletePatientService.deletePatient(patientId);
	    	verify(patientRepository,times(1)).deleteById(patientId);
	    }
	    @AfterEach
	    public void tearDown() {
	    patient1 = patient2 = null;
	    patientList = null;
	    }
	}
