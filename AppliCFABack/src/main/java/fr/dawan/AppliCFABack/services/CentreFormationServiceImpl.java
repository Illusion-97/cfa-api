package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;

@Service
@Transactional
public class CentreFormationServiceImpl implements CentreFormationService {

	@Autowired
	CentreFormationRepository centreFormationRepository;

	@Override
	public List<CentreFormationDto> getAllCentreFormation() {
		List<CentreFormation> lst = centreFormationRepository.findAll();

		List<CentreFormationDto> lstDto = new ArrayList<CentreFormationDto>();
		for (CentreFormation cf : lst) {
			lstDto.add(DtoTools.convert(cf, CentreFormationDto.class));
		}
		return lstDto;
	}

	@Override
	public List<CentreFormationDto> getAllCentreFormation(int page, int size) {
		List<CentreFormation> lst = centreFormationRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		List<CentreFormationDto> lstDto = new ArrayList<CentreFormationDto>();
		for (CentreFormation cf : lst) {
			lstDto.add(DtoTools.convert(cf, CentreFormationDto.class));
		}
		return lstDto;
	}

	@Override
	public CentreFormationDto getById(long id) {
		Optional<CentreFormation> cf = centreFormationRepository.findById(id);
		if (!cf.isPresent()) return null;
		
		CentreFormationDto cDto = DtoTools.convert(cf.get(), CentreFormationDto.class);
		
		cDto.setAdresseDto(DtoTools.convert(cf.get().getAdresse(), AdresseDto.class));
		
		return cDto;
	}

	@Override
	public CentreFormationDto saveOrUpdate(CentreFormationDto cfDto) {
		CentreFormation cf = DtoTools.convert(cfDto, CentreFormation.class);

		cf = centreFormationRepository.saveAndFlush(cf);

		return DtoTools.convert(cf, CentreFormationDto.class);
	}

	@Override
	public void deleteById(long id) {
		centreFormationRepository.deleteById(id);

	}

}
