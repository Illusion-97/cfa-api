package fr.dawan.AppliCFABack.mapper;

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
    date = "2022-05-23T15:30:52+0200",
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
        competenceProfessionnelleDto.setVersion( competenceProfessionnelle.getVersion() );
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
        activiteTypeDto.setVersion( activiteType.getVersion() );
        activiteTypeDto.setLibelle( activiteType.getLibelle() );
        activiteTypeDto.setNumeroFiche( activiteType.getNumeroFiche() );

        return activiteTypeDto;
    }

    @Override
    public AdresseDto AdresseToAdresseDto(Adresse adresse) {
        if ( adresse == null ) {
            return null;
        }

        AdresseDto adresseDto = new AdresseDto();

        adresseDto.setId( adresse.getId() );
        adresseDto.setVersion( adresse.getVersion() );
        adresseDto.setCodePostal( adresse.getCodePostal() );
        adresseDto.setNumero( adresse.getNumero() );
        adresseDto.setRue( adresse.getRue() );
        adresseDto.setVille( adresse.getVille() );

        return adresseDto;
    }

    @Override
    public CEFDto CEFToCEFDto(CEF cef) {
        if ( cef == null ) {
            return null;
        }

        CEFDto cEFDto = new CEFDto();

        cEFDto.setId( cef.getId() );
        cEFDto.setVersion( cef.getVersion() );

        return cEFDto;
    }

    @Override
    public CentreFormationDto CentreFormationToCentreFormationDto(CentreFormation centreFormation) {
        if ( centreFormation == null ) {
            return null;
        }

        CentreFormationDto centreFormationDto = new CentreFormationDto();

        centreFormationDto.setId( centreFormation.getId() );
        centreFormationDto.setVersion( centreFormation.getVersion() );
        centreFormationDto.setCountryCode( centreFormation.getCountryCode() );
        centreFormationDto.setIdDg2( centreFormation.getIdDg2() );
        centreFormationDto.setNom( centreFormation.getNom() );

        return centreFormationDto;
    }

    @Override
    public CongeDto CongeToCongeDto(Conge conge) {
        if ( conge == null ) {
            return null;
        }

        CongeDto congeDto = new CongeDto();

        congeDto.setId( conge.getId() );
        congeDto.setVersion( conge.getVersion() );
        congeDto.setDateDebut( conge.getDateDebut() );
        congeDto.setDateFin( conge.getDateFin() );
        congeDto.setJustificatif( conge.getJustificatif() );
        congeDto.setMotif( conge.getMotif() );
        congeDto.setStatus( conge.getStatus() );
        congeDto.setType( conge.getType() );

        return congeDto;
    }

    @Override
    public CursusDto CursusToCursusDto(Cursus cursus) {
        if ( cursus == null ) {
            return null;
        }

        CursusDto cursusDto = new CursusDto();

        cursusDto.setId( cursus.getId() );
        cursusDto.setVersion( cursus.getVersion() );
        cursusDto.setDescription( cursus.getDescription() );
        cursusDto.setDuree( cursus.getDuree() );
        cursusDto.setTitre( cursus.getTitre() );

        return cursusDto;
    }

    @Override
    public DevoirDto DevoirToDevoirDto(Devoir devoir) {
        if ( devoir == null ) {
            return null;
        }

        DevoirDto devoirDto = new DevoirDto();

        devoirDto.setId( devoir.getId() );
        devoirDto.setVersion( devoir.getVersion() );
        devoirDto.setConsigne( devoir.getConsigne() );
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
        entrepriseDto.setVersion( entreprise.getVersion() );
        entrepriseDto.setEffectifTotal( entreprise.getEffectifTotal() );
        entrepriseDto.setEmployeurType( entreprise.getEmployeurType() );
        entrepriseDto.setNaf( entreprise.getNaf() );
        entrepriseDto.setRaisonSociale( entreprise.getRaisonSociale() );
        entrepriseDto.setSiret( entreprise.getSiret() );

        return entrepriseDto;
    }

    @Override
    public EtudiantDto EtudiantToEtudiantDto(Etudiant etudiant) {
        if ( etudiant == null ) {
            return null;
        }

        EtudiantDto etudiantDto = new EtudiantDto();

        etudiantDto.setId( etudiant.getId() );
        etudiantDto.setVersion( etudiant.getVersion() );
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
        examenDto.setVersion( examen.getVersion() );
        examenDto.setDateExamen( examen.getDateExamen() );
        examenDto.setDescriptif( examen.getDescriptif() );
        examenDto.setDuree( examen.getDuree() );
        examenDto.setPieceJointe( examen.getPieceJointe() );
        examenDto.setTitre( examen.getTitre() );

        return examenDto;
    }

    @Override
    public FormateurDto FormateurToFormateurDto(Formateur formateur) {
        if ( formateur == null ) {
            return null;
        }

        FormateurDto formateurDto = new FormateurDto();

        formateurDto.setId( formateur.getId() );
        formateurDto.setVersion( formateur.getVersion() );

        return formateurDto;
    }

    @Override
    public FormationDto FormationToFormationDto(Formation formation) {
        if ( formation == null ) {
            return null;
        }

        FormationDto formationDto = new FormationDto();

        formationDto.setId( formation.getId() );
        formationDto.setVersion( formation.getVersion() );
        formationDto.setContenu( formation.getContenu() );
        formationDto.setDuration( formation.getDuration() );
        formationDto.setIdDg2( formation.getIdDg2() );
        formationDto.setSlug( formation.getSlug() );
        formationDto.setTitre( formation.getTitre() );

        return formationDto;
    }

    @Override
    public GroupeEtudiantDto GroupeEtudiantToGroupEtudiantDto(GroupeEtudiant groupeEtudiant) {
        if ( groupeEtudiant == null ) {
            return null;
        }

        GroupeEtudiantDto groupeEtudiantDto = new GroupeEtudiantDto();

        groupeEtudiantDto.setId( groupeEtudiant.getId() );
        groupeEtudiantDto.setVersion( groupeEtudiant.getVersion() );
        groupeEtudiantDto.setNom( groupeEtudiant.getNom() );

        return groupeEtudiantDto;
    }

    @Override
    public InterventionDto InterventionToInterventionDto(Intervention intervention) {
        if ( intervention == null ) {
            return null;
        }

        InterventionDto interventionDto = new InterventionDto();

        interventionDto.setId( intervention.getId() );
        interventionDto.setVersion( intervention.getVersion() );
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
        noteDto.setVersion( note.getVersion() );
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
        passageExamenDto.setVersion( passageExamen.getVersion() );
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
        projetDto.setVersion( projet.getVersion() );
        projetDto.setDescription( projet.getDescription() );
        projetDto.setNom( projet.getNom() );

        return projetDto;
    }

    @Override
    public PromotionDto PromotionToPromotionDto(Promotion promotion) {
        if ( promotion == null ) {
            return null;
        }

        PromotionDto promotionDto = new PromotionDto();

        promotionDto.setId( promotion.getId() );
        promotionDto.setVersion( promotion.getVersion() );
        promotionDto.setDateDebut( promotion.getDateDebut() );
        promotionDto.setDateFin( promotion.getDateFin() );
        promotionDto.setNom( promotion.getNom() );

        return promotionDto;
    }

    @Override
    public UtilisateurDto UtilisateurToUtilisateurDto(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();

        utilisateurDto.setId( utilisateur.getId() );
        utilisateurDto.setVersion( utilisateur.getVersion() );
        utilisateurDto.setCivilite( utilisateur.getCivilite() );
        utilisateurDto.setDateDeNaissance( utilisateur.getDateDeNaissance() );
        utilisateurDto.setLogin( utilisateur.getLogin() );
        utilisateurDto.setNom( utilisateur.getNom() );
        utilisateurDto.setPassword( utilisateur.getPassword() );
        utilisateurDto.setPrenom( utilisateur.getPrenom() );
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
        utilisateurRoleDto.setVersion( utilisateurRole.getVersion() );
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
        fichePosteDto.setVersion( fichePoste.getVersion() );
        fichePosteDto.setCompositionService( fichePoste.getCompositionService() );
        fichePosteDto.setIntitule( fichePoste.getIntitule() );
        fichePosteDto.setMission( fichePoste.getMission() );
        fichePosteDto.setMissionPrincipale( fichePoste.getMissionPrincipale() );
        fichePosteDto.setNature( fichePoste.getNature() );
        fichePosteDto.setPositionnement( fichePoste.getPositionnement() );

        return fichePosteDto;
    }

    @Override
    public DossierProfessionnelDto DossierProfessionnelToDossierProfessionnelDto(DossierProfessionnel dossierProfessionnel) {
        if ( dossierProfessionnel == null ) {
            return null;
        }

        DossierProfessionnelDto dossierProfessionnelDto = new DossierProfessionnelDto();

        dossierProfessionnelDto.setId( dossierProfessionnel.getId() );
        dossierProfessionnelDto.setVersion( dossierProfessionnel.getVersion() );
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
        dossierProjetDto.setVersion( dossierProjet.getVersion() );
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
        ficheEntrepriseDto.setVersion( FicheEntreprise.getVersion() );
        ficheEntrepriseDto.setActiviteDescription( FicheEntreprise.getActiviteDescription() );
        ficheEntrepriseDto.setChiffreAffaireAnnuel( FicheEntreprise.getChiffreAffaireAnnuel() );
        ficheEntrepriseDto.setClientType( FicheEntreprise.getClientType() );
        ficheEntrepriseDto.setFormationProfil( FicheEntreprise.getFormationProfil() );
        ficheEntrepriseDto.setHistorique( FicheEntreprise.getHistorique() );
        ficheEntrepriseDto.setMetiersExerces( FicheEntreprise.getMetiersExerces() );
        ficheEntrepriseDto.setNbSalarie( FicheEntreprise.getNbSalarie() );
        ficheEntrepriseDto.setNomDirigeant( FicheEntreprise.getNomDirigeant() );
        ficheEntrepriseDto.setOrganisationType( FicheEntreprise.getOrganisationType() );
        ficheEntrepriseDto.setSecteurActivite( FicheEntreprise.getSecteurActivite() );

        return ficheEntrepriseDto;
    }

    @Override
    public ContratDto ContratToContratDto(Contrat contrat) {
        if ( contrat == null ) {
            return null;
        }

        ContratDto contratDto = new ContratDto();

        contratDto.setId( contrat.getId() );
        contratDto.setVersion( contrat.getVersion() );
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
        maitreApprentissageDto.setVersion( maitreApprentissage.getVersion() );

        return maitreApprentissageDto;
    }

    @Override
    public CerfaDto CerfaToCerfaDto(Cerfa cerfa) {
        if ( cerfa == null ) {
            return null;
        }

        CerfaDto cerfaDto = new CerfaDto();

        cerfaDto.setId( cerfa.getId() );
        cerfaDto.setVersion( cerfa.getVersion() );
        cerfaDto.setAdresseApprenti( AdresseToAdresseDto( cerfa.getAdresseApprenti() ) );
        cerfaDto.setAdresseEmployeur( AdresseToAdresseDto( cerfa.getAdresseEmployeur() ) );
        cerfaDto.setAdresseRepresentant( AdresseToAdresseDto( cerfa.getAdresseRepresentant() ) );
        cerfaDto.setAdresseResponsable( AdresseToAdresseDto( cerfa.getAdresseResponsable() ) );
        cerfaDto.setAssuranceChomage( cerfa.getAssuranceChomage() );
        cerfaDto.setAutre( cerfa.getAutre() );
        cerfaDto.setCaisseDeRetraite( cerfa.getCaisseDeRetraite() );
        cerfaDto.setCfaEntreprise( cerfa.getCfaEntreprise() );
        cerfaDto.setCfaResponsable( cerfa.getCfaResponsable() );
        cerfaDto.setCfaSiret( cerfa.getCfaSiret() );
        cerfaDto.setCfaUai( cerfa.getCfaUai() );
        cerfaDto.setCodeIdccConvention( cerfa.getCodeIdccConvention() );
        cerfaDto.setCodeRncp( cerfa.getCodeRncp() );
        cerfaDto.setCommuneNaissance( cerfa.getCommuneNaissance() );
        cerfaDto.setComplementApprentit( cerfa.getComplementApprentit() );
        cerfaDto.setComplementEmployeur( cerfa.getComplementEmployeur() );
        cerfaDto.setComplementRepresentant( cerfa.getComplementRepresentant() );
        cerfaDto.setComplementResponsable( cerfa.getComplementResponsable() );
        cerfaDto.setContratNum( cerfa.getContratNum() );
        cerfaDto.setContratType( cerfa.getContratType() );
        cerfaDto.setConventionCollectiveApplicable( cerfa.getConventionCollectiveApplicable() );
        cerfaDto.setDateAvenant( cerfa.getDateAvenant() );
        cerfaDto.setDateConclusion( cerfa.getDateConclusion() );
        cerfaDto.setDateDeNaissance( cerfa.getDateDeNaissance() );
        cerfaDto.setDateDeNaissanceDeuxiemeTuteur( cerfa.getDateDeNaissanceDeuxiemeTuteur() );
        cerfaDto.setDateDeNaissancePremierTuteur( cerfa.getDateDeNaissancePremierTuteur() );
        cerfaDto.setDateDebutContrat( cerfa.getDateDebutContrat() );
        cerfaDto.setDateDebutFormation( cerfa.getDateDebutFormation() );
        cerfaDto.setDateDecision( cerfa.getDateDecision() );
        cerfaDto.setDateExamen( cerfa.getDateExamen() );
        cerfaDto.setDateFinContrat( cerfa.getDateFinContrat() );
        cerfaDto.setDepartementNaissance( cerfa.getDepartementNaissance() );
        cerfaDto.setDernierDiplome( cerfa.getDernierDiplome() );
        cerfaDto.setDerniereClasseSuivi( cerfa.getDerniereClasseSuivi() );
        cerfaDto.setDerogationType( cerfa.getDerogationType() );
        cerfaDto.setDiplomeCode( cerfa.getDiplomeCode() );
        cerfaDto.setDiplomeLePlusEleveObtenu( cerfa.getDiplomeLePlusEleveObtenu() );
        cerfaDto.setDiplomeVise( cerfa.getDiplomeVise() );
        cerfaDto.setEffectifEntreprise( cerfa.getEffectifEntreprise() );
        cerfaDto.setEgilibiliteFonction( cerfa.getEgilibiliteFonction() );
        cerfaDto.setEmailApprenti( cerfa.getEmailApprenti() );
        cerfaDto.setEmailEmployeur( cerfa.getEmailEmployeur() );
        cerfaDto.setEmployeurPriveOuPublic( cerfa.getEmployeurPriveOuPublic() );
        cerfaDto.setEmployeurSpecifique( cerfa.getEmployeurSpecifique() );
        cerfaDto.setEmployeurType( cerfa.getEmployeurType() );
        cerfaDto.setEtudiant( EtudiantToEtudiantDto( cerfa.getEtudiant() ) );
        cerfaDto.setFaitA( cerfa.getFaitA() );
        cerfaDto.setFormationDuree( cerfa.getFormationDuree() );
        cerfaDto.setHandicape( cerfa.getHandicape() );
        cerfaDto.setHeureTravail( cerfa.getHeureTravail() );
        cerfaDto.setIntitulePrecisDernierDiplome( cerfa.getIntitulePrecisDernierDiplome() );
        cerfaDto.setIntitulePrecisDiplomeVise( cerfa.getIntitulePrecisDiplomeVise() );
        cerfaDto.setLogement( cerfa.getLogement() );
        cerfaDto.setMachineRisque( cerfa.getMachineRisque() );
        cerfaDto.setMinuteTravail( cerfa.getMinuteTravail() );
        cerfaDto.setModeContractuelApprentissage( cerfa.getModeContractuelApprentissage() );
        cerfaDto.setNaf( cerfa.getNaf() );
        cerfaDto.setNationalite( cerfa.getNationalite() );
        cerfaDto.setNirApprenti( cerfa.getNirApprenti() );
        cerfaDto.setNomDeuxiemeTuteur( cerfa.getNomDeuxiemeTuteur() );
        cerfaDto.setNomEmployeur( cerfa.getNomEmployeur() );
        cerfaDto.setNomNaissanceApprenti( cerfa.getNomNaissanceApprenti() );
        cerfaDto.setNomOrganisme( cerfa.getNomOrganisme() );
        cerfaDto.setNomPremierTuteur( cerfa.getNomPremierTuteur() );
        cerfaDto.setNomRepresentant( cerfa.getNomRepresentant() );
        cerfaDto.setNourriture( cerfa.getNourriture() );
        cerfaDto.setNumAvenant( cerfa.getNumAvenant() );
        cerfaDto.setNumDepot( cerfa.getNumDepot() );
        cerfaDto.setPrenomApprenti( cerfa.getPrenomApprenti() );
        cerfaDto.setPrenomDeuxiemeTuteur( cerfa.getPrenomDeuxiemeTuteur() );
        cerfaDto.setPrenomEmployeur( cerfa.getPrenomEmployeur() );
        cerfaDto.setPrenomPremierTuteur( cerfa.getPrenomPremierTuteur() );
        cerfaDto.setPrenomRepresentant( cerfa.getPrenomRepresentant() );
        cerfaDto.setReceptionDossier( cerfa.getReceptionDossier() );
        cerfaDto.setRegimeSocial( cerfa.getRegimeSocial() );
        cerfaDto.setRemuneration1( RemunerationTORemunerationDto( cerfa.getRemuneration1() ) );
        cerfaDto.setRemuneration2( RemunerationTORemunerationDto( cerfa.getRemuneration2() ) );
        cerfaDto.setRemuneration3( RemunerationTORemunerationDto( cerfa.getRemuneration3() ) );
        cerfaDto.setRemuneration4( RemunerationTORemunerationDto( cerfa.getRemuneration4() ) );
        cerfaDto.setSalaireBrut( cerfa.getSalaireBrut() );
        cerfaDto.setSexe( cerfa.getSexe() );
        cerfaDto.setSiretEtablissement( cerfa.getSiretEtablissement() );
        cerfaDto.setSiretOrganisme( cerfa.getSiretOrganisme() );
        cerfaDto.setSituationAvantContrat( cerfa.getSituationAvantContrat() );
        cerfaDto.setSportifs( cerfa.getSportifs() );
        cerfaDto.setTelApprenti( cerfa.getTelApprenti() );
        cerfaDto.setTelEmployeur( cerfa.getTelEmployeur() );
        cerfaDto.setValidationEmployeur( cerfa.getValidationEmployeur() );

        return cerfaDto;
    }

    @Override
    public RemunerationDto RemunerationTORemunerationDto(Remuneration remuneration) {
        if ( remuneration == null ) {
            return null;
        }

        RemunerationDto remunerationDto = new RemunerationDto();

        remunerationDto.setId( remuneration.getId() );
        remunerationDto.setVersion( remuneration.getVersion() );
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
        activiteTypeDto.setVersion( activiteType.getVersion() );
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
        competenceProfessionnelleDto.setVersion( competenceProfessionnelle.getVersion() );
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
        formation.setVersion( formationDG2Dto.getVersion() );
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
        cursus.setVersion( cursusDG2Dto.getVersion() );

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
        centreFormation.setVersion( centreFormationDG2Dto.getVersion() );

        return centreFormation;
    }

    @Override
    public PromotionDto PromotionToPromotionDto(PromotionDto pDto) {
        if ( pDto == null ) {
            return null;
        }

        PromotionDto promotionDto = new PromotionDto();

        promotionDto.setId( pDto.getId() );
        promotionDto.setVersion( pDto.getVersion() );
        promotionDto.setCefDto( pDto.getCefDto() );
        promotionDto.setCentreFormationDto( pDto.getCentreFormationDto() );
        promotionDto.setCursusDto( pDto.getCursusDto() );
        promotionDto.setDateDebut( pDto.getDateDebut() );
        promotionDto.setDateFin( pDto.getDateFin() );
        List<EtudiantDto> list = pDto.getEtudiantsDto();
        if ( list != null ) {
            promotionDto.setEtudiantsDto( new ArrayList<EtudiantDto>( list ) );
        }
        Set<ExamenDto> set = pDto.getExamensDto();
        if ( set != null ) {
            promotionDto.setExamensDto( new HashSet<ExamenDto>( set ) );
        }
        List<InterventionDto> list1 = pDto.getInterventionsDto();
        if ( list1 != null ) {
            promotionDto.setInterventionsDto( new ArrayList<InterventionDto>( list1 ) );
        }
        promotionDto.setNom( pDto.getNom() );
        promotionDto.setReferentPedagogiqueDto( pDto.getReferentPedagogiqueDto() );

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
}
