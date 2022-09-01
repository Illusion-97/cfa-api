package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.customdtos.PlanningEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionEtudiantDto;
import fr.dawan.AppliCFABack.services.PromotionService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import fr.dawan.AppliCFABack.controllers.PromotionController;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PromotionControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PromotionService promoService;

	@Autowired
	private ObjectMapper objectMapper;

	List<PromotionEtudiantDto> promotions = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		List<PlanningEtudiantDto> plannings = new ArrayList<>();
		plannings.add(new PlanningEtudiantDto(LocalDate.now(),LocalDate.now(),"JavaScript","Stéphane Menut"));

		promotions.add(new PromotionEtudiantDto("Concepteur", "développement d'applications", "1 an", "Java",LocalDate.now(),LocalDate.now(), plannings));
	}

	@Test
	public void getCursusByIdEtudiant_test() throws Exception {
		int idEtudiant = 1;
		when(promoService.getCursusByIdEtudiant(idEtudiant)).thenReturn(promotions);
		mockMvc.perform(get("/promotions/cursus-etudiant/{id}", idEtudiant)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].cursusTitre", is("Concepteur")))
				.andExpect(jsonPath("$[0].cursusDescription", is("développement d'applications")))
				.andExpect(jsonPath("$[0].cursusDuree", is("1 an")))
				.andExpect(jsonPath("$[0].nom", is("Java")))
				.andExpect(jsonPath("$[0].dateDebut", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$[0].dateFin", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$[0].planningsEtudiantDto[0].interventionDateDebut", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$[0].planningsEtudiantDto[0].interventionDateFin", is(LocalDate.now().toString())))
				.andExpect(jsonPath("$[0].planningsEtudiantDto[0].formationTitre", is("JavaScript")))
				.andExpect(jsonPath("$[0].planningsEtudiantDto[0].formateurNom", is("Stéphane Menut")));
	}


	

}
