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
import fr.dawan.AppliCFABack.repositories.AdresseRepository;

@Service
@Transactional
public class AdresseServiceImpl implements AdresseService{
	
	@Autowired
	AdresseRepository adresseRepository;

	@Override
	public List<AdresseDto> getAllAdresse() {
		List<Adresse> lst = adresseRepository.findAll();
		
		List<AdresseDto> lstDto = new ArrayList<AdresseDto>();
		for (Adresse a : lst) {
			lstDto.add(DtoTools.convert(a, AdresseDto.class));
		}
		return lstDto;
	}

	@Override
	public AdresseDto getById(long id) {
		Optional<Adresse> adresseOpt = adresseRepository.findById(id);
		if (adresseOpt.isPresent())
			return DtoTools.convert(adresseOpt.get(), AdresseDto.class);
		return null;
	}

	@Override
	public List<AdresseDto> getAllByPage(int page, int size, String search) {
		List<Adresse> lst = adresseRepository.findAllByVille(search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<AdresseDto> lstDto = new ArrayList<AdresseDto>();
		for (Adresse a : lst) {
			AdresseDto aDto = DtoTools.convert(a, AdresseDto.class);
			lstDto.add(aDto);
		}
		return lstDto;
	}

	@Override
	public AdresseDto saveOrUpdate(AdresseDto aDto) {
		Adresse a = DtoTools.convert(aDto, Adresse.class);

		a= adresseRepository.saveAndFlush(a);

		return DtoTools.convert(a, AdresseDto.class);
	}

	@Override
	public void deleteById(long id) {
		adresseRepository.deleteById(id);
		
	}

	@Override
	public CountDto count(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
