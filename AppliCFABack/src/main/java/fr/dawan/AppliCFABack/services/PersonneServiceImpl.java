package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PersonneDto;
import fr.dawan.AppliCFABack.entities.Personne;
import fr.dawan.AppliCFABack.repositories.PersonneRepository;

@Service
@Transactional
public class PersonneServiceImpl implements PersonneService {
	@Autowired
	private PersonneRepository personneRepository;

	@Override
	public List<PersonneDto> getAll() {
		List<Personne> personnes = personneRepository.findAll();
		List<PersonneDto> res = new ArrayList<PersonneDto>();
		for (Personne p : personnes) {
			res.add(DtoTools.convert(p, PersonneDto.class));
		}
		return res;
	}

	@Override
	public List<PersonneDto> getAll(int page, int size) {
		List<Personne> personnes = personneRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<PersonneDto> res = new ArrayList<PersonneDto>();
		for (Personne p : personnes) {
			res.add(DtoTools.convert(p, PersonneDto.class));
		}
		return res;
	}

	@Override
	public PersonneDto findById(Long id) {
		Optional<Personne> personneOpt = personneRepository.findById(id);
		if (personneOpt.isPresent())
			return DtoTools.convert(personneOpt, PersonneDto.class);
		return null;
	}

	@Override
	public PersonneDto findByName(String name) {
		Personne personne = personneRepository.findByName(name);
		if (personne != null)
			return DtoTools.convert(personne, PersonneDto.class);
		return null;
	}

	@Override
	public List<PersonneDto> findByFormateurId(long id) {
		List<Personne> personnes = personneRepository.findByFormateur(id);
		List<PersonneDto> res = new ArrayList<PersonneDto>();
		for (Personne p : personnes) {
			res.add(DtoTools.convert(p, PersonneDto.class));
		}
		return res;
	}

	@Override
	public PersonneDto insertUpdate(PersonneDto persDto) {
		Personne personne = DtoTools.convert(persDto, Personne.class);
		personneRepository.saveAndFlush(personne);
		return DtoTools.convert(personne, PersonneDto.class);
	}

	@Override
	public void deleteById(long id) {
		personneRepository.deleteById(id);
	}

	@Override
	public List<PersonneDto> findByAdresse(long idAddr) {
		List<Personne> personnes = personneRepository.findByAddresse(idAddr);
		List<PersonneDto> res = new ArrayList<PersonneDto>();
		for (Personne p : personnes) {
			res.add(DtoTools.convert(p, PersonneDto.class));
		}
		return res;
	}

	@Override
	public List<PersonneDto> findByStudent(long idStud) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonneDto findByCEF(long idCEF) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonneDto> findByReferent(long idRef) {
		List<Personne> personnes = personneRepository.findByReferent(idRef);
		List<PersonneDto> res = new ArrayList<PersonneDto>();
		for (Personne p : personnes) {
			res.add(DtoTools.convert(p, PersonneDto.class));
		}
		return res;
	}
}
