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

import fr.dawan.AppliCFABack.controllers.DossierProjetController;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class DossierProjetControllerTests {


	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private DossierProjetController dossierProjetController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idDossierProjet;
	
	@BeforeAll
	void init() {
		assertThat(dossierProjetController).isNotNull();
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
			mockMvc.perform(get("/dossierProjet").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/dossierProjet/" + idDossierProjet).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nom", is("nom DossierProjet 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testSave() {
		try {
			DossierProjetDto dpToInsert = new DossierProjetDto();
			dpToInsert.setNom("nom DossierProjet save");
			

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(dpToInsert);

			String jsonReponse = mockMvc.perform(post("/dossierProjet")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			DossierProjetDto dpDto = objectMapper.readValue(jsonReponse, DossierProjetDto.class);
			assertTrue(dpDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		try {
			DossierProjetDto dpDto = dossierProjetController.getById(idDossierProjet+1);
			dpDto.setNom("nom DossierProjet update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(dpDto);

			String jsonReponse = mockMvc.perform(put("/dossierProjet") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			DossierProjetDto res = objectMapper.readValue(jsonReponse, DossierProjetDto.class);
			assertEquals(res.getId(), dpDto.getId());
			assertEquals(res.getNom(), dpDto.getNom());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/dossierProjet/"+ idDossierProjet) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
