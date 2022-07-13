package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.customdtos.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;

public interface DossierProfessionnelService {
	List<DossierProfessionnelDto> getAll();

	DossierProfessionnelDto getById(long id);

	List<DossierProfessionnelDto> getAllByPage(int page, int size, String string);

	DossierProfessionnelDto saveOrUpdate(DossierProfessionnelDto dpDto);

	void deleteById(long id);
	
	List<DossierProfessionnelDto> getByIdEtudiant(long id);

	DossierProfessionnelDto getByName(String nom);


	DossierProEtudiantDto saveOrUpdateDossierProfessionnel(DossierProEtudiantDto dpDto, long id);

	List<DossierProEtudiantDto> getAllDossierProfessionnel();

    String generateDossierProByStudentAndPromo(long etudiantId, long promotionId) throws Exception;
}
