package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.DevoirEtudiant;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;
/***
 * 
 * @author Feres BG Valentin C.
 * @see fr.dawan.AppliCFABack.repositories.DevoirRepository
 * @see fr.dawan.AppliCFABack.dto.DevoirDto
 * @since 1.0
 * @version 1.0
 *
 */
@Service
@Transactional
public class DevoirServiceImpl implements DevoirService {

	@Autowired
	private DevoirRepository devoirRepository;
	
	@Autowired
	private InterventionServiceImpl interventionServiceImpl;
	
	@Autowired
	private PromotionService promotionService;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des devoirs
	 * 
	 * @return lstDto	Liste des objets devoir
	 */
	
	@Override
	public List<DevoirDto> getAllDevoir() {
		List<Devoir> lst = devoirRepository.findAll();

		List<DevoirDto> lstDto = new ArrayList<>();
		for (Devoir d : lst) {
			lstDto.add(mapper.devoirToDevoirDto(d));
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les adresses avec pagination
	 * recherche par consigne et titre de l'intervention
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts du devoir
	 * @return LstDto Liste des objets devoirs
	 */
	
	@Override
	public List<DevoirDto> getAllByPage(int page, int size, String search) {
		List<Devoir> lst = devoirRepository.findAllByConsigneContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<DevoirDto> lstDto = new ArrayList<>();
		for (Devoir d : lst) {
			DevoirDto dDto = DtoTools.convert(d, DevoirDto.class);
		
			lstDto.add(dDto);
		}
		return lstDto;
	}

	/**
	 * Recherche d'un devoir
	 * 
	 * @param search recherche par consigne ou titre d'une intervention
	 */
	@Override
	public CountDto count(String search) {
		return new CountDto(devoirRepository.countByConsigneContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(search, search));
	}

	/**
	 * Récupération des Devoirs en fonction de l'id
	 * @param id Devoir
	 * @return Devoir DTO
	 */
	
	@Override
	public DevoirDto getById(long id) {
		Optional<Devoir> d = devoirRepository.findById(id);
		if (d.isPresent()) {
			return DtoTools.convert(d.get(),DevoirDto.class);
		}
			
		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'un devoir
	 * @param Devoir DTO
	 * @return Devoir DTO
	 */
	
	@Override
	public DevoirDto saveOrUpdate(DevoirDto dDto) {
//		Devoir devoir = DtoTools.convert(dDto, Devoir.class);
//		Devoir devoirDb = devoirRepository.saveAndFlush(devoir);
//		return DtoTools.convert(devoirDb, DevoirDto.class);
		Devoir devoir = DtoTools.convert(dDto, Devoir.class);
		if (dDto.getId() != 0) {
			return DtoTools.convert(devoirRepository.saveAndFlush(devoir), dDto.getClass());
		} else {
			Devoir devoirDb = devoirRepository.saveAndFlush(devoir);
			//On créer des champs vides dans devoirs_etudiant
			List<EtudiantDto> allEtuByIntervention = interventionServiceImpl.findAllEtudiantsByPromotionInterventionsId(devoirDb.getIntervention().getId());
			Set<DevoirEtudiant> devoirsEtudiantLst = new HashSet<>();
			for (EtudiantDto e: allEtuByIntervention) {
				DevoirEtudiant devoirEtudiant = new DevoirEtudiant();
				devoirEtudiant.setEtudiant(DtoTools.convert(e, Etudiant.class));
				devoirEtudiant.setDateRendu(null);
				devoirEtudiant.setPieceJointe("");
				devoirsEtudiantLst.add(devoirEtudiant);
			}
			
			devoirDb.setDevoirsEtudiant(devoirsEtudiantLst);
			return DtoTools.convert(devoirRepository.saveAndFlush(devoirDb), DevoirDto.class);
		}
	}

	/**
	 * Suppression d'un devoir
	 * 
	 * @param Id concernant le devoir
	 */
	
	@Override
	public void delete(long id) {
		devoirRepository.deleteById(id);

	}

	@Override
	public List<DevoirDto> getAllByInterventionId(long id) {
		List<Devoir> devoirs = devoirRepository.findAllByInterventionId(id);
		List<DevoirDto> result = new ArrayList<>();
		for(Devoir d: devoirs) {
			result.add(DtoTools.convert(d, DevoirDto.class));
		}
		return result;
	}

}
