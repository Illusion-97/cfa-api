package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CEFRepository;
import fr.dawan.AppliCFABack.tools.HashTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class CEFServiceImpl implements CEFService {

	@Autowired
	CEFRepository cefRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	private static Logger logger = Logger.getGlobal();

	/**
	 * Récupération de tous les CEF
	 * 
	 * @return lstDto	Liste des objets CEF
	 */
	
	@Override
	public List<CEFDto> getAllCef() {
		List<CEF> lst = cefRepository.findAll();

		List<CEFDto> lstDto = new ArrayList<>();
		for (CEF c : lst) {
			lstDto.add(mapper.cefToCEFDto(c));
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer tous les CEF avec pagination
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @return LstDto Liste des objets CEF
	 */
	
	@Override
	public List<CEFDto> getAllCef(int page, int size) {
		List<CEF> lst = cefRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<CEFDto> lstDto = new ArrayList<>();
		for (CEF c : lst) {
			CEFDto cDto = mapper.cefToCEFDto(c);
			cDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(c.getUtilisateur()));
			lstDto.add(cDto);
		}
		return lstDto;
	}

	
	/**
	 * Récupération des CEF en fonction de l'id
	 * 
	 */
	
	@Override
	public CEFDto getById(long id) {
		Optional<CEF> c = cefRepository.findById(id);
		if (c.isPresent()) {
			CEFDto cefDto = mapper.cefToCEFDto(c.get());
			cefDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(c.get().getUtilisateur()));
			return cefDto;
		}
			
		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'un CEF
	 * 
	 */
	
	@Override
	public CEFDto saveOrUpdate(CEFDto cDto) {
		CEF c = DtoTools.convert(cDto, CEF.class);
		
		if(c.getUtilisateur() != null) {
			//HashTools throw Exception
			try {
				//Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
				if(c.getUtilisateur().getId() == 0) {
					c.getUtilisateur().setPassword(HashTools.hashSHA512(c.getUtilisateur().getPassword()));
				}else {
					//Si on a modifié le mdp
					CEF cefInDB = cefRepository.getOne(c.getId());
					if(!cefInDB.getUtilisateur().getPassword().equals(c.getUtilisateur().getPassword())) {
		                c.getUtilisateur().setPassword(HashTools.hashSHA512(c.getUtilisateur().getPassword()));
		            }
				}	
			}catch (Exception e) {
	            logger.log(Level.WARNING, "Save failed", e);
	        }
		}		

		c = cefRepository.saveAndFlush(c);

		return mapper.cefToCEFDto(c);
	}

	/**
	 * Suppression d'un CEF
	 * 
	 * @param id	Id concernant le CEF
	 */
	
	@Override
	public void deleteById(long id) {
		cefRepository.deleteById(id);

	}

}
