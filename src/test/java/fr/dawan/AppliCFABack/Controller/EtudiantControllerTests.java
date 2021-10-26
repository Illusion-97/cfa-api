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

import fr.dawan.AppliCFABack.controllers.EtudiantController;
import fr.dawan.AppliCFABack.dto.EtudiantDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class EtudiantControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EtudiantController etudiantController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idEtudiant;
	
	@BeforeAll
	void init() {
		assertThat(etudiantController).isNotNull();
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
			mockMvc.perform(get("/etudiants").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
					//.andExpect(jsonPath("$[0].nom", is("nom 1"))).andExpect(status().isOk());
					//.andExpect(jsonPath("$[0].prenom", is("prenom 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/etudiants/" + idEtudiant).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nom", is("nom 1")))
					.andExpect(jsonPath("$.prenom", is("prenom 1")))
					.andExpect(jsonPath("$.login", is("login 1")))
					.andExpect(jsonPath("$.password", is("pwd 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSave() {
		try {
			EtudiantDto eToInsert = new EtudiantDto();
//			eToInsert.setNom("nom save");
//			eToInsert.setPrenom("prenom save");
//			eToInsert.setLogin("login save");
//			eToInsert.setPassword("pwd save");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eToInsert);

			String jsonReponse = mockMvc.perform(post("/etudiants")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			EtudiantDto eDto = objectMapper.readValue(jsonReponse, EtudiantDto.class);
			assertTrue(eDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		try {
			EtudiantDto eDto = etudiantController.getById(idEtudiant+1);
//			eDto.setNom("nom update");
//			eDto.setPrenom("prenom update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eDto);

			String jsonReponse = mockMvc.perform(put("/etudiants") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			EtudiantDto res = objectMapper.readValue(jsonReponse, EtudiantDto.class);
			assertEquals(res.getId(), eDto.getId());
//			assertEquals(res.getNom(),eDto.getNom());
//			assertEquals(res.getPrenom(),eDto.getPrenom());
//			assertEquals(res.getLogin(),eDto.getLogin());
//			assertEquals(res.getPassword(),eDto.getPassword());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	void testGetEntrepriseByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/entreprise").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.raisonSociale", is("raisone sociale")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetPromotionsByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/promotions").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].nom", is("promotion 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetGroupesByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/groupes").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].nom", is("groupe 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetAdresseByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/adresse").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.numero", is(0)))
					.andExpect(jsonPath("$.ligne", is("ligne")))
					.andExpect(jsonPath("$.ville", is("ville")))
					.andExpect(jsonPath("$.codePostal", is("code postal")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetNotesByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/notes").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].noteObtenu", is(20)))
					.andExpect(jsonPath("$[0].observations", is("parfait")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetAbsencesByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/absences").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].justificatif", is("justificatif")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetIntervenionByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/intervention").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetFormateurReferentByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/formateurReferent").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetFormateurManagerByIdEtudiant() {
		try {
			mockMvc.perform(get("/etudiants/"+idEtudiant+"/manager").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/etudiants/"+idEtudiant) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	
}
