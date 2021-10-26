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

import fr.dawan.AppliCFABack.controllers.ProjetController;
import fr.dawan.AppliCFABack.dto.ProjetDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ProjetControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProjetController projetController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idProjet;
	
	@BeforeAll
	void init() {
		assertThat(projetController).isNotNull();
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
			mockMvc.perform(get("/projets").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/projets/" + idProjet).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nom", is("nom 1")))
					.andExpect(jsonPath("$.description", is("description 1")))
					.andExpect(jsonPath("$.pjCahierDesCharges", is("pjCahierDesCharges 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSave() {
		try {
			ProjetDto eToInsert = new ProjetDto();
			eToInsert.setNom("nom projet save");
			eToInsert.setDescription("description projet save");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eToInsert);

			String jsonReponse = mockMvc.perform(post("/projets")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			ProjetDto eDto = objectMapper.readValue(jsonReponse, ProjetDto.class);
			assertTrue(eDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		try {
			ProjetDto eDto = projetController.getById(idProjet+1);
			eDto.setNom("nom projet update");
			eDto.setDescription("description projet update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eDto);

			String jsonReponse = mockMvc.perform(put("/projets") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			ProjetDto res = objectMapper.readValue(jsonReponse, ProjetDto.class);
			assertEquals(res.getId(), eDto.getId());
			assertEquals(res.getNom(), eDto.getNom());
			assertEquals(res.getDescription(), eDto.getDescription());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/projets/"+ idProjet) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
