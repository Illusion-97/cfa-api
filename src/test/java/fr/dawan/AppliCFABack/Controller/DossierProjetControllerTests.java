package fr.dawan.AppliCFABack.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.controllers.DossierProjetController;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.ProjetDossierProjetDto;
import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.services.DossierProjetService;
import fr.dawan.AppliCFABack.services.EmailService;
import fr.dawan.AppliCFABack.services.EtudiantService;
import fr.dawan.AppliCFABack.services.FilesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
	private ObjectMapper objectMapper;
	private final String fileName = "importTest.txt";
	private MockMultipartFile mockMultipartFile;
	private List<MultipartFile> mockMultipartFileList;
	private DossierProjetDto dpDtoTest;
	private ProjetDossierProjetDto projetDossierProjetDto;
	private final long entityId = 1;
	@BeforeEach
	void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		Mockito.when(tokenInterceptorMock.preHandle(any(), any(), any())).thenReturn(true);
		objectMapper = new ObjectMapper();
		dpDtoTest = new DossierProjetDto();
		dpDtoTest.setId(entityId);
		dpDtoTest.setDossierImport(fileName);
		projetDossierProjetDto = new ProjetDossierProjetDto().create(entityId,"projetTest",0);
		dpDtoTest.setProjet(projetDossierProjetDto);
		dpDtoTest.setNom("EntityName");
		mockMultipartFileList = new ArrayList<>();
		mockMultipartFile = new MockMultipartFile("import", fileName, "text/plain", "test".getBytes());
		mockMultipartFileList.add(mockMultipartFile);

	}

	@AfterEach
	void cleanup(){
		dossierProjetRepoMock.deleteAll();
	}
	@Test
	void testFindAll() {
		try {
			List<DossierProjetDto> lstDpDto = new ArrayList<>();
			mockMvc.perform(get("/dossierProjet").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFindById() {
		try {
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
	void testUpdate(){
		try {
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			String jsonReq = objectMapper.writeValueAsString(dpDtoTest);
			mockMvc.perform(MockMvcRequestBuilders
							.put("/dossierProjet/update")
							.contentType(MediaType.APPLICATION_JSON)
							.content(jsonReq)
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	void testSaveImport() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.multipart("/dossierProjet/save-import/"+ entityId)
				.file(mockMultipartFile)
				.param("id", String.valueOf(entityId))
				.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isCreated())
				.andReturn();
	}
	@Test
	void testSaveAnnexe(){
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.multipart("/dossierProjet/save-annexe/"+ entityId)
							.file(mockMultipartFile)
							.param("id", String.valueOf(entityId))
							.contentType(MediaType.MULTIPART_FORM_DATA))
					.andExpect(status().isCreated())
					.andReturn();
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	void testUpdateAnnece(){
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.put("/dossierProjet/update-annexe/"+ entityId)
							.param("pieceJointe","meow")
							.param("id", String.valueOf(entityId))
							.contentType(MediaType.MULTIPART_FORM_DATA))
					.andExpect(status().isOk())
					.andReturn();
		}catch (Exception e) {
			fail(e.getMessage());
		}

	}
	@Test
	void testDeleteFile() {
		try {
			mockMvc.perform(delete("/dossierProjet/"+ entityId)
							.param("file",fileName))
					.andExpect(status().isNoContent());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	/*@Test
	void testGenerate(){
		try {
			String outputPdf = storageFolder;
			Mockito.when(dossierProjetServiceMock.genererDossierProjet(entityId)).thenReturn(outputPdf);
			mockMvc.perform(MockMvcRequestBuilders
							.get("/dossierProjet/generer/"+ entityId))
					.andExpect(status().isOk());
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/
}
