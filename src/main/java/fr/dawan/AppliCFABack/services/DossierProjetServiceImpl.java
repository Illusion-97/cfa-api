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
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.DossierProjetEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.EtudiantDossierProjetDto;
import fr.dawan.AppliCFABack.entities.AnnexeDossierProjet;
import fr.dawan.AppliCFABack.entities.ContenuDossierProjet;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.InfoDossierProjet;
import fr.dawan.AppliCFABack.entities.ResumeDossierProjet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;

@Service
@Transactional
public class DossierProjetServiceImpl implements DossierProjetService {
	
	@Autowired
	DossierProjetRepository dossierProRepo;
	
	@Autowired
    EtudiantRepository etudiantRepository;
	
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

    @Override
    public DossierProjetEtudiantDto saveOrUpdateDossierProjet(DossierProjetEtudiantDto dpDto, long id) {
//        DossierProjet dp = DtoTools.convert(dpDto, DossierProjet.class);
        DossierProjet dp = mapper.dossierProjetDtoToDossierProjet(dpDto);
        //on récupère la liste des experiences d'un dossier projet et on les met à jour (en n'oubliant pas de set les clés étrangères de la table experience_professionnelle)
        assert dp != null;

        //on récupère la liste des annexes d'un dossier projet et on les met à jour (en n'oubliant pas de set les clés étrangères de la table annexe)
        List<AnnexeDossierProjet> annexes = dp.getAnnexeDossierProjets();
        for(AnnexeDossierProjet annexe : annexes) {
            annexe.setDossierProjet(dp);
        }
        
        List<InfoDossierProjet> infos = dp.getInfoDossierProjets();
        for(InfoDossierProjet info : infos) {
        	info.setDossierProjet(dp);
        }
        
        List<ContenuDossierProjet> contenus = dp.getContenuDossierProjets();
        for(ContenuDossierProjet contenu : contenus) {
        	contenu.setDossierProjet(dp);
        }
        
        List<ResumeDossierProjet> resumes = dp.getResumeDossierProjets();
        for(ResumeDossierProjet resume : resumes) {
        	resume.setDossierProjet(dp);
        }

        //on met à jour la clé étrangère etudiant de la table dossier_professionnel (dans le cas d'un save)
        EtudiantDossierProjetDto eDto = etudiantService.getByEtudiantIdForDossierProjet(id);
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        if(etudiant.isPresent()){
            dp.setEtudiant(etudiant.get());
        }
        //on insert ou met à jour le dossier en question
        dp = dossierProRepo.saveAndFlush(dp);

        return DtoTools.convert(dp, DossierProjetEtudiantDto.class);

    }

}
