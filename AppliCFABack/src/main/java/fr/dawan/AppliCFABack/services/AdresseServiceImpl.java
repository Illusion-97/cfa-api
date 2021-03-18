package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.GroupeDto;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.Groupe;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;

@Service
@Transactional
public class AdresseServiceImpl implements AdresseService{

	@Autowired
	AdresseRepository adresseRepository;
	@Override
	public List<AdresseDto> getAllAdresse() {
		List<Adresse> lst = adresseRepository.findAll();
		List<AdresseDto> res = new ArrayList<AdresseDto>();
		
		for (Adresse a : lst) {
			res.add(DtoTools.convert(a, AdresseDto.class));
		}
		return res;
	}

	@Override
	public AdresseDto getById(long id) {
		Optional<Adresse> a = adresseRepository.findById(id);

		if (a.isPresent())
			return DtoTools.convert(a.get(), AdresseDto.class);
		return null;
	}

	@Override
	public AdresseDto saveOrUpdate(AdresseDto aDto) {
		Adresse adresse = adresseRepository.saveAndFlush(DtoTools.convert(aDto, Adresse.class));
		return DtoTools.convert(adresse, AdresseDto.class);
	}

	@Override
	public void deleteById(long id) {
		adresseRepository.deleteById(id);		
	}

}
