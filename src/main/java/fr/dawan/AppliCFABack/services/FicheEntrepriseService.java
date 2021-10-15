package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.FicheEntrepriseDto;

public interface FicheEntrepriseService {

	List<FicheEntrepriseDto> getAllFicheEntreprise();

	List<FicheEntrepriseDto> getAllByPage(int page, int size, String string);

//	CountDto count(String string);

	FicheEntrepriseDto getById(long id);

	FicheEntrepriseDto saveOrUpdate(FicheEntrepriseDto fDto);

	void deleteById(long id);

	FicheEntrepriseDto getByIdEtudiant(long id);
}
