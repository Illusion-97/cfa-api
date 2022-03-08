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
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FichePosteDto;
import fr.dawan.AppliCFABack.entities.FichePoste;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.FichePosteRepository;

@Service
@Transactional
public class FichePosteServiceImpl implements FichePosteService{
	
	@Autowired
	FichePosteRepository fichePosteRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	//recuperation de la liste des fiches poste
	@Override
	public List<FichePosteDto> getAllFichePoste() {
		List<FichePoste> lst = fichePosteRepository.findAll();
		
		List<FichePosteDto> lstDto = new ArrayList<FichePosteDto>();
		for (FichePoste n : lst) {
			FichePosteDto fDto =mapper.FichePosteToFichePosteDto(n);
			fDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(n.getEtudiant()));
			lstDto.add(fDto);
		}
		return lstDto;
	}

	//recuperation de la liste des fiches postes + pagination + recherche
	@Override
	public List<FichePosteDto> getAllByPage(int page, int size, String search) {
		List<FichePoste> lst = fichePosteRepository.findAllByIntituleContainingIgnoringCase(search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<FichePosteDto> lstDto = new ArrayList<FichePosteDto>();
		for (FichePoste c : lst) {
			FichePosteDto fDto =mapper.FichePosteToFichePosteDto(c);
			lstDto.add(fDto);
		}
		return lstDto;
	}

	//methode count
	@Override
	public CountDto count(String search) {
		return new CountDto(fichePosteRepository.countByIntituleContainingIgnoringCase(search));
	}
	
	//recuperation fiches poste par id
	@Override
	public FichePosteDto getById(long id) {
		Optional<FichePoste> e = fichePosteRepository.findById(id);
		if (e.isPresent()) {
			
			FichePosteDto fDto = mapper.FichePosteToFichePosteDto(e.get());
			fDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(e.get().getEtudiant()));			
			return fDto;
		}			

		return null;
	}

	//methode d'ajout ou modification d'une fiche de poste
	@Override
	public FichePosteDto saveOrUpdate(FichePosteDto fDto) {
		FichePoste u = DtoTools.convert(fDto, FichePoste.class);
		
		u = fichePosteRepository.saveAndFlush(u);
		
		return mapper.FichePosteToFichePosteDto(u);
	}

	//methode de suppression d'une fiche de poste
	@Override
	public void deleteById(long id) {
		fichePosteRepository.deleteById(id);
		
	}

	//recuperation des fiches de poste par id etudiant
	@Override
	public FichePosteDto getByIdEtudiant(long id) {
		List<FichePosteDto> lst = getAllFichePoste();
		FichePosteDto f = new FichePosteDto();
		for (FichePosteDto fichePosteDto : lst) {
			if(fichePosteDto.getEtudiantDto().getId() == id) {
				f = fichePosteDto;
			}
		}
		return f;
	}

}
