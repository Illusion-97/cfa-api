package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.SupportCoursDto;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.SupportCours;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.SupportCoursRepository;

@Service
@Transactional
public class SupportCoursServiceImpl implements SupportCoursService {

	@Autowired 
	SupportCoursRepository supportCoursRepository;
	@Autowired
	InterventionRepository interventionRepository;
	
	
	@Override
	public SupportCoursDto getById(long id) {
		Optional<SupportCours> support = supportCoursRepository.findById(id);
		
		if (support.isPresent()) {
			SupportCoursDto supportDto = DtoTools.convert(support.get(), SupportCoursDto.class);
			List<Intervention> interventions = support.get().getInterventions();
			List<InterventionDto> interventionsDto = new ArrayList<InterventionDto>();
			for(Intervention i: interventions) {
				interventionsDto.add(DtoTools.convert(i, InterventionDto.class));
			}
			
//			supportDto.setInterventionsId(interventionsDto);
			return supportDto;
		}
		return null;
	}

	@Override
	public SupportCoursDto saveOrUpdate(SupportCoursDto tDto) throws Exception {
		if(tDto.getInterventionsId().isEmpty() || tDto.getInterventionsId() == null)
			throw new Exception ("Pas d'intervention ID rentrée");
	
		List<Intervention> interventions = new ArrayList<Intervention>();
		for(long interventionId : tDto.getInterventionsId()) {
			interventions.add(interventionRepository.getOne(interventionId));
		}
		
		SupportCours supportCours = DtoTools.convert(tDto, SupportCours.class);
		supportCours.setInterventions(interventions);
		
//		if(tDto.getId() != 0) {
//			return DtoTools.convert(supportCoursRepository.saveAndFlush(supportCours), SupportCoursDto.class);
//		}else {
			supportCours = supportCoursRepository.saveAndFlush(supportCours);
			SupportCoursDto res = DtoTools.convert(supportCours, SupportCoursDto.class);
			return res;
		//}
		//		supportCours = supportCoursRepository.saveAndFlush(supportCours);
//		return DtoTools.convert(supportCours, SupportCoursDto.class);
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(supportCoursRepository.countByTitreContaining(search));
	}

	@Override
	public void delete(long id) {
		supportCoursRepository.deleteById(id);
	}

	@Override
	public List<SupportCoursDto> getAll() {
		List<SupportCours> supports = supportCoursRepository.findAll();
		List<SupportCoursDto> supportDto = new ArrayList<SupportCoursDto>();
		for (SupportCours sc: supports) {
			supportDto.add(DtoTools.convert(sc, SupportCoursDto.class));
		}
		return supportDto;
	}

	
}
