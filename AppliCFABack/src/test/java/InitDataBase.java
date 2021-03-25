import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.controllers.EtudiantController;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CEFRepository;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;
import fr.dawan.AppliCFABack.repositories.EntrepriseRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.FormationRepository;
import fr.dawan.AppliCFABack.repositories.GroupeEtudiantRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.NoteRepository;
import fr.dawan.AppliCFABack.repositories.PassageExamenRepository;
import fr.dawan.AppliCFABack.repositories.ProjetRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRoleRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class InitDataBase {

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
	@Autowired
	private UtilisateurRoleRepository utilisateurRoleRepository;
	@Autowired
	private CEFRepository cefRepository;
	@Autowired
	private CursusRepository cursusRepository;
	@Autowired
	private DevoirRepository devoirRepository;
	@Autowired
	private ExamenRepository examenRepository;
	@Autowired
	private FormateurRepository formateurRepository;
	@Autowired
	private FormationRepository formationRepository;
	@Autowired
	private PassageExamenRepository passageExamenRepository;
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private CentreFormationRepository centreFormationRepository;

	private long idEtudiant;

	@Test
	void test() {
		initDataBase();
//		deleteDatabase();
	}

	/*
	 * PAS A JOURS
	 */
	void deleteDatabase() {

		etudiantRepository.deleteById(idEtudiant + 1);

		List<Adresse> adresses = adresseRepository.findAll();
		adresseRepository.delete(adresses.get(adresses.size() - 1));

		List<GroupeEtudiant> groupes = groupeEtudiantRepository.findAll();
		groupeEtudiantRepository.delete(groupes.get(groupes.size() - 1));

		List<Intervention> interventions = interventionRepository.findAll();
		Intervention inter = interventions.get(interventions.size() - 1);
		inter.setPromotion(null);
		interventionRepository.delete(inter);

		List<Promotion> promotions = promotionRepository.findAll();
		promotionRepository.delete(promotions.get(promotions.size() - 1));

		List<Entreprise> entreprises = entrepriseRepository.findAll();
		entrepriseRepository.delete(entreprises.get(entreprises.size() - 1));

	}

	void initDataBase() {

		UtilisateurRole roleEtudiant = new UtilisateurRole();
		roleEtudiant.setIntitule("ETUDIANT");
		UtilisateurRole roleformateur = new UtilisateurRole();
		roleformateur.setIntitule("FORMATEUR");
		UtilisateurRole roleadmin = new UtilisateurRole();
		roleadmin.setIntitule("ADMIN");
		UtilisateurRole rolecef = new UtilisateurRole();
		rolecef.setIntitule("CEF");

		Etudiant etudiant = new Etudiant();
		etudiant.setPrenom("prenom 1");
		etudiant.setNom("nom 1");
		etudiant.setLogin("login 1");
		etudiant.setPassword("pwd 1");

		Promotion promotion = new Promotion();
		promotion.setNom("promotion 1");
		promotion.setDateDebut(LocalDate.now());
		promotion.setDateFin(LocalDate.now());

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
		absence.setDateDebut(LocalDate.now());
		absence.setDateFin(LocalDate.now());
		absence.setJustificatif("justificatif");

		Intervention intervention = new Intervention();
		intervention.setDateDebut(LocalDate.now());
		intervention.setDateFin(LocalDate.now());

		// CEF
		CEF cef = new CEF();
		cef.setPrenom("prenom cef");
		cef.setNom("nom cef");
		cef.setLogin("login cef");
		cef.setPassword("pwd cef");

		// Centre Formation
		CentreFormation centre = new CentreFormation();

		// CUrsus
		Cursus cursus = new Cursus();
		cursus.setTitre("titre cursus");

		// DEvoir
		Devoir devoir = new Devoir();
		devoir.setEnonce("Enonce du devoir numero ##");
		devoir.setDateDebut(LocalDate.now());
		devoir.setDateFin(LocalDate.now());

		// Examen
		Examen exam = new Examen();
		exam.setEnonce("enonce examen");

		// Formateur
		Formateur formateur = new Formateur();
		formateur.setPrenom("prenom f");
		formateur.setNom("nom f");
		formateur.setLogin("login f");
		formateur.setPassword("pwd f");

		// Formation
		Formation formation = new Formation();
		formation.setTitre("titre formation");
		formation.setContenu("formation contenu");

		// PassageExamen
		PassageExamen passageExamen = new PassageExamen();
		passageExamen.setDateDebut(LocalDate.now());
		passageExamen.setDateFin(LocalDate.now());

		// Projet
		Projet projet = new Projet();
		projet.setNom("Projet Nom");
		projet.setDescription("Description");
		projet.setPjCahierDesCharges("CDA");

		etudiantRepository.save(etudiant);
		groupeEtudiantRepository.save(groupe);
		promotionRepository.save(promotion);
		noteRepository.save(note);
		entrepriseRepository.save(entreprise);
		adresseRepository.save(adresse);
		absenceRepository.save(absence);
		interventionRepository.save(intervention);
		utilisateurRoleRepository.save(roleEtudiant);
		utilisateurRoleRepository.save(roleformateur);
		utilisateurRoleRepository.save(roleadmin);
		utilisateurRoleRepository.save(rolecef);
		cefRepository.save(cef);
		cursusRepository.save(cursus);
		devoirRepository.save(devoir);
		examenRepository.save(exam);
		formateurRepository.save(formateur);
		formationRepository.save(formation);
		passageExamenRepository.save(passageExamen);
		projetRepository.save(projet);
		centreFormationRepository.save(centre);

		List<Etudiant> lstEtudiant = new ArrayList<Etudiant>();
		List<Promotion> lstPromotion = new ArrayList<Promotion>();
		List<GroupeEtudiant> lstGroupe = new ArrayList<GroupeEtudiant>();
		List<UtilisateurRole> lstRoleEtudiant = new ArrayList<UtilisateurRole>();
		List<UtilisateurRole> lstRoleFormateur = new ArrayList<UtilisateurRole>();
		List<Intervention> lstInterventions = new ArrayList<Intervention>();

		List<Formation> lstFormation = new ArrayList<Formation>();
		List<Devoir> lstDevoir = new ArrayList<Devoir>();
		List<CEF> lstCEF = new ArrayList<CEF>();
		List<CentreFormation> lstCentre = new ArrayList<CentreFormation>();
		List<Cursus> lstCursus = new ArrayList<Cursus>();
		List<Examen> lstExamen = new ArrayList<Examen>();
		List<Formateur> lstFormateur = new ArrayList<Formateur>();
		List<PassageExamen> lstPassageExamen = new ArrayList<PassageExamen>();
		List<Projet> lstProjet = new ArrayList<Projet>();

		lstEtudiant.add(etudiant);
		lstPromotion.add(promotion);
		lstGroupe.add(groupe);
		lstRoleEtudiant.add(roleEtudiant);
		lstRoleFormateur.add(roleformateur);
		lstInterventions.add(intervention);

		lstFormation.add(formation);
		lstDevoir.add(devoir);
		lstCEF.add(cef);
		lstCentre.add(centre);
		lstCursus.add(cursus);
		lstExamen.add(exam);
		lstFormateur.add(formateur);
		lstPassageExamen.add(passageExamen);
		lstProjet.add(projet);

		roleEtudiant.setUtilisateurs(lstEtudiant.stream().collect(Collectors.toList()));

		groupe.setEtudiants(lstEtudiant);

		promotion.setEtudiants(lstEtudiant);

		etudiant.setGroupes(lstGroupe);
		etudiant.setPromotions(lstPromotion);
		etudiant.setEntreprise(entreprise);
		etudiant.setAdresse(adresse);
		etudiant.setRoles(lstRoleEtudiant);

		absence.setEtudiant(etudiant);

		intervention.setPromotion(promotion);
		intervention.setFormation(formation);
		intervention.setFormateurs(lstFormateur);

		cursus.setFormations(lstFormation);

		cef.setCentreFormation(centre);

		centre.setAdresse(adresse);
		centre.setEntreprise(entreprise);

		devoir.setIntervention(intervention);

		exam.setCursus(cursus);
		exam.setFormation(formation);

		formateur.setAdresse(adresse);
		formateur.setEntreprise(entreprise);
		formateur.setInterventions(lstInterventions);
		formateur.setRoles(lstRoleFormateur);

		formation.setCursusLst(lstCursus);

		passageExamen.setExamen(exam);
		passageExamen.setIntervention(intervention);

		projet.setGroupe(groupe);

		etudiantRepository.save(etudiant);
		groupeEtudiantRepository.save(groupe);
		promotionRepository.save(promotion);
		noteRepository.save(note);
		entrepriseRepository.save(entreprise);
		adresseRepository.save(adresse);
		absenceRepository.save(absence);
		interventionRepository.save(intervention);
		utilisateurRoleRepository.save(roleEtudiant);
		utilisateurRoleRepository.save(roleformateur);
		utilisateurRoleRepository.save(roleadmin);
		utilisateurRoleRepository.save(rolecef);
		cefRepository.save(cef);
		cursusRepository.save(cursus);
		devoirRepository.save(devoir);
		examenRepository.save(exam);
		formateurRepository.save(formateur);
		formationRepository.save(formation);
		passageExamenRepository.save(passageExamen);
		projetRepository.save(projet);
		centreFormationRepository.save(centre);

		this.idEtudiant = etudiant.getId();
	}
}
