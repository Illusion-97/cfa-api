package fr.dawan.AppliCFABack.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.DossierProjetEtudiantDto;

public interface DossierProjetService {

	List<DossierProjetDto> getAll();

	DossierProjetEtudiantDto getById(long id);
	
	DossierProjetDto getByName(String nom);

	List<DossierProjetDto> getAllByPage(int page, int size, String string);

	DossierProjetDto saveOrUpdate(DossierProjetDto dpDto);

	void deleteById(long id);

	List<DossierProjetDto> getByIdEtudiant(long id);

	DossierProjetEtudiantDto saveOrUpdateDossierProjet(DossierProjetEtudiantDto dpDto, long id, List<MultipartFile> file);
	

}
