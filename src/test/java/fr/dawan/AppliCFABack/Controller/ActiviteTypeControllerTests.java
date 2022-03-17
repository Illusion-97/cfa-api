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

import fr.dawan.AppliCFABack.controllers.ActiviteTypeController;
import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.services.ActiviteTypeService;

@SpringBootTest
@AutoConfigureMockMvc
public class ActiviteTypeControllerTests {

@Autowired
private ActiviteTypeController activiteTypeController;

@Autowired 
private MockMvc mockMvc;

@MockBean
private TokenInterceptor tokenInterceptor;

@MockBean
private ActiviteTypeService activiteTypeService;

@Autowired
private ObjectMapper objectMapper;

private List<ActiviteTypeDto> activiteTypesDto = new ArrayList<ActiviteTypeDto>();

@BeforeEach 
public void beforeEach() throws Exception {
	
	when(tokenInterceptor.preHandle(any(), any(), any())).thenReturn(true);
	
//	List<ExamenDto>  exsDto= new ArrayList<ExamenDto>();
//	exsDto.add(new ExamenDto());
	activiteTypesDto.add(new ActiviteTypeDto(1,"TestActiviteType", (byte) 1));
	activiteTypesDto.add(new ActiviteTypeDto(2,"TestActiviteType2", (byte) 2));
	
}
@Test
void contextLoads() {
	assertThat(activiteTypeController).isNotNull();
}

@Test
void testFindAll() throws Exception {
	
	when(activiteTypeService.getAllActiviteType()).thenReturn(activiteTypesDto);
	int numFiche = activiteTypesDto.get(0).getNumeroFiche();
	int id = (int) activiteTypesDto.get(0).getId();
	int numFiche1 = activiteTypesDto.get(1).getNumeroFiche();
	int id1 = (int) activiteTypesDto.get(1).getId();
	mockMvc.perform(get("/activiteTypes").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())	
		.andExpect(jsonPath("$.size()", is(activiteTypesDto.size())))
		.andExpect(jsonPath("$[0].libelle", is(activiteTypesDto.get(0).getLibelle())))
		.andExpect(jsonPath("$[0].numeroFiche", is(numFiche)))
		.andExpect(jsonPath("$[0].id", is(id)))
		.andExpect(jsonPath("$[1].libelle", is(activiteTypesDto.get(1).getLibelle())))
		.andExpect(jsonPath("$[1].numeroFiche", is(numFiche1)))
		.andExpect(jsonPath("$[1].id", is(id1)))
		;
}
@Test
void testFindById()  throws Exception{
	int id = (int) activiteTypesDto.get(0).getId();
	when(activiteTypeService.getById(id)).thenReturn(activiteTypesDto.get(0));
	
	int numFiche = activiteTypesDto.get(0).getNumeroFiche();
	mockMvc.perform(get("/activiteTypes/{id}",id).accept(MediaType.APPLICATION_JSON))
	.andExpect(status().isOk())	
	.andExpect(jsonPath("$.libelle", is(activiteTypesDto.get(0).getLibelle())))
	.andExpect(jsonPath("$.numeroFiche", is(numFiche)))
	.andExpect(jsonPath("$.id", is(id)));
}
@Test
void testDeleteById() throws Exception {
	long id = activiteTypesDto.get(0).getId();
	
	doNothing().when(activiteTypeService).deleteById(id);
	
	String res = mockMvc.perform(delete("/activiteTypes/{id}",id).accept(MediaType.TEXT_PLAIN))
	.andExpect(status().isAccepted())	
	.andReturn().getResponse().getContentAsString()
	;
	assertEquals("suppression effectuée", res);
}
@Test
void testInsert() throws Exception {
	
	ActiviteTypeDto activiteTypeDto = activiteTypesDto.get(0);
	objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	String ActiviteTypeToActiviteTypeStr = objectMapper.writeValueAsString(activiteTypeDto);
	
	when(activiteTypeService.saveOrUpdate(activiteTypeDto)).thenReturn(activiteTypeDto);
	mockMvc.perform(post("/activiteTypes")
			.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
			.content(ActiviteTypeToActiviteTypeStr)
			.accept(MediaType.APPLICATION_JSON))
		   .andExpect(status().isCreated());
	
}
}
