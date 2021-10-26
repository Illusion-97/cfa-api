package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.controllers.FormateurController;
import fr.dawan.AppliCFABack.dto.FormateurDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class FormateurControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FormateurController formateurController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idFormateur;
	
	
	@BeforeAll
	void init() {
		assertThat(formateurController).isNotNull();
//		initDataBase();
	}
	
	@AfterAll
	void clean(){
//		testDelete();
//		deleteDatabase();
	}

	@Test
	void testFindAll() {
		try {
			mockMvc.perform(get("/formateurs").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
					
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/formateurs/" + idFormateur).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nom", is("formateur 1")))
					.andExpect(jsonPath("$.prenom", is("formateur 1")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSave() {
		try {
			FormateurDto eToInsert = new FormateurDto();
//			eToInsert.setNom("formateur save nom save");
//			eToInsert.setPrenom("formateur prenom save");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eToInsert);

			String jsonReponse = mockMvc.perform(post("/formateurs")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			FormateurDto cDto = objectMapper.readValue(jsonReponse, FormateurDto.class);
			assertTrue(cDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		try {
			FormateurDto cDto = formateurController.getById(idFormateur+1);
//			cDto.setNom("FormateurDto nom update");
//			cDto.setPrenom("FormateurDto prenom update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(cDto);

			String jsonReponse = mockMvc.perform(put("/formateurs") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			FormateurDto res = objectMapper.readValue(jsonReponse, FormateurDto.class);
			assertEquals(res.getId(), cDto.getId());
//			assertEquals(res.getNom(),cDto.getNom());
//			assertEquals(res.getPrenom(),cDto.getPrenom());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/FormateurDto/"+idFormateur) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	

}
