package fr.dawan.AppliCFABack.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.tools.EmailResetPasswordException;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import fr.dawan.AppliCFABack.tools.FileException;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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

	void modifierRolesUtilisateur(long utilisateurId, List<Long> nouveauRolesIds) throws NotFoundException;


}
