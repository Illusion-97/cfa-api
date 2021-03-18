package fr.dawan.AppliCFABack.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Admin;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.Centre;
import fr.dawan.AppliCFABack.entities.Cours;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Groupe;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.Personne;
import fr.dawan.AppliCFABack.entities.ProgrammePromotion;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Referent;

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
		Formateur formateur = new Formateur();
		Adresse adresse = new Adresse();
		Admin admin = new Admin();
		CEF cef = new CEF();
		Centre centre = new Centre();
		Cours cours = new Cours();
		Groupe groupe = new Groupe();
		Note note = new Note();
		Promotion promo = new Promotion();
		Projet projet = new Projet();
		Referent ref = new Referent();
		Examen exam = new Examen();
		Devoir devoir = new Devoir();
		Entreprise entreprise = new Entreprise();
		ProgrammePromotion programmePromotion = new ProgrammePromotion();
		Absence absence = new Absence();

		List<Absence> lstAbsence = new ArrayList<Absence>();
		List<Promotion> lstPromotion = new ArrayList<Promotion>();
		List<Etudiant> lstEtudiant = new ArrayList<Etudiant>();
		List<Formateur> lstFormateur = new ArrayList<Formateur>();
		List<Adresse> lstAdresse = new ArrayList<Adresse>();
		List<Admin> lstAdmin = new ArrayList<Admin>();
		List<CEF> lstCef = new ArrayList<CEF>();
		List<Centre> lstCentre = new ArrayList<Centre>();
		List<Cours> lstCours = new ArrayList<Cours>();
		List<Groupe> lstGroupe = new ArrayList<Groupe>();
		List<Note> lstNote = new ArrayList<Note>();
		List<Projet> lstProjet = new ArrayList<Projet>();
		List<Referent> lstReferent = new ArrayList<Referent>();
		List<Examen> lstExamen = new ArrayList<Examen>();
		List<Devoir> lstDevoir = new ArrayList<Devoir>();
		List<Entreprise> lstEntreprise = new ArrayList<Entreprise>();

		adresse.setNumero(3);
		adresse.setRue("12");
		adresse.setVille("Rennes");
		adresse.setCodePostal("35");

		centre.setAdresse(adresse);

		cef.setCentre(centre);
		
		centre.setCef(cef);
		
		cours.setNoteEntraide("8");
		cours.setNoteInformation("note d'information");
		
		groupe.setProjet(projet);
		
		note.setEtudiant(etudiant); 
		
		promo.setCentre(centre);
		
		projet.setGroupe(groupe);
		projet.setNom("NameProject");
		
		
		exam.setCours(cours); 
		
		devoir.setConsigne("Consigne du devoir numero ##"); 
		
		
		programmePromotion.setDescription("Java, SQL, UML"); 
		
		absence.setEtudiant(etudiant); 

		lstAbsence.add(absence);
		lstAdmin.add(admin);
		lstAdresse.add(adresse);
		lstCef.add(cef);
		lstCentre.add(centre);
		lstCours.add(cours);
		lstDevoir.add(devoir);
		lstEntreprise.add(entreprise);
		lstEtudiant.add(etudiant);
		lstExamen.add(exam);
		lstFormateur.add(formateur);
		lstGroupe.add(groupe);
		lstNote.add(note);
		lstProjet.add(projet);
		lstPromotion.add(promo);
		lstReferent.add(ref);
		
		ref.setPromotion(lstPromotion); 
		
		entreprise.setEtudiants(lstEtudiant); 
		
		groupe.setEtudiants(lstEtudiant);
		
		centre.setPromotions(lstPromotion);
		centre.setPromotions(lstPromotion);
		

		// Personne

		Personne pEtudiant = new Personne(5, "etudiant", "etudiant", "tata", "tata",
				new Adresse(5, 6, "Pompidou", "Lille", "59"));
		Personne pEtudiant2 = new Personne(6, "etudiant", "etudiant", "tata", "tata",
				new Adresse(9, 6, "Maugre", "Lille", "59"));
		Personne pEtudiant3 = new Personne(7, "etudiant", "etudiant", "tata", "tata",
				new Adresse(10, 6, "Mazagran", "Lille", "59"));

		Personne pFormateur = new Personne(4, "formateur", "formateur", "tutu", "tutu",
				new Adresse(4, 12, "Jean", "Marseille", "13"));
		Personne pFormateur2 = new Personne(8, "formateur", "formateur", "tutu", "tutu",
				new Adresse(11, 12, "Cervantès", "Marseille", "13"));
		Personne pFormateur3 = new Personne(9, "formateur", "formateur", "tutu", "tutu",
				new Adresse(12, 12, "Maraîchers", "Marseille", "13"));

		Personne pReferent = new Personne(3, "referent", "referent", "tata", "tata",
				new Adresse(3, 50, "Pasteur", "Bordeaux", "33"));
		Personne pReferent2 = new Personne(10, "referent", "referent", "tata", "tata",
				new Adresse(13, 50, "Abbesse", "Bordeaux", "33"));
		Personne pReferent3 = new Personne(11, "referent", "referent", "tata", "tata",
				new Adresse(14, 50, "Chales ", "Bordeaux", "33"));

		Personne pAdmin = new Personne(1, "admin", "admin", "toto", "toto",
				new Adresse(1, 3, "La chapelle ", "Nantes", "44"));
		Personne pAdmin2 = new Personne(14, "admin", "admin", "toto", "toto",
				new Adresse(17, 3, "Adrienne-Lecouvreur", "Paris", "75"));

		Personne pCEF = new Personne(2, "cef", "cef", "titi", "titi", new Adresse(2, 22, "Jaures", "Amiens", "80"));
		Personne pCEF2 = new Personne(12, "cef", "cef", "titi", "titi",
				new Adresse(15, 22, "Albert Roze", "Amiens", "80"));
		Personne pCEF3 = new Personne(13, "cef", "cef", "titi", "titi", new Adresse(16, 22, "Allart", "Amiens", "80"));

		// Adresse
		Adresse adresse1 = new Adresse(6, 50, "Pasteur", "Bordeaux", "33");
		Adresse adresse2 = new Adresse(7, 22, "Jaures", "Amiens", "80");
		Adresse adresse3 = new Adresse(8, 3, "La chapelle", "Nantes", "44");
		
		lstAdresse.add(adresse1);
		lstAdresse.add(adresse2);
		lstAdresse.add(adresse3);
		

		// Entreprise
		Entreprise entreprise1 = new Entreprise(1, "Dawan", adresse, lstEtudiant);
		Entreprise entreprise2 = new Entreprise(2, "Jehann", adresse2, lstEtudiant);
		Entreprise entreprise3 = new Entreprise(3, "King", adresse3, lstEtudiant);
		
		lstEntreprise.add(entreprise1);
		lstEntreprise.add(entreprise2);
		lstEntreprise.add(entreprise3);
		

		// Etudiant
		Etudiant etudiant1 = new Etudiant(1, pEtudiant, lstAbsence, entreprise1, lstPromotion, lstGroupe);
		Etudiant etudiant2 = new Etudiant(2, pEtudiant2, lstAbsence, entreprise2, lstPromotion, lstGroupe);
		Etudiant etudiant3 = new Etudiant(3, pEtudiant3, lstAbsence, entreprise3, lstPromotion, lstGroupe);
		
		lstEtudiant.add(etudiant1);
		lstEtudiant.add(etudiant2);
		lstEtudiant.add(etudiant3);

		// Formateur
		Formateur formateur1 = new Formateur(1, pFormateur, lstCours);
		Formateur formateur2 = new Formateur(2, pFormateur2, lstCours);
		Formateur formateur3 = new Formateur(3, pFormateur3, lstCours);
		
		lstFormateur.add(formateur1);
		lstFormateur.add(formateur2);
		lstFormateur.add(formateur3);

		// Admin
		Admin admin1 = new Admin(1, pAdmin);
		Admin admin2 = new Admin(1, pAdmin2);
		
		lstAdmin.add(admin1);
		lstAdmin.add(admin2);

		// Centre
		Centre centre1 = new Centre(1, adresse1, cef, lstPromotion);
		
		lstCentre.add(centre1);

		// Cef
		CEF cef1 = new CEF(1, pCEF, centre1);
		
		lstCef.add(cef1);

		// Cours
		Cours cours1 = new Cours(1, d, d);
		Cours cours2 = new Cours(2, d, d);
		Cours cours3 = new Cours(3, d, d);
		
		lstCours.add(cours1);
		lstCours.add(cours2);
		lstCours.add(cours3);

		// Groupe
		Groupe groupe1 = new Groupe(1, "GrpParisNantes", lstEtudiant, projet);
		Groupe groupe2 = new Groupe(2, "GrpParis", lstEtudiant, projet);
		Groupe groupe3 = new Groupe(3, "GrpNantes", lstEtudiant, projet);

		lstGroupe.add(groupe1);
		lstGroupe.add(groupe2);
		lstGroupe.add(groupe3);

		// Note
		Note note1 = new Note(0, 15.5, exam, etudiant1);

		lstNote.add(note1);

		// Promotion
		Promotion promo1 = new Promotion(1, d, d, centre1, programmePromotion, lstEtudiant, lstCours, ref);
		Promotion promo2 = new Promotion(2, d, d, centre1, programmePromotion, lstEtudiant, lstCours, ref);
		Promotion promo3 = new Promotion(3, d, d, centre1, programmePromotion, lstEtudiant, lstCours, ref);

		lstPromotion.add(promo1);
		lstPromotion.add(promo2);
		lstPromotion.add(promo3);

		// Referent
		Referent ref1 = new Referent(1, pReferent, lstPromotion);
		Referent ref2 = new Referent(2, pReferent2, lstPromotion);
		Referent ref3 = new Referent(3, pReferent3, lstPromotion);

		lstReferent.add(ref1);
		lstReferent.add(ref2);
		lstReferent.add(ref3);

		// Projet
		Projet projetCFA = new Projet(1, "CFA", "descCFA", groupe1, pReferent, null);
		Projet projetPlanning = new Projet(2, "Planning", "descPlanning", groupe2, pReferent2, null);
		Projet projetSkills = new Projet(3, "Skills", "descSkills", groupe3, pReferent3, null);

		lstProjet.add(projetCFA);
		lstProjet.add(projetSkills);
		lstProjet.add(projetPlanning);

		// Examen
		Examen exam1 = new Examen(1, d, lstNote, cours1);
		Examen exam2 = new Examen(2, d, lstNote, cours2);
		Examen exam3 = new Examen(3, d, lstNote, cours3);

		lstExamen.add(exam1);
		lstExamen.add(exam2);
		lstExamen.add(exam3);

		// Devoir
		Devoir devoir1 = new Devoir(1, "DevoirJava", "Créer des classes", d, d, cours1);
		Devoir devoir2 = new Devoir(1, "DevoirC#", "Créer des méthodes", d, d, cours2);
		Devoir devoir3 = new Devoir(1, "DevoirSql", "Exo Requete", d, d, cours3);

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
