package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;

@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService {

	@Autowired
	AbsenceRepository absenceRepository;

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<AbsenceDto> getAllAbsence() {
		List<Absence> lst = absenceRepository.findAll();

		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
		for (Absence a : lst) {
			AbsenceDto absDto = mapper.AbsenceToAbsenceDto(a);
			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(a.getEtudiant());
			absDto.setEtudiantDto(etuDto);
			lstDto.add(absDto);

		}
		return lstDto;
	}

	@Override
	public AbsenceDto getById(long id) {
		Optional<Absence> a = absenceRepository.findById(id);
		if (!a.isPresent()) return null;
		
		AbsenceDto aDto =mapper.AbsenceToAbsenceDto(a.get());
		aDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(a.get().getEtudiant()));
		aDto.getEtudiantDto().setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(a.get().getEtudiant().getUtilisateur()));
		
		return aDto;
	}

	@Override
	public AbsenceDto saveOrUpdate(AbsenceDto aDto) {
		Absence a = DtoTools.convert(aDto, Absence.class);

		a = absenceRepository.saveAndFlush(a);

		return mapper.AbsenceToAbsenceDto(a);
	}

	@Override
	public void deleteById(long id) {
		absenceRepository.deleteById(id);

	}

	@Override
	public CountDto count(String search) {
		return new CountDto(
				absenceRepository.countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(search, search));
	}

	@Override
	public List<AbsenceDto> getAllAbsence(int page, int size, String search) {
		List<Absence> lst = absenceRepository.findByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(search,
				search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
		for (Absence a : lst) {
			AbsenceDto absDto = mapper.AbsenceToAbsenceDto(a);
			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(a.getEtudiant());
////			UtilisateurDto ref = DtoTools.convert(a.getEtudiant().getFormateurReferent(), UtilisateurDto.class);
////			etuDto.setFormateurReferentDto(ref);
//			List<Promotion> lsPromotions = a.getEtudiant().getPromotions();
//			List<PromotionDto> lstPromotionDtos = new ArrayList<PromotionDto>();
//			for (Promotion p : lsPromotions) {
//				PromotionDto promoDto = DtoTools.convert(p, PromotionDto.class);
//				lstPromotionDtos.add(promoDto);
//				etuDto.setPromotionsDto(lstPromotionDtos);
//			}

			absDto.setEtudiantDto(etuDto);
			lstDto.add(absDto);
		}
		return lstDto;
	}

	@Override
	public List<AbsenceDto> getAllByIdEtudiant(long id) {
		List<AbsenceDto> result = new ArrayList<AbsenceDto>();
		List<Absence> list = absenceRepository.findAllByEtudiantId(id);
		for(Absence a : list) {
			AbsenceDto absence = mapper.AbsenceToAbsenceDto(a);
			absence.setEtudiantDto(mapper.EtudiantToEtudiantDto(etudiantRepository.getOne(id)));
			absence.getEtudiantDto().setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(etudiantRepository.getOne(id).getUtilisateur()));
			result.add(absence);
		}
		return result;
	}

}
