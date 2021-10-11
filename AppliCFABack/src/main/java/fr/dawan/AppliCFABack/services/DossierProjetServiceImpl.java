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
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
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

	@Override
	public List<DossierProjetDto> getAll() {
		// TODO Auto-generated method stub
		List<DossierProjet> lstDossierProjets= dossierProRepo.findAll();
		List<DossierProjetDto> lstDossierProjetDto= new ArrayList<DossierProjetDto>();
		
		for (DossierProjet dossierProjet : lstDossierProjets) {
			DossierProjetDto dpDto =mapper.DossierProjetToDossierProjetDto(dossierProjet);
			dpDto.setProjet(mapper.ProjetToProjetDto(dossierProjet.getProjet()));
			lstDossierProjetDto.add(dpDto);
			
		}
 		return lstDossierProjetDto;
	}

	@Override
	public DossierProjetDto getById(long id) {
		// TODO Auto-generated method stub
		Optional<DossierProjet> dp = dossierProRepo.findById(id);
		if(dp.isPresent()) {
			DossierProjetDto dpDto = mapper.DossierProjetToDossierProjetDto(dp.get());
			dpDto.setProjet(mapper.ProjetToProjetDto(dp.get().getProjet()));
			return dpDto;
		}
		return null;
	}

	@Override
	public List<DossierProjetDto> getAllByPage(int page, int size, String string) {
		List<DossierProjet> lst = dossierProRepo
				.findByNomContaining(string,
						PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		// conversion vers Dto
		List<DossierProjetDto> lstDto = new ArrayList<DossierProjetDto>();
		for (DossierProjet dp : lst) {
			DossierProjetDto dpDto = mapper.DossierProjetToDossierProjetDto(dp);
			dpDto.setProjet(mapper.ProjetToProjetDto(dp.getProjet()));
			
			lstDto.add(dpDto);
		}
		return lstDto;
	}
	@Override
	public DossierProjetDto getByName(String nom) {
		DossierProjetDto dpDto = mapper.DossierProjetToDossierProjetDto(dossierProRepo.getByName(nom));
		if(dpDto!=null) {
			if(dossierProRepo.getByName(nom).getProjet()==null)
				dpDto.setProjet( mapper.ProjetToProjetDto(dossierProRepo.getByName(nom).getProjet()));		
		return dpDto;
		}
		return null;
	}

	@Override
	public DossierProjetDto saveOrUpdate(DossierProjetDto dpDto) {
		DossierProjet d = DtoTools.convert(dpDto, DossierProjet.class);
		dossierProRepo.saveAndFlush(d);
		return mapper.DossierProjetToDossierProjetDto(d);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		dossierProRepo.deleteById(id);
		
	}

	@Override
	public List<DossierProjetDto> getByIdEtudiant(long id) {

		EtudiantDto e = etudiantService.getById(id);
		return e.getDossierProjet();
	}

}
