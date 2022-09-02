package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;

@Service
@Transactional
public class DossierProjetServiceImpl implements DossierProjetService {
	
	@Autowired
	DossierProjetRepository dossierProRepo;
	
	@Autowired
	EtudiantService etudiantService;

	@Autowired
	private DtoMapper mapper;

	/**
	 * Récupération de la liste des dossiers projets
	 * 
	 * @return lstDossierProjetDto	Liste des objets dossiers projets
	 */
	
	@Override
	public List<DossierProjetDto> getAll() {
		List<DossierProjet> lstDossierProjets= dossierProRepo.findAll();
		List<DossierProjetDto> lstDossierProjetDto= new ArrayList<>();
		
		for (DossierProjet dossierProjet : lstDossierProjets) {
			DossierProjetDto dpDto =mapper.dossierProjetToDossierProjetDto(dossierProjet);
			dpDto.setProjet(mapper.projetToProjetDto(dossierProjet.getProjet()));
			lstDossierProjetDto.add(dpDto);
			
		}
 		return lstDossierProjetDto;
	}

	/**
	 * Récupération des dossiers projets en fonction de l'id
	 * 
	 */
	
	@Override
	public DossierProjetDto getById(long id) {
		Optional<DossierProjet> dp = dossierProRepo.findById(id);
		if(dp.isPresent()) {
			DossierProjetDto dpDto = mapper.dossierProjetToDossierProjetDto(dp.get());
			dpDto.setProjet(mapper.projetToProjetDto(dp.get().getProjet()));
			return dpDto;
		}
		return null;
	}

	/**
	 * Va permettre de récupérer tous les dossier projets avec pagination
	 * recherche par nom
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param string élémént du dossier projet
	 * @return LstDto Liste des objets dossier projet
	 */
	
	@Override
	public List<DossierProjetDto> getAllByPage(int page, int size, String string) {
		List<DossierProjet> lst = dossierProRepo
				.findByNomContaining(string,
						PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		// conversion vers Dto
		List<DossierProjetDto> lstDto = new ArrayList<>();
		for (DossierProjet dp : lst) {
			DossierProjetDto dpDto = mapper.dossierProjetToDossierProjetDto(dp);
			dpDto.setProjet(mapper.projetToProjetDto(dp.getProjet()));
			
			lstDto.add(dpDto);
		}
		return lstDto;
	}
	
	/**
	 * Recuperation du dossier projet par nom
	 * 
	 * @param nom	nom concernant le dossier projet
	 */
	
	@Override
	public DossierProjetDto getByName(String nom) {
		DossierProjetDto dpDto = mapper.dossierProjetToDossierProjetDto(dossierProRepo.getByName(nom));
		if(dpDto!=null) {
			if(dossierProRepo.getByName(nom).getProjet()==null)
				dpDto.setProjet( mapper.projetToProjetDto(dossierProRepo.getByName(nom).getProjet()));		
		return dpDto;
		}
		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'un dossier pro
	 * 
	 */
	
	@Override
	public DossierProjetDto saveOrUpdate(DossierProjetDto dpDto) {
		DossierProjet d = DtoTools.convert(dpDto, DossierProjet.class);
		dossierProRepo.saveAndFlush(d);
		return mapper.dossierProjetToDossierProjetDto(d);
	}

	/**
	 * Suppression d'un dossier projet
	 * 
	 * @param id	Id concernant le dossier projet
	 */
	
	@Override
	public void deleteById(long id) {
		dossierProRepo.deleteById(id);
		
	}

	/**
	 * Recuperation du dossier projet de l'etudiant
	 * 
	 * @param id	Id concernant l'etudiant
	 * @return dossier projet de l'etudiant concerné
	 */
	
	@Override
	public List<DossierProjetDto> getByIdEtudiant(long id) {
		EtudiantDto e = etudiantService.getById(id);
		return e.getDossierProjet();
	}

}
