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

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Conge;
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
import fr.dawan.AppliCFABack.entities.StatusConge;
import fr.dawan.AppliCFABack.entities.TypeConge;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CEFRepository;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
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
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRoleRepository;
import fr.dawan.AppliCFABack.services.AbsenceService;
import fr.dawan.AppliCFABack.services.AdresseService;
import fr.dawan.AppliCFABack.services.CEFService;
import fr.dawan.AppliCFABack.services.CentreFormationService;
import fr.dawan.AppliCFABack.services.CongeService;
import fr.dawan.AppliCFABack.services.CursusService;
import fr.dawan.AppliCFABack.services.DevoirService;
import fr.dawan.AppliCFABack.services.EntrepriseService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.ExamenService;
import fr.dawan.AppliCFABack.services.FormateurService;
import fr.dawan.AppliCFABack.services.FormationService;
import fr.dawan.AppliCFABack.services.GroupeEtudiantService;
import fr.dawan.AppliCFABack.services.InterventionService;
import fr.dawan.AppliCFABack.services.NoteService;
import fr.dawan.AppliCFABack.services.PassageExamenService;
import fr.dawan.AppliCFABack.services.ProjetService;
import fr.dawan.AppliCFABack.services.PromotionService;
import fr.dawan.AppliCFABack.services.UtilisateurRoleService;
import fr.dawan.AppliCFABack.services.UtilisateurService;
import fr.dawan.AppliCFABack.tools.HashTools;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class InitDataBase {

	@Autowired
	private UtilisateurService utilisateurService;	
	@Autowired
	private EtudiantService etudiantService;
	@Autowired
	private FormateurService formateurService;
	@Autowired
	private CEFService cefService;
	@Autowired
	private GroupeEtudiantService groupeEtudiantService;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private EntrepriseService entrepriseService;
	@Autowired
	private AdresseService adresseService;
	@Autowired
	private AbsenceService absenceService;
	@Autowired
	private InterventionService interventionService;
	@Autowired
	private UtilisateurRoleService utilisateurRoleService;	
	@Autowired
	private CursusService cursusService;
	@Autowired
	private DevoirService devoirService;
	@Autowired
	private ExamenService examenService;
	@Autowired
	private FormationService formationService;
	@Autowired
	private PassageExamenService passageExamenService;
	@Autowired
	private ProjetService projetService;
	@Autowired
	private CentreFormationService centreFormationService;
	@Autowired
	private CongeService congeService;
	
	@Autowired
	private DtoMapper mapper;

	@Test
	void test() throws Exception {
		initDataBase();
		//deleteDatabase();
	}

	/*
	 * PAS A JOURS
	 */
//	void deleteDatabase() {
//
//		etudiantRepository.deleteById(idEtudiant + 1);
//
//		List<Adresse> adresses = adresseRepository.findAll();
//		adresseRepository.delete(adresses.get(adresses.size() - 1));
//
//		List<GroupeEtudiant> groupes = groupeEtudiantRepository.findAll();
//		groupeEtudiantRepository.delete(groupes.get(groupes.size() - 1));
//
//		List<Intervention> interventions = interventionRepository.findAll();
//		Intervention inter = interventions.get(interventions.size() - 1);
//		inter.setPromotions(null);
//		interventionRepository.delete(inter);
//
//		List<Promotion> promotions = promotionRepository.findAll();
//		promotionRepository.delete(promotions.get(promotions.size() - 1));
//
//		List<Entreprise> entreprises = entrepriseRepository.findAll();
//		entrepriseRepository.delete(entreprises.get(entreprises.size() - 1));
//
//	}
	void initDataBase() throws Exception {

		UtilisateurRole roleEtudiant = new UtilisateurRole();
		roleEtudiant.setIntitule("ETUDIANT");
		UtilisateurRole roleformateur = new UtilisateurRole();
		roleformateur.setIntitule("FORMATEUR");
		UtilisateurRole roleAdmin = new UtilisateurRole();
		roleAdmin.setIntitule("ADMIN");
		UtilisateurRole rolecef = new UtilisateurRole();
		rolecef.setIntitule("CEF");
//		UtilisateurRole roleRef = new UtilisateurRole();
//		roleRef.setIntitule("REFERENT");
		
		Utilisateur admin = new Etudiant();
		admin.setPrenom("Mohamed");
		admin.setNom("Derkaoui");
		admin.setLogin("admin@dawan.fr");
		admin.setPassword("pwd");
		admin.setCivilite("Mr");
		admin.setDateDeNaissance(LocalDate.now());
		admin.setTelephone("06.12.80.45.99");
		
		Etudiant etudiant = new Etudiant();
		etudiant.setPrenom("Tanguy");
		etudiant.setNom("Billon");
		etudiant.setLogin("tbillon@dawan.fr");
		etudiant.setPassword("pwd");
		etudiant.setCivilite("Mr");
		etudiant.setDateDeNaissance(LocalDate.now());
		etudiant.setTelephone("06.12.80.45.99");

		LocalDate promoDate = LocalDate.of(2021, 1, 1);

		Promotion promotion = new Promotion();
		promotion.setNom("Concepteur Developpeur d'Applications NANTES 2021");
		promotion.setDateDebut(promoDate);
		promotion.setDateFin(promoDate.plusYears(1).minusDays(1));

		Promotion promotion2 = new Promotion();
		promotion2.setNom("Concepteur Developpeur d'Applications PARIS 2021");
		promotion2.setDateDebut(promoDate);
		promotion2.setDateFin(promoDate.plusYears(1).minusDays(1));

		Promotion promotion3 = new Promotion();
		promotion3.setNom("Concepteur Developpeur d'Applications AMIENS 2021");
		promotion3.setDateDebut(promoDate);
		promotion3.setDateFin(promoDate.plusYears(1).minusDays(1));

		GroupeEtudiant groupe = new GroupeEtudiant();
		groupe.setNom("groupe 1");

		Note note = new Note();
		note.setNoteObtenu(20);
		note.setObservations("parfait");

		Entreprise entreprise = new Entreprise();
		entreprise.setRaisonSociale("raison sociale");

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
		intervention.setDateFin(date.plusDays(4));

		Intervention intervention2 = new Intervention();
		intervention2.setDateDebut(date2.plusDays(2));
		intervention2.setDateFin(date2.plusDays(4));

		Intervention intervention3 = new Intervention();
		intervention3.setDateDebut(date3.plusDays(2));
		intervention3.setDateFin(date3.plusDays(4));

		Intervention intervention4 = new Intervention();
		intervention4.setDateDebut(date4.plusDays(2));
		intervention4.setDateFin(date4.plusDays(4));

		// CEF
		CEF cef = new CEF();
		cef.setPrenom("Laurence");
		cef.setNom("Baron Gomez");
		cef.setLogin("cef@dawan.fr");
		cef.setPassword("pwd");
		cef.setCivilite("Mme");
		cef.setDateDeNaissance(date);
		cef.setTelephone("06.12.80.45.96");
		

		// Centre Formation
		CentreFormation centre = new CentreFormation();
		centre.setNom("Dawan");

		// CUrsus
		Cursus cursus0 = new Cursus();
		cursus0.setTitre("titre cursus 0");
		Cursus cursus1 = new Cursus();
		cursus1.setTitre("titre cursus 1");
		Cursus cursus2 = new Cursus();
		cursus2.setTitre("titre cursus 2");

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
		formateur.setLogin("formateur@dawan.fr");
		formateur.setPassword("pwd");
		formateur.setCivilite("Mr");
		formateur.setDateDeNaissance(date);
		formateur.setTelephone("06.12.80.45.95");

		// PassageExamen
		PassageExamen passageExamen = new PassageExamen();
		passageExamen.setDateDebut(LocalDate.now());
		passageExamen.setDateFin(LocalDate.now());

		// Projet
		Projet projet = new Projet();
		projet.setNom("Projet Nom");
		projet.setDescription("Description");

		// Conge
		Conge conge = new Conge();
		conge.setDateDebut(LocalDate.now());
		conge.setDateFin(LocalDate.now().plusDays(14));
		conge.setMotif("Covid-19");
		conge.setType(TypeConge.MALADIE);
		conge.setStatus(StatusConge.CONFIRME);
		
		try {
			admin.setPassword(HashTools.hashSHA512(admin.getPassword()));
			etudiant.setPassword(HashTools.hashSHA512(etudiant.getPassword()));
			cef.setPassword(HashTools.hashSHA512(cef.getPassword()));
			formateur.setPassword(HashTools.hashSHA512(formateur.getPassword()));
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		mapper.UtilisateurToUtilisateurDto(admin);

		admin = DtoTools.convert(utilisateurService.insertUpdate(mapper.UtilisateurToUtilisateurDto(admin)), Utilisateur.class);
		etudiant = DtoTools.convert(etudiantService.saveOrUpdate(mapper.EtudiantToEtudiantDto(etudiant)), Etudiant.class);
		cef = DtoTools.convert(cefService.saveOrUpdate(mapper.CEFToCEFDto(cef)), CEF.class);
		formateur = DtoTools.convert(formateurService.saveOrUpdate(mapper.FormateurToFormateurDto(formateur)), Formateur.class);		
		groupe = DtoTools.convert(groupeEtudiantService.saveOrUpdate(mapper.GroupeEtudiantToGroupEtudiantDto(groupe)), GroupeEtudiant.class);
		promotion = DtoTools.convert(promotionService.saveOrUpdate(mapper.PromotionToPromotionDto(promotion)), Promotion.class);
		promotion2 = DtoTools.convert(promotionService.saveOrUpdate(mapper.PromotionToPromotionDto(promotion2)), Promotion.class);
		promotion3 = DtoTools.convert(promotionService.saveOrUpdate(mapper.PromotionToPromotionDto(promotion3)), Promotion.class);
		note = DtoTools.convert(noteService.saveOrUpdate(mapper.NoteToNoteDto(note)), Note.class);
		entreprise = DtoTools.convert(entrepriseService.saveOrUpdate(mapper.EntrepriseToEntrepriseDto(entreprise)), Entreprise.class);
		adresse = DtoTools.convert(adresseService.saveOrUpdate(mapper.AdresseToAdresseDto(adresse)), Adresse.class);
		adresse2 = DtoTools.convert(adresseService.saveOrUpdate(mapper.AdresseToAdresseDto(adresse2)), Adresse.class);
		absence = DtoTools.convert(absenceService.saveOrUpdate(mapper.AbsenceToAbsenceDto(absence)), Absence.class);
		intervention = DtoTools.convert(interventionService.saveOrUpdate(mapper.InterventionToInterventionDto(intervention)), Intervention.class);
		intervention2 = DtoTools.convert(interventionService.saveOrUpdate(mapper.InterventionToInterventionDto(intervention2)), Intervention.class);
		intervention3 = DtoTools.convert(interventionService.saveOrUpdate(mapper.InterventionToInterventionDto(intervention3)), Intervention.class);
		intervention4 = DtoTools.convert(interventionService.saveOrUpdate(mapper.InterventionToInterventionDto(intervention4)), Intervention.class);
		roleEtudiant = DtoTools.convert(utilisateurRoleService.saveOrUpdate(mapper.UtilisateurRoleToUtilisateurRoleDto(roleEtudiant)), UtilisateurRole.class);
		roleformateur = DtoTools.convert(utilisateurRoleService.saveOrUpdate(mapper.UtilisateurRoleToUtilisateurRoleDto(roleformateur)), UtilisateurRole.class);
		roleAdmin = DtoTools.convert(utilisateurRoleService.saveOrUpdate(mapper.UtilisateurRoleToUtilisateurRoleDto(roleAdmin)), UtilisateurRole.class);
		rolecef = DtoTools.convert(utilisateurRoleService.saveOrUpdate(mapper.UtilisateurRoleToUtilisateurRoleDto(rolecef)), UtilisateurRole.class);		
		cursus0 = DtoTools.convert(cursusService.saveOrUpdate(mapper.CursusToCursusDto(cursus0)), Cursus.class);
		cursus1 = DtoTools.convert(cursusService.saveOrUpdate(mapper.CursusToCursusDto(cursus1)), Cursus.class);
		cursus2 = DtoTools.convert(cursusService.saveOrUpdate(mapper.CursusToCursusDto(cursus2)), Cursus.class);
		devoir = DtoTools.convert(devoirService.saveOrUpdate(mapper.DevoirToDevoirDto(devoir)), Devoir.class);
		exam = DtoTools.convert(examenService.saveOrUpdate(mapper.ExamenToExamenDto(exam)), Examen.class);
		formation = DtoTools.convert(formationService.saveOrUpdate(mapper.FormationToFormationDto(formation)), Formation.class);
		formation2 = DtoTools.convert(formationService.saveOrUpdate(mapper.FormationToFormationDto(formation2)), Formation.class);
		formation2 = DtoTools.convert(formationService.saveOrUpdate(mapper.FormationToFormationDto(formation2)), Formation.class);
		formation4 = DtoTools.convert(formationService.saveOrUpdate(mapper.FormationToFormationDto(formation4)), Formation.class);
		passageExamen = DtoTools.convert(passageExamenService.saveOrUpdate(mapper.PassageExamenToPassageExamenDto(passageExamen)), PassageExamen.class);
		projet = DtoTools.convert(projetService.saveOrUpdate(mapper.ProjetToProjetDto(projet)), Projet.class);
		centre = DtoTools.convert(centreFormationService.saveOrUpdate(mapper.CentreFormationToCentreFormationDto(centre)), CentreFormation.class);
		conge = DtoTools.convert(congeService.saveOrUpdate(mapper.CongeToCongeDto(conge)), Conge.class);

		List<Etudiant> lstEtudiant = new ArrayList<Etudiant>();
		List<Promotion> lstPromotion = new ArrayList<Promotion>();
		List<Promotion> lstPromotion1 = new ArrayList<Promotion>();
		List<GroupeEtudiant> lstGroupe = new ArrayList<GroupeEtudiant>();
		List<UtilisateurRole> lstRoleAdmin = new ArrayList<UtilisateurRole>();
		List<UtilisateurRole> lstRoleEtudiant = new ArrayList<UtilisateurRole>();
		List<UtilisateurRole> lstRoleFormateur = new ArrayList<UtilisateurRole>();
		List<UtilisateurRole> lstRoleCef = new ArrayList<UtilisateurRole>();
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
		lstPromotion1.add(promotion);
		lstGroupe.add(groupe);
		lstRoleAdmin.add(roleAdmin);
		lstRoleEtudiant.add(roleEtudiant);
		lstRoleFormateur.add(roleformateur);
		lstRoleCef.add(rolecef);

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
		lstCursus.add(cursus0);
		lstCursus.add(cursus1);
		lstCursus.add(cursus2);
		lstExamen.add(exam);
		lstFormateur.add(formateur);
		lstPassageExamen.add(passageExamen);
		lstProjet.add(projet);

		roleEtudiant.setUtilisateurs(lstEtudiant.stream().collect(Collectors.toList()));

		entreprise.setAdresseSiege(adresse2);
		groupe.setEtudiants(lstEtudiant);

		promotion.setEtudiants(lstEtudiant);
		promotion.setCef(cef);
		promotion.setCentreFormation(centre); 
		promotion.setCursus(cursus0);
		promotion.setReferentPedagogique(formateur);
		//promotion.setInterventions(lstInterventions);

		//promotion2.setInterventions(lstInterventions1);
		promotion2.setEtudiants(lstEtudiant);
		promotion2.setCef(cef);
		promotion2.setCentreFormation(centre);
		promotion2.setCursus(cursus1);
		promotion2.setReferentPedagogique(formateur);

		promotion3.setEtudiants(lstEtudiant);
		promotion3.setCef(cef);
		promotion3.setCentreFormation(centre);
		promotion3.setCursus(cursus2);
		promotion3.setReferentPedagogique(formateur);
		//promotion3.setInterventions(lstInterventions1);

		etudiant.setGroupes(lstGroupe);
		etudiant.setPromotions(lstPromotion);
		etudiant.setEntreprise(entreprise);
		etudiant.setAdresse(adresse);
		etudiant.setRoles(lstRoleEtudiant);
		etudiant.setManager(cef);
		etudiant.setFormateurReferent(formateur);

		absence.setEtudiant(etudiant);

		intervention.setFormation(formation);
		intervention.setPromotions(lstPromotion1);

		intervention2.setFormation(formation2);
		intervention2.setPromotions(lstPromotion1);

		intervention3.setFormation(formation3);
		intervention3.setPromotions(lstPromotion1);

		intervention4.setFormation(formation4);
		intervention4.setPromotions(lstPromotion1);

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

		cursus2.setFormations(lstFormation);

		admin.setRoles(lstRoleAdmin);
		
		cef.setCentreFormation(centre);
		cef.setRoles(lstRoleCef);
		cef.setAdresse(adresse);
		cef.setEntreprise(entreprise);
		
		centre.setAdresse(adresse);
		centre.setEntreprise(entreprise);

		devoir.setIntervention(intervention);
		devoir.setIntervention(intervention2);
		devoir.setIntervention(intervention3);
		devoir.setIntervention(intervention4);

		exam.setCursus(cursus2);
		exam.setFormation(formation);
		
		passageExamen.setExamen(exam);
		passageExamen.setIntervention(intervention);
		
		note.setEtudiant(etudiant);
		note.setDevoir(devoir);
		note.setExamen(passageExamen);

		formateur.setAdresse(adresse);
		formateur.setEntreprise(entreprise);
		formateur.setInterventions(lstInterventions);
		formateur.setRoles(lstRoleFormateur);

		formation.setCursusLst(lstCursus);		

		projet.setGroupe(groupe);

		conge.setUtilisateur(etudiant);
		
		utilisateurService.insertUpdate(mapper.UtilisateurToUtilisateurDto(admin));	//ATTENTION
		etudiantService.saveOrUpdate(mapper.EtudiantToEtudiantDto(etudiant));
		cefService.saveOrUpdate(mapper.CEFToCEFDto(cef));
		formateurService.saveOrUpdate(mapper.FormateurToFormateurDto(formateur));		
		groupeEtudiantService.saveOrUpdate(mapper.GroupeEtudiantToGroupEtudiantDto(groupe)); //ATTENTION
		promotionService.saveOrUpdate(mapper.PromotionToPromotionDto(promotion));
		promotionService.saveOrUpdate(mapper.PromotionToPromotionDto(promotion2));
		promotionService.saveOrUpdate(mapper.PromotionToPromotionDto(promotion3));
		noteService.saveOrUpdate(mapper.NoteToNoteDto(note));
		entrepriseService.saveOrUpdate(mapper.EntrepriseToEntrepriseDto(entreprise));
		adresseService.saveOrUpdate(mapper.AdresseToAdresseDto(adresse));
		adresseService.saveOrUpdate(mapper.AdresseToAdresseDto(adresse2));
		absenceService.saveOrUpdate(mapper.AbsenceToAbsenceDto(absence));
		interventionService.saveOrUpdate(mapper.InterventionToInterventionDto(intervention));
		interventionService.saveOrUpdate(mapper.InterventionToInterventionDto(intervention2));
		interventionService.saveOrUpdate(mapper.InterventionToInterventionDto(intervention3));
		interventionService.saveOrUpdate(mapper.InterventionToInterventionDto(intervention4));
		utilisateurRoleService.saveOrUpdate(mapper.UtilisateurRoleToUtilisateurRoleDto(roleEtudiant));
		utilisateurRoleService.saveOrUpdate(mapper.UtilisateurRoleToUtilisateurRoleDto(roleformateur));
		utilisateurRoleService.saveOrUpdate(mapper.UtilisateurRoleToUtilisateurRoleDto(roleAdmin));
		utilisateurRoleService.saveOrUpdate(mapper.UtilisateurRoleToUtilisateurRoleDto(rolecef));		
		cursusService.saveOrUpdate(mapper.CursusToCursusDto(cursus0));
		cursusService.saveOrUpdate(mapper.CursusToCursusDto(cursus1));
		cursusService.saveOrUpdate(mapper.CursusToCursusDto(cursus2));
		devoirService.saveOrUpdate(mapper.DevoirToDevoirDto(devoir));
		examenService.saveOrUpdate(mapper.ExamenToExamenDto(exam));
		formationService.saveOrUpdate(mapper.FormationToFormationDto(formation));
		formationService.saveOrUpdate(mapper.FormationToFormationDto(formation2));
		formationService.saveOrUpdate(mapper.FormationToFormationDto(formation2));
		formationService.saveOrUpdate(mapper.FormationToFormationDto(formation4));
		passageExamenService.saveOrUpdate(mapper.PassageExamenToPassageExamenDto(passageExamen));
		projetService.saveOrUpdate(mapper.ProjetToProjetDto(projet));	//ATTENTION
		centreFormationService.saveOrUpdate(mapper.CentreFormationToCentreFormationDto(centre));
		congeService.saveOrUpdate(mapper.CongeToCongeDto(conge));

	}
}
