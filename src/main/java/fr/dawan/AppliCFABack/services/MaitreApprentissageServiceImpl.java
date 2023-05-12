package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.MaitreApprentissage;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.MaitreApprentissageRepository;
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
public class MaitreApprentissageServiceImpl implements MaitreApprentissageService {

	@Autowired
	MaitreApprentissageRepository maitreApprentissageRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	private static Logger logger = Logger.getGlobal();
	
	/**
	 * Récupération de la liste des maitres d'apprentissages
	 * 
	 * @return lstDto	Liste des objets maitre d'apprentissage
	 */
	
	
	@Override
	public List<MaitreApprentissageDto> getAllMaitreApprentissage() {
		List<MaitreApprentissage> lst = maitreApprentissageRepository.findAll();

		List<MaitreApprentissageDto> lstDto = new ArrayList<>();
		for (MaitreApprentissage ma : lst) {
			lstDto.add(mapper.maitreApprentissageToMaitreApprentissageDto(ma));
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer tous les maitres d'apprentissages avec pagination
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @return lstDto Liste des objets maitre d'apprentisage
	 */
	
	@Override
	public List<MaitreApprentissageDto> getAllMaitreApprentissage(int page, int size) {
		List<MaitreApprentissage> lst = maitreApprentissageRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<MaitreApprentissageDto> lstDto = new ArrayList<>();
		for (MaitreApprentissage ma : lst) {
			lstDto.add(mapper.maitreApprentissageToMaitreApprentissageDto(ma));
		}
		return lstDto;
	}

	/**
	 * Récupération des MaitreApprentissage en fonction de l'id
	 * 
	 * @param id	id du MaitreApprentissage
	 */

	@Override
	public MaitreApprentissageDto getById(long id) {
		Optional<MaitreApprentissage> c = maitreApprentissageRepository.findById(id);
		if (c.isPresent())
			return mapper.maitreApprentissageToMaitreApprentissageDto(c.get());

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour du MaitreApprentissage
	 * 
	 */

	@Override
	public MaitreApprentissageDto saveOrUpdate(MaitreApprentissageDto maDto) {
		MaitreApprentissage ma = DtoTools.convert(maDto, MaitreApprentissage.class);
		
		//HashTools throw Exception
		try {
			//Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
			if(ma.getUtilisateur().getId() == 0) {
				ma.getUtilisateur().setPassword(HashTools.hashSHA512(ma.getUtilisateur().getPassword()));
			}else {
				//Si on a modifié le mdp
				MaitreApprentissage maInDB = maitreApprentissageRepository.getOne(ma.getUtilisateur().getId());
				if(!maInDB.getUtilisateur().getPassword().equals(ma.getUtilisateur().getPassword())) {
					ma.getUtilisateur().setPassword(HashTools.hashSHA512(ma.getUtilisateur().getPassword()));
	            }
			}	
		}catch (Exception e) {
			logger.log(Level.SEVERE,"hash mdp failed", e);
        }

		ma = maitreApprentissageRepository.saveAndFlush(ma);

		return mapper.maitreApprentissageToMaitreApprentissageDto(ma);
	}

	/**
	 * Suppression d'un MaitreApprentissage
	 * 
	 * @param id	Id concernant le MaitreApprentissage
	 */
	
	@Override
	public void deleteById(long id) {
		maitreApprentissageRepository.deleteById(id);
		
	}

	@Override
	public MaitreApprentissageDto getMaitreApprentissageByEtudiantId(long id) {
		Optional<MaitreApprentissage> maOpt = maitreApprentissageRepository.findMaitreApprentissageByEtudiantId(id);
		if(maOpt.isPresent()) {
			MaitreApprentissageDto maDto = DtoTools.convert(maOpt.get(), MaitreApprentissageDto.class);
			maDto.setUtilisateurDto(DtoTools.convert(maOpt.get().getUtilisateur(), UtilisateurDto.class));
			return maDto;
		}
		return null;
	}



}
