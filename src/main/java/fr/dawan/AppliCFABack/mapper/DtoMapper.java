package fr.dawan.AppliCFABack.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.AnnexeDto;
import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.CentreFormationDG2Dto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CursusDG2Dto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.EmployeeDG2Dto;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.EtudiantUtilisateurDG2Dto;
import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.FacultatifDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PassageExamenDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.TuteurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.Annexe;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.CompetenceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.EvaluationFormation;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;
import fr.dawan.AppliCFABack.entities.Facultatif;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.MaitreApprentissage;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Tuteur;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;

@Mapper
@Component
public interface DtoMapper {
	
	DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);
    
    default List<Long> mapCompetencesEvalueesIds(List<CompetenceProfessionnelle> competences) {
        return competences.stream()
                .map(CompetenceProfessionnelle::getId)
                .collect(Collectors.toList());
    }
    


    @Mapping(source = ".", target = ".")
    AdresseDto adresseToAdresseDto(Adresse adresse);

    @Mapping(source = ".", target = ".")
    CEFDto cefToCEFDto(CEF cef);

    @Mapping(source = ".", target = ".")
    @Mapping(source = "entreprise", target = "entrepriseDto")
    @Mapping(source = "adresse", target = "adresseDto")
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
    @Mapping(source = "tuteur", target = "tuteurDto")
    @Mapping(source = "utilisateur", target = "utilisateurDto")
    EtudiantDto etudiantToEtudiantDto(Etudiant etudiant);

    @Mapping(source = ".", target = ".")
    @Mapping(source = "descriptif", target = "descriptif")
    @Mapping(source = "activiteTypes", target = "activiteTypesDto")
    @Mapping(source = "competencesProfessionnelles", target = "competencesProfessionnellesDto")
    ExamenDto examenToExamenDto(Examen examen);

    @Mapping(source = ".", target = ".")
    @Mapping(source = "utilisateur", target = "utilisateurDto")
    FormateurDto formateurToFormateurDto(Formateur formateur);

    @Mapping(source = ".", target = ".")
    FormationDto formationToFormationDto(Formation formation);
    
    @Mapping(source = ".", target = ".")
    TuteurDto tuteurTotuteurDto(Tuteur tuteur);

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
    @Mapping(source = "centreFormation", target = "centreFormationDto")
    @Mapping(source = "centreFormation.nom", target = "centreFormationAdresseVille")
    //@Mapping(source = "etudiants", target = "etudiantsDto")
    //@Mapping(source = "interventions", target = "interventionsDto")
    @Mapping(source = "examens", target = "examensDto")
    PromotionDto promotionToPromotionDto(Promotion promotion);


    @Mapping(source = ".", target = ".")
    List<PromotionDto> promotionListToPromotionDtoList(List<Promotion> promotion);
    @Mapping(source = ".", target = ".")
    @Mapping(source = "adresse", target = "adresseDto")
    @Mapping(source = "entreprise", target = "entrepriseDto")
    @Mapping(source = "centreFormation.id", target = "centreFormationId")
    UtilisateurDto utilisateurToUtilisateurDto(Utilisateur utilisateur);
    
    @Mapping(source = ".", target = ".")
    @Mapping(source = "adresseDto", target = "adresse")
    @Mapping(source = "entrepriseDto", target = "entreprise")
    @Mapping(source = "centreFormationId", target = "centreFormation.id")
    Utilisateur utilisateurDtoToUtilisateur(UtilisateurDto utilisateurDto);

    @Mapping(source = ".", target = ".")
    UtilisateurRoleDto utilisateurRoleToUtilisateurRoleDto(UtilisateurRole utilisateurRole);

    @Mapping(source="id", target="id")
    @Mapping(source="nom", target="nom")
    @Mapping(source="cursus", target="cursusDto")
    @Mapping(source="experienceProfessionnelles", target="experienceProfessionnelleDtos")
    @Mapping(source="annexes", target="annexeDtos")
    @Mapping(source="facultatifs", target="facultatifDto")
    DossierProfessionnelDto dossierProfessionnelToDossierProfessionnelDto(DossierProfessionnel dossierProfessionnel);

    
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "cursusDto", target = "cursus")
    @Mapping(source = "experienceProfessionnelleDtos", target = "experienceProfessionnelles")
    @Mapping(source = "annexeDtos", target = "annexes")
    @Mapping(source = "facultatifDto", target = "facultatifs")
    DossierProfessionnel dossierProfessionnelDtoToDossierProfessionnel(DossierProEtudiantDto dosierProEtudiant);

    @Mapping(source = "id", target = "id")
    @Mapping(source="nom", target="nom")
    @Mapping(source="cursus", target="cursusDto")
    @Mapping(source="experienceProfessionnelles", target="experienceProfessionnelleDtos")
    @Mapping(source="annexes", target="annexeDtos")
    @Mapping(source="facultatifs", target="facultatifDto")
    DossierProEtudiantDto dossierProfessionnelToDossierProEtudiantDto(DossierProfessionnel dossierPro);
/*
*
*   Partie Dossier Projet
*
* */
    @Mapping(source = ".", target = ".")
    DossierProjetDto dossierProjetToDpDto(DossierProjet dossierProjet);
    @Mapping(source = ".", target = ".")
    @Mapping(source = "competenceProfessionnelles", target = "competenceProfessionnelleIds", qualifiedByName = "competenceProToId")
    DossierProjetDto dossierProjetToDossierProjetDto(DossierProjet dossierProjet);

    @Mapping(source="competenceProfessionnelleIds", target = "competenceProfessionnelles", qualifiedByName = "idToCompetencePro")
    DossierProjet dossierProjetDtoToDossierProjet(DossierProjetDto dpDto);

    /*
     * Attribution d'une List de Competence Professionnelle a une liste de Long
     * */
    @Named("idToCompetencePro")
    default List<CompetenceProfessionnelle> idToCompetencePro(List<Long> idComp){
        return idComp.stream().map(id -> {
            CompetenceProfessionnelle comp = new CompetenceProfessionnelle();
            comp.setId(id);
            return comp;
        }).collect(Collectors.toList());
    }
    @Named("competenceProToId")
    default List<Long> competenceProToId(List<CompetenceProfessionnelle> competencesPro){
        return competencesPro.stream().map(competencePro -> competencePro.getId()).collect(Collectors.toList());
    }

    //**********************************************************************************//
    @Mapping(source ="id", target = "id")
    @Mapping(source ="libelleAnnexe", target = "libelleAnnexe")
    @Mapping(source ="pieceJointe", target = "pieceJointe")
    AnnexeDto AnnexeToAnnexeDto(Annexe annexe);
    
    @Mapping(source = ".", target = ".")
    FacultatifDto FacultatifToFacultatifDto(Facultatif facultatif);
    

    @Mapping(source = ".", target = ".")
    MaitreApprentissageDto maitreApprentissageToMaitreApprentissageDto(MaitreApprentissage maitreApprentissage);

    @Mapping(source = ".", target = ".")
    ActiviteTypeDto activiteTypeToActiviteTypeDto(ActiviteType activiteType);

    @Mapping(source = ".", target = ".")
    @Mapping(source = "activiteType.id", target = "activiteTypeId")
    CompetenceProfessionnelleDto competenceProfessionnelleToCompetenceProfessionnelleDto(CompetenceProfessionnelle competenceProfessionnelle);
    @Mapping(source = ".", target = ".")
    @Mapping(source = "activiteTypeId", target = "activiteType.id")
    CompetenceProfessionnelle competenceProfessionnelleDtoToCompetenceProfessionnelle(CompetenceProfessionnelleDto competenceProfessionnelleDto);
    //@Mapping(target = "cursusLst", ignore = true)
    //@Mapping(source = "id", target = "idDg2")
    //@Mapping(source = "title", target = "titre")
    //@Mapping(source = "objectives", target = "objectif")
    //@Mapping(source = "prerequisites", target = "prerequis")
    //Formation formationDG2DtoToFormation(FormationDG2Dto formationDG2Dto);

    @Mapping(target = "activiteTypes", ignore = true)
    @Mapping(target = "formations", ignore = true)
    @Mapping(source = "id", target = "idDg2")
    @Mapping(source = "title", target = "titre")
    @Mapping(source = "duration", target = "duree")
    Cursus cursusDG2DtoToCursus(CursusDG2Dto cursusDG2Dto);

    @Mapping(target = "adresse", ignore = true)
    @Mapping(target = "entreprise", ignore = true)
    @Mapping(source = "name", target = "nom")
    @Mapping(source = "id", target = "idDg2")
    @Mapping(target = "id", ignore = true)
    CentreFormation centreFormationDG2DtoToCentreFormation(CentreFormationDG2Dto centreFormationDG2Dto);


    @Mapping(source = ".", target = ".")
    ExperienceProfessionnelleDto experienceProfessionnelleToExperienceProfessionnelleDto(
            ExperienceProfessionnelle experienceProfessionnelle);
   


	@Mapping(source = "personId", target ="idDg2" )
	@Mapping(source = "firstName", target = "prenom")
	@Mapping(source = "lastName", target = "nom")
	@Mapping(source = "email", target = "login")
	@Mapping(source = "landline", target = "telephoneFixe")
	@Mapping(source = "mobile", target = "telephone")
    Utilisateur etudiantUtilisateurDG2DtoToUtilisateur(EtudiantUtilisateurDG2Dto eDG2);

	@Mapping(source = "personId", target ="idDg2" )
	@Mapping(source = "firstName", target = "prenom")
	@Mapping(source = "lastName", target = "nom")
	@Mapping(source = "email", target = "login")

	Utilisateur employeeDg2ToUtilisateur(EmployeeDG2Dto eDg2);
	

	@Mapping(source = "postcode", target = "codePostal")
	@Mapping(source = "city", target = "ville")
	@Mapping(source = "country", target = "countryCode")
	@Mapping(source = "street", target = "libelle")
    Adresse etudiantUtilisateurDG2DtoToAdresse(EtudiantUtilisateurDG2Dto eDG2);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "libelleAnnexe", target = "libelleAnnexe")
	@Mapping(source = "pieceJointe", target = "pieceJointe")
	//@Mapping(source = "dossierProfessionnel", target = "dossierProfessionnelId")
	List<AnnexeDto> annexeToAnnexeDto(List<Annexe> annexes);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "intitule", target = "intitule")
	@Mapping(source = "organisme", target = "organisme")
	@Mapping(source = "date", target = "date")
	List<FacultatifDto> facultatifToFacultatifDto(List<Facultatif> facultatifs);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "tacheRealisee", target = "tacheRealisee")
	@Mapping(source = "moyenUtilise", target = "moyenUtilise")
	@Mapping(source = "collaborateur", target = "collaborateur")
	@Mapping(source = "contexte", target = "contexte")
	@Mapping(source = "information", target = "information")
	List<ExperienceProfessionnelleDto> experienceProfessionnelleToExperienceProfessionnelleDto(
			List<ExperienceProfessionnelle> experienceProfessionnelles);
    @Mapping(source = "intervention.id", target = "interventionId")
    @Mapping(source = "competencesEvaluees", target = "competencesEvalueesId", qualifiedByName = "competenceProToId")
    EvaluationFormationDto evaluationToEvaluationDto(EvaluationFormation eval);
    @Mapping(source = "interventionId", target = "intervention.id")
    @Mapping(source = "competencesEvalueesId", target = "competencesEvaluees", qualifiedByName = "idToCompetencePro")
    EvaluationFormation evaluationDtoToEvaluation(EvaluationFormationDto evalDto);
}
