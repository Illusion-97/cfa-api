package fr.dawan.AppliCFABack.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.annotations.Convert;

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
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.ResumeDossierProjet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.ProjetRepository;

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
	private ProjetRepository projetRepository;
	
	@Autowired
	private DtoMapper mapper;
	
	@Value("${app.storagefolder}")
	private String storageFolder;

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
	public DossierProjetEtudiantDto getById(long id) {
		Optional<DossierProjet> dp = dossierProRepo.findById(id);
		Optional<Projet> p = projetRepository.findById(id);
		if(dp.isPresent()) {
			DossierProjetEtudiantDto dpDto = mapper.dossierProjetToDossierProjetEtudiantDto(dp.get());
			dpDto.setProjets(mapper.projetToProjetDto1(p.get()));
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
	    Optional<Etudiant> etudiant = etudiantRepository.findById(id);
	    List<DossierProjetDto> dossierProjetDtoList = new ArrayList<>();
	        Etudiant e = etudiant.get();
	        List<DossierProjet> dossierProjetList = e.getDossierProjet();
            //DossierProjetDto dossierProjetDto = DtoTools.convert(dossierProjetList, DossierProjetDto.class);
	        for (DossierProjet dp : dossierProjetList) {

	            DossierProjetDto dossierProjetDto = mapper.dossierProjetToDossierProjetDto(dp);
	            
	            dossierProjetDto.setId(dp.getId());
	            dossierProjetDto.setNom(dp.getNom());
	            dossierProjetDto.setProjet(mapper.projetToProjetDto(dp.getProjet()));
	            dossierProjetDto.setAnnexeDossierProjetDtos(mapper.annexeProjetToAnnexeProjetDto(dp.getAnnexeDossierProjets()));
	            dossierProjetDto.setContenuDossierProjetDtos(mapper.contenuToContenuDto(dp.getContenuDossierProjets()));
	            dossierProjetDto.setInfoDossierProjetDtos(mapper.infoToInfoDto(dp.getInfoDossierProjets()));
	            dossierProjetDto.setResumeDossierProjetDtos(mapper.resumeToResumeDto(dp.getResumeDossierProjets()));
	            
	            dossierProjetDtoList.add(dossierProjetDto);
	        }
	    
	    return dossierProjetDtoList;
	}

    @Override
    public DossierProjetEtudiantDto saveOrUpdateDossierProjet(DossierProjetEtudiantDto dpDto, long id, List<MultipartFile> files) {
        DossierProjet dp = mapper.dossierProjetDtoToDossierProjet(dpDto);
        //on récupère la liste des experiences d'un dossier projet et on les met à jour (en n'oubliant pas de set les clés étrangères de la table experience_professionnelle)
        assert dp != null;
        String path = storageFolder + "DossierProjet" + "/";
        //on récupère la liste des annexes d'un dossier projet et on les met à jour (en n'oubliant pas de set les clés étrangères de la table annexe)
        List<AnnexeDossierProjet> annexes = dp.getAnnexeDossierProjets();
        int i = 0;
        for(MultipartFile file : files) {
            String pathFile = path + file.getOriginalFilename();
            
            File newAnnexe = new File(pathFile);
            AnnexeDossierProjet annexe = annexes.get(i++);
            annexe.setPieceJointe(pathFile);
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newAnnexe))){
                try {
                    bos.write(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
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
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        if(etudiant.isPresent()){
            dp.setEtudiant(etudiant.get());
        }
        //on insert ou met à jour le dossier en question
        dp = dossierProRepo.saveAndFlush(dp);

        return DtoTools.convert(dp, DossierProjetEtudiantDto.class);

    }

}
