package fr.dawan.AppliCFABack.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.CentreFormationDG2Dto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.ContratDto;
import fr.dawan.AppliCFABack.dto.InterventionDG2Dto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.FicheEntrepriseDto;
import fr.dawan.AppliCFABack.dto.FichePosteDto;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDG2Dto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PassageExamenDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.RemunerationDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Cerfa;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Contrat;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
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
	AbsenceDto AbsenceToAbsenceDto(Absence absence);

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
	ExamenDto ExamenToExamenDto(Examen examen);

	@Mapping(source = ".", target = ".")
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
	
}
