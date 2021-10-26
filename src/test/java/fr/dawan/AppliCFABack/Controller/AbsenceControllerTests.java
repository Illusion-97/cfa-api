package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.controllers.AbsenceController;
import fr.dawan.AppliCFABack.dto.AbsenceDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class AbsenceControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AbsenceController absenceController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idAbsence;
	
	@BeforeAll
	void init() {
		assertThat(absenceController).isNotNull();
//		initDataBase();
	}
	
	@AfterAll
	void clean(){
//		testDelete();
//		deleteDatabase();
	}
	
	@Test
	void testFindAll() {
		try {
			mockMvc.perform(get("/absences").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/absences/" + idAbsence).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.dateDebut", is("06/10/2021")))
					.andExpect(jsonPath("$.dateFin", is("07/10/2021")))
					.andExpect(jsonPath("$.justificatif", is("justif")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testSave() {
		try {
			AbsenceDto aToInsert = new AbsenceDto();
			aToInsert.setDateDebut(LocalDate.now());
			aToInsert.setDateFin(LocalDate.now());
			aToInsert.setJustificatif("justificatif save");
			

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(aToInsert);

			String jsonReponse = mockMvc.perform(post("/absences")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			AbsenceDto aDto = objectMapper.readValue(jsonReponse, AbsenceDto.class);
			assertTrue(aDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		try {
			AbsenceDto aDto = absenceController.getById(idAbsence+1);
			aDto.setDateDebut(LocalDate.now());
			aDto.setDateFin(LocalDate.now());
			aDto.setJustificatif("justificatif update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(aDto);

			String jsonReponse = mockMvc.perform(put("/absences") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			AbsenceDto res = objectMapper.readValue(jsonReponse, AbsenceDto.class);
			assertEquals(res.getId(), aDto.getId());
			assertEquals(res.getDateDebut(), aDto.getDateDebut());
			assertEquals(res.getDateFin(), aDto.getDateFin());
			assertEquals(res.getJustificatif(), aDto.getJustificatif());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/absences/"+ idAbsence) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
