package fr.dawan.AppliCFABack.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.FacultatifDto;
import fr.dawan.AppliCFABack.entities.Facultatif;
import fr.dawan.AppliCFABack.repositories.FacultatifRepository;

@Service
@Transactional
public class FacultatifServiceImpl extends GenericServiceImpl<Facultatif, FacultatifDto> implements FacultatifService{
	
	@Autowired
	FacultatifRepository facultRepo;

	@Autowired
	public FacultatifServiceImpl(FacultatifRepository repository) {
		super(repository, FacultatifDto.class, Facultatif.class);
		this.facultRepo =  repository;
		
	}

	@Override
	public void deleteById(long facultatifId) {
		facultRepo.deleteById(facultatifId);
		
	}
	
}