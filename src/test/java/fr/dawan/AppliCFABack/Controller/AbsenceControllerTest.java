package fr.dawan.AppliCFABack.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.controllers.AbsenceController;
import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.services.AbsenceService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AbsenceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AbsenceController absenceController;
	
	@MockBean
	private AbsenceService absenceService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private TokenInterceptor tokenInterceptor;
	
	private final List<AbsenceDto> absencesDto = new ArrayList<AbsenceDto>();
	
	@BeforeAll
	public void beforeAll() throws Exception {
		when(tokenInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		
//		absencesDto.add(new AbsenceDto(1L, 1, LocalDate.now(), LocalDate.now().plusDays(1), "bouchons", 1L, "RETARD"));
//		absencesDto.add(new AbsenceDto(2L, 1, LocalDate.now(), LocalDate.now().plusDays(1), "bouchons", 1L, "RETARD"));
	}
	
	@Test
	void contextLoads() {
		assertThat(absenceController).isNotNull();
	}

	
	@Test
	void testFindById() throws Exception {
		int id = (int) absencesDto.get(0).getId();
		when(absenceService.getById(id)).thenReturn(absencesDto.get(0));
		int intInterventionId = (int) absencesDto.get(0).getInterventionId();
		
		mockMvc.perform(get("/absences/{id}", id).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(id)))	
			//.andExpect(jsonPath("$.dateDebut", is(dateDebut)))
			.andExpect(jsonPath("$.version", is(absencesDto.get(0).getVersion())))
			//.andExpect(jsonPath("$.dateFin", is(absencesDto.get(0).getDateFin())))
			.andExpect(jsonPath("$.justificatif", is(absencesDto.get(0).getJustificatif())))
			.andExpect(jsonPath("$.interventionId", is(intInterventionId)))
			.andExpect(jsonPath("$.typeAbsence", is(absencesDto.get(0).getTypeAbsence())))
		;
		
	}
	
	@Test
	void testSave() throws Exception {
		AbsenceDto absenceDto = absencesDto.get(0);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		String absenceToAbsenceCoursStr = objectMapper.writeValueAsString(absenceDto);
		
		when(absenceService.saveOrUpdate(absenceDto)).thenReturn(absenceDto);
		mockMvc.perform(post("/absences")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(absenceToAbsenceCoursStr)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				;
	}
	
	@Test
	void testDelete() throws Exception{
		long id = absencesDto.get(0).getId();
		doNothing().when(absenceService).delete(id);
		String res = mockMvc.perform(delete("/absences/{id}", id).accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isAccepted())
				.andReturn().getResponse().getContentAsString();
		assertEquals("suppression effectuée", res);
		
	}
}
