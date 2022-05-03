package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.DossierProfessionnelRepository;

@Service
@Transactional
public class DossierProfessionnelServiceImpl implements DossierProfessionnelService{
	
	@Autowired
	DossierProfessionnelRepository dossierProRepo;
	@Autowired
	EtudiantService etudiantService;

	@Autowired
	private DtoMapper mapper;
	
	/**
	 * Récupération de la liste des dossiers professionnes
	 * 
	 * @return lstDossierProfessionnelDto	Liste des objets dossiers pro
	 */
	
	@Override
	public List<DossierProfessionnelDto> getAll() {
		// TODO Auto-generated method stub
				List<DossierProfessionnel> lstDossierProfessionnel= dossierProRepo.findAll();
				List<DossierProfessionnelDto> lstDossierProfessionnelDto= new ArrayList<DossierProfessionnelDto>();
				
				for (DossierProfessionnel dp : lstDossierProfessionnel) {
					DossierProfessionnelDto dpDto =mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
					dpDto.setCursusDto(mapper.CursusToCursusDto(dp.getCursus()));
					lstDossierProfessionnelDto.add(dpDto);
					
				}
		 		return lstDossierProfessionnelDto;
	}

	/**
	 * Récupération des dossiers professionnel en fonction de l'id
	 * 
	 */
	@Override
	public DossierProfessionnelDto getById(long id) {
		// TODO Auto-generated method stub
				Optional<DossierProfessionnel> dp = dossierProRepo.findById(id);
				if(dp.isPresent()) {
					DossierProfessionnelDto dpDto =mapper.DossierProfessionnelToDossierProfessionnelDto(dp.get());
					dpDto.setCursusDto(mapper.CursusToCursusDto(dp.get().getCursus()));
					return dpDto;
				}
				return null;
	}

	/**
	 * Va permettre de récupérer tous les dossier pro avec pagination
	 * recherche par nom
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param string élémént du dossier pro
	 * @return LstDto Liste des objets dossier pro
	 */
	
	@Override
	public List<DossierProfessionnelDto> getAllByPage(int page, int size, String string) {
		List<DossierProfessionnel> lst = dossierProRepo
				.findByNomContaining(string,
						PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		// conversion vers Dto
		List<DossierProfessionnelDto> lstDto = new ArrayList<DossierProfessionnelDto>();
		for (DossierProfessionnel dp : lst) {
			DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
			dpDto.setCursusDto(mapper.CursusToCursusDto(dp.getCursus()));
			
			lstDto.add(dpDto);
		}
		return lstDto;
	}

	/**
	 * Sauvegarde ou mise à jour d'un dossier pro
	 * 
	 */
	@Override
	public DossierProfessionnelDto saveOrUpdate(DossierProfessionnelDto dpDto) {
		DossierProfessionnel d = DtoTools.convert(dpDto, DossierProfessionnel.class);
		d.setCursus(DtoTools.convert(dpDto.getCursusDto(), Cursus.class));
		dossierProRepo.saveAndFlush(d);
		return mapper.DossierProfessionnelToDossierProfessionnelDto(d);
	}

	/**
	 * Suppression d'un dossier pro
	 * 
	 * @param id	Id concernant le dossier pro
	 */
	
	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub

		dossierProRepo.deleteById(id);
		
	}
	
	/**
	 * Recuperation du dossier pro de l'etudiant
	 * 
	 * @param id	Id concernant l'etudiant
	 * @return dossier pro de l'etudiant concerné
	 */
	
	@Override
	public List<DossierProfessionnelDto> getByIdEtudiant(long id) {
		// TODO Auto-generated method stub
		EtudiantDto e = etudiantService.getById(id);
		
		return e.getDossierProfessionnel();
	}

	/**
	 * Recuperation du dossier pro par nom
	 * 
	 * @param nom	nom concernant le dossier pro
	 */
	@Override
	public DossierProfessionnelDto getByName(String nom) {
		// TODO Auto-generated method stub
		DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dossierProRepo.getByName(nom));
		if(dpDto!=null) {
			if(dossierProRepo.getByName(nom).getCursus()==null)
				dpDto.setCursusDto( mapper.CursusToCursusDto(dossierProRepo.getByName(nom).getCursus()));		
		return dpDto;
		}
		return null;
	}

}
