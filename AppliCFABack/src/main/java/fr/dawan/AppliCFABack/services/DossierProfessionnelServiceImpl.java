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
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.DossierProfessionnelRepository;

@Service
@Transactional
public class DossierProfessionnelServiceImpl implements DossierProfessionnelService{
	
	@Autowired
	DossierProfessionnelRepository dossierProRepo;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	@Override
	public List<DossierProfessionnelDto> getAll() {
		// TODO Auto-generated method stub
				List<DossierProfessionnel> lstDossierProfessionnel= dossierProRepo.findAll();
				List<DossierProfessionnelDto> lstDossierProfessionnelDto= new ArrayList<DossierProfessionnelDto>();
				
				for (DossierProfessionnel dp : lstDossierProfessionnel) {
					DossierProfessionnelDto dpDto =mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
					dpDto.setCursus(mapper.CursusToCursusDto(dp.getCursus()));
					lstDossierProfessionnelDto.add(dpDto);
					
				}
		 		return lstDossierProfessionnelDto;
	}

	@Override
	public DossierProfessionnelDto getById(long id) {
		// TODO Auto-generated method stub
				Optional<DossierProfessionnel> dp = dossierProRepo.findById(id);
				if(dp.isPresent()) {
					DossierProfessionnelDto dpDto =mapper.DossierProfessionnelToDossierProfessionnelDto(dp.get());
					dpDto.setCursus(mapper.CursusToCursusDto(dp.get().getCursus()));
					return dpDto;
				}
				return null;
	}

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
			dpDto.setCursus(mapper.CursusToCursusDto(dp.getCursus()));
			
			lstDto.add(dpDto);
		}
		return lstDto;
	}

	@Override
	public DossierProfessionnelDto saveOrUpdate(DossierProfessionnelDto dpDto) {
		DossierProfessionnel d = DtoTools.convert(dpDto, DossierProfessionnel.class);
		dossierProRepo.saveAndFlush(d);
		return mapper.DossierProfessionnelToDossierProfessionnelDto(d);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub

		dossierProRepo.deleteById(id);
		
	}
	@Override
	public List<DossierProfessionnelDto> getByIdEtudiant(long id) {
		// TODO Auto-generated method stub
//		List<DossierProfessionnel> lstDp =dossierProRepo.findByIdEtudiant(id);
//		List<DossierProfessionnelDto> lstdpDto = new ArrayList<DossierProfessionnelDto>();
//		for (DossierProfessionnel dp : lstDp) {
//			DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
//			dpDto.setCursus(mapper.CursusToCursusDto(dp.getCursus()));
//			lstdpDto.add(dpDto);
//			
//		}
//		return lstdpDto;
		return null;
	}

}
