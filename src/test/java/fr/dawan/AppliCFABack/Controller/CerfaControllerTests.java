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
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.RemunerationDto;
import fr.dawan.AppliCFABack.services.EtudiantService;

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
	
	@Autowired
	private EtudiantService etudiantService;

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
			AdresseDto eToInsert = new AdresseDto();
			eToInsert.setNumero(3);
			eToInsert.setRue("rue save");
			eToInsert.setVille("ville save");
			eToInsert.setCodePostal("code postal save");
			EtudiantDto e = etudiantService.getById(1);
				
				RemunerationDto rDto = new RemunerationDto();
				rDto.setDateDebut(LocalDate.now());
				rDto.setDateFin(LocalDate.now());
				rDto.setPourcentage("10%");
				rDto.setSmicOuSmc("smic");
				CerfaDto cDto =  new CerfaDto();
				cDto.setAdresseApprenti(e.getUtilisateurDto().getAdresseDto());
				cDto.setAdresseEmployeur(e.getUtilisateurDto().getAdresseDto());
				cDto.setAdresseRepresentant(e.getUtilisateurDto().getAdresseDto());
				cDto.setAdresseResponsable(e.getUtilisateurDto().getAdresseDto());
				cDto.setAutre("j");
				cDto.setCaisseDeRetraite("S65F4S56FS");
				cDto.setCfaEntreprise("DAWAN");
				cDto.setCfaResponsable("");
				cDto.setCfaSiret("464564646");
				cDto.setCfaUai("46464646");
				cDto.setCodeIdccConvention("44fsfsf4s63f");
				cDto.setCommuneNaissance("paris");
				cDto.setContratNum("742446547");
				cDto.setContratType("QD");
				cDto.setConventionCollectiveApplicable("QDQD");
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
				cDto.setDerogationType("QDQDQDQDQD");
				cDto.setDiplomeCode("456");
				cDto.setDiplomeLePlusEleveObtenu("bac");
				cDto.setDiplomeVise("bac+3");
				cDto.setEffectifEntreprise("200");
				cDto.setEmailApprenti("aa@gmail.com");
				cDto.setEmailEmployeur("aa@gmail.com");
				cDto.setEmployeurPriveOuPublic("public");
				cDto.setEmployeurSpecifique("non");
				
				cDto.setEtudiant(e);
				cDto.setFormationDuree("700 J");
				cDto.setHandicape("NON");
				cDto.setHeureTravail("300");
				cDto.setIntitulePrecisDernierDiplome("BAC");
				cDto.setIntitulePrecisDiplomeVise("BAC +3");
				cDto.setLogement("NON");
				cDto.setMachineRisque("AEAEAEAE");
				cDto.setMinuteTravail("18000");
				cDto.setNaf("54544");
				cDto.setNationalite("FR");
				cDto.setNirApprenti("68546");
				cDto.setNomDeuxiemeTuteur("AEAEA");
				cDto.setNomEmployeur("AEAEAE");
				cDto.setNomNaissanceApprenti("EAEAEAE");
				cDto.setNomOrganisme("AEAEA");
				cDto.setNomPremierTuteur("AEAEA");
				cDto.setNomRepresentant("AEAEAE");
				cDto.setNourriture("0");
				cDto.setNumAvenant("44365435");
				cDto.setNumDepot("425424");
				cDto.setPrenomApprenti("AEAEAE");
				cDto.setPrenomDeuxiemeTuteur("AEAEAE");
				cDto.setPrenomEmployeur("AEAEAE");
				cDto.setPrenomPremierTuteur("AEAEA");
				cDto.setPrenomRepresentant("AEAEAE");
				cDto.setSalaireBrut("15625");
				cDto.setSexe("M");
				cDto.setSiretEtablissement("545678664654");
				cDto.setSiretOrganisme("5456564654845244");
				cDto.setSituationAvantContrat("Chomage");
				cDto.setSportifs("...,????");
				cDto.setReceptionDossier(LocalDate.now());
				cDto.setRegimeSocial("");
				cDto.setRemuneration1(rDto);
				cDto.setRemuneration2(rDto);
				cDto.setRemuneration3(rDto);
				cDto.setRemuneration4(rDto);
				cDto.setTelApprenti("0123456789");
				cDto.setTelEmployeur("0123456789");
				cDto.setValidationEmployeur("56");
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

//		try {
//			String rep = mockMvc.perform(delete("/cerfa/"+16) 
//					.accept(MediaType.TEXT_PLAIN))
//					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
//			assertEquals("suppression effectuée", rep);
//
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
	}

}
