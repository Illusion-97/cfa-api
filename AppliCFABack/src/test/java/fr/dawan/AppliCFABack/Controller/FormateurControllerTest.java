package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.controllers.EtudiantController;
import fr.dawan.AppliCFABack.controllers.FormateurController;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.services.FormateurService;

@SpringBootTest
public class FormateurControllerTest {
	@Autowired
	private FormateurRepository formateurRepository;
	@Autowired
	private ObjectMapper objMapper;

	@Autowired
	private FormateurController formateurController;
	
	
	@Test
	void contextLoads() {
		assertThat(formateurController).isNotNull();
		
		initDataBase();
	}
	
	@Test
	void testFindAll() {
		try {
//			mockMvc.perform(get("/AppliCFABack/etudiants").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//					.andExpect(jsonPath("$[0].name", is("admin_name")));
						
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	void initDataBase() {
		formateurRepository.save(new Formateur());
		formateurRepository.save(new Formateur());
		formateurRepository.save(new Formateur());
	}
}
