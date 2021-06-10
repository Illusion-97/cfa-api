package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.repositories.FormationRepository;

@Service
@Transactional
public class FormationServiceImpl implements FormationService {

	@Autowired
	FormationRepository formationRepository;

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
	public List<FormationDto> getAllFormation(int page, int size) {
		List<Formation> lst = formationRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<FormationDto> lstDto = new ArrayList<FormationDto>();
		for (Formation f : lst) {
			lstDto.add(DtoTools.convert(f, FormationDto.class));
		}
		return lstDto;
	}

	@Override
	public FormationDto getById(long id) {
		Optional<Formation> f = formationRepository.findById(id);
		if (f.isPresent())
			return DtoTools.convert(f.get(), FormationDto.class);

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

}
