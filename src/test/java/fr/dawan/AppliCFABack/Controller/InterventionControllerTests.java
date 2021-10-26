package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import fr.dawan.AppliCFABack.controllers.InterventionController;
import fr.dawan.AppliCFABack.dto.InterventionDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class InterventionControllerTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private InterventionController interventionController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idIntervention;
	
	@BeforeAll
	void init() {
		assertThat(interventionController).isNotNull();
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
			mockMvc.perform(get("/interventions").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/interventions/" + idIntervention).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.dateDebut", is("06/10/2021")))
					.andExpect(jsonPath("$.dateFin", is("08/10/2021")))
					.andExpect(jsonPath("$.noteInfoPersonnel", is("note Info Personnel")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testSave() {
		try {
			InterventionDto iToInsert = new InterventionDto();
			iToInsert.setDateDebut(LocalDate.now());
			iToInsert.setDateFin(LocalDate.now());
			iToInsert.setNoteInfoPersonnel("note info save");
			

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(iToInsert);

			String jsonReponse = mockMvc.perform(post("/interventions")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			InterventionDto iDto = objectMapper.readValue(jsonReponse, InterventionDto.class);
			assertTrue(iDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		/*try {
			InterventionDto iDto = interventionController.getById(idIntervention+1);
			iDto.setDateDebut(LocalDate.now());
			iDto.setDateFin(LocalDate.now());
			iDto.setNoteInfoPersonnel("note info save");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(iDto);

			String jsonReponse = mockMvc.perform(put("/interventions") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			InterventionDto res = objectMapper.readValue(jsonReponse, InterventionDto.class);
			assertEquals(res.getId(), iDto.getId());
			assertEquals(res.getDateDebut(), iDto.getDateDebut());
			assertEquals(res.getDateFin(), iDto.getDateFin());
			assertEquals(res.getNoteInfoPersonnel(), iDto.getNoteInfoPersonnel());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		*/
	}
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/interventions/"+ idIntervention) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
