package fr.dawan.AppliCFABack.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.controllers.DossierProjetController;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.ProjetDossierProjetDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.services.DossierProjetService;
import fr.dawan.AppliCFABack.services.EmailService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FilesService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(DossierProjetController.class)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class DossierProjetControllerTests {

	@MockBean
	TokenInterceptor tokenInterceptorMock;
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private DossierProjetRepository dossierProjetRepoMock;
	@MockBean
	private DossierProjetService dossierProjetServiceMock;
	@MockBean
	private EtudiantService etudiantServiceMock;
	@MockBean
	private FilesService filesServiceMock;
	@MockBean
	private EmailService emailService;
	@Mock
	private DtoMapper mapper;
	@Mock
	private ObjectMapper objectMapper;
	@Value("${app.storagefolder}")
	private String storageFolder;
	private DossierProjet dpTest;
	private DossierProjetDto dpDtoTest;
	private ProjetDossierProjetDto projetDossierProjetDto;
	private Projet projet;
	private long entityId = 1;
	@BeforeAll
	void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		Mockito.when(tokenInterceptorMock.preHandle(any(), any(), any())).thenReturn(true);
	}

	@AfterEach
	void cleanup(){
		dossierProjetRepoMock.deleteAll();
	}
	@Test
	void testFindAll() {
		try {
			List<DossierProjetDto> lstDpDto = new ArrayList<>();
			Mockito.when(dossierProjetServiceMock.getAll()).thenReturn(lstDpDto);
			mockMvc.perform(get("/dossierProjet").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFindById() {
		try {
			dpDtoTest = new DossierProjetDto();
			dpDtoTest.setId(entityId);
			Mockito.when(dossierProjetServiceMock.getById(entityId)).thenReturn(dpDtoTest);
			mockMvc.perform(MockMvcRequestBuilders
							.get("/dossierProjet/" + entityId)
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$.id").value(entityId));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	void testSave() {
		try {
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			dpDtoTest = new DossierProjetDto();
			projetDossierProjetDto = new ProjetDossierProjetDto().create(entityId,"projetTest",0);
			dpDtoTest.setProjet(projetDossierProjetDto);
			dpDtoTest.setNom("EntityName");
			String jsonReq = objectMapper.writeValueAsString(dpDtoTest);
			mockMvc.perform(MockMvcRequestBuilders
					.post("/dossierProjet/save")
							.contentType(MediaType.APPLICATION_JSON)
							.content(jsonReq)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	void testSaveImport() throws Exception {
		MockMultipartFile file = new MockMultipartFile(
				"import",
				dpDtoTest.getDossierImport(),
				"text/plain",
				"test".getBytes());

		mockMvc.perform(MockMvcRequestBuilders
				.multipart("/dossierProjet/save-import/"+ dpDtoTest.getId())
				.file(file)
				.param("id", String.valueOf(dpDtoTest.getId()))
				.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isCreated())
				.andReturn();
		File ereasedfile = new File(storageFolder+"/DossierProjet/"+
				dpDtoTest.getNom() + "_" + dpDtoTest.getDossierImport());
		ereasedfile.delete();
	}
	@Test
	void testSaveAnnexe(){

	}
//	@Test
//	void testUpdate() {
//
//		try {
//			DossierProjetEtudiantDto dpDto = dossierProjetController.getById(idDossierProjet+1);
//			dpDto.setNom("nom DossierProjet update");
//
//			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//			String jsonReq = objectMapper.writeValueAsString(dpDto);
//
//			String jsonReponse = mockMvc.perform(put("/dossierProjet") 
//					.contentType(MediaType.APPLICATION_JSON) 
//					.accept(MediaType.APPLICATION_JSON) 
//					.content(jsonReq)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//
//			DossierProjetDto res = objectMapper.readValue(jsonReponse, DossierProjetDto.class);
//			assertEquals(res.getId(), dpDto.getId());
//			assertEquals(res.getNom(), dpDto.getNom());
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//
//	}
	
	@Test
	void testDeleteFile() {
		try {
			String rep = mockMvc.perform(delete("/dossierProjet/"+ dpTest.getId())
					.accept(MediaType.TEXT_PLAIN))
					.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
			assertEquals("suppression effectuée", rep);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
