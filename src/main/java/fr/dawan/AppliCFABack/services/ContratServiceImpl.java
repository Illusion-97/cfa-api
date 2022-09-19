package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.ContratDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Contrat;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ContratRepository;

@Service
@Transactional
public class ContratServiceImpl implements ContratService{
	
	@Autowired
	ContratRepository contratRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des contrats
	 * 
	 * @return lstDto	Liste des objets contrat
	 */
	
	@Override
	public List<ContratDto> getAllContrat() {
		List<Contrat> lst = contratRepository.findAll();
		
		List<ContratDto> lstDto = new ArrayList<>();
		for (Contrat c : lst) {
			lstDto.add(mapper.contratToContratDto(c));
		}
		return lstDto;
	}

	/**
	 * Récupération des contrats en fonction de l'id
	 * 
	 */
	
	@Override
	public ContratDto getById(long id) {
		Optional<Contrat> c = contratRepository.findById(id);
		if (c.isPresent()) {
			return mapper.contratToContratDto(c.get());
		}
		return null;
	}

	/**
	 * Va permettre de récupérer tous les contrats avec pagination
	 * recherche prenom ou nom du maitre d'apprentissage
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts du contrat
	 * @return LstDto Liste des objets contrat
	 */
	
	@Override
	public List<ContratDto> getAllByPage(int page, int size, String search) {
		List<Contrat> lst = contratRepository.findAllByMaitreApprentissageUtilisateurPrenomContainingIgnoringCaseOrMaitreApprentissageUtilisateurNomContainingIgnoringCase(search,search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<ContratDto> lstDto = new ArrayList<>();
		for (Contrat c : lst) {
			ContratDto cDto = mapper.contratToContratDto(c);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	/**
	 * Sauvegarde ou mise à jour d'un contrat
	 * 
	 */
	
	@Override
	public ContratDto saveOrUpdate(ContratDto cDto) {
		Contrat c = DtoTools.convert(cDto, Contrat.class);
		contratRepository.saveAndFlush(c);
		return mapper.contratToContratDto(c);
	}

	/**
	 * Suppression d'un contart
	 * 
	 * @param id	Id concernant le contrat
	 */
	
	@Override
	public void deleteById(long id) {
		contratRepository.deleteById(id);
		
	}

	@Override
	public ContratDto count(String string) {
		
		return null;
	}

	/**
	 * Récupération du contrat d'un étudiant
	 * 
	 * @param id	Id concernant l'etudiant
	 */
	
	@Override
	public ContratDto getByEtudiantId(long id) {
		Contrat c = contratRepository.findByEtudiantId(id);
	if (c != null) {
		return mapper.contratToContratDto(c);
	}
	return null;
	}

}
