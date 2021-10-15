package fr.dawan.AppliCFABack.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class CongeServiceTest {

	@Autowired
	CongeService congeService;
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private CongeRepository congeRepository;
	
	
	@Test
	void testGetAcquisDisponiblesRestantsByIdUtilisateur() {
		
//		Etudiant etudiant = new Etudiant();
//		etudiant.setPrenom("ETUDIANT Test congeService");
//		etudiant.setNom("Billon");
//		etudiant.setLogin("tbillon@dawan.fr");
//		etudiant.setPassword("pwd");
//		
//		Promotion promotion = new Promotion();
//		promotion.setNom("PROMOTION Test congeService");
//		promotion.setDateDebut(LocalDate.of(2021, 1, 1));
//		promotion.setDateFin(LocalDate.of(2021, 12, 31));
//		
//		Conge conge = new Conge();
//		conge.setDateDebut(LocalDate.of(2021, 1, 27));
//		conge.setDateFin(LocalDate.of(2021, 2, 4));
//		
//		etudiantRepository.save(etudiant);
//		promotionRepository.save(promotion);
//		congeRepository.save(conge);
//		
//		List<Promotion> promos = new ArrayList<Promotion>();
//		promos.add(promotion);
//		
//		List<Etudiant> etudiants = new ArrayList<Etudiant>();
//		etudiants.add(etudiant);
//		
//		List<Conge> conges = new ArrayList<Conge>();
//		conges.add(conge);
//						
//		etudiant.setPromotions(promos);
//		promotion.setEtudiants(etudiants);
//		conge.setUtilisateur(etudiant);
//		
//		etudiantRepository.save(etudiant);
//		promotionRepository.save(promotion);
//		congeRepository.save(conge);
		
		
//		double[] result = congeService.getAcquisDisponiblesRestantsByIdUtilisateur(etudiant.getId());
		
		double[] result = congeService.getAcquisDisponiblesRestantsByIdUtilisateur(23);
		
		System.err.println("result[0] = " + result[0]);
		System.err.println("result[1] = " + result[1]);
		System.err.println("result[2] = " + result[2]);
	}
}
