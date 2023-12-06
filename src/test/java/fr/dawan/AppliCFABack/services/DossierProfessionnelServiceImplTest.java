package fr.dawan.AppliCFABack.services;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DossierProfessionnelRepository;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@SpringBootTest
class DossierProfessionnelServiceImplTest {

	@Autowired
    private DossierProfessionnelService service;

    @Mock
    private DossierProfessionnelRepository repository;
    
    @Mock
    private EtudiantService etudiantService;

    @Mock
    private DtoMapper mapper = new DtoMapperImpl();
    
    private Long etudiantId;

    @BeforeEach
    void setUp() {

    }
    
    @Test
    void givenDto_whenSave_thenAddToList_AndReturnUpdatedDto() throws FileNotFoundException, IOException {
        List<DossierProfessionnel> dossierEntities = new ArrayList<>();
        DossierProEtudiantDto dossierDto = new DossierProEtudiantDto(0L, "dossier1", null, null, null, null, null, 0);
        DossierProfessionnel dossierEntity = new DossierProfessionnel("dossier1", null, null, null, null, null, null);
        Mockito.when(mapper.dossierProfessionnelDtoToDossierProfessionnel(dossierDto)).thenReturn(dossierEntity);
        Mockito.when(repository.saveAndFlush(dossierEntity)).thenAnswer(invocationOnMock -> {
            dossierEntity.setId(1L);
            dossierEntities.add(dossierEntity);
            return dossierEntity;
        });
        Mockito.when(mapper.dossierProfessionnelToDossierProfessionnelDto(ArgumentMatchers.any(DossierProfessionnel.class))).thenAnswer(invocationOnMock -> {
            DossierProfessionnel argument = invocationOnMock.getArgument(0);
            return new DossierProEtudiantDto(argument.getId(), argument.getNom(), null, null, null, null, null, argument.getVersion());
        });

        DossierProEtudiantDto result = service.saveOrUpdateDossierProfessionnel(dossierDto, 1, null);
        
        assertEquals(143L, result.getId());
        assertTrue(dossierEntities.stream()
                .anyMatch(dossier -> dossier.getNom().equals("dossier1")));

    }
    
    private static Stream<Arguments> byIdProvider() {
        DossierProfessionnel dossier1 = new DossierProfessionnel("dossier1", null, null, null, null, null, null);
        dossier1.setId(8L);
        DossierProfessionnel dossier2 = new DossierProfessionnel("dossier2", null, null, null, null, null, null);
        dossier2.setId(10L);

        List<DossierProfessionnel> entities = new ArrayList<>(Arrays.asList(dossier1, dossier2));

        return entities.stream()
                .map(entity -> Arguments.of(entities, entity.getId(), entity.getId()));
    }


    @ParameterizedTest
    @MethodSource("byIdProvider")
    void givenProvider_whenById_thenReturnFoundOrNew(List<DossierProfessionnel> entities, long id, long expectedId) {
        Mockito.when(repository.findById(id))
                .thenReturn(entities.stream().filter(projet -> projet.getId() == id).findFirst());

        Mockito.when(mapper.dossierProfessionnelToDossierProEtudiantDto(ArgumentMatchers.any(DossierProfessionnel.class))).thenAnswer(invocationOnMock -> {
            DossierProfessionnel argument = invocationOnMock.getArgument(0);
            return new DossierProfessionnelDto(argument.getId(), argument.getNom(),argument.getVersion(), null, null, null, null, null, null);
        });

       DossierProfessionnelDto result = service.getById(id);

        assertEquals(expectedId,result.getId());
    }
    
    @Test
    void givenNothing_whenAll_thenReturnDtos() {
        DossierProfessionnel dossier1 = new DossierProfessionnel("dossier1", null, null, null, null, null, null);
        DossierProfessionnel dossier2 = new DossierProfessionnel("dossier2", null, null, null, null, null, null);

        //creation d'une list de dossier pro
        List<DossierProfessionnel> entities = Collections.unmodifiableList(Arrays.asList(dossier1, dossier2));

        DossierProfessionnelDto dossierDto1 = new  DossierProfessionnelDto(1L,"dossier2",4, null, null, null, null, null, null);
        DossierProfessionnelDto dossierDto2 = new DossierProfessionnelDto(2L,"dossier2",4, null, null, null, null, null, null);

        List<DossierProfessionnelDto> dtos = new ArrayList<>(Arrays.asList(dossierDto1, dossierDto2));

        Mockito.when(repository.findAll()).thenReturn(entities);
        Mockito.when(mapper.dossierProfessionnelToDossierProfessionnelDto(dossier1)).thenReturn(dossierDto1);
        Mockito.when(mapper.dossierProfessionnelToDossierProfessionnelDto(dossier2)).thenReturn(dossierDto2);

        List<DossierProfessionnelDto> result = service.getAll();
        System.out.println("Result from service: " + result);
        assertTrue(result.containsAll(dtos));

    }

}
