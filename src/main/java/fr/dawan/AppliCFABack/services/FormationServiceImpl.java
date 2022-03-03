package fr.dawan.AppliCFABack.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDG2Dto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDG2Dto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.FormationRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;

@Service
@Transactional
public class FormationServiceImpl implements FormationService {

	@Autowired
	FormationRepository formationRepository;
	@Autowired
	InterventionRepository interventionRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<FormationDto> getAllFormation() {
		List<Formation> lst = formationRepository.findAll();

		List<FormationDto> lstDto = new ArrayList<FormationDto>();
		for (Formation f : lst) {
			FormationDto formationDto = mapper.FormationToFormationDto(f);

			List<Cursus> lstCursus = f.getCursusLst();
			List<CursusDto> lstCursusDto = new ArrayList<CursusDto>();

			for (Cursus cursus : lstCursus) {
				if (cursus != null)
					lstCursusDto.add(mapper.CursusToCursusDto(cursus));
			}

			formationDto.setCursusLstDto(lstCursusDto);
			lstDto.add(formationDto);
		}
		return lstDto;
	}

	@Override
	public List<FormationDto> getAllByPage(int page, int size, String search) {
		List<Formation> lst = formationRepository
				.findAllByTitreContainingIgnoringCaseOrContenuContainingIgnoringCase(search, search,
						PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		// conversion vers Dto
		List<FormationDto> lstDto = new ArrayList<FormationDto>();
		for (Formation c : lst) {
			FormationDto cDto = mapper.FormationToFormationDto(c);
			List<CursusDto> cursusLstDto = new ArrayList<CursusDto>();
			for (Cursus cursus : c.getCursusLst()) {
				cursusLstDto.add(mapper.CursusToCursusDto(cursus));
			}
			cDto.setCursusLstDto(cursusLstDto);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(
				formationRepository.countByTitreContainingIgnoringCaseOrContenuContainingIgnoringCase(search, search));
	}

	@Override
	public FormationDto getById(long id) {
		Optional<Formation> f = formationRepository.findById(id);
		if (f.isPresent()) {
			FormationDto formationDto = mapper.FormationToFormationDto(f.get());
			List<Cursus> lstCursus = f.get().getCursusLst();
			List<CursusDto> lstCursusDto = new ArrayList<CursusDto>();

			for (Cursus cursus : lstCursus) {
				if (cursus != null)
					lstCursusDto.add(mapper.CursusToCursusDto(cursus));
			}

			formationDto.setCursusLstDto(lstCursusDto);
			return formationDto;
		}

		return null;
	}

	@Override
	public FormationDto saveOrUpdate(FormationDto fDto) {
		Formation f = DtoTools.convert(fDto, Formation.class);

		f = formationRepository.saveAndFlush(f);

		return mapper.FormationToFormationDto(f);
	}

	@Override
	public void deleteById(long id) {
		List<Intervention> lstInt = interventionRepository.findAllByFormationId(id);
		for (Intervention intervention : lstInt) {
			intervention.setFormation(null);
		}
		formationRepository.deleteById(id);

	}

	@Override
	public List<InterventionDto> findAllByFormationId(long id) {

		List<Intervention> lstInt = interventionRepository.findAllByFormationId(id);
		List<InterventionDto> lstIntDto = new ArrayList<InterventionDto>();
		for (Intervention itv : lstInt) {
			if (itv != null)
				lstIntDto.add(mapper.InterventionToInterventionDto(itv));
		}
		return lstIntDto;
	}

	@Override
	public void fetchDG2Formations(String email, String password) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<FormationDG2Dto> fResJson = new ArrayList<>();
		
		URI url = new URI("https://dawan.org/api2/cfa/trainings");

		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		if (repWs.getStatusCode() == HttpStatus.OK) {
			String json = repWs.getBody();
			
			try {
				fResJson = objectMapper.readValue(json, new TypeReference<List<FormationDG2Dto>>() { 
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (FormationDG2Dto fDG2 : fResJson) {
				Formation formationImport = mapper.formationDG2DtoToFormation(fDG2);
				Optional<Formation> optFormation = formationRepository.findByIdDg2(formationImport.getIdDg2());
				if (optFormation.isPresent()) {
					if (optFormation.get().equals(formationImport))
						continue;
					else if (!optFormation.get().equals(formationImport)) {
						formationImport.setTitre(optFormation.get().getTitre());
						formationImport.setId(optFormation.get().getId());
					}
					try {
						formationRepository.saveAndFlush(formationImport);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						formationRepository.saveAndFlush(formationImport);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			throw new Exception("ResponseEntity from the webservice WDG2 not correct");
		}


	}
	
	//import InterventionDG2 => mettre dans interventionService
	public List<InterventionDG2Dto> fetchInterventionFromDG2(long id, String email, String password) throws Exception{
		List<InterventionDG2Dto> lst = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		URI urlSession = new URI("https://dawan.org/api2/cfa/trainings/"+ id +"/sessions");
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> repSessionWs = restTemplate.exchange(urlSession, HttpMethod.GET, httpEntity, String.class);
		if (repSessionWs.getStatusCode() == HttpStatus.OK) {
			String json2 = repSessionWs.getBody();
			
			try {
				lst = objectMapper.readValue(json2, new TypeReference<List<InterventionDG2Dto>>() { 
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lst;
	}

}
