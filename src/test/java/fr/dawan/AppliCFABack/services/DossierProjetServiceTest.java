package fr.dawan.AppliCFABack.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;

@SpringBootTest
@ActiveProfiles("dev")
class DossierProjetServiceTest {


    @Autowired
    DossierProjetService service;

    @MockBean
    private DossierProjetRepository repository;

    @MockBean
    private DtoMapper mapper;

    @Test
    void givenNothing_whenAll_thenReturnDtos() {
        DossierProjet dossierProjet1 = new DossierProjet();
        DossierProjet dossierProjet2 = new DossierProjet();
        List<DossierProjet> entities = Arrays.asList(dossierProjet1, dossierProjet2);

        DossierProjetDto projetDto1 = new DossierProjetDto();
        DossierProjetDto projetDto2 = new DossierProjetDto();
        List<DossierProjetDto> dtos = Arrays.asList(projetDto1, projetDto2);

        Mockito.when(repository.findAll()).thenReturn(entities);
        Mockito.when(mapper.dossierProjetToDossierProjetDto(dossierProjet1)).thenReturn(projetDto1);
        Mockito.when(mapper.dossierProjetToDossierProjetDto(dossierProjet2)).thenReturn(projetDto2);

        List<DossierProjetDto> result = service.getAll();
        assertTrue(result.containsAll(dtos));
    }



    @Test
    void givenDto_whenSave_thenAddToList_AndReturnUpdatedDto() {

        List<DossierProjet> entities = new ArrayList<>();
        DossierProjetDto dto = new DossierProjetDto();
        DossierProjet entity = new DossierProjet();

        Mockito.when(mapper.dossierProjetDtoToDossierProjet(dto)).thenReturn(entity);
        Mockito.when(repository.saveAndFlush(entity)).thenAnswer(invocationOnMock -> {
            entity.setId(1L);
            entities.add(entity);
            return entity;
        });
        Mockito.when(mapper.dossierProjetToDossierProjetDto(ArgumentMatchers.any(DossierProjet.class))).thenAnswer(invocationOnMock -> {
            DossierProjet argument = invocationOnMock.getArgument(0);
            return new DossierProjetDto(argument.getId(), argument.getNom(), null, null,
                    null,null, null, null,
                    null, null, argument.getVersion());
        });

        DossierProjetDto result = service.saveOrUpdate(dto);
        assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertTrue(entities.contains(entity))
        );
    }
    

    
	private static Stream<Arguments> byIdProvider() {
		DossierProjet dossierProjet1 = new DossierProjet();
        dossierProjet1.setId(1L);
        DossierProjet dossierProjet2 = new DossierProjet();
        dossierProjet2.setId(2L);
        List<DossierProjet> entities = Arrays.asList(dossierProjet1, dossierProjet2);

        return Stream.of(
                Arguments.of(entities, 1L, 1L),
                Arguments.of(entities, 2L, 2L)
        );
    }

    @ParameterizedTest
    @MethodSource("byIdProvider")
    void givenProvider_whenById_thenReturnFoundOrNew(List<DossierProjet> entities, long id, long expectedId) {
        Mockito.when(repository.findById(id))
                .thenReturn(entities.stream().filter(projet -> projet.getId() == id).findFirst());

        Mockito.when(mapper.dossierProjetToDossierProjetDto(ArgumentMatchers.any(DossierProjet.class))).thenAnswer(invocationOnMock -> {
        	DossierProjet argument = invocationOnMock.getArgument(0);
        	return new DossierProjetDto(argument.getId(), argument.getNom(), null, null,
                    null,null, null, null,
                    null, null, argument.getVersion());
        });

        DossierProjetDto result = service.getById(id);

        assertEquals(expectedId,result.getId());
    }


}


