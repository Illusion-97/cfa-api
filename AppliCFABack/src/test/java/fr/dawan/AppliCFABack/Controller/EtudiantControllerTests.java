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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.EntrepriseRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.GroupeEtudiantRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.NoteRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

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

	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private GroupeEtudiantRepository groupeEtudiantRepository;
	@Autowired
	private PromotionRepository promotionRepository;
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private AbsenceRepository absenceRepository;
	@Autowired
	private InterventionRepository interventionRepository;

	private long idEtudiant;
	
	@BeforeAll
	void init() {
		assertThat(etudiantController).isNotNull();
		initDataBase();
	}
	
	@AfterAll
	void clean(){
		testDelete();
		deleteDatabase();
	}

	@Test
	void testFindAll() {
		try {
			mockMvc.perform(get("/AppliCFABack/etudiants").accept(MediaType.APPLICATION_JSON))
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
			mockMvc.perform(get("/AppliCFABack/etudiants/" + idEtudiant).accept(MediaType.APPLICATION_JSON))
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
			eToInsert.setNom("nom save");
			eToInsert.setPrenom("prenom save");
			eToInsert.setLogin("login save");
			eToInsert.setPassword("pwd save");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eToInsert);

			String jsonReponse = mockMvc.perform(post("/AppliCFABack/etudiants")
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
			eDto.setNom("nom update");
			eDto.setPrenom("prenom update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eDto);

			String jsonReponse = mockMvc.perform(put("/AppliCFABack/etudiants") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			EtudiantDto res = objectMapper.readValue(jsonReponse, EtudiantDto.class);
			assertEquals(res.getId(), eDto.getId());
			assertEquals(res.getNom(),eDto.getNom());
			assertEquals(res.getPrenom(),eDto.getPrenom());
			assertEquals(res.getLogin(),eDto.getLogin());
			assertEquals(res.getPassword(),eDto.getPassword());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	void testGetEntrepriseByIdEtudiant() {
		try {
			mockMvc.perform(get("/AppliCFABack/etudiants/"+idEtudiant+"/entreprise").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.raisonSociale", is("raisone sociale")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetPromotionsByIdEtudiant() {
		try {
			mockMvc.perform(get("/AppliCFABack/etudiants/"+idEtudiant+"/promotions").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].nom", is("promotion 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetGroupesByIdEtudiant() {
		try {
			mockMvc.perform(get("/AppliCFABack/etudiants/"+idEtudiant+"/groupes").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].nom", is("groupe 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetAdresseByIdEtudiant() {
		try {
			mockMvc.perform(get("/AppliCFABack/etudiants/"+idEtudiant+"/adresse").accept(MediaType.APPLICATION_JSON))
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
			mockMvc.perform(get("/AppliCFABack/etudiants/"+idEtudiant+"/notes").accept(MediaType.APPLICATION_JSON))
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
			mockMvc.perform(get("/AppliCFABack/etudiants/"+idEtudiant+"/absences").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].justificatif", is("justificatif")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testGetIntervenionByIdEtudiant() {
		try {
			mockMvc.perform(get("/AppliCFABack/etudiants/"+idEtudiant+"/intervention").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
		
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/AppliCFABack/etudiants/"+idEtudiant) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	void deleteDatabase() {
		
		etudiantRepository.deleteById(idEtudiant+1);
		
		List<Adresse> adresses = adresseRepository.findAll();
		adresseRepository.delete(adresses.get(adresses.size()-1));
		
		List<GroupeEtudiant> groupes = groupeEtudiantRepository.findAll();
		groupeEtudiantRepository.delete(groupes.get(groupes.size()-1));
		
		List<Intervention> interventions = interventionRepository.findAll();
		Intervention inter = interventions.get(interventions.size()-1);
		inter.setPromotion(null);
		interventionRepository.delete(inter);
		
		List<Promotion> promotions = promotionRepository.findAll();
		promotionRepository.delete(promotions.get(promotions.size()-1));
		
		List<Entreprise> entreprises = entrepriseRepository.findAll();
		entrepriseRepository.delete(entreprises.get(entreprises.size()-1));
		
	}

	void initDataBase() {

		Etudiant etudiant = new Etudiant();
		etudiant.setPrenom("prenom 1");
		etudiant.setNom("nom 1");
		etudiant.setLogin("login 1");
		etudiant.setPassword("pwd 1");

		Promotion promotion = new Promotion();
		promotion.setNom("promotion 1");
		promotion.setDateDebut(new Date());
		promotion.setDateFin(new Date());

		GroupeEtudiant groupe = new GroupeEtudiant();
		groupe.setNom("groupe 1");

		Note note = new Note();
		note.setNoteObtenu(20);
		note.setObservations("parfait");
		note.setEtudiant(etudiant);

		Entreprise entreprise = new Entreprise();
		entreprise.setRaisonSociale("raisone sociale");

		Adresse adresse = new Adresse();
		adresse.setNumero(0);
		adresse.setLigne("ligne");
		adresse.setVille("ville");
		adresse.setCodePostal("code postal");

		Absence absence = new Absence();
		absence.setDateDebut(new Date());
		absence.setDateFin(new Date());
		absence.setJustificatif("justificatif");

		Intervention intervention = new Intervention();
		intervention.setDateDebut(new Date());
		intervention.setDateFin(new Date());

		etudiantRepository.save(etudiant);
		groupeEtudiantRepository.save(groupe);
		promotionRepository.save(promotion);
		noteRepository.save(note);
		entrepriseRepository.save(entreprise);
		adresseRepository.save(adresse);
		absenceRepository.save(absence);
		interventionRepository.save(intervention);

		List<Etudiant> lstEtudiant = new ArrayList<Etudiant>();
		List<Promotion> lstPromotion = new ArrayList<Promotion>();
		List<GroupeEtudiant> lstGroupe = new ArrayList<GroupeEtudiant>();

		lstEtudiant.add(etudiant);
		lstPromotion.add(promotion);
		lstGroupe.add(groupe);

		groupe.setEtudiants(lstEtudiant);

		promotion.setEtudiants(lstEtudiant);

		etudiant.setGroupes(lstGroupe);
		etudiant.setPromotions(lstPromotion);
		etudiant.setEntreprise(entreprise);
		etudiant.setAdresse(adresse);

		absence.setEtudiant(etudiant);

		intervention.setPromotion(promotion);

		etudiantRepository.save(etudiant);
		groupeEtudiantRepository.save(groupe);
		promotionRepository.save(promotion);
		absenceRepository.save(absence);
		interventionRepository.save(intervention);
		
		this.idEtudiant = etudiant.getId();
	}
}
