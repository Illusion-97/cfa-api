package fr.dawan.AppliCFABack;

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
		etudiant.setPrenom("Tanguy");
		etudiant.setNom("Billon");
		etudiant.setLogin("tbillon@dawan.fr");
		etudiant.setPassword("pwd");

		Promotion promotion = new Promotion();
		promotion.setNom("Concepteur Developpeur d'Applications NANTES 2021");
		promotion.setDateDebut(LocalDate.now());
		promotion.setDateFin(LocalDate.now().plusYears(1));

		Promotion promotion2 = new Promotion();
		promotion2.setNom("Concepteur Developpeur d'Applications PARIS 2021");
		promotion2.setDateDebut(LocalDate.now());
		promotion2.setDateFin(LocalDate.now().plusYears(1));

		Promotion promotion3 = new Promotion();
		promotion3.setNom("Concepteur Developpeur d'Applications AMIENS 2021");
		promotion3.setDateDebut(LocalDate.now());
		promotion3.setDateFin(LocalDate.now().plusYears(1));

		GroupeEtudiant groupe = new GroupeEtudiant();
		groupe.setNom("groupe 1");

		Note note = new Note();
		note.setNoteObtenu(20);
		note.setObservations("parfait");
		note.setEtudiant(etudiant);

		Entreprise entreprise = new Entreprise();
		entreprise.setRaisonSociale("raisone sociale");

		Adresse adresse = new Adresse();
		adresse.setNumero(12);
		adresse.setRue("rue Gaetan Rondeau");
		adresse.setVille("Nantes");
		adresse.setCodePostal("44200");

		Adresse adresse2 = new Adresse();
		adresse2.setNumero(11);
		adresse2.setRue("rue Antoine Bourdelle");
		adresse2.setVille("Paris");
		adresse2.setCodePostal("75015");

		Absence absence = new Absence();
		absence.setDateDebut(LocalDate.now());
		absence.setDateFin(LocalDate.now());
		absence.setJustificatif("justificatif");

		// Formation
		Formation formation = new Formation();

		formation.setTitre("JAVA");
		formation.setContenu(
				"La formation : Java Initiation + Approfondissement représente le point de départ de votre apprentissage. Elle s'adresse à des développeurs ayant déjà des bases d'algorithmique et des connaissances sur un langage de programmation.");

		Formation formation2 = new Formation();
		formation2.setTitre("Usine logicielle");
		formation2.setContenu("Initiation gitlab / ligne de commande bash linux");

		Formation formation3 = new Formation();
		formation3.setTitre("Postgres SQL");
		formation3.setContenu("Administration Postgres");

		Formation formation4 = new Formation();
		formation4.setTitre("Spring MVC");
		formation4.setContenu("");

		LocalDate date = LocalDate.now();
		LocalDate date2 = date.plusDays(7);
		LocalDate date3 = date2.plusDays(7);
		LocalDate date4 = date3.plusDays(7);

		Intervention intervention = new Intervention();
		intervention.setDateDebut(date);
		intervention.setDateFin(date.plusDays(7));

		Intervention intervention2 = new Intervention();
		intervention2.setDateDebut(date2.plusDays(2));
		intervention2.setDateFin(date2.plusDays(7));

		Intervention intervention3 = new Intervention();
		intervention3.setDateDebut(date3.plusDays(2));
		intervention3.setDateFin(date3.plusDays(7));

		Intervention intervention4 = new Intervention();
		intervention4.setDateDebut(date4.plusDays(2));
		intervention4.setDateFin(date4.plusDays(7));

		// CEF
		CEF cef = new CEF();
		cef.setPrenom("Laurence");
		cef.setNom("Baron Gomez");
		cef.setLogin("lbarongomez@dawan.fr");
		cef.setPassword("pwd");

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
		formateur.setPrenom("Stéphane");
		formateur.setNom("Menut");
		formateur.setLogin("smenut@dawan.fr");
		formateur.setPassword("pwd");

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
		promotionRepository.save(promotion2);
		promotionRepository.save(promotion3);
		noteRepository.save(note);
		entrepriseRepository.save(entreprise);
		adresseRepository.save(adresse);
		adresseRepository.save(adresse2);
		absenceRepository.save(absence);
		interventionRepository.save(intervention);
		interventionRepository.save(intervention2);
		interventionRepository.save(intervention3);
		interventionRepository.save(intervention4);
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
		formationRepository.save(formation2);
		formationRepository.save(formation3);
		formationRepository.save(formation4);
		passageExamenRepository.save(passageExamen);
		projetRepository.save(projet);
		centreFormationRepository.save(centre);

		List<Etudiant> lstEtudiant = new ArrayList<Etudiant>();
		List<Promotion> lstPromotion = new ArrayList<Promotion>();
		List<GroupeEtudiant> lstGroupe = new ArrayList<GroupeEtudiant>();
		List<UtilisateurRole> lstRoleEtudiant = new ArrayList<UtilisateurRole>();
		List<UtilisateurRole> lstRoleFormateur = new ArrayList<UtilisateurRole>();
		List<Intervention> lstInterventions = new ArrayList<Intervention>();

		List<Intervention> lstInterventions1 = new ArrayList<Intervention>();

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
		lstPromotion.add(promotion2);
		lstPromotion.add(promotion3);
		lstGroupe.add(groupe);
		lstRoleEtudiant.add(roleEtudiant);
		lstRoleFormateur.add(roleformateur);
		lstInterventions.add(intervention);
		lstInterventions.add(intervention2);
		lstInterventions.add(intervention3);
		lstInterventions.add(intervention4);

		lstInterventions1.add(intervention);

		lstFormation.add(formation);
		lstFormation.add(formation2);
		lstFormation.add(formation3);
		lstFormation.add(formation4);
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
		promotion.setCef(cef);
		promotion.setCentreFormation(centre);
		promotion.setCursus(cursus);
		promotion.setReferentPedagogique(formateur);
		promotion.setInterventions(lstInterventions1);

		promotion2.setInterventions(lstInterventions1);
		promotion2.setEtudiants(lstEtudiant);
		promotion2.setCef(cef);
		promotion2.setCentreFormation(centre);
		promotion2.setCursus(cursus);
		promotion2.setReferentPedagogique(formateur);

		promotion3.setEtudiants(lstEtudiant);
		promotion3.setCef(cef);
		promotion3.setCentreFormation(centre);
		promotion3.setCursus(cursus);
		promotion3.setReferentPedagogique(formateur);
		promotion3.setInterventions(lstInterventions1);

		etudiant.setGroupes(lstGroupe);
		etudiant.setPromotions(lstPromotion);
		etudiant.setEntreprise(entreprise);
		etudiant.setAdresse(adresse);
		etudiant.setRoles(lstRoleEtudiant);
		etudiant.setManager(cef);
		etudiant.setFormateurReferent(formateur);

		absence.setEtudiant(etudiant);

		intervention.setFormation(formation);
		intervention.setPromotion(lstPromotion);

		intervention2.setFormation(formation2);
		intervention2.setPromotion(lstPromotion);

		intervention3.setFormation(formation3);
		intervention3.setPromotion(lstPromotion);

		intervention4.setFormation(formation4);
		intervention4.setPromotion(lstPromotion);

//		intervention2.setPromotion(promotion);
//		intervention2.setPromotion(promotion2);
//		intervention2.setPromotion(promotion3);

//		intervention3.setPromotion(promotion);
//		intervention3.setPromotion(promotion2);
//		intervention3.setPromotion(promotion3);

//		intervention4.setPromotion(promotion);
//		intervention4.setPromotion(promotion2);
//		intervention4.setPromotion(promotion3);

		intervention.setFormateurs(lstFormateur);

		cursus.setFormations(lstFormation);

		cef.setCentreFormation(centre);

		centre.setAdresse(adresse);
		centre.setEntreprise(entreprise);

		devoir.setIntervention(intervention);
		devoir.setIntervention(intervention2);
		devoir.setIntervention(intervention3);
		devoir.setIntervention(intervention4);

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
		promotionRepository.save(promotion2);
		promotionRepository.save(promotion3);
		noteRepository.save(note);
		entrepriseRepository.save(entreprise);
		adresseRepository.save(adresse);
		absenceRepository.save(absence);
		interventionRepository.save(intervention);
		interventionRepository.save(intervention2);
		interventionRepository.save(intervention3);
		interventionRepository.save(intervention4);
		utilisateurRoleRepository.save(roleEtudiant);
		utilisateurRoleRepository.save(roleformateur);
		utilisateurRoleRepository.save(roleadmin);
		utilisateurRoleRepository.save(rolecef);
		cefRepository.save(cef);
		cursusRepository.save(cursus);
		devoirRepository.save(devoir);
		examenRepository.save(exam);
		formateurRepository.save(formateur);
		formationRepository.save(formation2);
		formationRepository.save(formation3);
		formationRepository.save(formation4);
		passageExamenRepository.save(passageExamen);
		projetRepository.save(projet);
		centreFormationRepository.save(centre);

		this.idEtudiant = etudiant.getId();
	}
}
