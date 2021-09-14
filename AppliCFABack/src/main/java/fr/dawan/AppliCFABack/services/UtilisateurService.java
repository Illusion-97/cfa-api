package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;

public interface UtilisateurService {
	List<UtilisateurDto> getAll();

	List<UtilisateurDto> getAllUtilisateurs(int page, int size, String string);
	
	CountDto count(String string);
	
	List<UtilisateurDto> findAllByRoleByPage(int page, int size,String role, String search);

	CountDto countByRole(String role, String string);

	List<UtilisateurDto> getAllWithObject();

	UtilisateurDto getById(long id);

	UtilisateurDto findByEmail(String email);

	UtilisateurDto getName(String name);

	UtilisateurDto insertUpdate(UtilisateurDto uDto) throws Exception;

	void deleteById(long id);

	List<UtilisateurDto> findByAdresse(String ville);

	List<UtilisateurDto> findByEntreprise(long idEntreprise);

	List<JourneePlanningDto> getAllJourneePlanningByIdUtilisateur(long id);

	List<CongeDto> getAllCongesByIdUtilisateur(long id);

	AdresseDto getAdresseByIdUtilisateur(long id);

	UtilisateurDto getByIdWithObject(long id);

	List<UtilisateurDto> findByRole(long idRole);

	List<AbsenceDto> findAllByEtudiantPromotionsReferentPedagogiqueId(long id);

	CountDto countEtudiantPromotionsReferentPedagogiqueId(long id);

}
