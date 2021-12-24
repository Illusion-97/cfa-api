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
import fr.dawan.AppliCFABack.entities.Contrat;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.MaitreApprentissage;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.StatusConge;
import fr.dawan.AppliCFABack.entities.TypeConge;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CEFRepository;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.ContratRepository;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;
import fr.dawan.AppliCFABack.repositories.EntrepriseRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.FormationRepository;
import fr.dawan.AppliCFABack.repositories.GroupeEtudiantRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.MaitreApprentissageRepository;
import fr.dawan.AppliCFABack.repositories.NoteRepository;
import fr.dawan.AppliCFABack.repositories.PassageExamenRepository;
import fr.dawan.AppliCFABack.repositories.ProjetRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRoleRepository;
import fr.dawan.AppliCFABack.services.CEFService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FormateurService;
import fr.dawan.AppliCFABack.services.UtilisateurService;

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
	private UtilisateurRepository utilisateurRepository;
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
	@Autowired
	private CongeRepository congeRepository;
	@Autowired
	private MaitreApprentissageRepository maitreApprentissageRepository;
	@Autowired
	private ContratRepository contratRepository;
	
	@Autowired
	private DtoMapper mapper;

	@Test
	void test() throws Exception {
		initDataBase();
	}

	void initDataBase() throws Exception {

		LocalDate date = LocalDate.now();
		LocalDate date2 = date.plusDays(7);
		LocalDate date3 = date2.plusDays(7);
		LocalDate date4 = date3.plusDays(7);

		UtilisateurRole roleEtudiant = new UtilisateurRole();
		roleEtudiant.setIntitule("ETUDIANT");
		UtilisateurRole roleformateur = new UtilisateurRole();
		roleformateur.setIntitule("FORMATEUR");
		UtilisateurRole roleAdmin = new UtilisateurRole();
		roleAdmin.setIntitule("ADMIN");
		UtilisateurRole rolecef = new UtilisateurRole();
		rolecef.setIntitule("CEF");
		UtilisateurRole roleMaitreApprentissage = new UtilisateurRole();
		roleMaitreApprentissage.setIntitule("MAITREAPPRENTISSAGE");
		
		Utilisateur admin = new Utilisateur();
		admin.setPrenom("Mohamed");
		admin.setNom("Derkaoui");
		admin.setLogin("admin@dawan.fr");
		admin.setPassword("pwd");
		admin.setCivilite("M");
		admin.setDateDeNaissance(LocalDate.now());
		admin.setTelephone("06.12.80.45.99");
				
		Utilisateur monEtudiant = new Utilisateur();
		monEtudiant.setPrenom("Tanguy");
		monEtudiant.setNom("Billon");
		monEtudiant.setLogin("tbillon@dawan.fr");
		monEtudiant.setPassword("pwd");
		monEtudiant.setCivilite("M");
		monEtudiant.setDateDeNaissance(LocalDate.now());
		monEtudiant.setTelephone("06.12.80.45.99");
		
		// CEF
		Utilisateur monCEF = new Utilisateur();
		monCEF.setPrenom("Laurence");
		monCEF.setNom("Baron Gomez");
		monCEF.setLogin("cef@dawan.fr");
		monCEF.setPassword("pwd");
		monCEF.setCivilite("Mme");
		monCEF.setDateDeNaissance(date);
		monCEF.setTelephone("06.12.80.45.96");
		

		// Formateur
		Utilisateur monFormateur = new Utilisateur();
		monFormateur.setPrenom("Stéphane");
		monFormateur.setNom("Menut");
		monFormateur.setLogin("formateur@dawan.fr");
		monFormateur.setPassword("pwd");
		monFormateur.setCivilite("M");
		monFormateur.setDateDeNaissance(date);
		monFormateur.setTelephone("06.12.80.45.95");
				
		
		Etudiant etudiant = new Etudiant();
		CEF cef = new CEF();
		Formateur formateur = new Formateur();
		MaitreApprentissage mApprentissage = new MaitreApprentissage();
		
		Contrat contrat = new Contrat();
		contrat.setDateDebut(LocalDate.now());
		contrat.setDateFin(LocalDate.now());

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
		entreprise.setEffectifTotal("200");
		entreprise.setEmployeurType("Employeur type test");
		entreprise.setSiret("42998754800162");
		entreprise.setNaf("Naf test");


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
		
		admin = DtoTools.convert(utilisateurService.insertUpdate(mapper.UtilisateurToUtilisateurDto(admin)), Utilisateur.class);
		monEtudiant = DtoTools.convert(utilisateurService.insertUpdate(mapper.UtilisateurToUtilisateurDto(monEtudiant)), Utilisateur.class);
		monCEF = DtoTools.convert(utilisateurService.insertUpdate(mapper.UtilisateurToUtilisateurDto(monCEF)), Utilisateur.class);
		monFormateur = DtoTools.convert(utilisateurService.insertUpdate(mapper.UtilisateurToUtilisateurDto(monFormateur)), Utilisateur.class);
		
		
		etudiant = DtoTools.convert(etudiantService.saveOrUpdate(mapper.EtudiantToEtudiantDto(etudiant)), Etudiant.class);
		cef = DtoTools.convert(cefService.saveOrUpdate(mapper.CEFToCEFDto(cef)), CEF.class);
		formateur = DtoTools.convert(formateurService.saveOrUpdate(mapper.FormateurToFormateurDto(formateur)), Formateur.class);	
		mApprentissage = maitreApprentissageRepository.save(mApprentissage);	
		
		contrat = contratRepository.save(contrat);
		groupe = groupeEtudiantRepository.save(groupe);
		promotion = promotionRepository.save(promotion);
		promotion2 = promotionRepository.save(promotion2);
		promotion3 = promotionRepository.save(promotion3);
		note = noteRepository.save(note);
		entreprise = entrepriseRepository.save(entreprise);
		adresse = adresseRepository.save(adresse);
		adresse2 = adresseRepository.save(adresse2);
		absence = absenceRepository.save(absence);
		intervention = interventionRepository.save(intervention);
		intervention2 = interventionRepository.save(intervention2);
		intervention3 = interventionRepository.save(intervention3);
		intervention4 = interventionRepository.save(intervention4);
		roleEtudiant = utilisateurRoleRepository.save(roleEtudiant);
		roleformateur = utilisateurRoleRepository.save(roleformateur);
		roleAdmin = utilisateurRoleRepository.save(roleAdmin);
		rolecef = utilisateurRoleRepository.save(rolecef);	
		roleMaitreApprentissage = utilisateurRoleRepository.save(roleMaitreApprentissage);	
		cursus0 = cursusRepository.save(cursus0);
		cursus1 = cursusRepository.save(cursus1);
		cursus2 = cursusRepository.save(cursus2);
		devoir = devoirRepository.save(devoir);
		exam = examenRepository.save(exam);
		formation = formationRepository.save(formation);
		formation2 = formationRepository.save(formation2);
		formation3 = formationRepository.save(formation3);
		formation4 = formationRepository.save(formation4);
		passageExamen = passageExamenRepository.save(passageExamen);
		projet = projetRepository.save(projet);
		centre = centreFormationRepository.save(centre);
		conge = congeRepository.save(conge);

		List<Etudiant> lstEtudiant = new ArrayList<Etudiant>();
		List<Utilisateur> lstUtilisateurEtudiant = new ArrayList<Utilisateur>();
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
		lstUtilisateurEtudiant.add(monEtudiant);
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

		roleEtudiant.setUtilisateurs(lstUtilisateurEtudiant.stream().collect(Collectors.toList()));

		entreprise.setAdresseSiege(adresse2);
		groupe.setEtudiants(lstEtudiant);

		promotion.setEtudiants(lstEtudiant);
		promotion.setCef(cef);
		promotion.setCentreFormation(centre); 
		promotion.setCursus(cursus0);
		promotion.setReferentPedagogique(monFormateur);

		promotion2.setEtudiants(lstEtudiant);
		promotion2.setCef(cef);
		promotion2.setCentreFormation(centre);
		promotion2.setCursus(cursus1);
		promotion2.setReferentPedagogique(monFormateur);

		promotion3.setEtudiants(lstEtudiant);
		promotion3.setCef(cef);
		promotion3.setCentreFormation(centre);
		promotion3.setCursus(cursus2);
		promotion3.setReferentPedagogique(monFormateur);


		monEtudiant.setAdresse(adresse);
		monEtudiant.setRoles(lstRoleEtudiant);
		monEtudiant.setEtudiant(etudiant);
		
		etudiant.setGroupes(lstGroupe);
		etudiant.setPromotions(lstPromotion);	
		etudiant.setUtilisateur(monEtudiant);

//		etudiant.setManager(monCEF);	
		
		absence.setEtudiant(etudiant);

		intervention.setFormateurs(lstFormateur);
		intervention.setFormation(formation);
		intervention.setPromotions(lstPromotion1);

		intervention2.setFormateurs(lstFormateur);
		intervention2.setFormation(formation2);
		intervention2.setPromotions(lstPromotion1);

		intervention3.setFormateurs(lstFormateur);
		intervention3.setFormation(formation3);
		intervention3.setPromotions(lstPromotion1);

		intervention4.setFormateurs(lstFormateur);
		intervention4.setFormation(formation4);
		intervention4.setPromotions(lstPromotion1);		

		cursus2.setFormations(lstFormation);

		admin.setRoles(lstRoleAdmin);
		

		monCEF.setRoles(lstRoleCef);
		monCEF.setAdresse(adresse);
		monCEF.setCef(cef);
		
		cef.setCentreFormation(centre);
		cef.setUtilisateur(monCEF);
		
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


		monFormateur.setAdresse(adresse);
		monFormateur.setRoles(lstRoleFormateur);
		monFormateur.setFormateur(formateur);
		
		mApprentissage.setUtilisateur(monFormateur);
		monFormateur.setMaitreApprentissage(mApprentissage);

		formateur.setInterventions(lstInterventions);
		formateur.setUtilisateur(monFormateur);

		formation.setCursusLst(lstCursus);		

		projet.setGroupe(groupe);

		conge.setUtilisateur(monEtudiant);
		
		contrat.setEtudiant(etudiant);
		contrat.setMaitreApprentissage(mApprentissage);
		
		utilisateurRepository.save(admin);
		utilisateurRepository.save(monEtudiant);
		utilisateurRepository.save(monFormateur);
		utilisateurRepository.save(monCEF);
		etudiantRepository.save(etudiant);
		maitreApprentissageRepository.save(mApprentissage);
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
		utilisateurRoleRepository.save(roleAdmin);
		utilisateurRoleRepository.save(rolecef);
		cefRepository.save(cef);
		cursusRepository.save(cursus0);
		cursusRepository.save(cursus1);
		cursusRepository.save(cursus2);
		devoirRepository.save(devoir);
		examenRepository.save(exam);
		formateurRepository.save(formateur);
		formationRepository.save(formation2);
		formationRepository.save(formation3);
		formationRepository.save(formation4);
		passageExamenRepository.save(passageExamen);
		projetRepository.save(projet);
		centreFormationRepository.save(centre);
		congeRepository.save(conge);
		contratRepository.save(contrat);
	}
}
