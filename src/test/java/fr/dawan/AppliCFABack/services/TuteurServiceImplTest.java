package fr.dawan.AppliCFABack.services;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.dawan.AppliCFABack.dto.TuteurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Tuteur;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.TuteurRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class TuteurServiceImplTest {
	
	@Autowired
	private TuteurService service;
	
	 @Mock
	 private DtoMapper mapper = new DtoMapperImpl();
	 
	 @Mock
	 private TuteurRepository repository;
	 
	 @Autowired
	    private MockMvc mockMvc;
	 
	    
	 private static Stream<Arguments> byIdProvider() {
		    Tuteur tuteur1 = new Tuteur(1, 448);
		    tuteur1.setId(1L);
		    Tuteur tuteur2 = new Tuteur(5 , 449);
		    tuteur2.setId(5L);
		    List<Tuteur> entities = new ArrayList<>(Arrays.asList(tuteur1, tuteur2));

		    return Stream.of(
		            Arguments.of(entities, 1L, 448L),
		            Arguments.of(entities, 5L, 449L),
		            Arguments.of(entities, 3L, 0L)
		    );
		}

	 @ParameterizedTest
	 @MethodSource("byIdProvider")
		void givenProvider_whenById_thenReturnFoundOrNew(List<Tuteur> entities, long id, long expectedId) {
		    Mockito.when(repository.findById(id))
		            .thenReturn(entities.stream().filter(tuteur -> tuteur.getId() == id).findFirst());

		    Mockito.when(mapper.tuteurTotuteurDto(ArgumentMatchers.any(Tuteur.class))).thenAnswer(invocationOnMock -> {
		        Tuteur tuteurArgument = invocationOnMock.getArgument(0);
		        UtilisateurDto utilisateurDto = tuteurArgument.getUtilisateur() != null ?
		                new UtilisateurDto(tuteurArgument.getUtilisateur().getNom(), tuteurArgument.getUtilisateur().getLogin(), null, null, null, null, null, null, null, null, null, null, null, null, null, expectedId, expectedId, false, false) :
		                null;
		        
		        return new TuteurDto(utilisateurDto);
		    });

		    TuteurDto result = service.getById(id);

		    assertEquals(expectedId, result.getId());
		}

		@Test
	    void getEtudiantBySearch_shouldReturnEtudiantsAndHttpStatusOk() throws Exception {
	        long tuteurId = 1L;
	        int page = 0;
	        int size = 4;
	        String search = "t";

	        mockMvc.perform(MockMvcRequestBuilders.get("/tuteur/{id}/etudiants/{page}/{size}/", tuteurId, page, size)
	                .param("search", search)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(Matchers.greaterThan(0)))
	                .andReturn();
	    }
   
}
