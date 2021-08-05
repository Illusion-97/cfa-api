package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.dawan.AppliCFABack.controllers.PromotionController;


@SpringBootTest
public class PromotionControllerTests {

//	@Autowired
//	private MockMvc mockMvc;
	
	@Autowired
	private PromotionController PromotionController;

	@Test
	void contextLoads() {
		assertThat(PromotionController).isNotNull();
	}
	
	void deleteDatabase() {
	
	}
	
	void initDataBase() {
		
	}
	

}
