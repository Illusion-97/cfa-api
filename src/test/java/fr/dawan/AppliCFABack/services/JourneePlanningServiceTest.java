package fr.dawan.AppliCFABack.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Utilisateur;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class JourneePlanningServiceTest {

	@Autowired
	JourneePlanningService journeePlanningService;
	
	@Test
	void testGetJourneePlanningFromIntervention() {
		
		Intervention intervention1 = new Intervention();
		intervention1.setDateDebut(LocalDate.of(2021, 4, 1));	// 4 jours ouvrable
		intervention1.setDateFin(LocalDate.of(2021, 4, 7));		//8 jours ouvrable
		
		Intervention intervention2 = new Intervention();
		intervention2.setDateDebut(LocalDate.of(2021, 10, 31));
		intervention2.setDateFin(LocalDate.of(2021, 11, 12));
		
		Utilisateur monFormateur1 = new Utilisateur();
		monFormateur1.setNom("formateur 1 nom");
		monFormateur1.setPrenom("formateur 1 prenom");		
		Formateur formateur1 = new Formateur();
		formateur1.setUtilisateur(monFormateur1);
		
		
		Utilisateur monFormateur2 = new Utilisateur();
		monFormateur2.setNom("formateur 1 nom");
		monFormateur2.setPrenom("formateur 1 prenom");		
		Formateur formateur2 = new Formateur();
		formateur2.setUtilisateur(monFormateur2);
		
		Formation formation1 = new Formation();
		formation1.setContenu("formation 1 contenu");
		formation1.setTitre("formation 1 titre");
		
		Formation formation2 = new Formation();
		formation2.setContenu("formation 2 contenu");
		formation2.setTitre("formation 2 titre");
		
		List<Formateur> formateurs = new ArrayList<Formateur>();
		formateurs.add(formateur1);
		formateurs.add(formateur2);
		
		intervention1.setFormateurs(formateurs);
		intervention1.setFormation(formation1);
		
		intervention2.setFormateurs(formateurs);
		intervention2.setFormation(formation2);
		
		List<JourneePlanningDto> result = new ArrayList<JourneePlanningDto>();
		
		List<Intervention> interventions = new ArrayList<Intervention>();
		interventions.add(intervention1);
		interventions.add(intervention2);
		
		for(Intervention i : interventions)
			result.addAll(journeePlanningService.getJourneePlanningFromIntervention(i));
		
		assertEquals(12, result.size()); 
		
		//Journee 0
		assertEquals(LocalDate.of(2021, 4, 1), result.get(0).getDate());
		assertEquals("formateur 1 nom", result.get(0).getFormateurDto().get(0).getUtilisateurDto().getNom());
		assertEquals("formateur 2 nom", result.get(0).getFormateurDto().get(1).getUtilisateurDto().getNom());
		assertEquals("formation 1 titre", result.get(0).getFormationDto().getTitre());
		
		//Journee 4
		assertEquals(LocalDate.of(2021, 11, 2), result.get(4).getDate());
		assertEquals("formateur 1 nom", result.get(4).getFormateurDto().get(0).getUtilisateurDto().getNom());
		assertEquals("formateur 2 nom", result.get(4).getFormateurDto().get(1).getUtilisateurDto().getNom());
		assertEquals("formation 2 titre", result.get(4).getFormationDto().getTitre());
		
		for(JourneePlanningDto j : result) {
			//On vérifie que les jours fériés sont absents
			assertNotEquals(LocalDate.of(2021, 4, 5),j.getDate());
			assertNotEquals(LocalDate.of(2021, 11, 11),j.getDate());
			
			//On vérifie que les samedi/dimanche sont absents
			assertNotEquals(LocalDate.of(2021, 11, 6),j.getDate());
			assertNotEquals(LocalDate.of(2021, 11, 7),j.getDate());
		}
			
	}
	
	
}
