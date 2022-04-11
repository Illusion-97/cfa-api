package fr.dawan.AppliCFABack.mapper;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.CentreFormationDG2Dto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.ContratDto;
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
import fr.dawan.AppliCFABack.entities.Absence;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-11T11:11:57+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class DtoMapperImpl implements DtoMapper {

    @Override
    public CompetenceProfessionnelleDto CompetenceProfessionnelleDto(CompetenceProfessionnelle competenceProfessionnelle) {
        if ( competenceProfessionnelle == null ) {
            return null;
        }

        CompetenceProfessionnelleDto competenceProfessionnelleDto = new CompetenceProfessionnelleDto();

        competenceProfessionnelleDto.setId( competenceProfessionnelle.getId() );
        competenceProfessionnelleDto.setLibelle( competenceProfessionnelle.getLibelle() );
        competenceProfessionnelleDto.setNumeroFiche( competenceProfessionnelle.getNumeroFiche() );

        return competenceProfessionnelleDto;
    }

    @Override
    public ActiviteTypeDto ActiviteTypeToActiviteDto(ActiviteType activiteType) {
        if ( activiteType == null ) {
            return null;
        }

        ActiviteTypeDto activiteTypeDto = new ActiviteTypeDto();

        activiteTypeDto.setId( activiteType.getId() );
        activiteTypeDto.setLibelle( activiteType.getLibelle() );
        activiteTypeDto.setNumeroFiche( activiteType.getNumeroFiche() );

        return activiteTypeDto;
    }

    @Override
    public AbsenceDto AbsenceToAbsenceDto(Absence absence) {
        if ( absence == null ) {
            return null;
        }

        AbsenceDto absenceDto = new AbsenceDto();

        absenceDto.setId( absence.getId() );
        absenceDto.setDateDebut( absence.getDateDebut() );
        absenceDto.setDateFin( absence.getDateFin() );
        absenceDto.setJustificatif( absence.getJustificatif() );

        return absenceDto;
    }

    @Override
    public AdresseDto AdresseToAdresseDto(Adresse adresse) {
        if ( adresse == null ) {
            return null;
        }

        AdresseDto adresseDto = new AdresseDto();

        adresseDto.setId( adresse.getId() );
        adresseDto.setNumero( adresse.getNumero() );
        adresseDto.setRue( adresse.getRue() );
        adresseDto.setVille( adresse.getVille() );
        adresseDto.setCodePostal( adresse.getCodePostal() );

        return adresseDto;
    }

    @Override
    public CEFDto CEFToCEFDto(CEF cef) {
        if ( cef == null ) {
            return null;
        }

        CEFDto cEFDto = new CEFDto();

        cEFDto.setId( cef.getId() );

        return cEFDto;
    }

    @Override
    public CentreFormationDto CentreFormationToCentreFormationDto(CentreFormation centreFormation) {
        if ( centreFormation == null ) {
            return null;
        }

        CentreFormationDto centreFormationDto = new CentreFormationDto();

        centreFormationDto.setId( centreFormation.getId() );
        centreFormationDto.setNom( centreFormation.getNom() );
        centreFormationDto.setIdDg2( centreFormation.getIdDg2() );
        centreFormationDto.setCountryCode( centreFormation.getCountryCode() );

        return centreFormationDto;
    }

    @Override
    public CongeDto CongeToCongeDto(Conge conge) {
        if ( conge == null ) {
            return null;
        }

        CongeDto congeDto = new CongeDto();

        congeDto.setId( conge.getId() );
        congeDto.setDateDebut( conge.getDateDebut() );
        congeDto.setDateFin( conge.getDateFin() );
        congeDto.setMotif( conge.getMotif() );
        congeDto.setType( conge.getType() );
        congeDto.setStatus( conge.getStatus() );
        congeDto.setJustificatif( conge.getJustificatif() );

        return congeDto;
    }

    @Override
    public CursusDto CursusToCursusDto(Cursus cursus) {
        if ( cursus == null ) {
            return null;
        }

        CursusDto cursusDto = new CursusDto();

        cursusDto.setId( cursus.getId() );
        cursusDto.setTitre( cursus.getTitre() );
        cursusDto.setDescription( cursus.getDescription() );
        cursusDto.setDuree( cursus.getDuree() );

        return cursusDto;
    }

    @Override
    public DevoirDto DevoirToDevoirDto(Devoir devoir) {
        if ( devoir == null ) {
            return null;
        }

        DevoirDto devoirDto = new DevoirDto();

        devoirDto.setId( devoir.getId() );
        devoirDto.setEnonce( devoir.getEnonce() );
        devoirDto.setDateDebut( devoir.getDateDebut() );
        devoirDto.setDateFin( devoir.getDateFin() );

        return devoirDto;
    }

    @Override
    public EntrepriseDto EntrepriseToEntrepriseDto(Entreprise entreprise) {
        if ( entreprise == null ) {
            return null;
        }

        EntrepriseDto entrepriseDto = new EntrepriseDto();

        entrepriseDto.setId( entreprise.getId() );
        entrepriseDto.setRaisonSociale( entreprise.getRaisonSociale() );
        entrepriseDto.setSiret( entreprise.getSiret() );
        entrepriseDto.setNaf( entreprise.getNaf() );
        entrepriseDto.setEffectifTotal( entreprise.getEffectifTotal() );
        entrepriseDto.setEmployeurType( entreprise.getEmployeurType() );

        return entrepriseDto;
    }

    @Override
    public EtudiantDto EtudiantToEtudiantDto(Etudiant etudiant) {
        if ( etudiant == null ) {
            return null;
        }

        EtudiantDto etudiantDto = new EtudiantDto();

        etudiantDto.setId( etudiant.getId() );
        etudiantDto.setDossierProfessionnel( dossierProfessionnelListToDossierProfessionnelDtoList( etudiant.getDossierProfessionnel() ) );
        etudiantDto.setDossierProjet( dossierProjetListToDossierProjetDtoList( etudiant.getDossierProjet() ) );

        return etudiantDto;
    }

    @Override
    public ExamenDto ExamenToExamenDto(Examen examen) {
        if ( examen == null ) {
            return null;
        }

        ExamenDto examenDto = new ExamenDto();

        examenDto.setId( examen.getId() );
        examenDto.setTitre( examen.getTitre() );
        examenDto.setDescriptif( examen.getDescriptif() );
        examenDto.setDuree( examen.getDuree() );
        examenDto.setPieceJointe( examen.getPieceJointe() );
        examenDto.setDateExamen( examen.getDateExamen() );

        return examenDto;
    }

    @Override
    public FormateurDto FormateurToFormateurDto(Formateur formateur) {
        if ( formateur == null ) {
            return null;
        }

        FormateurDto formateurDto = new FormateurDto();

        formateurDto.setId( formateur.getId() );

        return formateurDto;
    }

    @Override
    public FormationDto FormationToFormationDto(Formation formation) {
        if ( formation == null ) {
            return null;
        }

        FormationDto formationDto = new FormationDto();

        formationDto.setId( formation.getId() );
        formationDto.setTitre( formation.getTitre() );
        formationDto.setContenu( formation.getContenu() );
        formationDto.setIdDg2( formation.getIdDg2() );
        formationDto.setDuration( formation.getDuration() );
        formationDto.setSlug( formation.getSlug() );

        return formationDto;
    }

    @Override
    public GroupeEtudiantDto GroupeEtudiantToGroupEtudiantDto(GroupeEtudiant groupeEtudiant) {
        if ( groupeEtudiant == null ) {
            return null;
        }

        GroupeEtudiantDto groupeEtudiantDto = new GroupeEtudiantDto();

        groupeEtudiantDto.setId( groupeEtudiant.getId() );
        groupeEtudiantDto.setNom( groupeEtudiant.getNom() );
        groupeEtudiantDto.setEtudiants( etudiantListToEtudiantDtoList( groupeEtudiant.getEtudiants() ) );

        return groupeEtudiantDto;
    }

    @Override
    public InterventionDto InterventionToInterventionDto(Intervention intervention) {
        if ( intervention == null ) {
            return null;
        }

        InterventionDto interventionDto = new InterventionDto();

        interventionDto.setId( intervention.getId() );
        interventionDto.setDateDebut( intervention.getDateDebut() );
        interventionDto.setDateFin( intervention.getDateFin() );
        interventionDto.setNoteInfoPersonnel( intervention.getNoteInfoPersonnel() );

        return interventionDto;
    }

    @Override
    public NoteDto NoteToNoteDto(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteDto noteDto = new NoteDto();

        noteDto.setId( note.getId() );
        noteDto.setNoteObtenue( note.getNoteObtenue() );
        noteDto.setSatisfaction( note.getSatisfaction() );

        return noteDto;
    }

    @Override
    public PassageExamenDto PassageExamenToPassageExamenDto(PassageExamen passageExamen) {
        if ( passageExamen == null ) {
            return null;
        }

        PassageExamenDto passageExamenDto = new PassageExamenDto();

        passageExamenDto.setId( passageExamen.getId() );
        passageExamenDto.setDateDebut( passageExamen.getDateDebut() );
        passageExamenDto.setDateFin( passageExamen.getDateFin() );

        return passageExamenDto;
    }

    @Override
    public ProjetDto ProjetToProjetDto(Projet projet) {
        if ( projet == null ) {
            return null;
        }

        ProjetDto projetDto = new ProjetDto();

        projetDto.setId( projet.getId() );
        projetDto.setNom( projet.getNom() );
        projetDto.setDescription( projet.getDescription() );
        projetDto.setGroupe( GroupeEtudiantToGroupEtudiantDto( projet.getGroupe() ) );

        return projetDto;
    }

    @Override
    public PromotionDto PromotionToPromotionDto(Promotion promotion) {
        if ( promotion == null ) {
            return null;
        }

        PromotionDto promotionDto = new PromotionDto();

        promotionDto.setId( promotion.getId() );
        promotionDto.setNom( promotion.getNom() );
        promotionDto.setDateDebut( promotion.getDateDebut() );
        promotionDto.setDateFin( promotion.getDateFin() );

        return promotionDto;
    }

    @Override
    public UtilisateurDto UtilisateurToUtilisateurDto(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();

        utilisateurDto.setId( utilisateur.getId() );
        utilisateurDto.setLogin( utilisateur.getLogin() );
        utilisateurDto.setPassword( utilisateur.getPassword() );
        utilisateurDto.setPrenom( utilisateur.getPrenom() );
        utilisateurDto.setNom( utilisateur.getNom() );
        utilisateurDto.setCivilite( utilisateur.getCivilite() );
        utilisateurDto.setDateDeNaissance( utilisateur.getDateDeNaissance() );
        utilisateurDto.setTelephone( utilisateur.getTelephone() );

        return utilisateurDto;
    }

    @Override
    public UtilisateurRoleDto UtilisateurRoleToUtilisateurRoleDto(UtilisateurRole utilisateurRole) {
        if ( utilisateurRole == null ) {
            return null;
        }

        UtilisateurRoleDto utilisateurRoleDto = new UtilisateurRoleDto();

        utilisateurRoleDto.setId( utilisateurRole.getId() );
        utilisateurRoleDto.setIntitule( utilisateurRole.getIntitule() );

        return utilisateurRoleDto;
    }

    @Override
    public FichePosteDto FichePosteToFichePosteDto(FichePoste fichePoste) {
        if ( fichePoste == null ) {
            return null;
        }

        FichePosteDto fichePosteDto = new FichePosteDto();

        fichePosteDto.setId( fichePoste.getId() );
        fichePosteDto.setIntitule( fichePoste.getIntitule() );
        fichePosteDto.setNature( fichePoste.getNature() );
        fichePosteDto.setMission( fichePoste.getMission() );
        fichePosteDto.setCompositionService( fichePoste.getCompositionService() );
        fichePosteDto.setPositionnement( fichePoste.getPositionnement() );
        fichePosteDto.setMissionPrincipale( fichePoste.getMissionPrincipale() );

        return fichePosteDto;
    }

    @Override
    public DossierProfessionnelDto DossierProfessionnelToDossierProfessionnelDto(DossierProfessionnel dossierProfessionnel) {
        if ( dossierProfessionnel == null ) {
            return null;
        }

        DossierProfessionnelDto dossierProfessionnelDto = new DossierProfessionnelDto();

        dossierProfessionnelDto.setId( dossierProfessionnel.getId() );
        dossierProfessionnelDto.setNom( dossierProfessionnel.getNom() );

        return dossierProfessionnelDto;
    }

    @Override
    public DossierProjetDto DossierProjetToDossierProjetDto(DossierProjet dossierProjet) {
        if ( dossierProjet == null ) {
            return null;
        }

        DossierProjetDto dossierProjetDto = new DossierProjetDto();

        dossierProjetDto.setId( dossierProjet.getId() );
        dossierProjetDto.setNom( dossierProjet.getNom() );
        dossierProjetDto.setProjet( ProjetToProjetDto( dossierProjet.getProjet() ) );

        return dossierProjetDto;
    }

    @Override
    public FicheEntrepriseDto FicheEntrepriseToFicheEntrepriseDto(FicheEntreprise FicheEntreprise) {
        if ( FicheEntreprise == null ) {
            return null;
        }

        FicheEntrepriseDto ficheEntrepriseDto = new FicheEntrepriseDto();

        ficheEntrepriseDto.setId( FicheEntreprise.getId() );
        ficheEntrepriseDto.setHistorique( FicheEntreprise.getHistorique() );
        ficheEntrepriseDto.setNomDirigeant( FicheEntreprise.getNomDirigeant() );
        ficheEntrepriseDto.setSecteurActivite( FicheEntreprise.getSecteurActivite() );
        ficheEntrepriseDto.setOrganisationType( FicheEntreprise.getOrganisationType() );
        ficheEntrepriseDto.setNbSalarie( FicheEntreprise.getNbSalarie() );
        ficheEntrepriseDto.setChiffreAffaireAnnuel( FicheEntreprise.getChiffreAffaireAnnuel() );
        ficheEntrepriseDto.setActiviteDescription( FicheEntreprise.getActiviteDescription() );
        ficheEntrepriseDto.setClientType( FicheEntreprise.getClientType() );
        ficheEntrepriseDto.setFormationProfil( FicheEntreprise.getFormationProfil() );
        ficheEntrepriseDto.setMetiersExerces( FicheEntreprise.getMetiersExerces() );

        return ficheEntrepriseDto;
    }

    @Override
    public ContratDto ContratToContratDto(Contrat contrat) {
        if ( contrat == null ) {
            return null;
        }

        ContratDto contratDto = new ContratDto();

        contratDto.setId( contrat.getId() );
        contratDto.setDateDebut( contrat.getDateDebut() );
        contratDto.setDateFin( contrat.getDateFin() );

        return contratDto;
    }

    @Override
    public MaitreApprentissageDto MaitreApprentissageToMaitreApprentissageDto(MaitreApprentissage maitreApprentissage) {
        if ( maitreApprentissage == null ) {
            return null;
        }

        MaitreApprentissageDto maitreApprentissageDto = new MaitreApprentissageDto();

        maitreApprentissageDto.setId( maitreApprentissage.getId() );

        return maitreApprentissageDto;
    }

    @Override
    public CerfaDto CerfaToCerfaDto(Cerfa cerfa) {
        if ( cerfa == null ) {
            return null;
        }

        CerfaDto cerfaDto = new CerfaDto();

        cerfaDto.setModeContractuelApprentissage( cerfa.getModeContractuelApprentissage() );
        cerfaDto.setComplementEmployeur( cerfa.getComplementEmployeur() );
        cerfaDto.setEmployeurType( cerfa.getEmployeurType() );
        cerfaDto.setAssuranceChomage( cerfa.getAssuranceChomage() );
        cerfaDto.setComplementApprentit( cerfa.getComplementApprentit() );
        cerfaDto.setComplementRepresentant( cerfa.getComplementRepresentant() );
        cerfaDto.setEgilibiliteFonction( cerfa.getEgilibiliteFonction() );
        cerfaDto.setComplementResponsable( cerfa.getComplementResponsable() );
        cerfaDto.setFaitA( cerfa.getFaitA() );
        cerfaDto.setId( cerfa.getId() );
        cerfaDto.setEmployeurPriveOuPublic( cerfa.getEmployeurPriveOuPublic() );
        cerfaDto.setNomEmployeur( cerfa.getNomEmployeur() );
        cerfaDto.setPrenomEmployeur( cerfa.getPrenomEmployeur() );
        cerfaDto.setAdresseEmployeur( AdresseToAdresseDto( cerfa.getAdresseEmployeur() ) );
        cerfaDto.setTelEmployeur( cerfa.getTelEmployeur() );
        cerfaDto.setEmailEmployeur( cerfa.getEmailEmployeur() );
        cerfaDto.setSiretEtablissement( cerfa.getSiretEtablissement() );
        cerfaDto.setEmployeurSpecifique( cerfa.getEmployeurSpecifique() );
        cerfaDto.setNaf( cerfa.getNaf() );
        cerfaDto.setEffectifEntreprise( cerfa.getEffectifEntreprise() );
        cerfaDto.setConventionCollectiveApplicable( cerfa.getConventionCollectiveApplicable() );
        cerfaDto.setCodeIdccConvention( cerfa.getCodeIdccConvention() );
        cerfaDto.setNomNaissanceApprenti( cerfa.getNomNaissanceApprenti() );
        cerfaDto.setPrenomApprenti( cerfa.getPrenomApprenti() );
        cerfaDto.setNirApprenti( cerfa.getNirApprenti() );
        cerfaDto.setDateDeNaissance( cerfa.getDateDeNaissance() );
        cerfaDto.setSexe( cerfa.getSexe() );
        cerfaDto.setAdresseApprenti( AdresseToAdresseDto( cerfa.getAdresseApprenti() ) );
        cerfaDto.setDepartementNaissance( cerfa.getDepartementNaissance() );
        cerfaDto.setCommuneNaissance( cerfa.getCommuneNaissance() );
        cerfaDto.setTelApprenti( cerfa.getTelApprenti() );
        cerfaDto.setEmailApprenti( cerfa.getEmailApprenti() );
        cerfaDto.setNationalite( cerfa.getNationalite() );
        cerfaDto.setRegimeSocial( cerfa.getRegimeSocial() );
        cerfaDto.setSportifs( cerfa.getSportifs() );
        cerfaDto.setHandicape( cerfa.getHandicape() );
        cerfaDto.setSituationAvantContrat( cerfa.getSituationAvantContrat() );
        cerfaDto.setDernierDiplome( cerfa.getDernierDiplome() );
        cerfaDto.setDerniereClasseSuivi( cerfa.getDerniereClasseSuivi() );
        cerfaDto.setIntitulePrecisDernierDiplome( cerfa.getIntitulePrecisDernierDiplome() );
        cerfaDto.setDiplomeLePlusEleveObtenu( cerfa.getDiplomeLePlusEleveObtenu() );
        cerfaDto.setNomRepresentant( cerfa.getNomRepresentant() );
        cerfaDto.setPrenomRepresentant( cerfa.getPrenomRepresentant() );
        cerfaDto.setAdresseRepresentant( AdresseToAdresseDto( cerfa.getAdresseRepresentant() ) );
        cerfaDto.setNomPremierTuteur( cerfa.getNomPremierTuteur() );
        cerfaDto.setPrenomPremierTuteur( cerfa.getPrenomPremierTuteur() );
        cerfaDto.setDateDeNaissancePremierTuteur( cerfa.getDateDeNaissancePremierTuteur() );
        cerfaDto.setNomDeuxiemeTuteur( cerfa.getNomDeuxiemeTuteur() );
        cerfaDto.setPrenomDeuxiemeTuteur( cerfa.getPrenomDeuxiemeTuteur() );
        cerfaDto.setDateDeNaissanceDeuxiemeTuteur( cerfa.getDateDeNaissanceDeuxiemeTuteur() );
        cerfaDto.setContratType( cerfa.getContratType() );
        cerfaDto.setDerogationType( cerfa.getDerogationType() );
        cerfaDto.setContratNum( cerfa.getContratNum() );
        cerfaDto.setDateConclusion( cerfa.getDateConclusion() );
        cerfaDto.setDateDebutContrat( cerfa.getDateDebutContrat() );
        cerfaDto.setDateAvenant( cerfa.getDateAvenant() );
        cerfaDto.setDateFinContrat( cerfa.getDateFinContrat() );
        cerfaDto.setHeureTravail( cerfa.getHeureTravail() );
        cerfaDto.setMinuteTravail( cerfa.getMinuteTravail() );
        cerfaDto.setMachineRisque( cerfa.getMachineRisque() );
        cerfaDto.setRemuneration1( RemunerationTORemunerationDto( cerfa.getRemuneration1() ) );
        cerfaDto.setRemuneration2( RemunerationTORemunerationDto( cerfa.getRemuneration2() ) );
        cerfaDto.setRemuneration3( RemunerationTORemunerationDto( cerfa.getRemuneration3() ) );
        cerfaDto.setRemuneration4( RemunerationTORemunerationDto( cerfa.getRemuneration4() ) );
        cerfaDto.setSalaireBrut( cerfa.getSalaireBrut() );
        cerfaDto.setCaisseDeRetraite( cerfa.getCaisseDeRetraite() );
        cerfaDto.setNourriture( cerfa.getNourriture() );
        cerfaDto.setLogement( cerfa.getLogement() );
        cerfaDto.setAutre( cerfa.getAutre() );
        cerfaDto.setCfaEntreprise( cerfa.getCfaEntreprise() );
        cerfaDto.setCfaResponsable( cerfa.getCfaResponsable() );
        cerfaDto.setDiplomeVise( cerfa.getDiplomeVise() );
        cerfaDto.setIntitulePrecisDiplomeVise( cerfa.getIntitulePrecisDiplomeVise() );
        cerfaDto.setCfaUai( cerfa.getCfaUai() );
        cerfaDto.setCfaSiret( cerfa.getCfaSiret() );
        cerfaDto.setDiplomeCode( cerfa.getDiplomeCode() );
        cerfaDto.setCodeRncp( cerfa.getCodeRncp() );
        cerfaDto.setAdresseResponsable( AdresseToAdresseDto( cerfa.getAdresseResponsable() ) );
        cerfaDto.setDateDebutFormation( cerfa.getDateDebutFormation() );
        cerfaDto.setDateExamen( cerfa.getDateExamen() );
        cerfaDto.setFormationDuree( cerfa.getFormationDuree() );
        cerfaDto.setValidationEmployeur( cerfa.getValidationEmployeur() );
        cerfaDto.setNomOrganisme( cerfa.getNomOrganisme() );
        cerfaDto.setSiretOrganisme( cerfa.getSiretOrganisme() );
        cerfaDto.setReceptionDossier( cerfa.getReceptionDossier() );
        cerfaDto.setDateDecision( cerfa.getDateDecision() );
        cerfaDto.setNumDepot( cerfa.getNumDepot() );
        cerfaDto.setNumAvenant( cerfa.getNumAvenant() );
        cerfaDto.setEtudiant( EtudiantToEtudiantDto( cerfa.getEtudiant() ) );

        return cerfaDto;
    }

    @Override
    public RemunerationDto RemunerationTORemunerationDto(Remuneration remuneration) {
        if ( remuneration == null ) {
            return null;
        }

        RemunerationDto remunerationDto = new RemunerationDto();

        remunerationDto.setId( remuneration.getId() );
        remunerationDto.setDateDebut( remuneration.getDateDebut() );
        remunerationDto.setDateFin( remuneration.getDateFin() );
        remunerationDto.setPourcentage( remuneration.getPourcentage() );
        remunerationDto.setSmicOuSmc( remuneration.getSmicOuSmc() );

        return remunerationDto;
    }

    @Override
    public ActiviteTypeDto ActiviteTypeToActiviteTypeDto(ActiviteType activiteType) {
        if ( activiteType == null ) {
            return null;
        }

        ActiviteTypeDto activiteTypeDto = new ActiviteTypeDto();

        activiteTypeDto.setId( activiteType.getId() );
        activiteTypeDto.setLibelle( activiteType.getLibelle() );
        activiteTypeDto.setNumeroFiche( activiteType.getNumeroFiche() );

        return activiteTypeDto;
    }

    @Override
    public CompetenceProfessionnelleDto CompetenceProfessionnelleToCompetenceProfessionnelleDto(CompetenceProfessionnelle competenceProfessionnelle) {
        if ( competenceProfessionnelle == null ) {
            return null;
        }

        CompetenceProfessionnelleDto competenceProfessionnelleDto = new CompetenceProfessionnelleDto();

        competenceProfessionnelleDto.setId( competenceProfessionnelle.getId() );
        competenceProfessionnelleDto.setLibelle( competenceProfessionnelle.getLibelle() );
        competenceProfessionnelleDto.setNumeroFiche( competenceProfessionnelle.getNumeroFiche() );

        return competenceProfessionnelleDto;
    }

    @Override
    public Formation formationDG2DtoToFormation(FormationDG2Dto formationDG2Dto) {
        if ( formationDG2Dto == null ) {
            return null;
        }

        Formation formation = new Formation();

        formation.setIdDg2( formationDG2Dto.getId() );
        formation.setTitre( formationDG2Dto.getTitle() );
        formation.setDuration( formationDG2Dto.getDuration() );
        formation.setId( formationDG2Dto.getId() );
        formation.setSlug( formationDG2Dto.getSlug() );

        return formation;
    }

    @Override
    public Cursus cursusDG2DtoToCursus(InterventionDG2Dto cursusDG2Dto) {
        if ( cursusDG2Dto == null ) {
            return null;
        }

        Cursus cursus = new Cursus();

        cursus.setTitre( cursusDG2Dto.getSlug() );
        cursus.setId( cursusDG2Dto.getId() );

        return cursus;
    }

    @Override
    public List<Cursus> lstCursusDG2DtoToListCursus(List<InterventionDG2Dto> lstCurusDto) {
        if ( lstCurusDto == null ) {
            return null;
        }

        List<Cursus> list = new ArrayList<Cursus>( lstCurusDto.size() );
        for ( InterventionDG2Dto interventionDG2Dto : lstCurusDto ) {
            list.add( cursusDG2DtoToCursus( interventionDG2Dto ) );
        }

        return list;
    }

    @Override
    public CentreFormation centreFormationDG2DtoToCentreFormation(CentreFormationDG2Dto centreFormationDG2Dto) {
        if ( centreFormationDG2Dto == null ) {
            return null;
        }

        CentreFormation centreFormation = new CentreFormation();

        centreFormation.setNom( centreFormationDG2Dto.getName() );
        centreFormation.setIdDg2( centreFormationDG2Dto.getId() );
        centreFormation.setCountryCode( centreFormationDG2Dto.getCountry() );

        return centreFormation;
    }

    @Override
    public PromotionDto PromotionToPromotionDto(PromotionDto pDto) {
        if ( pDto == null ) {
            return null;
        }

        PromotionDto promotionDto = new PromotionDto();

        promotionDto.setId( pDto.getId() );
        promotionDto.setNom( pDto.getNom() );
        promotionDto.setDateDebut( pDto.getDateDebut() );
        promotionDto.setDateFin( pDto.getDateFin() );
        promotionDto.setCefDto( pDto.getCefDto() );
        List<EtudiantDto> list = pDto.getEtudiantsDto();
        if ( list != null ) {
            promotionDto.setEtudiantsDto( new ArrayList<EtudiantDto>( list ) );
        }
        promotionDto.setCentreFormationDto( pDto.getCentreFormationDto() );
        promotionDto.setReferentPedagogiqueDto( pDto.getReferentPedagogiqueDto() );
        promotionDto.setCursusDto( pDto.getCursusDto() );
        List<InterventionDto> list1 = pDto.getInterventionsDto();
        if ( list1 != null ) {
            promotionDto.setInterventionsDto( new ArrayList<InterventionDto>( list1 ) );
        }
        Set<ExamenDto> set = pDto.getExamensDto();
        if ( set != null ) {
            promotionDto.setExamensDto( new HashSet<ExamenDto>( set ) );
        }

        return promotionDto;
    }

    protected List<DossierProfessionnelDto> dossierProfessionnelListToDossierProfessionnelDtoList(List<DossierProfessionnel> list) {
        if ( list == null ) {
            return null;
        }

        List<DossierProfessionnelDto> list1 = new ArrayList<DossierProfessionnelDto>( list.size() );
        for ( DossierProfessionnel dossierProfessionnel : list ) {
            list1.add( DossierProfessionnelToDossierProfessionnelDto( dossierProfessionnel ) );
        }

        return list1;
    }

    protected List<DossierProjetDto> dossierProjetListToDossierProjetDtoList(List<DossierProjet> list) {
        if ( list == null ) {
            return null;
        }

        List<DossierProjetDto> list1 = new ArrayList<DossierProjetDto>( list.size() );
        for ( DossierProjet dossierProjet : list ) {
            list1.add( DossierProjetToDossierProjetDto( dossierProjet ) );
        }

        return list1;
    }

    protected List<EtudiantDto> etudiantListToEtudiantDtoList(List<Etudiant> list) {
        if ( list == null ) {
            return null;
        }

        List<EtudiantDto> list1 = new ArrayList<EtudiantDto>( list.size() );
        for ( Etudiant etudiant : list ) {
            list1.add( EtudiantToEtudiantDto( etudiant ) );
        }

        return list1;
    }
}
