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

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.controllers.ExamenController;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.services.ExamenService;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ExamenControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ExamenController examenController;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ExamenService examenService;

	private long idExamen;
	
	private List<ExamenDto> examenDto = new ArrayList<ExamenDto>();
	
	@MockBean
	private TokenInterceptor tokenInterceptor;
	
	@BeforeEach
	void init() throws Exception {
		when(tokenInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		examenDto.add(new ExamenDto(1L, "Cours Python", "nouveau cours python", 8.4, "PJ examen", LocalDate.of(2015, 29, 19)));
		examenDto.add(new ExamenDto(2L, "Cours Trello", "nouveau cours sur trello", 4.5, "PJ examen", LocalDate.of(2015, 29, 19)));
//		examenDto = InitDataBaseV2.getExamenAll();
//		assertThat(examenController).isNotNull();
////		initDataBase();
	}
	
	@AfterAll
	void clean(){
//		testDelete();
//		deleteDatabase();
	}
		
	@Test
	void contextLoads() {
		assertThat(examenController).isNotNull();
	}
	@Test
	void testFindAll() throws Exception{

			when(examenService.getAllExamen()).thenReturn(examenDto);
			mockMvc.perform(get("/examens").accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.size()", is(examenDto.size())))
		       .andExpect(jsonPath("$[0].titre", is(examenDto.get(0).getTitre())));
	}
	
	@Test
	void testInsert() throws Exception{
		ExamenDto examenToInsert = examenDto.get(0);
		
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		String examenToInsertStr = objectMapper.writeValueAsString(examenToInsert);
		
		when(examenService.saveOrUpdate(examenToInsert)).thenReturn(examenToInsert);
		
		mockMvc.perform(post("/examens")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(examenToInsertStr)
				.accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isCreated());
	
	}

	@Test
	void testFindById() throws Exception{
			//s'il y a un appel à userService.getById(2) ==> retourner l'élément de la liste (et non req vers la Bdd)
			int examenId = (int)examenDto.get(1).getId();
			when(examenService.getById(examenId)).thenReturn(examenDto.get(1));
			
			//récupérer la réponse sous forme de chaine de caractères
			//la convertir en un objet Dto
			//comparer les 2 objets celui en réponse et celui dans la liste initiale
			
			mockMvc.perform(get("/examens/{id}",examenId).accept(MediaType.APPLICATION_JSON))
				   .andExpect(status().isOk())
				   .andExpect(jsonPath("$.id", is(examenId)))
			       .andExpect(jsonPath("$.titre", is(examenDto.get(1).getTitre())));
	
	}
		
	
	
//	@Test
//	void testSave() {
//		try {
//			ExamenDto eToInsert = new ExamenDto();
//			eToInsert.setTitre("Titre de mon examen");
//
//			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//			String jsonReq = objectMapper.writeValueAsString(eToInsert);
//
//			String jsonReponse = mockMvc.perform(post("/examens")
//					.contentType(MediaType.APPLICATION_JSON) 
//					.accept(MediaType.APPLICATION_JSON)
//					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//			ExamenDto eDto = objectMapper.readValue(jsonReponse, ExamenDto.class);
//			assertTrue(eDto.getId() != 0);
//
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//	}
//	
//	@Test
//	void testUpdate() {
//
//		try {
//			ExamenDto eDto = examenController.getById(idExamen+1);
//			eDto.setTitre("Mon titre update");
//
//			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//			String jsonReq = objectMapper.writeValueAsString(eDto);
//
//			String jsonReponse = mockMvc.perform(put("/examens") 
//					.contentType(MediaType.APPLICATION_JSON) 
//					.accept(MediaType.APPLICATION_JSON) 
//					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//			ExamenDto res = objectMapper.readValue(jsonReponse, ExamenDto.class);
//			assertEquals(res.getId(), eDto.getId());
//			assertEquals(res.getTitre(), eDto.getTitre());
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//
//	}
//	
	@Test
	void testDelete() throws Exception{
		long examenId = 2L;
		doNothing().when(examenService).deleteById(examenId);
			
			String rep = mockMvc.perform(delete("/examens/{examenId}", examenId) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted())
					.andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);
		}
	}

