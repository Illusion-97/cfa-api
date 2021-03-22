package fr.dawan.AppliCFABack.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;

@Controller
@RequestMapping("/AppliCFABack/test")
public class TestDataController {
/*
	public static void ResetDB() {

//		try {
//			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:8080/AppliCFABack/test","postgres","postgres");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		Date d = Date.valueOf(LocalDate.now());
		Etudiant etudiant = new Etudiant();
		Adresse adresse = new Adresse();
		CEF cef = new CEF();
		CentreFormation centre = new CentreFormation();
		GroupeEtudiant groupe = new GroupeEtudiant();
		Note note = new Note();
		Promotion promo = new Promotion();
		Projet projet = new Projet();
		Examen exam = new Examen();
		Devoir devoir = new Devoir();
		Entreprise entreprise = new Entreprise();
		Absence absence = new Absence();
		PassageExamen passageExamen = new PassageExamen();
		Intervention intervention = new Intervention();
		Formation formation = new Formation();
		Cursus cursus = new Cursus();
		UtilisateurRole utilisateurRole = new UtilisateurRole();

		List<Absence> lstAbsence = new ArrayList<Absence>();
		List<Promotion> lstPromo = new ArrayList<Promotion>();
		List<Etudiant> lstEtudiant = new ArrayList<Etudiant>();
		List<Adresse> lstAdresse = new ArrayList<Adresse>();
		List<CEF> lstCef = new ArrayList<CEF>();
		List<CentreFormation> lstCentre = new ArrayList<CentreFormation>();
		List<GroupeEtudiant> lstGroupe = new ArrayList<GroupeEtudiant>();
		List<Note> lstNote = new ArrayList<Note>();
		List<Projet> lstProjet = new ArrayList<Projet>();
		List<Examen> lstExamen = new ArrayList<Examen>();
		List<Devoir> lstDevoir = new ArrayList<Devoir>();
		List<Entreprise> lstEntreprise = new ArrayList<Entreprise>();
		List<PassageExamen> lstPassageExamen = new ArrayList<PassageExamen>();
		List<Intervention> lstIntervention = new ArrayList<Intervention>();
		List<Formation> lstFormation = new ArrayList<Formation>();
		List<Cursus> lstCursus = new ArrayList<Cursus>();
		List<UtilisateurRole> lstUtilisateurRole = new ArrayList<UtilisateurRole>();
		List<Utilisateur> lstUtilisateur = new ArrayList<Utilisateur>();

		intervention.setFormation(formation);
		intervention.setPromotion(promo);

		formation.setContenu("Contenu de la formation");
		formation.setCursusLst(lstCursus);
		formation.setTitre("#.Net");

		adresse.setNumero(3);
		adresse.setLigne("12");
		adresse.setVille("Rennes");
		adresse.setCodePostal("35");

		centre.setAdresse(adresse);

		cef.setCentreFormation(centre);

		groupe.setNom("groupe1");

		note.setEtudiant(etudiant);

		promo.setNom("Paris");

		projet.setGroupe(groupe);
		projet.setNom("NameProject");

		passageExamen.setExamen(exam);

		devoir.setEnonce("Enonce du devoir numero ##");

		absence.setEtudiant(etudiant);

		lstAbsence.add(absence);

		lstAdresse.add(adresse);
		lstCef.add(cef);
		lstCentre.add(centre);

		lstDevoir.add(devoir);
		lstEntreprise.add(entreprise);
		lstEtudiant.add(etudiant);
		lstExamen.add(exam);
		lstPassageExamen.add(passageExamen);

		lstGroupe.add(groupe);
		lstNote.add(note);
		lstProjet.add(projet);

		lstPromo.add(promo);
		lstIntervention.add(intervention);
		lstFormation.add(formation);
		lstUtilisateurRole.add(utilisateurRole);
		lstCursus.add(cursus);

		entreprise.setRaisonSociale("Raison sociale entreprise");
		entreprise.setAdresseSiege(adresse);

		groupe.setEtudiants(lstEtudiant);

		// Adresse
		Adresse adresse1 = new Adresse(50, "Pasteur", "Bordeaux", "33");
		Adresse adresse2 = new Adresse(22, "Jaures", "Amiens", "80");
		Adresse adresse3 = new Adresse(3, "La chapelle", "Nantes", "44");

		lstAdresse.add(adresse1);
		lstAdresse.add(adresse2);
		lstAdresse.add(adresse3);

		// UtilisateurRole
		UtilisateurRole userEtudiant = new UtilisateurRole();
		UtilisateurRole userFormateur = new UtilisateurRole();
		UtilisateurRole userReferent = new UtilisateurRole();
		UtilisateurRole userAdmin = new UtilisateurRole();
		UtilisateurRole userCEF = new UtilisateurRole();
		userEtudiant.setIntitule("Etudiant");
		userFormateur.setIntitule("Formateur");
		userReferent.setIntitule("Referent");
		userAdmin.setIntitule("Admin");
		userCEF.setIntitule("CEF");

		// Personne

		Utilisateur pEtudiant = new Utilisateur("etudiant", "etudiant", "tata", "tata",
				new Adresse(6, "Pompidou", "Lille", "59"), null, entreprise);
		Utilisateur pEtudiant2 = new Utilisateur("etudiant", "etudiant", "tata", "tata",
				new Adresse(6, "Maugre", "Lille", "59"), null, entreprise);
		Utilisateur pEtudiant3 = new Utilisateur("etudiant", "etudiant", "tata", "tata",
				new Adresse(6, "Mazagran", "Lille", "59"), null, entreprise);

		Utilisateur pFormateur = new Utilisateur("formateur", "formateur", "tutu", "tutu",
				new Adresse(12, "Jean", "Marseille", "13"), null, entreprise);
		Utilisateur pFormateur2 = new Utilisateur("formateur", "formateur", "tutu", "tutu",
				new Adresse(12, "Cervantès", "Marseille", "13"), null, entreprise);
		Utilisateur pFormateur3 = new Utilisateur("formateur", "formateur", "tutu", "tutu",
				new Adresse(12, "Maraîchers", "Marseille", "13"), null, entreprise);

		Utilisateur pReferent = new Utilisateur("referent", "referent", "tata", "tata",
				new Adresse(50, "Pasteur", "Bordeaux", "33"), null, entreprise);
		Utilisateur pReferent2 = new Utilisateur("referent", "referent", "tata", "tata",
				new Adresse(50, "Abbesse", "Bordeaux", "33"), null, entreprise);
		Utilisateur pReferent3 = new Utilisateur("referent", "referent", "tata", "tata",
				new Adresse(50, "Chales ", "Bordeaux", "33"), null, entreprise);

		Utilisateur pAdmin = new Utilisateur("admin", "admin", "toto", "toto",
				new Adresse(3, "La chapelle ", "Nantes", "44"), null, entreprise);
		Utilisateur pAdmin2 = new Utilisateur("admin", "admin", "toto", "toto",
				new Adresse(3, "Adrienne-Lecouvreur", "Paris", "75"), null, entreprise);

		Utilisateur pCEF = new Utilisateur("cef", "cef", "titi", "titi", new Adresse(22, "Jaures", "Amiens", "80"),
				null, entreprise);
		Utilisateur pCEF2 = new Utilisateur("cef", "cef", "titi", "titi",
				new Adresse(22, "Albert Roze", "Amiens", "80"), null, entreprise);
		Utilisateur pCEF3 = new Utilisateur("cef", "cef", "titi", "titi", new Adresse(22, "Allart", "Amiens", "80"),
				null, entreprise);

		lstUtilisateur.add(pEtudiant);
		lstUtilisateur.add(pEtudiant2);
		lstUtilisateur.add(pEtudiant3);
		lstUtilisateur.add(pFormateur2);
		lstUtilisateur.add(pFormateur3);
		lstUtilisateur.add(pFormateur);
		lstUtilisateur.add(pReferent);
		lstUtilisateur.add(pReferent2);
		lstUtilisateur.add(pReferent3);
		lstUtilisateur.add(pAdmin);
		lstUtilisateur.add(pAdmin2);
		lstUtilisateur.add(pCEF);
		lstUtilisateur.add(pCEF2);
		lstUtilisateur.add(pCEF3);

		// Entreprise
		Entreprise entreprise1 = new Entreprise("public", adresse);
		Entreprise entreprise2 = new Entreprise("public", adresse2);
		Entreprise entreprise3 = new Entreprise("prive", adresse3);

		lstEntreprise.add(entreprise1);
		lstEntreprise.add(entreprise2);
		lstEntreprise.add(entreprise3);

		// Centre
		CentreFormation centre1 = new CentreFormation();
		CentreFormation centre2 = new CentreFormation();

		lstCentre.add(centre1);
		lstCentre.add(centre2);

		// Cef
		CEF cef1 = new CEF(centre1);
		CEF cef2 = new CEF(centre2);

		lstCef.add(cef1);
		lstCef.add(cef2);

		// Groupe
		GroupeEtudiant groupe1 = new GroupeEtudiant("GrpParisNantes", lstEtudiant);
		GroupeEtudiant groupe2 = new GroupeEtudiant("GrpParis", lstEtudiant);
		GroupeEtudiant groupe3 = new GroupeEtudiant("GrpNantes", lstEtudiant);

		lstGroupe.add(groupe1);
		lstGroupe.add(groupe2);
		lstGroupe.add(groupe3);

		// Formation
		Formation formation1 = new Formation("JavaFormation", "JavaFormationContenu", lstCursus);
		Formation formation2 = new Formation("DesignFormation", "DesignFormationContenu", lstCursus);
		Formation formation3 = new Formation("SQLFormation", "SQLFormationContenu", lstCursus);

		lstFormation.add(formation1);
		lstFormation.add(formation2);
		lstFormation.add(formation3);

		// Cursus
		Cursus cursus1 = new Cursus("Dev Full Stack", lstFormation);
		Cursus cursus2 = new Cursus("Designer Web", lstFormation);

		// PassageExamen
		PassageExamen passageExamen2 = new PassageExamen(d, d, exam, intervention);

		// Examen
		Examen exam1 = new Examen("enonce1", formation, cursus1);
		Examen exam2 = new Examen("enonce1", formation, cursus1);
		Examen exam3 = new Examen("enonce1", formation, cursus2);

		lstExamen.add(exam1);
		lstExamen.add(exam2);
		lstExamen.add(exam3);
		// Note
		Note note1 = new Note(15, "observations", etudiant, passageExamen2, devoir);
		Note note2 = new Note(19, "Bravo", etudiant, passageExamen, devoir);

		lstNote.add(note1);
		lstNote.add(note2);

		// Promotion
//		Promotion promo1 = new Promotion("Marseille2021", "promo de Marseille");
//		Promotion promo2 = new Promotion("Paris2021", "promo de Paris");
//		Promotion promo3 = new Promotion("Nantes2021", "promo de Nantes");

		Promotion promo1 = new Promotion("promo2020", d, d, cef1, lstEtudiant, centre1, pReferent, cursus1);
		Promotion promo2 = new Promotion("promo2021", d, d, cef2, lstEtudiant, centre2, pReferent, cursus2);

		lstPromo.add(promo1);
		lstPromo.add(promo2);
		// lstPromo.add(promo3);

		// Etudiant
		Etudiant etudiant1 = new Etudiant(lstPromo, lstGroupe);
		
		lstEtudiant.add(etudiant1);

		// Intervention
		Intervention intervention1 = new Intervention(d, d, formation1, intervention, promo1);
		Intervention intervention2 = new Intervention(d, d, formation2, intervention, promo2);

		// Projet

		Projet projetCFA = new Projet("CFA", "descCFA", "Cahier des charges", groupe1);
		Projet projetPlanning = new Projet("Planning", "descPlanning", "Cahier des charges", groupe2);
		Projet projetSkills = new Projet("Skills", "descSkills", "Cahier des charges", groupe3);

		lstProjet.add(projetCFA);
		lstProjet.add(projetSkills);
		lstProjet.add(projetPlanning);

		// Devoir
		Devoir devoir1 = new Devoir("DevoirJava", d, d, intervention1);
		Devoir devoir2 = new Devoir("DevoirC#", d, d, intervention2);
		Devoir devoir3 = new Devoir("DevoirSql", d, d, intervention);

		lstDevoir.add(devoir1);
		lstDevoir.add(devoir2);
		lstDevoir.add(devoir3);

		//

//		lstAbsence.add(absence);
//		lstAdmin.add(admin);
//		lstAdresse.add(adresse);
//		lstCef.add(cef);
//		lstCentre.add(centre);
//		lstCours.add(cours);
//		lstDevoir.add(devoir);
//		lstEntreprise.add(entreprise);
//		lstEtudiant.add(etudiant);
//		lstExamen.add(exam);
//		lstFormateur.add(formateur);
//		lstGroupe.add(groupe);
//		lstNote.add(note);
//		lstProjet.add(projet);
//		lstPromotion.add(promo);
//		lstReferent.add(ref);

	}
*/
}
