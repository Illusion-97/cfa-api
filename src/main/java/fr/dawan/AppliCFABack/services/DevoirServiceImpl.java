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
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;

@Service
@Transactional
public class DevoirServiceImpl implements DevoirService {

	@Autowired
	private DevoirRepository devoirRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	//recuperation de la liste des devoirs
	@Override
	public List<DevoirDto> getAllDevoir() {
		List<Devoir> lst = devoirRepository.findAll();

		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			lstDto.add(mapper.DevoirToDevoirDto(d));
		}
		return lstDto;
	}

	//recuperation de la liste des devoirs avec pagination et recherche
	@Override
	public List<DevoirDto> getAllByPage(int page, int size, String search) {
		List<Devoir> lst = devoirRepository.findAllByConsigneContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			DevoirDto dDto = DtoTools.convert(d, DevoirDto.class);
		
			lstDto.add(dDto);
		}
		return lstDto;
	}

	//methode count
	@Override
	public CountDto count(String search) {
		return new CountDto(devoirRepository.countByConsigneContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search));
	}

	//recuperation des devoirs par id
	@Override
	public DevoirDto getById(long id) {
		Optional<Devoir> d = devoirRepository.findById(id);
		if (d.isPresent()) {
			return DtoTools.convert(d.get(),DevoirDto.class);
		}
			
		return null;
	}

	//methode d'ajout ou modification d'un  devoir
	@Override
	public DevoirDto saveOrUpdate(DevoirDto dDto) {
		Devoir d = DtoTools.convert(dDto, Devoir.class);

		d = devoirRepository.saveAndFlush(d);

		return mapper.DevoirToDevoirDto(d);
	}

	//methode de suppression d'un devoir
	@Override
	public void delete(long id) {
		devoirRepository.deleteById(id);

	}



	



}
