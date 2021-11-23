package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import fr.dawan.AppliCFABack.controllers.CerfaController;
import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.RemunerationDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class CerfaControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CerfaController cerfaController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idCerfa;
	
	@BeforeAll
	void init() {
		assertThat(cerfaController).isNotNull();
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
			mockMvc.perform(get("/cerfa").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindById() {
		try {
//			mockMvc.perform(get("/adresses/" + idAdresse).accept(MediaType.APPLICATION_JSON))
//					.andExpect(status().isOk())
//					.andExpect(jsonPath("$.numero", is("3")))
//					.andExpect(jsonPath("$.rue", is("rue de la poissoniere")))
//					.andExpect(jsonPath("$.ville", is("Lille")))
//					.andExpect(jsonPath("$.codePostal", is("59000")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSave() {
		try {
				
				RemunerationDto rDto = new RemunerationDto();
				rDto.setDateDebut(LocalDate.now());
				rDto.setDateFin(LocalDate.now());
				rDto.setPourcentage("10%");
				rDto.setSmicOuSmc("smic");
				CerfaDto cDto =  new CerfaDto();
				cDto.setAdresseApprenti(null);
				cDto.setAdresseEmployeur(null);
				cDto.setAdresseRepresentant(null);
				cDto.setAdresseResponsable(null);
				cDto.setAutre("");
				cDto.setCaisseDeRetraite("");
				cDto.setCfaEntreprise("");
				cDto.setCfaResponsable("");
				cDto.setCfaSiret("");
				cDto.setCfaUai("");
				cDto.setCodeIdccConvention("");
				cDto.setCommuneNaissance("");
				cDto.setContratNum("");
				cDto.setContratType("");
				cDto.setConventionCollectiveApplicable("");
				cDto.setDateAvenant(LocalDate.now());
				cDto.setDateConclusion(LocalDate.now());
				cDto.setDateDebutContrat(LocalDate.now());
				cDto.setDateDebutFormation(LocalDate.now());
				cDto.setDateDecision(LocalDate.now());
				cDto.setDateDeNaissance("22/08/1999");
				cDto.setDateDeNaissanceDeuxiemeTuteur("22/08/1999");
				cDto.setDateDeNaissancePremierTuteur("22/08/1999");
				cDto.setDateExamen(LocalDate.now());
				cDto.setDateFinContrat(LocalDate.now());
				cDto.setDepartementNaissance("93420");
				cDto.setDernierDiplome("Bac");
				cDto.setDerniereClasseSuivi("Terminal");
				cDto.setDerogationType("");
				cDto.setDiplomeCode("456");
				cDto.setDiplomeLePlusEleveObtenu("bac");
				cDto.setDiplomeVise("bac+3");
				cDto.setEffectifEntreprise("200");
				cDto.setEmailApprenti("aa@gmail.com");
				cDto.setEmailEmployeur("aa@gmail.com");
				cDto.setEmployeurPriveOuPublic("public");
				cDto.setEmployeurSpecifique("non");
				cDto.setEtudiant(null);
				cDto.setFormationDuree("");
				cDto.setHandicape("");
				cDto.setHeureTravail("");
				cDto.setIntitulePrecisDernierDiplome("");
				cDto.setIntitulePrecisDiplomeVise("");
				cDto.setLogement("");
				cDto.setMachineRisque("");
				cDto.setMinuteTravail("");
				cDto.setNaf("");
				cDto.setNationalite("");
				cDto.setNirApprenti("");
				cDto.setNomDeuxiemeTuteur("");
				cDto.setNomEmployeur("");
				cDto.setNomNaissanceApprenti("");
				cDto.setNomOrganisme("");
				cDto.setNomPremierTuteur("");
				cDto.setNomRepresentant("");
				cDto.setNourriture("");
				cDto.setNumAvenant("");
				cDto.setNumDepot("");
				cDto.setPrenomApprenti("");
				cDto.setPrenomDeuxiemeTuteur("");
				cDto.setPrenomEmployeur("");
				cDto.setPrenomPremierTuteur("");
				cDto.setPrenomRepresentant("");
				cDto.setSalaireBrut("");
				cDto.setSexe("");
				cDto.setSiretEtablissement("");
				cDto.setSiretOrganisme("");
				cDto.setSituationAvantContrat("");
				cDto.setSportifs("");
				cDto.setReceptionDossier(LocalDate.now());
				cDto.setRegimeSocial("");
				cDto.setRemuneration1(rDto);
				cDto.setRemuneration2(rDto);
				cDto.setRemuneration3(rDto);
				cDto.setRemuneration4(rDto);
				cDto.setTelApprenti("");
				cDto.setTelEmployeur("");
				cDto.setValidationEmployeur("");
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(cDto);
			
			String jsonReponse = mockMvc.perform(post("/cerfa")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
			CerfaDto eDto = objectMapper.readValue(jsonReponse, CerfaDto.class);
			assertTrue(eDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		/*try {
			AdresseDto eDto = adresseController.getById(idAdresse+1);
			eDto.setNumero(4);
			eDto.setRue("rue update");
			eDto.setVille("ville update");
			eDto.setCodePostal("code postal update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eDto);

			String jsonReponse = mockMvc.perform(put("/adresses") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			AdresseDto res = objectMapper.readValue(jsonReponse, AdresseDto.class);
			assertEquals(res.getId(), eDto.getId());
			assertEquals(res.getNumero(), eDto.getNumero());
			assertEquals(res.getRue(), eDto.getRue());
			assertEquals(res.getVille(), eDto.getVille());
			assertEquals(res.getCodePostal(), eDto.getCodePostal());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}*/

	}
	
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/cerfa/"+idCerfa) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
