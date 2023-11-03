package fr.dawan.AppliCFABack.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.controllers.SoutenanceController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SoutenanceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SoutenanceController soutenanceController;
    private long id;

    @BeforeAll
    void init() {
        assertThat(soutenanceController).isNotNull();
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
            mockMvc.perform(get("/soutenance").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testFindById() {
        try {
            mockMvc.perform(get("/soutenance/" + id).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
