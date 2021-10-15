package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;

@Service
@Transactional
public class CentreFormationServiceImpl implements CentreFormationService {

	@Autowired
	CentreFormationRepository centreFormationRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<CentreFormationDto> getAllCentreFormation() {
		List<CentreFormation> lst = centreFormationRepository.findAll();

		List<CentreFormationDto> lstDto = new ArrayList<CentreFormationDto>();
		for (CentreFormation cf : lst) {
			CentreFormationDto cDto = mapper.CentreFormationToCentreFormationDto(cf);
			cDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(cf.getEntreprise()));
			cDto.setAdresseDto(mapper.AdresseToAdresseDto(cf.getAdresse()));
			lstDto.add(cDto);
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
			CentreFormationDto cDto = mapper.CentreFormationToCentreFormationDto(cf);
			cDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(cf.getEntreprise()));
			cDto.setAdresseDto(mapper.AdresseToAdresseDto(cf.getAdresse()));
			lstDto.add(cDto);
		}
		return lstDto;
	}

	@Override
	public CentreFormationDto getById(long id) {
		Optional<CentreFormation> cf = centreFormationRepository.findById(id);
		if (!cf.isPresent()) return null;
		
		CentreFormationDto cDto = mapper.CentreFormationToCentreFormationDto(cf.get());
		cDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(cf.get().getEntreprise()));
		cDto.setAdresseDto(mapper.AdresseToAdresseDto(cf.get().getAdresse()));
		
		return cDto;
	}

	@Override
	public CentreFormationDto saveOrUpdate(CentreFormationDto cfDto) {
		CentreFormation cf = DtoTools.convert(cfDto, CentreFormation.class);

		cf = centreFormationRepository.saveAndFlush(cf);

		return mapper.CentreFormationToCentreFormationDto(cf);
	}

	@Override
	public void deleteById(long id) {
		centreFormationRepository.deleteById(id);

	}

	@Override
	public CountDto count(String search) {
		
		return new CountDto(centreFormationRepository.countByNomContaining(search));
	}

	@Override
	public List<CentreFormationDto> getAllCentreFormations(int page, int size, String search) {
		List<CentreFormation> cf = centreFormationRepository.findAllByNomContaining(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<CentreFormationDto> res = new ArrayList<CentreFormationDto>();
		for (CentreFormation c : cf) {
			CentreFormationDto cfDto = mapper.CentreFormationToCentreFormationDto(c);
			cfDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(c.getEntreprise()));
			cfDto.setAdresseDto(mapper.AdresseToAdresseDto(c.getAdresse()));
			res.add(cfDto);
		}
		return res;
	}

}
