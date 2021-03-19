package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.dawan.AppliCFABack.controllers.EtudiantController;


@SpringBootTest
public class EtudiantControllerTests {

//	@Autowired
//	private MockMvc mockMvc;
	
	@Autowired
	private EtudiantController etudiantController;
	
	@Autowired
	//private EtudiantRepository etudiantRepository;
	
	@Test
	void contextLoads() {
		assertThat(etudiantController).isNotNull();
		
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
//		etudiantRepository.deleteAll();
//		etudiantRepository.save(new Etudiant());
//		etudiantRepository.save(new Etudiant());
//		etudiantRepository.save(new Etudiant());
	}
}
