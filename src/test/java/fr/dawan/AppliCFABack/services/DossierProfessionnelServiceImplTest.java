package fr.dawan.AppliCFABack.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
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
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DossierProfessionnelServiceImplTest {

	@Autowired
    private DossierProfessionnelService service;

    @Mock
    private DossierProfessionnelRepository repository;
    
    private EtudiantService etudiantService;

    @Mock
    private DtoMapper mapper = new DtoMapperImpl();
    
    private Long etudiantId;

    @BeforeEach
    void setUp() {

        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.getUtilisateurDto().setNom("Étudiant de Test");

        EtudiantDto et = etudiantService.saveOrUpdate(etudiantDto);
        etudiantId = et.getId();
    }
    
    @Test
    void givenDto_whenSave_thenAddToList_AndReturnUpdatedDto() throws FileNotFoundException, IOException {
        List<DossierProfessionnel> dossierEntities = new ArrayList<>();
        DossierProEtudiantDto dossierDto = new DossierProEtudiantDto(0L, "dossier1", null, null, null, null, null, 0);
        DossierProfessionnel dossierEntity = new DossierProfessionnel("dossier1", null, null, null, null, null, null);;

        Mockito.when(mapper.dossierProfessionnelDtoToDossierProfessionnel(dossierDto)).thenReturn(dossierEntity);
        Mockito.when(repository.saveAndFlush(dossierEntity)).thenAnswer(invocationOnMock -> {
            dossierEntity.setId(1L);
            dossierEntities.add(dossierEntity);
            return dossierEntity;
        });
        Mockito.when(mapper.dossierProfessionnelToDossierProfessionnelDto(ArgumentMatchers.any(DossierProfessionnel.class))).thenAnswer(invocationOnMock -> {
            DossierProfessionnel argument = invocationOnMock.getArgument(0);
            return new DossierProEtudiantDto(argument.getId(), argument.getNom(), null, null, null, null, null, 0);
        });

        DossierProEtudiantDto result = service.saveOrUpdateDossierProfessionnel(dossierDto, etudiantId, null);
        
        assertEquals(1L, result.getId()); 
        assertTrue(dossierEntities.contains(dossierEntity));
    }
    
   /* @Test
    void givenNothing_whenAll_thenReturnDtos() {
        // Arrange
    	 DossierProfessionnel dossierEntity = new DossierProfessionnel("dossier1", null, null, null, null, null, null);
    	 DossierProfessionnel dossierEntity2 = new DossierProfessionnel("dossier2", null, null, null, null, null, null);
    	 List<DossierProfessionnel> dossierEntities = List.of(dossierEntity,dossierEntity2);

    	 DossierProfessionnelDto dossierDto = new DossierProfessionnelDto("dossier1", null,null,null,null,null,null);
    	 DossierProfessionnelDto dossierDto2 = new DossierProfessionnelDto("dossier2", null,null,null,null,null,null);
        List<DossierProfessionnelDto> dtos = List.of(dossierDto,dossierDto2);

        Mockito.when(repository.findAll()).thenReturn(dossierEntities);
        Mockito.when(mapper.dossierProfessionnelToDossierProfessionnelDto(dossierEntity)).thenReturn(dossierDto);
        Mockito.when(mapper.dossierProfessionnelToDossierProfessionnelDto(dossierEntity2)).thenReturn(dossierDto2);

        // Act
        List<DossierProfessionnelDto> result = service.getAll();
        // Assert
        assertTrue(result.containsAll(dtos));
    }*/
    
    
   

}
