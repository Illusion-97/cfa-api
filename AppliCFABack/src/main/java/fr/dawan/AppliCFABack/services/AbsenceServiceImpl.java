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
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;

@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService {

	@Autowired
	AbsenceRepository absenceRepository;

	@Override
	public List<AbsenceDto> getAllAbsence() {
		List<Absence> lst = absenceRepository.findAll();

		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
		for (Absence a : lst) {
			AbsenceDto absDto = DtoTools.convert(a, AbsenceDto.class);
			EtudiantDto etuDto = DtoTools.convert(a.getEtudiant(), EtudiantDto.class);
			absDto.setEtudiantDto(etuDto);
			lstDto.add(absDto);

		}
		return lstDto;
	}

	@Override
	public AbsenceDto getById(long id) {
		Optional<Absence> a = absenceRepository.findById(id);
		if (a.isPresent()) {
			AbsenceDto absDto = DtoTools.convert(a.get(), AbsenceDto.class);
			EtudiantDto etuDto = DtoTools.convert(a.get().getEtudiant(), EtudiantDto.class);
			absDto.setEtudiantDto(etuDto);
			return absDto;
		}

		return null;
	}

	@Override
	public AbsenceDto saveOrUpdate(AbsenceDto aDto) {
		Absence a = DtoTools.convert(aDto, Absence.class);

		a = absenceRepository.saveAndFlush(a);

		return DtoTools.convert(a, AbsenceDto.class);
	}

	@Override
	public void deleteById(long id) {
		absenceRepository.deleteById(id);

	}

	@Override
	public CountDto count(String search) {
		return new CountDto(
				absenceRepository.countByEtudiantNomContainingOrEtudiantPrenomContainingAllIgnoreCase(search, search));
	}

	@Override
	public List<AbsenceDto> getAllAbsence(int page, int size, String search) {
		List<Absence> lst = absenceRepository.findByEtudiantNomContainingOrEtudiantPrenomContainingAllIgnoreCase(search,
				search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
		for (Absence a : lst) {
			AbsenceDto absDto = DtoTools.convert(a, AbsenceDto.class);
			EtudiantDto etuDto = DtoTools.convert(a.getEtudiant(), EtudiantDto.class);
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
	public List<AbsenceDto> findAllByEtudiantPromotionsReferentPedagogiqueId(long id) {
		List<Absence> lstAbs = absenceRepository.findByRefId(id);
		List<AbsenceDto> lstAbsDto = new ArrayList<AbsenceDto>();
		for (Absence abs : lstAbs) {
			AbsenceDto absDto = DtoTools.convert(abs, AbsenceDto.class);
			EtudiantDto etuDto = DtoTools.convert(abs.getEtudiant(), EtudiantDto.class);
			absDto.setEtudiantDto(etuDto);
			lstAbsDto.add(absDto);
			return lstAbsDto;
		}
		return null;
	}

}
