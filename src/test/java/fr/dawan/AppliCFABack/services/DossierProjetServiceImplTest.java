package fr.dawan.AppliCFABack.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.tools.DossierProjetException;
import freemarker.template.TemplateException;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DossierProjetServiceImplTest {

	@Autowired
    DossierProjetService service;

    @MockBean
    private DossierProjetRepository repository;

    @MockBean
    private DtoMapper mapper = new DtoMapperImpl();


    /*@Test
    void givenNothing_whenAll_thenReturnDtos() {
        DossierProjet projet1 = new DossierProjet();
        DossierProjet projet2 = new DossierProjet();
        List<DossierProjet> entities = Collections.unmodifiableList(projet1);
        DossierProjetDto projetDto1 = new DossierProjetDto();
        DossierProjetDto projetDto2 = new DossierProjetDto();
        List<DossierProjetDto> dtos = List.of(projetDto1,projetDto2);
        Mockito.when(repository.findAll()).thenReturn(entities);
        Mockito.when(mapper.dossierProjetToDossierProjetDto(projet1)).thenReturn(projetDto1);
        Mockito.when(mapper.dossierProjetToDossierProjetDto(projet2)).thenReturn(projetDto2);
        List<DossierProjetDto> result = service.getAll();
        assertTrue(result.containsAll(dtos));
    }*/




    @Test
    void givenDto_whenSave_thenAddToList_AndReturnUpdatedDto() throws DossierProjetException, TemplateException, IOException {

        List<DossierProjet> entities = new ArrayList<>();
        DossierProjetDto dto = new DossierProjetDto();
        DossierProjet entity = new DossierProjet();

        Mockito.when(mapper.dossierProjetDtoToDossierProjet(dto)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenAnswer(invocationOnMock -> {
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
}
