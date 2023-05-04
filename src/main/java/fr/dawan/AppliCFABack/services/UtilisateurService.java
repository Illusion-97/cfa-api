package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.LoginDto;
import fr.dawan.AppliCFABack.dto.LoginResponseDto;
import fr.dawan.AppliCFABack.dto.ResetResponse;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.tools.EmailResetPasswordException;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import fr.dawan.AppliCFABack.tools.FileException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

public interface UtilisateurService {
	List<UtilisateurDto> getAll();

	List<UtilisateurDto> getAllUtilisateurs(int page, int size, String string);

	CountDto count(String string);

	List<UtilisateurDto> findAllByRoleByPage(int page, int size, String role, String search);

	CountDto countByRole(String role, String string);

	List<UtilisateurDto> getAllWithObject();

	UtilisateurDto getById(long id);

	UtilisateurDto findByEmail(String email);

	UtilisateurDto getName(String name);

	UtilisateurDto insertUpdate(UtilisateurDto uDto) throws SaveInvalidException;
	
	UtilisateurDto insertTuteur(UtilisateurDto uDto) throws SaveInvalidException;

	void deleteById(long id);

	List<UtilisateurDto> findByAdresse(String ville);

	List<JourneePlanningDto> getAllJourneePlanningByIdUtilisateur(long id);

	List<CongeDto> getAllCongesByIdUtilisateur(long id);

	AdresseDto getAdresseByIdUtilisateur(long id);

	UtilisateurDto getByIdWithObject(long id);

	List<UtilisateurDto> findByRole(long idRole);

//    List<AbsenceDto> findAllByEtudiantPromotionsReferentPedagogiqueId(long id);

//    CountDto countEtudiantPromotionsReferentPedagogiqueId(long id);

	Boolean isReferent(long id);

	void uploadFile(MultipartFile file, long idUser) throws FileException, IOException;

	boolean resetPassword(ResetResponse reset) throws EmailResetPasswordException;

	String generatePassword();

	void fetchAllDG2Employees(String email, String password)
			throws FetchDG2Exception, URISyntaxException, JsonProcessingException;
	
	LoginResponseDto checkLogin(LoginDto loginDto) throws Exception;


}
