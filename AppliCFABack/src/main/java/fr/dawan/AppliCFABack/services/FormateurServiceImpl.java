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
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;

@Service
@Transactional
public class FormateurServiceImpl implements FormateurService {
	@Autowired
	private FormateurRepository formateurRepository;

	@Override
	public List<FormateurDto> getAllContacts() {
		List<Formateur> formateurs = formateurRepository.findAll();
		List<FormateurDto> res = new ArrayList<FormateurDto>();
		for (Formateur f : formateurs) {
			res.add(DtoTools.convert(f, FormateurDto.class));
		}
		return res;
	}

	@Override
	public List<FormateurDto> getAllContacts(int page, int max) {
		List<Formateur> formateurs = formateurRepository.findAll(PageRequest.of(page, max)).get()
				.collect(Collectors.toList());
		List<FormateurDto> res = new ArrayList<FormateurDto>();
		for (Formateur f : formateurs) {
			res.add(DtoTools.convert(f, FormateurDto.class));
		}
		return res;
	}

	@Override
	public FormateurDto findById(long id) {
		Optional<Formateur> formateurOpt = formateurRepository.findById(id);
		if (formateurOpt.isPresent())
			return DtoTools.convert(formateurOpt.get(), FormateurDto.class);
		return null;
	}

}
