package fr.dawan.AppliCFABack.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.SignatureDto;
import fr.dawan.AppliCFABack.entities.Signature;
import fr.dawan.AppliCFABack.repositories.SignatureRepository;
@Service
@Transactional
public class SignatureServiceImpl implements SignatureService{

	@Autowired
	private SignatureRepository signatureRepository;
	
	@Override
	public SignatureDto getById(long id) {
		Optional<Signature> s= signatureRepository.findById(id) ;
		if (s.isPresent()) 
			return DtoTools.convert(s.get(), SignatureDto .class);
		
		return null;
	}

	@Override
	public SignatureDto saveOrUpdate(SignatureDto tDto) throws Exception {
		Signature s = DtoTools.convert(tDto, Signature.class);
		s = signatureRepository.saveAndFlush(s);
		return DtoTools.convert(s, SignatureDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = signatureRepository.count();
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		
		signatureRepository.deleteById(id);
	}

	@Override
	public SignatureDto getByUtilisateurId(long uId) {
		Optional<Signature> s= signatureRepository.getByUtilisateurId(uId) ;
		if (s.isPresent()) 
			return DtoTools.convert(s.get(), SignatureDto .class);
		
		return null;
		
	}

}
