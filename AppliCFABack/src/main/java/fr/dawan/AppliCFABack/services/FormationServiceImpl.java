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
import fr.dawan.AppliCFABack.repositories.FormationRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;

@Service
@Transactional
public class FormationServiceImpl implements FormationService {

	@Autowired
	FormationRepository formationRepository;
	@Autowired
	InterventionRepository interventionRepository;

	@Override
	public List<FormationDto> getAllFormation() {
		List<Formation> lst = formationRepository.findAll();

		List<FormationDto> lstDto = new ArrayList<FormationDto>();
		for (Formation f : lst) {
			FormationDto formationDto = DtoTools.convert(f, FormationDto.class);

			List<Cursus> lstCursus = f.getCursusLst();
			List<CursusDto> lstCursusDto = new ArrayList<CursusDto>();

			for (Cursus cursus : lstCursus) {
				if (cursus != null)
					lstCursusDto.add(DtoTools.convert(cursus, CursusDto.class));
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
			FormationDto cDto = DtoTools.convert(c, FormationDto.class);
			List<CursusDto> cursusLstDto = new ArrayList<CursusDto>();
			for (Cursus cursus : c.getCursusLst()) {
				cursusLstDto.add(DtoTools.convert(cursus, CursusDto.class));
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
			FormationDto formationDto = DtoTools.convert(f.get(), FormationDto.class);
			List<Cursus> lstCursus = f.get().getCursusLst();
			List<CursusDto> lstCursusDto = new ArrayList<CursusDto>();

			for (Cursus cursus : lstCursus) {
				if (cursus != null)
					lstCursusDto.add(DtoTools.convert(cursus, CursusDto.class));
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

		return DtoTools.convert(f, FormationDto.class);
	}

	@Override
	public void deleteById(long id) {
		formationRepository.deleteById(id);

	}

	@Override
	public List<InterventionDto> findAllByFormationId(long id) {

		List<Intervention> lstInt = interventionRepository.findAllByFormationId(id);
		List<InterventionDto> lstIntDto = new ArrayList<InterventionDto>();
		for (Intervention itv : lstInt) {
			if (itv != null)
				lstIntDto.add(DtoTools.convert(itv, InterventionDto.class));
		}
		return lstIntDto;
	}

}
