package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.GroupeEtudiantRepository;
import fr.dawan.AppliCFABack.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupeEtudiantServiceImpl implements GroupeEtudiantService{

	@Autowired
	private GroupeEtudiantRepository groupeEtudiantRepository;
	
	@Autowired
	private ProjetRepository projetRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	private static Logger logger = Logger.getGlobal();
	
	/**
	 * Récupération de la liste des groupes
	 * 
	 * @return lstDto	Liste des objets groupe
	 */
	
	@Override
	public List<GroupeEtudiantDto> getAllGroupeEtudiant() {
		List<GroupeEtudiant> lst = groupeEtudiantRepository.findAll();
		List<EtudiantDto> lstEtuDto = new ArrayList<>();
		List<GroupeEtudiantDto> lstDto = new ArrayList<>();
		for (GroupeEtudiant g : lst) {
			lstDto.add(mapper.groupeEtudiantToGroupEtudiantDto(g));
			for(Etudiant e : g.getEtudiants()) {
				EtudiantDto eDto = mapper.etudiantToEtudiantDto(e);
				List<PromotionDto> pDtos = new ArrayList<>();
				for(Promotion p : e.getPromotions()) {
					pDtos.add(mapper.promotionToPromotionDto(p));
				}
				eDto.setPromotionsDto(pDtos);
				lstEtuDto.add(eDto);
			}
		}
		return lstDto;
	}
	
	/**
	 * Va permettre de récupérer tous les groupes avec pagination
	 * et recherche
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments groupe (nom, nom etudiant, prenom etudiant)
	 * @return lstDto Liste des objets groupes etudiants
	 */

	@Override
	public List<GroupeEtudiantDto> getAllByPage(int page, int size, String search) {
		List<GroupeEtudiant> lst = groupeEtudiantRepository.findAllByNomContainingIgnoringCase(search, PageRequest.of
				(page, size)).get().collect(Collectors.toList());
		List<GroupeEtudiantDto> dpDto = new ArrayList<>();
		if(!lst.isEmpty()){
			for (GroupeEtudiant group : lst){
				GroupeEtudiantDto gpDtos = mapper.groupeEtudiantToGroupEtudiantDto(group);
				dpDto.add(gpDtos);
			}
		}

		return dpDto;
	}

	/**
	 * Recherche d'un groupe / nb groupe
	 * 
	 * @param search recherche par nom, nom etudiant / prenom etudiant
	 */
	
	@Override
	public CountDto count(String search) {
		return new CountDto(groupeEtudiantRepository.countByNomContainingIgnoringCase(search));
	}

	/**
	 * Récupération des groupes en fonction de l'id
	 * 
	 * @param id	id du groupe
	 */
	
	@Override
	public GroupeEtudiantDto getById(long id) {
		Optional<GroupeEtudiant> g = groupeEtudiantRepository.findById(id);
		
		if(!g.isPresent()) return null;
		
		GroupeEtudiantDto gDto = mapper.groupeEtudiantToGroupEtudiantDto(g.get());
		List<EtudiantDto> etudiantsDto = new ArrayList<>();
		for(Etudiant e : g.get().getEtudiants()) {
			EtudiantDto eDto = mapper.etudiantToEtudiantDto(e);
			List<PromotionDto> pDtos = new ArrayList<>();
			for(Promotion p : e.getPromotions()) {
				pDtos.add(mapper.promotionToPromotionDto(p));
			}
			eDto.setPromotionsDto(pDtos);
			etudiantsDto.add(eDto);
		}
		gDto.setEtudiantsDto(etudiantsDto);
		
		return gDto;
	}

	/**
	 * Ajout ou modification d'un groupe
	 * 
	 */
	
	@Override
	public GroupeEtudiantDto saveOrUpdate(GroupeEtudiantDto gDto) {
		logger.info("GroupeEtudiantDto saveOrUpdate");
		GroupeEtudiant g = groupeEtudiantRepository.saveAndFlush(DtoTools.convert(gDto, GroupeEtudiant.class));
		return mapper.groupeEtudiantToGroupEtudiantDto(g);
	}

	/**
	 * Suppression d'un groupe
	 * 
	 * @param id	Id concernant le groupe
	 */
	
	@Override
	public void deleteById(long id) {
		
		//La relation ManyToMany avec etudiants est mappé par le groupe
		//Donc pas besoin de s'en occuper
		
		//Mais Projet a un groupe => il faut supprimer le lien
		List<Projet> projets = projetRepository.findAllByGroupeId(id);
		
		for(Projet p : projets) {
			p.setGroupe(null);
			projetRepository.saveAndFlush(p);
		}
		
		groupeEtudiantRepository.deleteById(id);		
	}

	/**
	 * Récupération des etudiant en fonction de l'id du groupe
	 * 
	 * @param id	id du groupe
	 * @return result	Liste des etudiants du groupe
	 */
	
	//recuperation des etudiants avec id du groupe
	@Override
	public List<EtudiantDto> getEtudiantsByGroupeId(long id) {
		Optional<GroupeEtudiant> g = groupeEtudiantRepository.findById(id);
		
		if(!g.isPresent())
			return null;
		
		List<EtudiantDto> result = new ArrayList<>();
		for(Etudiant e : g.get().getEtudiants()) {
			EtudiantDto eDto = mapper.etudiantToEtudiantDto(e);
			eDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(e.getUtilisateur()));
			result.add(eDto);
		}		
		
		return result;
		
	}

	

}
