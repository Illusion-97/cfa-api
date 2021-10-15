package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDto;
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

}
