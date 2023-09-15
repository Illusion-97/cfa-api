package fr.dawan.AppliCFABack.services;


import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.TuteurDto;
import fr.dawan.AppliCFABack.entities.Tuteur;
import fr.dawan.AppliCFABack.entities.Utilisateur;

import java.util.List;


public interface TuteurService extends GenericService<TuteurDto>{

	List<TuteurDto> getAll();

	List<TuteurDto> getAllByPage(int page, int size);

	List<TuteurDto> getAllByPageWithKeyword(int page, int size, String search);

	TuteurDto getById(long id);
	
	TuteurDto saveOrUpdate(TuteurDto tuteurDto);
	
	List<EtudiantDto> findAllByTuteurId(long id);
	
	List<EtudiantDto> getAllEtudiantsByTuteurIdPerPage(long id, int page, int size);
	
	List<EtudiantDto> getEtudiantBySearch(long id, int page, int size, String search);
	
	CountDto countEtudiantByIdTuteur(long id);

	CountDto countEtudiantByIdTuteurSearch(long id, String search);

	//CountDto count(String string);
	
	Tuteur saveTuteur(Utilisateur utilisateur);

}
