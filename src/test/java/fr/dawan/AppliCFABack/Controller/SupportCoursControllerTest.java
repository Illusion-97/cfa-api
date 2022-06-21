package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
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

import fr.dawan.AppliCFABack.controllers.SupportCoursController;
import fr.dawan.AppliCFABack.dto.SupportCoursDto;
import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.services.SupportCoursService;



@SpringBootTest
@AutoConfigureMockMvc
public class SupportCoursControllerTest {
	
	@Autowired
	private SupportCoursController supportCoursController;
	
	@Autowired 
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private SupportCoursService supportCoursService;
	
	@MockBean
	private TokenInterceptor tokenInterceptor;

//	@Autowired
//	private ObjectMapper objectMapper;
	
	private final List<SupportCoursDto> supportsCoursDto = new ArrayList<SupportCoursDto>();
	private final List<Long> interventionsId = new ArrayList<Long>();
	

	
	@BeforeEach	
	public void beforeEach() throws Exception {
		when(tokenInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		
		Collections.addAll(interventionsId, 1L, 4L);
		supportsCoursDto.add(new SupportCoursDto(1, 1,"suppCours1", "maPieceJointe", interventionsId));
		supportsCoursDto.add(new SupportCoursDto(2, 1,"suppCours2", "maPieceJointe", interventionsId));

	}
	
	@Test
	void contextLoads() {
		assertThat(supportCoursController).isNotNull();
	}
	
	@Test
	void testFindAll() throws Exception {
		when(supportCoursService.getAll()).thenReturn(supportsCoursDto);
		int id = (int) supportsCoursDto.get(0).getId();
		String titre = supportsCoursDto.get(0).getTitre();
		int id1 = (int) supportsCoursDto.get(1).getId();
		String titre1 = supportsCoursDto.get(1).getTitre();
		
		mockMvc.perform(get("/supportCours").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())	
			.andExpect(jsonPath("$.size()", is(supportsCoursDto.size())))
			.andExpect(jsonPath("$[0].id", is(id)))
			.andExpect(jsonPath("$[0].titre", is(titre)))
			.andExpect(jsonPath("$[0].version", is(supportsCoursDto.get(0).getVersion())))
			.andExpect(jsonPath("$[0].pieceJointe", is(supportsCoursDto.get(0).getPieceJointe())))
			
			.andExpect(jsonPath("$[1].id", is(id1)))
			.andExpect(jsonPath("$[1].titre", is(titre1)))
			.andExpect(jsonPath("$[1].version", is(supportsCoursDto.get(0).getVersion())))
			.andExpect(jsonPath("$[1].pieceJointe", is(supportsCoursDto.get(0).getPieceJointe())))
			;
	}
	
	@Test
	void testFindById() throws Exception{
		int id = (int) supportsCoursDto.get(0).getId();
		when(supportCoursService.getById(id)).thenReturn(supportsCoursDto.get(0));
		
		String titre = supportsCoursDto.get(0).getTitre();
		mockMvc.perform(get("/supportCours/{id}", id).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is (id)))
			.andExpect(jsonPath("$.titre", is (titre)))
			.andExpect(jsonPath("$.version", is (supportsCoursDto.get(0).getVersion())))
			.andExpect(jsonPath("$.pieceJointe", is (supportsCoursDto.get(0).getPieceJointe())))
			
		;
	}
	
	@Test
	void insert() throws Exception {
		
		SupportCoursDto supportCoursDto = supportsCoursDto.get(0);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		String suppCoursToSuppCoursStr = objectMapper.writeValueAsString(supportCoursDto);
		
		when(supportCoursService.saveOrUpdate(supportCoursDto)).thenReturn(supportCoursDto);
		mockMvc.perform(post("/supportCours")
			.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
			.content(suppCoursToSuppCoursStr)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			;
	}
	
	@Test
	void deleteById() throws Exception {
		long id = supportsCoursDto.get(0).getId();
		doNothing().when(supportCoursService).delete(id);
		String res = mockMvc.perform(delete("/supportCours/{id}", id).accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isAccepted())
				.andReturn().getResponse().getContentAsString();
		assertEquals("suppression effectuée", res);
		
	}

}
