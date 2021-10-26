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

import fr.dawan.AppliCFABack.controllers.FichePosteController;
import fr.dawan.AppliCFABack.dto.FichePosteDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class FichePosteControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FichePosteController fichePosteController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idFichePoste;
	
	@BeforeAll
	void init() {
		assertThat(fichePosteController).isNotNull();
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
	void testVindById() {
		try {
			mockMvc.perform(get("/fichePostes/" + idFichePoste).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.intitule", is("intitule1")))
			.andExpect(jsonPath("$.nature", is("nature 1")))
			.andExpect(jsonPath("$.mission", is("mission 1")));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void TestSave(){
		try {
			FichePosteDto fToInsert = new FichePosteDto();
			fToInsert.setIntitule("intitule fiche poste save");
			fToInsert.setNature("nature fiche poste save");
			fToInsert.setMission("mission fiche poste save");
			
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(fToInsert);
			
			String jsonReponse = mockMvc.perform(post("/fichePostes")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			FichePosteDto fDto = objectMapper.readValue(jsonReponse, FichePosteDto.class);
			assertTrue(fDto.getId() != 0);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	void testUpdate() {
		try {
			FichePosteDto fDto = fichePosteController.getById(idFichePoste+1);
			fDto.setIntitule("intitule fiche poste update");
			fDto.setNature("nature fiche poste update");
			fDto.setMission("mission fiche poste update");
			
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(fDto);

			String jsonReponse = mockMvc.perform(put("/fichePostes") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
			FichePosteDto res = objectMapper.readValue(jsonReponse, FichePosteDto.class);
			assertEquals(res.getId(), fDto.getId());
			assertEquals(res.getIntitule(), fDto.getIntitule());
			assertEquals(res.getNature(), fDto.getNature());
			assertEquals(res.getMission(), fDto.getMission());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/fichePostes/"+ idFichePoste) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
