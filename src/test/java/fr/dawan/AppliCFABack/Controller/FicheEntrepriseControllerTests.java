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

import fr.dawan.AppliCFABack.controllers.FicheEntrepriseController;
import fr.dawan.AppliCFABack.dto.FicheEntrepriseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class FicheEntrepriseControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private FicheEntrepriseController ficheEntrepriseController;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private long idFicheEntreprise;
	
	@BeforeAll
	void init() {
		assertThat(ficheEntrepriseController).isNotNull();
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
			mockMvc.perform(get("/ficheEntreprises").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/ficheEntreprises/" + idFicheEntreprise).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.historique", is("historique 1")))
					.andExpect(jsonPath("$.nomDirigeant", is("nomDirigeant 1")))
					.andExpect(jsonPath("$.secteurActivite", is("secteurActivite 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testSave() {
		try {
			FicheEntrepriseDto feToInsert = new FicheEntrepriseDto();
			feToInsert.setHistorique("historique fiche entreprise save");
			feToInsert.setNomDirigeant("nomDirigeant fiche entreprise save");
			feToInsert.setSecteurActivite("secteurActivite fiche entreprise save");
			
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(feToInsert);

			String jsonReponse = mockMvc.perform(post("/ficheEntreprises")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			FicheEntrepriseDto feDto = objectMapper.readValue(jsonReponse, FicheEntrepriseDto.class);
			assertTrue(feDto.getId() != 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		try {
			FicheEntrepriseDto feDto = ficheEntrepriseController.getById(idFicheEntreprise+1);
			feDto.setHistorique("historique fiche entreprise update");
			feDto.setNomDirigeant("nomDirigeant fiche entreprise update");
			feDto.setSecteurActivite("secteurActivite fiche entreprise update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(feDto);

			String jsonReponse = mockMvc.perform(put("/ficheEntreprises") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			FicheEntrepriseDto res = objectMapper.readValue(jsonReponse, FicheEntrepriseDto.class);
			assertEquals(res.getId(), feDto.getId());
			assertEquals(res.getNomDirigeant(), feDto.getNomDirigeant());
			assertEquals(res.getSecteurActivite(), feDto.getSecteurActivite());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/ficheEntreprises/"+ idFicheEntreprise) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
