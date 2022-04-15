package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;

@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService {

	@Autowired
	AbsenceRepository absenceRepository;

//	@Autowired
//	EtudiantRepository etudiantRepository;
	
	@Override
	public AbsenceDto getById(long id) {
		Optional<Absence> absence = absenceRepository.findById(id);
		if(absence.isPresent()) {
			return DtoTools.convert(absence.get(), AbsenceDto.class);
			
		}
		return null;
	}
	
	@Override
	public List<AbsenceDto> getAllByInterventionId(long id) {
		List<Absence> absences = absenceRepository.findAllByInterventionId(id);
		List<AbsenceDto> result = new ArrayList<AbsenceDto>();
		for(Absence a: absences) {
			result.add(DtoTools.convert(a, AbsenceDto.class));
		}
		return result;
	}
	
	@Override
	public AbsenceDto saveOrUpdate(AbsenceDto aDto) {
		Absence abs = DtoTools.convert(aDto, Absence.class);
		Absence absDb = absenceRepository.saveAndFlush(abs);
		return DtoTools.convert(absDb, AbsenceDto.class);	
	}
	
	@Override
	public void delete(long id) {
		absenceRepository.deleteById(id);
	}
		


	@Override
	public List<AbsenceDto> getAllByEtudiantId(long id) {
		List<Absence> absences = absenceRepository.findAllByEtudiantId(id);
		List<AbsenceDto> result = new ArrayList<AbsenceDto>();
		for(Absence a : absences) {
			result.add(DtoTools.convert(a, AbsenceDto.class));
		}
		return result;
	} 

	@Override
	public List<AbsenceDto> getAllByNomPrenomContaining(String search) {
		List<Absence> absences = absenceRepository.findByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase("%" +search+ "%");
		List<AbsenceDto> result = new ArrayList<AbsenceDto>();
		for(Absence a: absences) {
			result.add(DtoTools.convert(a, AbsenceDto.class));
		}
		return result;
	}

//	@Override
//	public List<AbsenceDto> getAllByPromotionId(long id) {
//		List<Absence> absences = absenceRepository.findAllByPromotionId(id);
//		List<AbsenceDto> result = new ArrayList<AbsenceDto>();
//		for(Absence a : absences) {
//			result.add(DtoTools.convert(a, AbsenceDto.class));
//		}
//		return result;
//	}


	@Override
	public CountDto count (String search) {
		return new CountDto(absenceRepository.countByUtilisateurEtudiantNomOrPrenomContaining(search));
	}


//	@Autowired
//	private DtoMapper mapper = new DtoMapperImpl();
//
//	//recuperation de la liste des absences
//	@Override
//	public List<AbsenceDto> getAllAbsence() {
//		List<Absence> lst = absenceRepository.findAll();
//
//		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
//		for (Absence a : lst) {
//			AbsenceDto absDto = mapper.AbsenceToAbsenceDto(a);
//			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(a.getEtudiant());
//			absDto.setEtudiantDto(etuDto);
//			lstDto.add(absDto);
//
//		}
//		return lstDto;
//	}
//
//	//recuperation des absences par id
//	@Override
//	public AbsenceDto getById(long id) {
//		Optional<Absence> a = absenceRepository.findById(id);
//		if (!a.isPresent()) return null;
//		
//		AbsenceDto aDto =mapper.AbsenceToAbsenceDto(a.get());
//		aDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(a.get().getEtudiant()));
//		aDto.getEtudiantDto().setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(a.get().getEtudiant().getUtilisateur()));
//		
//		return aDto;
//	}
//
//	//methode d'ajout ou modification d'une absence
//	@Override
//	public AbsenceDto saveOrUpdate(AbsenceDto aDto) {
//		Absence a = DtoTools.convert(aDto, Absence.class);
//
//		a = absenceRepository.saveAndFlush(a);
//
//		return mapper.AbsenceToAbsenceDto(a);
//	}
//
//	//methode de suppression d'une absence
//	@Override
//	public void deleteById(long id) {
//		absenceRepository.deleteById(id);
//
//	}
//
//	//methode count
//	@Override
//	public CountDto count(String search) {
//		return new CountDto(
//				absenceRepository.countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(search, search));
//	}
//
//	//recuperation de la liste des absences avec pagination et recherche
//	@Override
//	public List<AbsenceDto> getAllAbsence(int page, int size, String search) {
//		List<Absence> lst = absenceRepository.findByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(search,
//				search, PageRequest.of(page, size)).get().collect(Collectors.toList());
//		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
//		for (Absence a : lst) {
//			AbsenceDto absDto = mapper.AbsenceToAbsenceDto(a);
//			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(a.getEtudiant());
//////			UtilisateurDto ref = DtoTools.convert(a.getEtudiant().getFormateurReferent(), UtilisateurDto.class);
//////			etuDto.setFormateurReferentDto(ref);
////			List<Promotion> lsPromotions = a.getEtudiant().getPromotions();
////			List<PromotionDto> lstPromotionDtos = new ArrayList<PromotionDto>();
////			for (Promotion p : lsPromotions) {
////				PromotionDto promoDto = DtoTools.convert(p, PromotionDto.class);
////				lstPromotionDtos.add(promoDto);
////				etuDto.setPromotionsDto(lstPromotionDtos);
////			}
//
//			absDto.setEtudiantDto(etuDto);
//			lstDto.add(absDto);
//		}
//		return lstDto;
//	}
//
//	//recuperation des absences par etudiant id
//	@Override
//	public List<AbsenceDto> getAllByIdEtudiant(long id) {
//		List<AbsenceDto> result = new ArrayList<AbsenceDto>();
//		List<Absence> list = absenceRepository.findAllByEtudiantId(id);
//		for(Absence a : list) {
//			AbsenceDto absence = mapper.AbsenceToAbsenceDto(a);
//			absence.setEtudiantDto(mapper.EtudiantToEtudiantDto(etudiantRepository.getOne(id)));
//			absence.getEtudiantDto().setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(etudiantRepository.getOne(id).getUtilisateur()));
//			result.add(absence);
//		}
//		return result;
//	}

	

}
