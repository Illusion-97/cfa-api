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
/***
 * 
 * @author Feres BG Valentin C.
 * @see fr.dawan.AppliCFABack.repositories.SignatureRepository
 * @see fr.dawan.AppliCFABack.dto.SignatureDto
 * @since 1.0
 * @version 1.0
 *
 */
@Service
@Transactional
public class SignatureServiceImpl implements SignatureService{

	@Autowired
	private SignatureRepository signatureRepository;
	
	/**
	 * Récupération de la signature en fonction de son id
	 * 
	 * @param id Signature
	 * @return Signature DTO
	 * 
	 */
	
	@Override
	public SignatureDto getById(long id) {
		Optional<Signature> s= signatureRepository.findById(id) ;
		if (s.isPresent()) 
			return DtoTools.convert(s.get(), SignatureDto .class);
		
		return null;
	}

	/**
	 * Sauvegarde ou mise à jour de la Signature
	 * 
	 * @param Signature DTO
	 * @return Signature DTO
	 */
	@Override
	public SignatureDto saveOrUpdate(SignatureDto tDto) throws Exception {
		Signature s = DtoTools.convert(tDto, Signature.class);
		s = signatureRepository.saveAndFlush(s);
		return DtoTools.convert(s, SignatureDto.class);
	}
	/***
	 * Compte le nombre de  la Signature en fonction de l'élément de la recherche
	 * 
	 * @param Élément de la recherche
	 * @return Count DTO
	 */
	@Override
	public CountDto count(String search) {
		long nb = signatureRepository.count();
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	/**
	 * Suppression de la Signature
	 * 
	 * @param id Signature
	 */
	
	@Override
	public void delete(long id) {
		
		signatureRepository.deleteById(id);
	}
	/***
	 * Récupération la Signatures en fonction de l'id de Utilisateur
	 * 
	 * @param id Utilisateur
	 * @return SignatureDto Liste des objets SignatureDto
	 */
	@Override
	public SignatureDto getByUtilisateurId(long uId) {
		Optional<Signature> s= signatureRepository.getByUtilisateurId(uId) ;
		if (s.isPresent()) 
			return DtoTools.convert(s.get(), SignatureDto .class);
		
		return null;
		
	}

}
