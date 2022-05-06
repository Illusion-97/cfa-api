package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.*;


public interface PromotionService {

	List<PromotionDto> getAll();

	PromotionDto getById(long id);

	PromotionDto saveOrUpdate(PromotionDto pDto);

	void deleteById(long id);

	UtilisateurDto getReferentById(long id);

	CountDto count(String string);

	List<PromotionDto> getAllPromotions(int page, int size, String string);

	List<EtudiantDto> getEtudiantsById(long id);

	List<PromotionDto> getAllByCursusId(long id);

	List<PromotionDto> getPromotionByEtudiantIdAndByCursusId(long id);

	UtilisateurDto getCefById(long id);

	List<PromotionEtudiantDto> getCursusByIdEtudiant(long id);
}
