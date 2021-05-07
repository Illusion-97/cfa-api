package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;

@Service
@Transactional
public class FormateurServiceImpl implements FormateurService {
	@Autowired
	private FormateurRepository formateurRepository;

	@Override
	public List<FormateurDto> getAll() {
		List<Formateur> lst = formateurRepository.findAll();
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();
		for (Formateur f : lst) {
			lstDto.add(DtoTools.convert(f, FormateurDto.class));
		}
		return lstDto;
	}

	@Override
	public List<FormateurDto> getAllByPage(int page, int size) {
		List<Formateur> lst = formateurRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();
		for (Formateur f : lst) {
			lstDto.add(DtoTools.convert(f, FormateurDto.class));
		}
		return lstDto;
	}

	@Override
	public FormateurDto getById(long id) {
		Optional<Formateur> i = formateurRepository.findById(id);
		if (i.isPresent())
			return DtoTools.convert(i.get(), FormateurDto.class);

		return null;
	}

	@Override
	public FormateurDto saveOrUpdate(FormateurDto fDto) {
		Formateur formateur = DtoTools.convert(fDto, Formateur.class);
		formateur = formateurRepository.saveAndFlush(formateur);
		return DtoTools.convert(formateur, FormateurDto.class);
	}

	@Override
	public void deleteById(long id) {
		formateurRepository.deleteById(id);
	}

	@Override
	public List<FormateurDto> getAllWithObject() {
		List<Formateur> lstFor = formateurRepository.findAll();
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();

		for (Formateur formateur : lstFor) {

			FormateurDto formateurDto = DtoTools.convert(formateur, FormateurDto.class);

			List<Intervention> lstInter = formateur.getInterventions();
			List<InterventionDto> lstInterDto = new ArrayList<InterventionDto>();
			for (Intervention intervention : lstInter) {
				if (intervention != null)
					lstInterDto.add(DtoTools.convert(intervention, InterventionDto.class));
			}
			formateurDto.setInterventionsDto(lstInterDto);
			lstDto.add(formateurDto);
		}
		return lstDto;
	}

}
