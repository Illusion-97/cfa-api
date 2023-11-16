package fr.dawan.AppliCFABack.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.controllers.TuteurController;
import fr.dawan.AppliCFABack.dto.TuteurDto;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class TuteurControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TuteurController tuteurController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idTuteur;
	
	@BeforeAll
	void init() {
		assertThat(tuteurController).isNotNull();
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
			mockMvc.perform(get("/tuteur").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
					
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/tuteur/" + idTuteur).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nom", is("tuteur 1")))
					.andExpect(jsonPath("$.prenom", is("tuteur 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSave() {
		try {
			TuteurDto eToInsert = new TuteurDto();
//			eToInsert.setNom("tuteur save nom save");
//			eToInsert.setPrenom("tuteur prenom save");
			
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eToInsert);

			String jsonReponse = mockMvc.perform(post("/tuteur")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			TuteurDto tDto = objectMapper.readValue(jsonReponse, TuteurDto.class);
			assertTrue(tDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		try {
			TuteurDto tDto = tuteurController.getById(idTuteur + 1);
//			cDto.setNom("TuteurDto nom update");
//			cDto.setPrenom("TuteurDto prenom update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(tDto);

			String jsonReponse = mockMvc.perform(put("/formateurs") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			TuteurDto res = objectMapper.readValue(jsonReponse, TuteurDto.class);
			assertEquals(res.getId(), tDto.getId());
//			assertEquals(res.getNom(),tDto.getNom());
//			assertEquals(res.getPrenom(),tDto.getPrenom());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	void testGetEtudiantsByTuteurId() {
		try {
			mockMvc.perform(get("/etudiants/"+idTuteur).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/tuteur/" +idTuteur) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	

	
}
