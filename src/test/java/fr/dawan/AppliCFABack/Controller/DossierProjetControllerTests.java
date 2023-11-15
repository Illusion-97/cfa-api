package fr.dawan.AppliCFABack.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.controllers.DossierProjetController;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.ProjetDossierProjetDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.services.DossierProjetService;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class DossierProjetControllerTests {


	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private DossierProjetController dossierProjetController;
	@Autowired
	private DossierProjetRepository dossierProjetRepository;
	@Autowired
	private DossierProjetService dossierProjetService;
	@Autowired
	private ObjectMapper objectMapper;
	private DossierProjet dpTest;
	@BeforeAll
	void init() throws DossierProjetException, TemplateException, IOException {
		assertThat(dossierProjetController).isNotNull();
		dpTest = new DossierProjet();
		dpTest.setNom("Dossier de Test");
		//dpTest.setProjet(new Projet("TEST","TEST",1));
		dossierProjetRepository.save(dpTest);
	}
	
	@AfterAll
	void clean(){
	}
	@AfterEach
	void cleanup(){
		dossierProjetRepository.deleteAll();
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
			mockMvc.perform(MockMvcRequestBuilders
							.get("/dossierProjet/" + dpTest.getId())
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$.id", is((int) dpTest.getId())));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	@Transactional
	@DirtiesContext
	void testSave() {
		try {
			DossierProjetDto dpToInsert = new DossierProjetDto();

			// Champs nécessaires a la création d'un Dossier Projet
			dpToInsert.setNom("nom DossierProjet save");
			//dpToInsert.setProjet(ProjetDossierProjetDto.create(1,"testProjet",0));
			dpToInsert.setCompetenceProfessionnelleIds(new ArrayList<>());
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

			String jsonReq = objectMapper.writeValueAsString(dpToInsert);

			String jsonReponse = mockMvc.perform(MockMvcRequestBuilders
					.post("/dossierProjet/save")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

			DossierProjetDto dpDto = objectMapper.readValue(jsonReponse, DossierProjetDto.class);
			assertTrue(dpDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
//	@Test
//	void testUpdate() {
//
//		try {
//			DossierProjetEtudiantDto dpDto = dossierProjetController.getById(idDossierProjet+1);
//			dpDto.setNom("nom DossierProjet update");
//
//			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//			String jsonReq = objectMapper.writeValueAsString(dpDto);
//
//			String jsonReponse = mockMvc.perform(put("/dossierProjet") 
//					.contentType(MediaType.APPLICATION_JSON) 
//					.accept(MediaType.APPLICATION_JSON) 
//					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//			DossierProjetDto res = objectMapper.readValue(jsonReponse, DossierProjetDto.class);
//			assertEquals(res.getId(), dpDto.getId());
//			assertEquals(res.getNom(), dpDto.getNom());
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//
//	}
	
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/dossierProjet/"+ dpTest.getId())
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
