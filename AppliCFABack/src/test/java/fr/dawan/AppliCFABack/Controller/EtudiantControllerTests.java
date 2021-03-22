package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.dawan.AppliCFABack.controllers.EtudiantController;
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
public class EtudiantControllerTests {

//	@Autowired
//	private MockMvc mockMvc;
	
	@Autowired
	private EtudiantController etudiantController;
	
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
	
	@Test
	void contextLoads() {
		assertThat(etudiantController).isNotNull();
		//deleteDatabase();
		initDataBase();
	}
	
	void deleteDatabase() {
		etudiantRepository.deleteAll();
		groupeEtudiantRepository.deleteAll();
		promotionRepository.deleteAll();
		noteRepository.deleteAll();
		entrepriseRepository.deleteAll();
		adresseRepository.deleteAll();
		absenceRepository.deleteAll();
		interventionRepository.deleteAll();
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
	}
	
	@Test
	void testFindAll() {
//		try {
//			mockMvc.perform(get("/AppliCFABack/etudiants").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//					.andExpect(jsonPath("$[0].name", is("admin_name")));
//						
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
	}
}
