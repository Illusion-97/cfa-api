package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    UtilisateurDto insertUpdate(UtilisateurDto uDto) throws Exception;

    void deleteById(long id);

    List<UtilisateurDto> findByAdresse(String ville);

//	List<UtilisateurDto> findByEntreprise(long idEntreprise);

    List<JourneePlanningDto> getAllJourneePlanningByIdUtilisateur(long id);

    List<CongeDto> getAllCongesByIdUtilisateur(long id);

    AdresseDto getAdresseByIdUtilisateur(long id);

    UtilisateurDto getByIdWithObject(long id);

    List<UtilisateurDto> findByRole(long idRole);

    List<AbsenceDto> findAllByEtudiantPromotionsReferentPedagogiqueId(long id);

    CountDto countEtudiantPromotionsReferentPedagogiqueId(long id);

    Boolean isReferent(long id);

    void uploadFile(MultipartFile file, long idUser) throws Exception;

	boolean resetPassword(ResetResponse reset) throws Exception;

}
