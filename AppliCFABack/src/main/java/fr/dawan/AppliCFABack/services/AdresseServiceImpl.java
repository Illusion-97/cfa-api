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
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;

@Service
@Transactional
public class AdresseServiceImpl implements AdresseService{
	
	@Autowired
	AdresseRepository adresseRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<AdresseDto> getAllAdresse() {
		List<Adresse> lst = adresseRepository.findAll();
		
		List<AdresseDto> lstDto = new ArrayList<AdresseDto>();
		for (Adresse a : lst) {
			lstDto.add(mapper.AdresseToAdresseDto(a));
		}
		return lstDto;
	}

	@Override
	public AdresseDto getById(long id) {
		Optional<Adresse> adresseOpt = adresseRepository.findById(id);
		if (adresseOpt.isPresent())
			return mapper.AdresseToAdresseDto(adresseOpt.get());
		return null;
	}

	@Override
	public List<AdresseDto> getAllByPage(int page, int size, String search) {
		List<Adresse> lst = adresseRepository.findAllByRueContainingOrVilleContaining(search,search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<AdresseDto> lstDto = new ArrayList<AdresseDto>();
		for (Adresse a : lst) {
			AdresseDto aDto =mapper.AdresseToAdresseDto(a);
			lstDto.add(aDto);
		}
		return lstDto;
	}

	@Override
	public AdresseDto saveOrUpdate(AdresseDto aDto) {
		Adresse a = DtoTools.convert(aDto, Adresse.class);

		a= adresseRepository.saveAndFlush(a);

		return mapper.AdresseToAdresseDto(a);
	}

	@Override
	public void deleteById(long id) {
		adresseRepository.deleteById(id);
		
	}

	@Override
	public CountDto count(String search) {
		
		return new CountDto(adresseRepository.countByRueContainingOrVilleContaining(search, search));
	}

	@Override
	public List<AdresseDto> getAllAdresses(int page, int size, String search) {
		List<Adresse> adresses = adresseRepository.findAllByRueContainingOrVilleContaining(search, search,PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<AdresseDto> res = new ArrayList<AdresseDto>();
		for (Adresse a : adresses) {
			res.add(mapper.AdresseToAdresseDto(a));
		}
		return res;
	}

}
