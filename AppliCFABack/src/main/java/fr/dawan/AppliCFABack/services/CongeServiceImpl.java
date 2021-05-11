package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.repositories.CongeRepository;

@Service
@Transactional
public class CongeServiceImpl implements CongeService {

	@Autowired
	CongeRepository congeRepository;

	@Override
	public List<CongeDto> getAllConge() {
		List<Conge> lst = congeRepository.findAll();

		List<CongeDto> lstDto = new ArrayList<CongeDto>();
		for (Conge c : lst) {
			lstDto.add(DtoTools.convert(c, CongeDto.class));
		}
		return lstDto;
	}

	@Override
	public CongeDto getById(long id) {
		Optional<Conge> f = congeRepository.findById(id);
		if (f.isPresent())
			return DtoTools.convert(f.get(), CongeDto.class);

		return null;
	}

	@Override
	public List<CongeDto> getAllConge(int page, int size) {
		List<Conge> lst = congeRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<CongeDto> lstDto = new ArrayList<CongeDto>();
		for (Conge c : lst) {
			lstDto.add(DtoTools.convert(c, CongeDto.class));
		}
		return lstDto;
	}

	@Override
	public CongeDto saveOrUpdate(CongeDto cDto) {
		Conge c = DtoTools.convert(cDto, Conge.class);
		
		c = congeRepository.saveAndFlush(c);
		
		return DtoTools.convert(c, CongeDto.class);
	}

	@Override
	public void deleteById(long id) {
		congeRepository.deleteById(id);
		
	}
}
