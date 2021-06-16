package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.repositories.ExamenRepository;

@Service
@Transactional
public class ExamenServiceImpl implements ExamenService {

	@Autowired
	ExamenRepository examenRepository;

	@Override
	public List<ExamenDto> getAllExamen() {
		List<Examen> lst = examenRepository.findAll();

		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
		for (Examen e : lst) {
			lstDto.add(DtoTools.convert(e, ExamenDto.class));
		}
		return lstDto;
	}

	@Override
	public List<ExamenDto> getAllByPage(int page, int size, String search) {
		List<Examen> lst = examenRepository.findAllByEnonceContainingOrFormationTitreContainingOrCursusTitreContaining(search,search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<ExamenDto> lstDto = new ArrayList<ExamenDto>();
		for (Examen e : lst) {
			ExamenDto eDto = DtoTools.convert(e, ExamenDto.class);
			eDto.setCursusDto(DtoTools.convert(e.getCursus(), CursusDto.class));
			eDto.setFormationDto(DtoTools.convert(e.getFormation(), FormationDto.class));
			lstDto.add(eDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(examenRepository.countByEnonceContainingOrFormationTitreContainingOrCursusTitreContaining(search, search, search));
	}

	@Override
	public ExamenDto getById(long id) {
		Optional<Examen> e = examenRepository.findById(id);
		if (e.isPresent())
			return DtoTools.convert(e.get(), ExamenDto.class);

		return null;
	}

	@Override
	public ExamenDto saveOrUpdate(ExamenDto eDto) {
		Examen e = DtoTools.convert(eDto, Examen.class);

		e = examenRepository.saveAndFlush(e);

		return DtoTools.convert(e, ExamenDto.class);
	}

	@Override
	public void deleteById(long id) {
		examenRepository.deleteById(id);

	}

	

}
