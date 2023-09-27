package fr.dawan.AppliCFABack.Controller;

import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;
import fr.dawan.AppliCFABack.services.ExperienceProfessionnelleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ExperienceProfessionnelle.class)
class ExperienceProfessionnelleControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ExperienceProfessionnelleService service;
	
	@Test
	void givenNothing_whenAll_thenReturnDtos() throws Exception {
		ExperienceProfessionnelleDto expDto1 = new ExperienceProfessionnelleDto();
	}
	

}
