package fr.dawan.AppliCFABack.mapper;

import java.util.List;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DtoMapper {

	@Mapping(source = ".", target = ".")
	CompetenceProfessionnelleDto CompetenceProfessionnelleDto(CompetenceProfessionnelle competenceProfessionnelle);

	@Mapping(source = ".", target = ".")
	ActiviteTypeDto ActiviteTypeToActiviteDto(ActiviteType activiteType);

	@Mapping(source = ".", target = ".")
	AdresseDto AdresseToAdresseDto(Adresse adresse);

	@Mapping(source = ".", target = ".")
	CEFDto CEFToCEFDto(CEF cef);

	@Mapping(source = ".", target = ".")
	CentreFormationDto CentreFormationToCentreFormationDto(CentreFormation centreFormation);

	@Mapping(source = ".", target = ".")
	CongeDto CongeToCongeDto(Conge conge);

	@Mapping(source = ".", target = ".")
	CursusDto CursusToCursusDto(Cursus cursus);

	@Mapping(source = ".", target = ".")
	DevoirDto DevoirToDevoirDto(Devoir devoir);

	@Mapping(source = ".", target = ".")
	EntrepriseDto EntrepriseToEntrepriseDto(Entreprise entreprise);

	@Mapping(source = ".", target = ".")
	EtudiantDto EtudiantToEtudiantDto(Etudiant etudiant);

	@Mapping(source = ".", target = ".")
	@Mapping(source = "promotion.id", target = "promotionId")
	ExamenDto ExamenToExamenDto(Examen examen);

	@Mapping(source = ".", target = ".")
	@Mapping(source = "utilisateur", target = "utilisateurDto")
	FormateurDto FormateurToFormateurDto(Formateur formateur);

	@Mapping(source = ".", target = ".")
	FormationDto FormationToFormationDto(Formation formation);

	@Mapping(source = ".", target = ".")
	GroupeEtudiantDto GroupeEtudiantToGroupEtudiantDto(GroupeEtudiant groupeEtudiant);

	@Mapping(source = ".", target = ".")
	InterventionDto InterventionToInterventionDto(Intervention intervention);

	@Mapping(source = ".", target = ".")
	NoteDto NoteToNoteDto(Note note);

	@Mapping(source = ".", target = ".")
	PassageExamenDto PassageExamenToPassageExamenDto(PassageExamen passageExamen);

	@Mapping(source = ".", target = ".")
	ProjetDto ProjetToProjetDto(Projet projet);

	@Mapping(source = ".", target = ".")
	@Mapping(source = "cursus", target = "cursusDto")
	PromotionDto PromotionToPromotionDto(Promotion promotion);

	@Mapping(source = ".", target = ".")
	UtilisateurDto UtilisateurToUtilisateurDto(Utilisateur utilisateur);

	@Mapping(source = ".", target = ".")
	UtilisateurRoleDto UtilisateurRoleToUtilisateurRoleDto(UtilisateurRole utilisateurRole);

	@Mapping(source = ".", target = ".")
	FichePosteDto FichePosteToFichePosteDto(FichePoste fichePoste);

	@Mapping(source = ".", target = ".")
	DossierProfessionnelDto DossierProfessionnelToDossierProfessionnelDto(DossierProfessionnel dossierProfessionnel);

	@Mapping(source = ".", target = ".")
	DossierProjetDto DossierProjetToDossierProjetDto(DossierProjet dossierProjet);

	@Mapping(source = ".", target = ".")
	FicheEntrepriseDto FicheEntrepriseToFicheEntrepriseDto(FicheEntreprise FicheEntreprise);

	@Mapping(source = ".", target = ".")
	ContratDto ContratToContratDto(Contrat contrat);

	@Mapping(source = ".", target = ".")
	MaitreApprentissageDto MaitreApprentissageToMaitreApprentissageDto(MaitreApprentissage maitreApprentissage);

	@Mapping(source = ".", target = ".")
	CerfaDto CerfaToCerfaDto(Cerfa cerfa);

	@Mapping(source = ".", target = ".")
	RemunerationDto RemunerationTORemunerationDto(Remuneration remuneration);

	@Mapping(source = ".", target = ".")
	ActiviteTypeDto ActiviteTypeToActiviteTypeDto(ActiviteType activiteType);

	@Mapping(source = ".", target = ".")
	CompetenceProfessionnelleDto CompetenceProfessionnelleToCompetenceProfessionnelleDto(
			CompetenceProfessionnelle competenceProfessionnelle);

	@Mapping(target = "contenu", ignore = true)
	@Mapping(target = "cursusLst", ignore = true)
	@Mapping(source = "id", target = "idDg2")
	@Mapping(source = "title", target = "titre")
	@Mapping(source = "duration", target = "duration")
	Formation formationDG2DtoToFormation(FormationDG2Dto formationDG2Dto);

	@Mapping(target = "formations", ignore = true)
	@Mapping(source = "slug", target = "titre")
	Cursus cursusDG2DtoToCursus(InterventionDG2Dto cursusDG2Dto);

	List<Cursus> lstCursusDG2DtoToListCursus(List<InterventionDG2Dto> lstCurusDto);

	@Mapping(target = "adresse", ignore = true)
	@Mapping(target = "entreprise", ignore = true)
	@Mapping(source = "name", target = "nom")
	@Mapping(source = "id", target = "idDg2")
	@Mapping(source = "country", target = "countryCode")
	@Mapping(target = "id", ignore = true)
	CentreFormation centreFormationDG2DtoToCentreFormation(CentreFormationDG2Dto centreFormationDG2Dto);


	@Mapping(source = ".", target = ".")
	ExperienceProfessionnelleDto ExperienceProfessionnelleToExperienceProfessionnelleDto(
			ExperienceProfessionnelle experienceProfessionnelle);
}
