package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.TuteurDto;
import fr.dawan.AppliCFABack.entities.Etudiant;


public interface TuteurService {

	List<TuteurDto> getAll();

	List<TuteurDto> getAllByPage(int page, int size);

	List<TuteurDto> getAllByPageWithKeyword(int page, int size, String search);

	TuteurDto getById(long id);
	
	TuteurDto saveOrUpdate(TuteurDto tuteurDto);
	
	//List<EtudiantDto> getAllByEtudiatId(long id, int page, int size);
	List<EtudiantDto> findAllByTuteurId(long id);
	
	List<EtudiantDto> getAllEtudiantsByTuteurIdPerPage(long id, int page, int size);
	
	//CountDto count(String string);
	
}
