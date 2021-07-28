package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.dawan.AppliCFABack.controllers.CursusController;


@SpringBootTest
public class CursusControllerTests {

//	@Autowired
//	private MockMvc mockMvc;
	
	@Autowired
	private CursusController CursusController;

	
	@Test
	void contextLoads() {
		assertThat(CursusController).isNotNull();
		
	}
	
	void deleteDatabase() {
		
	}
	
	void initDataBase() {
		
	
	}
	
	@Test
	void testFindAll() {
//		
	}
}
