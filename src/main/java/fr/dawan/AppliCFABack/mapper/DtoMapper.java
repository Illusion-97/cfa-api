package fr.dawan.AppliCFABack.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.CentreFormationDG2Dto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.ContratDto;
import fr.dawan.AppliCFABack.dto.CursusDG2Dto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.EtudiantUtilisateurDG2Dto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.FicheEntrepriseDto;
import fr.dawan.AppliCFABack.dto.FichePosteDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDG2Dto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDG2Dto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PassageExamenDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.RemunerationDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Cerfa;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Contrat;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;
import fr.dawan.AppliCFABack.entities.FicheEntreprise;
import fr.dawan.AppliCFABack.entities.FichePoste;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.MaitreApprentissage;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Remuneration;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;

@Mapper
@Component
public interface DtoMapper {

    @Mapping(source = ".", target = ".")
    CompetenceProfessionnelleDto competenceProfessionnelleDto(CompetenceProfessionnelle competenceProfessionnelle);

    @Mapping(source = ".", target = ".")
    ActiviteTypeDto activiteTypeToActiviteDto(ActiviteType activiteType);

    @Mapping(source = ".", target = ".")
    AdresseDto adresseToAdresseDto(Adresse adresse);

    @Mapping(source = ".", target = ".")
    CEFDto cefToCEFDto(CEF cef);

    @Mapping(source = ".", target = ".")
    CentreFormationDto centreFormationToCentreFormationDto(CentreFormation centreFormation);

    @Mapping(source = ".", target = ".")
    CongeDto congeToCongeDto(Conge conge);

    @Mapping(source = ".", target = ".")
    CursusDto cursusToCursusDto(Cursus cursus);

    @Mapping(source = ".", target = ".")
    DevoirDto devoirToDevoirDto(Devoir devoir);

    @Mapping(source = ".", target = ".")
    EntrepriseDto entrepriseToEntrepriseDto(Entreprise entreprise);

    @Mapping(source = ".", target = ".")
    EtudiantDto etudiantToEtudiantDto(Etudiant etudiant);

    @Mapping(source = ".", target = ".")
    ExamenDto examenToExamenDto(Examen examen);

    @Mapping(source = ".", target = ".")
    @Mapping(source = "utilisateur", target = "utilisateurDto")
    FormateurDto formateurToFormateurDto(Formateur formateur);

    @Mapping(source = ".", target = ".")
    FormationDto formationToFormationDto(Formation formation);

    @Mapping(source = ".", target = ".")
    GroupeEtudiantDto groupeEtudiantToGroupEtudiantDto(GroupeEtudiant groupeEtudiant);

    @Mapping(source = ".", target = ".")
    InterventionDto interventionToInterventionDto(Intervention intervention);

    @Mapping(source = ".", target = ".")
    NoteDto noteToNoteDto(Note note);

    @Mapping(source = ".", target = ".")
    PassageExamenDto passageExamenToPassageExamenDto(PassageExamen passageExamen);

    @Mapping(source = ".", target = ".")
    ProjetDto projetToProjetDto(Projet projet);

    @Mapping(source = ".", target = ".")
    @Mapping(source = "cursus", target = "cursusDto")
    PromotionDto promotionToPromotionDto(Promotion promotion);

    @Mapping(source = ".", target = ".")
    UtilisateurDto utilisateurToUtilisateurDto(Utilisateur utilisateur);

    @Mapping(source = ".", target = ".")
    UtilisateurRoleDto utilisateurRoleToUtilisateurRoleDto(UtilisateurRole utilisateurRole);

    @Mapping(source = ".", target = ".")
    FichePosteDto fichePosteToFichePosteDto(FichePoste fichePoste);

    @Mapping(source = ".", target = ".")
    DossierProfessionnelDto dossierProfessionnelToDossierProfessionnelDto(DossierProfessionnel dossierProfessionnel);

    @Mapping(source = ".", target = ".")
    DossierProjetDto dossierProjetToDossierProjetDto(DossierProjet dossierProjet);

    @Mapping(source = ".", target = ".")
    FicheEntrepriseDto ficheEntrepriseToFicheEntrepriseDto(FicheEntreprise FicheEntreprise);

    @Mapping(source = ".", target = ".")
    ContratDto contratToContratDto(Contrat contrat);

    @Mapping(source = ".", target = ".")
    MaitreApprentissageDto maitreApprentissageToMaitreApprentissageDto(MaitreApprentissage maitreApprentissage);

    @Mapping(source = ".", target = ".")
    CerfaDto cerfaToCerfaDto(Cerfa cerfa);

    @Mapping(source = ".", target = ".")
    RemunerationDto remunerationTORemunerationDto(Remuneration remuneration);

    @Mapping(source = ".", target = ".")
    ActiviteTypeDto activiteTypeToActiviteTypeDto(ActiviteType activiteType);

    @Mapping(source = ".", target = ".")
    CompetenceProfessionnelleDto competenceProfessionnelleToCompetenceProfessionnelleDto(
            CompetenceProfessionnelle competenceProfessionnelle);

    @Mapping(target = "cursusLst", ignore = true)
    @Mapping(source = "id", target = "idDg2")
    @Mapping(source = "title", target = "titre")
    @Mapping(source = "objectives", target = "objectif")
    @Mapping(source = "prerequisites", target = "prerequis")
    Formation formationDG2DtoToFormation(FormationDG2Dto formationDG2Dto);

    @Mapping(target = "activiteTypes", ignore = true)
    @Mapping(target = "formations", ignore = true)
    @Mapping(source = "id", target = "idDg2")
    @Mapping(source = "title", target = "titre")
    @Mapping(source = "duration", target = "duree")
    Cursus cursusDG2DtoToCursus(CursusDG2Dto cursusDG2Dto);

//	@Mapping(target = "formations", ignore = true)
//	@Mapping(source = "slug", target = "titre")
//	Cursus cursusDG2DtoToCursus(InterventionDG2Dto cursusDG2Dto);

    List<Cursus> lstCursusDG2DtoToListCursus(List<InterventionDG2Dto> lstCurusDto);

    @Mapping(target = "adresse", ignore = true)
    @Mapping(target = "entreprise", ignore = true)
    @Mapping(source = "name", target = "nom")
    @Mapping(source = "id", target = "idDg2")
    @Mapping(target = "id", ignore = true)
    CentreFormation centreFormationDG2DtoToCentreFormation(CentreFormationDG2Dto centreFormationDG2Dto);


    @Mapping(source = ".", target = ".")
    ExperienceProfessionnelleDto experienceProfessionnelleToExperienceProfessionnelleDto(
            ExperienceProfessionnelle experienceProfessionnelle);


    @Mapping(source = "personId", target = "idDg2")
    @Mapping(target = "promotions", ignore = true)
    @Mapping(target = "groupes", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "dossierProfessionnel", ignore = true)
    @Mapping(target = "dossierProjet", ignore = true)
    Etudiant etudiantUtilisateurDG2DtoToEtudiant(EtudiantUtilisateurDG2Dto eDG2);

//	@Mapping(source = ".", target = ".")
//	@Mapping(source = "cursus", target = "cursusDescription")
//	PromotionEtudiantDto PromotionToPromotionEtudiantDto(Promotion promotion);
}
