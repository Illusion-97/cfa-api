package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PassageExamenDto;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.repositories.NoteRepository;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository noteRepository;

	@Override
	public List<NoteDto> getAllNote() {
		List<Note> lst = noteRepository.findAll();

		List<NoteDto> lstDto = new ArrayList<NoteDto>();
		for (Note n : lst) {
			lstDto.add(DtoTools.convert(n, NoteDto.class));
		}
		return lstDto;
	}

	@Override
	public List<NoteDto> getAllByPage(int page, int size, String search) {
		List<Note> lst = noteRepository.findAllByEtudiantPrenomContainingIgnoringCaseOrEtudiantNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(search,search, search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<NoteDto> lstDto = new ArrayList<NoteDto>();
		for (Note n : lst) {
			NoteDto nDto = DtoTools.convert(n, NoteDto.class);
			nDto.setDevoirDto(DtoTools.convert(n.getDevoir(), DevoirDto.class));
			nDto.setEtudiantDto(DtoTools.convert(n.getEtudiant(), EtudiantDto.class));
			nDto.setExamenDto(DtoTools.convert(n.getExamen(), PassageExamenDto.class));
			lstDto.add(nDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(noteRepository.countByEtudiantPrenomContainingIgnoringCaseOrEtudiantNomContainingIgnoringCaseOrExamenExamenEnonceContainingIgnoringCaseOrDevoirEnonceContainingIgnoringCase(search, search, search, search));
	}

	@Override
	public NoteDto getById(long id) {
		Optional<Note> n = noteRepository.findById(id);
		if (!n.isPresent())
			return null;
		
		NoteDto nDto = DtoTools.convert(n.get(), NoteDto.class);
		nDto.setDevoirDto(DtoTools.convert(n.get().getDevoir(), DevoirDto.class));
		nDto.setEtudiantDto(DtoTools.convert(n.get().getEtudiant(), EtudiantDto.class));
		nDto.setExamenDto(DtoTools.convert(n.get().getExamen(), PassageExamenDto.class));
		//nDto.getExamenDto().setExamenDto(DtoTools.convert(n.get().getExamen().getExamen(), ExamenDto.class));
		return nDto;
	}

	@Override
	public NoteDto saveOrUpdate(NoteDto nDto) {
		Note n = DtoTools.convert(nDto, Note.class);

		n = noteRepository.saveAndFlush(n);

		return DtoTools.convert(n, NoteDto.class);
	}

	@Override
	public CountDto count() {
		return new CountDto(noteRepository.count());
	}
	@Override
	public void deleteById(long id) {
		noteRepository.deleteById(id);

	}

}
