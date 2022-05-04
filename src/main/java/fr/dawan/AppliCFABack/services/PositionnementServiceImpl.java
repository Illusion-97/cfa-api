package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PositionnementDto;
import fr.dawan.AppliCFABack.entities.Positionnement;
import fr.dawan.AppliCFABack.repositories.PositionnementRepository;
/***
 * 
 * @author Feres BG Valentin C.
 * @see fr.dawan.AppliCFABack.repositories.PositionnementRepository
 * @see fr.dawan.AppliCFABack.dto.PositionnementDto
 * @since 1.0
 * @version 1.0
 *
 */
@Service
@Transactional
public class PositionnementServiceImpl implements PositionnementService {

	@Autowired
	private PositionnementRepository positionnementRepository;
	
	/**
	 * Récupération du Positionnement en fonction de son id
	 * 
	 * @param id Positionnement
	 * @return Positionnement DTO
	 * 
	 */
	@Override
	public PositionnementDto getById(long id) {
		Optional<Positionnement> p= positionnementRepository.findById(id) ;
		if (p.isPresent()) 
			return DtoTools.convert(p.get(), PositionnementDto.class);
		
		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'un Positionnement
	 * 
	 * @param Positionnement DTO
	 * @return Positionnement DTO
	 */
	@Override
	public PositionnementDto saveOrUpdate(PositionnementDto tDto) throws Exception {
		if (tDto.getEtudiantId() == 0 || tDto.getInterventionId() == 0)
			throw new Exception("Inmposible de persister etudiant ou intervention invalide");
		
		Positionnement p = DtoTools.convert(tDto, Positionnement.class);
		p = positionnementRepository.saveAndFlush(p);
		return DtoTools.convert(p, PositionnementDto.class);
	}
	/***
	 * Compte le nombre de Positionnement en fonction de l'élément de la recherche
	 * 
	 * @param Élément de la recherche
	 * @return Count DTO
	 */
	@Override
	public CountDto count(String search) {
		long nb = positionnementRepository.countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	/**
	 * Suppression du Positionnement
	 * 
	 * @param id Positionnement
	 */
	@Override
	public void delete(long id) {
		positionnementRepository.deleteById(id);
		
	}
	/***
	 * Récupération des Positionnement en fonction de l'id de la Promotion
	 * 
	 * @param id Promotion
	 * @return n PositionnementDto Liste des objets PositionnementDto
	 */
	@Override
	public List<PositionnementDto> getAllByPromotionId(long idPromotion) {
		List<Positionnement> positionnements = positionnementRepository.getAllByPromotionId(idPromotion);
		List<PositionnementDto> result = new ArrayList<PositionnementDto>();
		for (Positionnement p : positionnements) {
			result.add(DtoTools.convert(p, PositionnementDto.class));
		}
		return result;
	}

	/***
	 * Récupération des Positionnement en fonction de l'id de l'intervention
	 * 
	 * @param id Intervention
	 * @return PositionnementDto Liste des objets PositionnementDto
	 */
	@Override
	public List<PositionnementDto> getAllByInterventionId(long idIntervention) {
		List<Positionnement> positionnements = positionnementRepository.getAllByInterventionId(idIntervention);
		List<PositionnementDto> result = new ArrayList<PositionnementDto>();
		for (Positionnement p : positionnements) {
			result.add(DtoTools.convert(p, PositionnementDto.class));
		}
		return result;
	}

}
