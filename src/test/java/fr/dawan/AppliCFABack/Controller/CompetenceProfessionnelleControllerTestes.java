package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.controllers.CompetenceProfessionnelleController;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.services.CompetenceProfessionnelleService;

@SpringBootTest
@AutoConfigureMockMvc
public class CompetenceProfessionnelleControllerTestes {

	@Autowired
	private CompetenceProfessionnelleController competenceProfessionnelleController;
	
	@Autowired 
	private MockMvc mockMvc;

	@MockBean
	private TokenInterceptor tokenInterceptor;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean 
	private CompetenceProfessionnelleService competenceProfessionnelleService;
	
	private List<CompetenceProfessionnelleDto> competenceProfessionnellesDto = new ArrayList<CompetenceProfessionnelleDto>();
	
	@BeforeEach 
	public void beforeEach() throws Exception {
		
		when(tokenInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		
		competenceProfessionnellesDto.add(new CompetenceProfessionnelleDto(1,"TestCompetenceProfessionnelles", (byte) 1 ,1));
		competenceProfessionnellesDto.add(new CompetenceProfessionnelleDto(2,"TestCompetenceProfessionnelles2", (byte) 2 ,1));
	}
	@Test
	void contextLoads() {
		assertThat(competenceProfessionnelleController).isNotNull();
		
	}
	@Test
	void testFindAll() throws Exception {
		
		when(competenceProfessionnelleService.getAllCompetenceProfessionnelle()).thenReturn(competenceProfessionnellesDto);
		
		int numFiche = competenceProfessionnellesDto.get(0).getNumeroFiche();
		int id = (int) competenceProfessionnellesDto.get(0).getId();
		int activiteTypeId =(int) competenceProfessionnellesDto.get(0).getActiviteTypeId();
		int numFiche1 = competenceProfessionnellesDto.get(1).getNumeroFiche();
		int id1 = (int) competenceProfessionnellesDto.get(1).getId();
		int activiteTypeId1 =(int) competenceProfessionnellesDto.get(1).getActiviteTypeId();
		mockMvc.perform(get("/competenceProfessionnelles").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())	
			.andExpect(jsonPath("$.size()", is(competenceProfessionnellesDto.size())))
			.andExpect(jsonPath("$[0].libelle", is(competenceProfessionnellesDto.get(0).getLibelle())))
			.andExpect(jsonPath("$[0].numeroFiche", is(numFiche)))
			.andExpect(jsonPath("$[0].id", is(id)))
			.andExpect(jsonPath("$[0].activiteTypeId", is(activiteTypeId)))
			.andExpect(jsonPath("$[1].libelle", is(competenceProfessionnellesDto.get(1).getLibelle())))
			.andExpect(jsonPath("$[1].numeroFiche", is(numFiche1)))
			.andExpect(jsonPath("$[1].id", is(id1)))
			.andExpect(jsonPath("$[1].activiteTypeId", is(activiteTypeId1)))
			;
	}
	@Test
	void testFindById()  throws Exception{
		int id = (int) competenceProfessionnellesDto.get(0).getId();
		int activiteTypeId =(int) competenceProfessionnellesDto.get(0).getActiviteTypeId();
		when(competenceProfessionnelleService.getById(id)).thenReturn(competenceProfessionnellesDto.get(0));
		
		int numFiche = competenceProfessionnellesDto.get(0).getNumeroFiche();
		mockMvc.perform(get("/competenceProfessionnelles/{id}",id).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())	
		.andExpect(jsonPath("$.libelle", is(competenceProfessionnellesDto.get(0).getLibelle())))
		.andExpect(jsonPath("$.numeroFiche", is(numFiche)))
		.andExpect(jsonPath("$.id", is(id)))
		.andExpect(jsonPath("$.activiteTypeId", is(activiteTypeId)));
	}
	@Test
	void testDeleteById() throws Exception {
		long id = competenceProfessionnellesDto.get(0).getId();
		
		doNothing().when(competenceProfessionnelleService).deleteById(id);
		
		String res = mockMvc.perform(delete("/competenceProfessionnelles/{id}",id).accept(MediaType.TEXT_PLAIN))
		.andExpect(status().isAccepted())	
		.andReturn().getResponse().getContentAsString()
		;
		assertEquals("suppression effectuée", res);
	}

	@Test
	void testInsert() throws Exception {
		
		CompetenceProfessionnelleDto competenceProfessionnelleDto = competenceProfessionnellesDto.get(0);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		String competenceProfessionnelleStr = objectMapper.writeValueAsString(competenceProfessionnelleDto);
		
		when(competenceProfessionnelleService.saveOrUpdate(competenceProfessionnelleDto)).thenReturn(competenceProfessionnelleDto);
		mockMvc.perform(post("/competenceProfessionnelles")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(competenceProfessionnelleStr)
				.accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isCreated());
		
	}
	@Test
	void testUpdate() throws Exception {
		
		CompetenceProfessionnelleDto competenceProfessionnelleDto = competenceProfessionnellesDto.get(0);
		competenceProfessionnelleDto.setNumeroFiche((byte) 3);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		String competenceProfessionnelleStr = objectMapper.writeValueAsString(competenceProfessionnelleDto);
		
		when(competenceProfessionnelleService.saveOrUpdate(competenceProfessionnelleDto)).thenReturn(competenceProfessionnelleDto);
		mockMvc.perform(put("/competenceProfessionnelles")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(competenceProfessionnelleStr)
				.accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());
		
	}
	
}
