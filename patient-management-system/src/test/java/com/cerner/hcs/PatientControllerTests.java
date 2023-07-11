package com.cerner.hcs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cerner.hcs.entity.Patient;
import com.cerner.hcs.service.DeletePatientService;
import com.cerner.hcs.service.FindPatientService;
import com.cerner.hcs.service.GetAllPatientsService;
import com.cerner.hcs.service.SavePatientService;
import com.cerner.hcs.service.UpdatePatientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class PatientControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SavePatientService savePatientService;
	@MockBean
	private DeletePatientService deletePatientService;
	@MockBean
	private FindPatientService findPatientService;
	@MockBean
	private UpdatePatientService updatePatientService;
	@MockBean
	private GetAllPatientsService getAllPatientsService;

	@Autowired
	private ObjectMapper objectMapper;
	
	DateTimeFormatter formatter;

	// JUnit test for Save Patient REST API
	@BeforeEach
	public void setup() {
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}
	@Test
	public void testCreatePatient() throws Exception { 
		// Data Setup
		Patient patient = Patient.builder().name("Prasad").dob(LocalDate.of(1987, 05, 12)).gender("Male")
				.address("Hyderabad").phonenumber("9475837189").build();

		given(savePatientService.savePatient(any(Patient.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(post("/api/patient/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patient)));

		// then - verify the result or output using assert statements
		response.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(patient.getName())))
				.andExpect(jsonPath("$.dob", is(patient.getDob().format(formatter))))
				.andExpect(jsonPath("$.gender", is(patient.getGender())))
				.andExpect(jsonPath("$.address", is(patient.getAddress())))
				.andExpect(jsonPath("$.phonenumber", is(patient.getPhonenumber())));

	}

	// JUnit test for Get All Patients REST API
	@Test
	public void testGetAllPatients() throws Exception {
		// Data Setup
		List<Patient> listOfPatients = new ArrayList<>();
		listOfPatients.add(Patient.builder().name("Prasad").dob(LocalDate.of(1987, 05, 31)).gender("Male")
				.address("Hyderabad").phonenumber("123456789").build());
		listOfPatients.add(Patient.builder().name("Suresh").dob(LocalDate.of(1989, 04, 23)).gender("Male")
				.address("Bangalore").phonenumber("123456789").build());
		given(getAllPatientsService.getAllPatients()).willReturn(listOfPatients);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/patient/all"));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(listOfPatients.size())));

	}

	// JUnit test for GET patient by id REST API
	// Test for getPatientById with Valid Patient Id then return Patient Object
	@Test
	public void testGetPatientByIdValidId() throws Exception {
		// Data Setup
		Long patientId = 1L;
		Patient patient = Patient.builder().id(patientId).name("prasad").dob(LocalDate.of(1995, 03, 11)).gender("male")
				.address("nalgonda").phonenumber("6303242998").build();
		given(findPatientService.getPatientById(patientId)).willReturn(patient);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/patient/get/{id}", patientId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.name", is(patient.getName())))
				.andExpect(jsonPath("$.dob", is(patient.getDob().format(formatter))))
				.andExpect(jsonPath("$.gender", is(patient.getGender())))
				.andExpect(jsonPath("$.address", is(patient.getAddress())))
				.andExpect(jsonPath("$.phonenumber", is(patient.getPhonenumber())));

	}

	// JUnit test for GET patient by id REST API
	// Negative Scenario - Test for getPatientById with In Valid Patient Id then
	// return empty Patient Object
	//@Test
	public void testGetPatientByIdInvalidId() throws Exception {
		// Data Setup
		Long patientId = 601L;
		Patient patient = Patient.builder().id(patientId).name("Bhavani").dob(LocalDate.of(1986, 05, 02)).gender("Female")
				.address("Chennai").phonenumber("8712647890").build();
		given(findPatientService.getPatientById(patientId)).willReturn((Patient) Optional.empty().get());

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/patient/get/{id}", patientId));

		// then - verify the output
		response.andExpect(status().isNotFound()).andDo(print());

	}

	// JUnit test for Update Patient REST API - positive scenario
	@Test
	public void testUpdatePatientValidId() throws Exception {
		// Data Setup
		Long patientId = 1L;
		Patient savedPatient = Patient.builder().name("Bhavani").dob(LocalDate.of(1986, 05, 02)).gender("Female")
				.address("Chennai").phonenumber("8712647890").build();

		Patient updatedPatient = Patient.builder().name("Bhavya").dob(LocalDate.of(1986, 05, 02)).gender("Female")
				.address("Bengalore").phonenumber("9712647890").build();
		given(findPatientService.getPatientById(patientId)).willReturn(savedPatient);
		given(updatePatientService.updatePatient(any(Patient.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(put("/api/patient/update/{id}", patientId)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedPatient)));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.name", is(updatedPatient.getName())))
				.andExpect(jsonPath("$.dob", is(updatedPatient.getDob().format(formatter))))
				.andExpect(jsonPath("$.gender", is(updatedPatient.getGender())))
				.andExpect(jsonPath("$.address", is(updatedPatient.getAddress())))
				.andExpect(jsonPath("$.phonenumber", is(updatedPatient.getPhonenumber())));

	}

	// JUnit test for Update Patient REST API - Negative scenario
	//@Test
	public void testUpdatedPatientInvalidId() throws Exception {
		// Data Setup
		Long patientId = 701L;
		Patient savedPatient = Patient.builder().name("Bhavani").dob(LocalDate.of(1986, 05, 02)).gender("Female")
				.address("Chennai").phonenumber("8712647890").build();

		Patient updatedPatient = Patient.builder()
				.name("Bhavya").dob(LocalDate.of(1986, 05, 02)).gender("Female")
				.address("Bengalore").phonenumber("9712647890").build();
		given(findPatientService.getPatientById(patientId)).willReturn(new Patient());
		given(updatePatientService.updatePatient(any(Patient.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(put("/api/patient/update/{id}", patientId)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedPatient)));

		// then - verify the output
		response.andExpect(status().isNotFound()).andDo(print());
	}

	// JUnit test for Delete Patient REST API
	@Test
	public void testDeletePatient() throws Exception {
		// Data Setup
		Long patientId = 101L;
		willDoNothing().given(deletePatientService).deletePatient(patientId);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(delete("/api/patient/delete/{id}", patientId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print());
	}
}
