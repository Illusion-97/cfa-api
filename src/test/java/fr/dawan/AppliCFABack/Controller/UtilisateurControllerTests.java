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

import fr.dawan.AppliCFABack.controllers.UtilisateurController;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class UtilisateurControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UtilisateurController utilisateurController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idUtilisateur;
	
	@BeforeAll
	void init() {
		assertThat(utilisateurController).isNotNull();
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
			mockMvc.perform(get("/utilisateurs").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/utilisateurs/" + idUtilisateur).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.login", is(" test login")))
					.andExpect(jsonPath("$.password", is("test password")))
					.andExpect(jsonPath("$.prenom", is("prenom test")))
					.andExpect(jsonPath("$.nom", is("test nom")));
					

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSave() {
		try {
			UtilisateurDto eToInsert = new UtilisateurDto();
			eToInsert.setLogin("save login");
			eToInsert.setPassword("save password");
			eToInsert.setPrenom("save prenom");
			eToInsert.setNom("save nom");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eToInsert);

			String jsonReponse = mockMvc.perform(post("/utilisateurs")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			UtilisateurDto eDto = objectMapper.readValue(jsonReponse, UtilisateurDto.class);
			assertTrue(eDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		try {
			UtilisateurDto cDto = utilisateurController.getById(idUtilisateur+1);
			cDto.setLogin("login update");
			cDto.setPassword("password update");
			cDto.setPrenom("prenom update");
			cDto.setNom("nom update");
	

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(cDto);

			String jsonReponse = mockMvc.perform(put("/utilisateurs") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			UtilisateurDto res = objectMapper.readValue(jsonReponse, UtilisateurDto.class);
			assertEquals(res.getId(), cDto.getId());
			assertEquals(res.getNom(),cDto.getNom());
			assertEquals(res.getPrenom(),cDto.getPrenom());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/utilisateurs/"+idUtilisateur) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
