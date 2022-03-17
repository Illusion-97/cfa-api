package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CompetenceProfessionnelleRepository;
@Service
@Transactional
public class CompetenceProfessionnelleServiceImpl implements CompetenceProfessionnelleService {

	@Autowired
	private CompetenceProfessionnelleRepository competenceProfessionnelleRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Override
	public List<CompetenceProfessionnelleDto> getAllCompetenceProfessionnelle() {
		List<CompetenceProfessionnelle> competenceProfessionnelles = competenceProfessionnelleRepository.findAll();
		List<CompetenceProfessionnelleDto> competenceProfessionnellesDto = new ArrayList<CompetenceProfessionnelleDto>();
		for (CompetenceProfessionnelle competenceProfessionnelle : competenceProfessionnelles) {
			 CompetenceProfessionnelleDto cptDto = DtoTools.convert(competenceProfessionnelle, CompetenceProfessionnelleDto.class);
			 List<ExamenDto> examensDto = new ArrayList<ExamenDto>();
			 for (Examen ex :competenceProfessionnelle.getExamens() ) {
	
				 examensDto.add(mapper.ExamenToExamenDto(ex));
				 
			}
			 cptDto.setExamensDto(examensDto);
			 competenceProfessionnellesDto.add(cptDto);
		}
		return competenceProfessionnellesDto;
	}

	@Override
	public CompetenceProfessionnelleDto getById(long id) {
		Optional<CompetenceProfessionnelle> cpt = competenceProfessionnelleRepository.findById(id);
		if (cpt.isPresent()) {
			return DtoTools.convert(cpt.get(), CompetenceProfessionnelleDto.class);
		}
		
		return null;
	}

	@Override
	public CompetenceProfessionnelleDto saveOrUpdate(CompetenceProfessionnelleDto cpDto) {
		CompetenceProfessionnelle cpt = DtoTools.convert(cpDto, CompetenceProfessionnelle.class);
		CompetenceProfessionnelle cptBd = competenceProfessionnelleRepository.saveAndFlush(cpt);
		
		return DtoTools.convert(cptBd, CompetenceProfessionnelleDto.class);
	}

	@Override
	public void deleteById(long id) {
		competenceProfessionnelleRepository.deleteById(id);		
	}

}
