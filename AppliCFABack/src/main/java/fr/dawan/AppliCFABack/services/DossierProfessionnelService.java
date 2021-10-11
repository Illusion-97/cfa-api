package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;

public interface DossierProfessionnelService {
	List<DossierProfessionnelDto> getAll();

	DossierProfessionnelDto getById(long id);

	List<DossierProfessionnelDto> getAllByPage(int page, int size, String string);

	DossierProfessionnelDto saveOrUpdate(DossierProfessionnelDto dpDto);

	void deleteById(long id);
	
	public List<DossierProfessionnelDto> getByIdEtudiant(long id);

	DossierProfessionnelDto getByName(String nom);

}
