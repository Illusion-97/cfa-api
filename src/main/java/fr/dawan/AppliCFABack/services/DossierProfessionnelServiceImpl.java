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
	
	//recuperation de la liste des dossiers professionnnel
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

	//recuperation des dossier professionnel par id
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

	//recuperation de la liste des dossiers professionnels avec pagination et recherche
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

	//methode d'ajout ou modification d'un dossier professionnel
	@Override
	public DossierProfessionnelDto saveOrUpdate(DossierProfessionnelDto dpDto) {
		DossierProfessionnel d = DtoTools.convert(dpDto, DossierProfessionnel.class);
		d.setCursus(DtoTools.convert(dpDto.getCursusDto(), Cursus.class));
		dossierProRepo.saveAndFlush(d);
		return mapper.DossierProfessionnelToDossierProfessionnelDto(d);
	}

	//methode de suppression d'un dossier professionnel
	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub

		dossierProRepo.deleteById(id);
		
	}
	
	//recuperation d'un dp par id etudiant
	@Override
	public List<DossierProfessionnelDto> getByIdEtudiant(long id) {
		// TODO Auto-generated method stub
		EtudiantDto e = etudiantService.getById(id);
		
		return e.getDossierProfessionnel();
	}

	//recuperation d'un dossier par nom
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
