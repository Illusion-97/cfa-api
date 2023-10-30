package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Soutenance;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.SoutenanceRepository;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

@Service
@Transactional
public class SoutenanceServiceImpl implements SoutenanceService {

	@Autowired
	SoutenanceRepository soutenanceRepository;
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	private static Logger logger = LoggerFactory.getLogger(EtudiantServiceImpl.class);
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public SoutenanceDto getById(long id) {
		Optional<Soutenance> soutenance = soutenanceRepository.findById(id);
		if (soutenance.isPresent()) {
			return mapper.soutenanceToSoutenanceDto(soutenance.get());
		}
		return null;
	}

	@Override
	public SoutenanceDto saveOrUpdate(SoutenanceDto tDto) throws SaveInvalidException {
		Soutenance soutenance = mapper.soutenanceDtoToSoutenance(tDto);
		try {
			if (soutenance.getEtudiant().getId() > 0) {
				Etudiant etudiant = etudiantRepository.getOne(soutenance.getEtudiant().getId());
				soutenance.setEtudiant(etudiant);
			}
		} catch (Exception ex) {
			logger.warn("Error save etudiant", ex);
		}
		
		soutenanceRepository.saveAndFlush(soutenance);
			
		return mapper.soutenanceToSoutenanceDto(soutenance);
		
	}

	@Override
	public CountDto count(String search) {
		long nb = soutenanceRepository.countByPromotionId(Long.parseLong(search));
		CountDto result =  new CountDto();
		result.setNb(nb);
				
		return result;
	}

	@Override
	public void delete(long id) {
		soutenanceRepository.deleteById(id);
	}

	@Override
	public List<SoutenanceDto> getByPromotionId(long id) {
		List<Soutenance> soutenances = soutenanceRepository.getByPromotionId(id);
		List<SoutenanceDto> soutenanceDtos = new ArrayList<>();
		for (Soutenance soutenance : soutenances) {
			soutenanceDtos.add(mapper.soutenanceToSoutenanceDto(soutenance));
		}
		
		return soutenanceDtos;
	}
	
	public List<SoutenanceDto> getPageByPromotionId(long id, int page, int size) {
		List<Soutenance> lstSoutenances = soutenanceRepository.getPageByPromotionId(id, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<SoutenanceDto> lstSoutenanceDtos = new ArrayList<>();
		if (!lstSoutenances.isEmpty()) {
			for (Soutenance soutenance : lstSoutenances) {
				SoutenanceDto soutenanceDto = mapper.soutenanceToSoutenanceDto(soutenance);
				lstSoutenanceDtos.add(soutenanceDto);
			}
		}
		return lstSoutenanceDtos;
	}

	@Override
	public SoutenanceDto save(SoutenanceDto soutenanceDto) {
		Soutenance soutenance2 = new Soutenance();
		soutenance2.getHeure();
		Soutenance soutenance = mapper.soutenanceDtoToSoutenance(soutenanceDto);
		try {
			if (soutenance.getEtudiant().getId() > 0) {
				Etudiant etudiant = etudiantRepository.getOne(soutenance.getEtudiant().getId());
				soutenance.setEtudiant(etudiant);
			}
		} catch (Exception ex) {
			logger.warn("Error save etudiant", ex);
		}
		
		soutenanceRepository.saveAndFlush(soutenance);
			
		return mapper.soutenanceToSoutenanceDto(soutenance);
	}
	
	
}
