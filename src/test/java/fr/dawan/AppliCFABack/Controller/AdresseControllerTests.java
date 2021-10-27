package fr.dawan.AppliCFABack.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import fr.dawan.AppliCFABack.controllers.AdresseController;
import fr.dawan.AppliCFABack.dto.AdresseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class AdresseControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AdresseController adresseController;
	
	@Autowired
	private ObjectMapper objectMapper;

	private long idAdresse;
	
	@BeforeAll
	void init() {
		assertThat(adresseController).isNotNull();
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
			mockMvc.perform(get("/adresses").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindById() {
		try {
			mockMvc.perform(get("/adresses/" + idAdresse).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.numero", is("3")))
					.andExpect(jsonPath("$.rue", is("rue de la poissoniere")))
					.andExpect(jsonPath("$.ville", is("Lille")))
					.andExpect(jsonPath("$.codePostal", is("59000")));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSave() {
		try {
			AdresseDto eToInsert = new AdresseDto();
			eToInsert.setNumero(3);
			eToInsert.setRue("rue save");
			eToInsert.setVille("ville save");
			eToInsert.setCodePostal("code postal save");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eToInsert);

			String jsonReponse = mockMvc.perform(post("/adresses")
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON)
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			AdresseDto eDto = objectMapper.readValue(jsonReponse, AdresseDto.class);
			assertTrue(eDto.getId() != 0);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testUpdate() {

		/*try {
			AdresseDto eDto = adresseController.getById(idAdresse+1);
			eDto.setNumero(4);
			eDto.setRue("rue update");
			eDto.setVille("ville update");
			eDto.setCodePostal("code postal update");

			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(eDto);

			String jsonReponse = mockMvc.perform(put("/adresses") 
					.contentType(MediaType.APPLICATION_JSON) 
					.accept(MediaType.APPLICATION_JSON) 
					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			AdresseDto res = objectMapper.readValue(jsonReponse, AdresseDto.class);
			assertEquals(res.getId(), eDto.getId());
			assertEquals(res.getNumero(), eDto.getNumero());
			assertEquals(res.getRue(), eDto.getRue());
			assertEquals(res.getVille(), eDto.getVille());
			assertEquals(res.getCodePostal(), eDto.getCodePostal());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}*/

	}
	
	@Test
	void testDelete() {

		try {
			String rep = mockMvc.perform(delete("/adresses/"+idAdresse) 
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
