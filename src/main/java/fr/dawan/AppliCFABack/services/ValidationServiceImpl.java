	package fr.dawan.AppliCFABack.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ValidationDto;
import fr.dawan.AppliCFABack.entities.Validation;
import fr.dawan.AppliCFABack.repositories.ValidationRepository;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

@Service
@Transactional
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	ValidationRepository validationRepository;
	
	@Override
	public ValidationDto getById(long id) {
		Optional<Validation> validation = validationRepository.findById(id);
		
		if(validation.isPresent()) {
			return DtoTools.convert(validation.get(), ValidationDto.class);
			
		}
		return null;
	}

	@Override
	public ValidationDto saveOrUpdate(ValidationDto tDto) throws SaveInvalidException {
		Validation validation = DtoTools.convert(tDto, Validation.class);
		Validation validationDb = validationRepository.saveAndFlush(validation);
		return DtoTools.convert(validationDb, ValidationDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = validationRepository.count();
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		validationRepository.deleteById(id);
		
	}

}
