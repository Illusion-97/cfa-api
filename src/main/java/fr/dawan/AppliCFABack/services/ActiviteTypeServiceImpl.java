package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ActiviteTypeRepository;
@Service
@Transactional
public class ActiviteTypeServiceImpl implements ActiviteTypeService {
	@Autowired
	private ActiviteTypeRepository activiteTypeRepo;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Override
	public List<ActiviteTypeDto> getAllActiviteType() {
		List<ActiviteType> activiteTypes = activiteTypeRepo.findAll();
		List<ActiviteTypeDto> activiteTypesDto = new ArrayList<ActiviteTypeDto>();
		for (ActiviteType activiteType : activiteTypes) {
			 ActiviteTypeDto atDto = mapper.ActiviteTypeToActiviteTypeDto(activiteType);
			 List<ExamenDto> examensDto = new ArrayList<ExamenDto>();
			 for (Examen ex :activiteType.getExamens() ) {
				 examensDto.add(mapper.ExamenToExamenDto(ex));
				 
			}
			
			 atDto.setExamensDto(examensDto);
			 atDto.setCursusActiviteTypeId(activiteType.getCursusActiviteType().getId());
			 activiteTypesDto.add(atDto);
		}
		return activiteTypesDto;
	}

	@Override
	public List<ActiviteTypeDto> getAllActiviteType(int page, int size, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActiviteTypeDto getById(long id) {
		Optional<ActiviteType>  act = activiteTypeRepo.findById(id);
		if (act.isPresent()) {
			 ActiviteTypeDto atDto = mapper.ActiviteTypeToActiviteTypeDto(act.get());
			 List<Examen> examens = act.get().getExamens();
			 List<ExamenDto> examensDto = new ArrayList<ExamenDto>();
			 for (Examen examen : examens) {
				 examensDto.add(mapper.ExamenToExamenDto(examen));
			}
			 atDto.setExamensDto(examensDto);
			 atDto.setCursusActiviteTypeId(act.get().getCursusActiviteType().getId());
			return atDto;
		}
		
		return null;
	}

	@Override
	public ActiviteTypeDto saveOrUpdate(ActiviteTypeDto atDto) {
		ActiviteType activiteType = DtoTools.convert(atDto, ActiviteType.class);
		
		activiteType = activiteTypeRepo.saveAndFlush(activiteType);
		
		return mapper.ActiviteTypeToActiviteTypeDto(activiteType);
	}

	@Override
	public void deleteById(long id) {
		activiteTypeRepo.deleteById(id);
		
	}
	@Override
	public List<ActiviteTypeDto> getAllActiviteTypesByPromotionId(long id) {
		 
		List<ActiviteType> activiteTypes = activiteTypeRepo.getActiviteTypesByPromotionId(id);
		List<ActiviteTypeDto> activiteTypeDto = new ArrayList<ActiviteTypeDto>() ;
		   for (ActiviteType activiteType : activiteTypes) {
			ActiviteTypeDto atDto = mapper.ActiviteTypeToActiviteDto(activiteType);
			List<CompetenceProfessionnelleDto> cpDto = new ArrayList<CompetenceProfessionnelleDto>();
			for (CompetenceProfessionnelle cp: activiteType.getCompetenceProfessionnelles()) {
				cpDto.add(mapper.CompetenceProfessionnelleToCompetenceProfessionnelleDto(cp));
				atDto.setCompetenceProfessionnellesDto(cpDto);
			}
			activiteTypeDto.add(atDto);
		} 
		return activiteTypeDto;
	}

}
