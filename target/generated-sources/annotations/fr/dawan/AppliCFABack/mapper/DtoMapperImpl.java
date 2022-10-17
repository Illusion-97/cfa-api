package fr.dawan.AppliCFABack.mapper;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.CentreFormationDG2Dto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CerfaDto;
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
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
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
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;
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
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-17T10:47:10+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
public class DtoMapperImpl implements DtoMapper {

    @Override
    public CompetenceProfessionnelleDto competenceProfessionnelleDto(CompetenceProfessionnelle competenceProfessionnelle) {
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
    public ActiviteTypeDto activiteTypeToActiviteDto(ActiviteType activiteType) {
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
    public AdresseDto adresseToAdresseDto(Adresse adresse) {
        if ( adresse == null ) {
            return null;
        }

        AdresseDto adresseDto = new AdresseDto();

        adresseDto.setId( adresse.getId() );
        adresseDto.setVersion( adresse.getVersion() );
        adresseDto.setLibelle( adresse.getLibelle() );
        adresseDto.setVille( adresse.getVille() );
        adresseDto.setCodePostal( adresse.getCodePostal() );

        return adresseDto;
    }

    @Override
    public CEFDto cefToCEFDto(CEF cef) {
        if ( cef == null ) {
            return null;
        }

        CEFDto cEFDto = new CEFDto();

        cEFDto.setId( cef.getId() );
        cEFDto.setVersion( cef.getVersion() );

        return cEFDto;
    }

    @Override
    public CentreFormationDto centreFormationToCentreFormationDto(CentreFormation centreFormation) {
        if ( centreFormation == null ) {
            return null;
        }

        CentreFormationDto centreFormationDto = new CentreFormationDto();

        centreFormationDto.setId( centreFormation.getId() );
        centreFormationDto.setVersion( centreFormation.getVersion() );
        centreFormationDto.setIdDg2( centreFormation.getIdDg2() );
        centreFormationDto.setNom( centreFormation.getNom() );

        return centreFormationDto;
    }

    @Override
    public CongeDto congeToCongeDto(Conge conge) {
        if ( conge == null ) {
            return null;
        }

        CongeDto congeDto = new CongeDto();

        congeDto.setId( conge.getId() );
        congeDto.setVersion( conge.getVersion() );
        congeDto.setDateDebut( conge.getDateDebut() );
        congeDto.setDateFin( conge.getDateFin() );
        congeDto.setMotif( conge.getMotif() );
        congeDto.setType( conge.getType() );
        congeDto.setStatus( conge.getStatus() );
        congeDto.setJustificatif( conge.getJustificatif() );

        return congeDto;
    }

    @Override
    public CursusDto cursusToCursusDto(Cursus cursus) {
        if ( cursus == null ) {
            return null;
        }

        CursusDto cursusDto = new CursusDto();

        cursusDto.setId( cursus.getId() );
        cursusDto.setVersion( cursus.getVersion() );
        cursusDto.setTitre( cursus.getTitre() );
        cursusDto.setDuree( cursus.getDuree() );
        cursusDto.setSlug( cursus.getSlug() );
        cursusDto.setIdDg2( cursus.getIdDg2() );

        return cursusDto;
    }

    @Override
    public DevoirDto devoirToDevoirDto(Devoir devoir) {
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
    public EntrepriseDto entrepriseToEntrepriseDto(Entreprise entreprise) {
        if ( entreprise == null ) {
            return null;
        }

        EntrepriseDto entrepriseDto = new EntrepriseDto();

        entrepriseDto.setId( entreprise.getId() );
        entrepriseDto.setVersion( entreprise.getVersion() );
        entrepriseDto.setRaisonSociale( entreprise.getRaisonSociale() );
        entrepriseDto.setSiret( entreprise.getSiret() );
        entrepriseDto.setNaf( entreprise.getNaf() );
        entrepriseDto.setEffectifTotal( entreprise.getEffectifTotal() );
        entrepriseDto.setEmployeurType( entreprise.getEmployeurType() );

        return entrepriseDto;
    }

    @Override
    public EtudiantDto etudiantToEtudiantDto(Etudiant etudiant) {
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
    public ExamenDto examenToExamenDto(Examen examen) {
        if ( examen == null ) {
            return null;
        }

        ExamenDto examenDto = new ExamenDto();

        examenDto.setId( examen.getId() );
        examenDto.setVersion( examen.getVersion() );
        examenDto.setTitre( examen.getTitre() );
        examenDto.setDescriptif( examen.getDescriptif() );
        examenDto.setDuree( examen.getDuree() );
        examenDto.setPieceJointe( examen.getPieceJointe() );
        examenDto.setDateExamen( examen.getDateExamen() );

        return examenDto;
    }

    @Override
    public FormateurDto formateurToFormateurDto(Formateur formateur) {
        if ( formateur == null ) {
            return null;
        }

        FormateurDto formateurDto = new FormateurDto();

        formateurDto.setUtilisateurDto( utilisateurToUtilisateurDto( formateur.getUtilisateur() ) );
        formateurDto.setId( formateur.getId() );
        formateurDto.setVersion( formateur.getVersion() );

        return formateurDto;
    }

    @Override
    public FormationDto formationToFormationDto(Formation formation) {
        if ( formation == null ) {
            return null;
        }

        FormationDto formationDto = new FormationDto();

        formationDto.setId( formation.getId() );
        formationDto.setVersion( formation.getVersion() );
        formationDto.setTitre( formation.getTitre() );
        List<Long> list = formation.getCursusLstId();
        if ( list != null ) {
            formationDto.setCursusLstId( new ArrayList<Long>( list ) );
        }
        formationDto.setIdDg2( formation.getIdDg2() );
        formationDto.setSlug( formation.getSlug() );
        formationDto.setDuration( formation.getDuration() );
        formationDto.setObjectif( formation.getObjectif() );
        formationDto.setPrerequis( formation.getPrerequis() );
        formationDto.setPlan( formation.getPlan() );

        return formationDto;
    }

    @Override
    public GroupeEtudiantDto groupeEtudiantToGroupEtudiantDto(GroupeEtudiant groupeEtudiant) {
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
    public InterventionDto interventionToInterventionDto(Intervention intervention) {
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
    public NoteDto noteToNoteDto(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteDto noteDto = new NoteDto();

        noteDto.setId( note.getId() );
        noteDto.setVersion( note.getVersion() );
        noteDto.setNoteObtenue( note.getNoteObtenue() );
        noteDto.setSatisfaction( note.getSatisfaction() );
        noteDto.setObservation( note.getObservation() );

        return noteDto;
    }

    @Override
    public PassageExamenDto passageExamenToPassageExamenDto(PassageExamen passageExamen) {
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
    public ProjetDto projetToProjetDto(Projet projet) {
        if ( projet == null ) {
            return null;
        }

        ProjetDto projetDto = new ProjetDto();

        projetDto.setId( projet.getId() );
        projetDto.setVersion( projet.getVersion() );
        projetDto.setNom( projet.getNom() );
        projetDto.setDescription( projet.getDescription() );

        return projetDto;
    }

    @Override
    public PromotionDto promotionToPromotionDto(Promotion promotion) {
        if ( promotion == null ) {
            return null;
        }

        PromotionDto promotionDto = new PromotionDto();

        promotionDto.setCursusDto( cursusToCursusDto( promotion.getCursus() ) );
        promotionDto.setId( promotion.getId() );
        promotionDto.setVersion( promotion.getVersion() );
        promotionDto.setNom( promotion.getNom() );
        promotionDto.setDateDebut( promotion.getDateDebut() );
        promotionDto.setDateFin( promotion.getDateFin() );

        return promotionDto;
    }

    @Override
    public UtilisateurDto utilisateurToUtilisateurDto(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();

        utilisateurDto.setId( utilisateur.getId() );
        utilisateurDto.setVersion( utilisateur.getVersion() );
        utilisateurDto.setLogin( utilisateur.getLogin() );
        utilisateurDto.setPassword( utilisateur.getPassword() );
        utilisateurDto.setPrenom( utilisateur.getPrenom() );
        utilisateurDto.setNom( utilisateur.getNom() );
        utilisateurDto.setCivilite( utilisateur.getCivilite() );
        utilisateurDto.setDateDeNaissance( utilisateur.getDateDeNaissance() );
        utilisateurDto.setTelephone( utilisateur.getTelephone() );
        utilisateurDto.setTelephoneFixe( utilisateur.getTelephoneFixe() );
        utilisateurDto.setIdDg2( utilisateur.getIdDg2() );

        return utilisateurDto;
    }

    @Override
    public UtilisateurRoleDto utilisateurRoleToUtilisateurRoleDto(UtilisateurRole utilisateurRole) {
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
    public DossierProfessionnelDto dossierProfessionnelToDossierProfessionnelDto(DossierProfessionnel dossierProfessionnel) {
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
    public DossierProjetDto dossierProjetToDossierProjetDto(DossierProjet dossierProjet) {
        if ( dossierProjet == null ) {
            return null;
        }

        DossierProjetDto dossierProjetDto = new DossierProjetDto();

        dossierProjetDto.setId( dossierProjet.getId() );
        dossierProjetDto.setVersion( dossierProjet.getVersion() );
        dossierProjetDto.setNom( dossierProjet.getNom() );
        dossierProjetDto.setProjet( projetToProjetDto( dossierProjet.getProjet() ) );

        return dossierProjetDto;
    }

    @Override
    public MaitreApprentissageDto maitreApprentissageToMaitreApprentissageDto(MaitreApprentissage maitreApprentissage) {
        if ( maitreApprentissage == null ) {
            return null;
        }

        MaitreApprentissageDto maitreApprentissageDto = new MaitreApprentissageDto();

        maitreApprentissageDto.setId( maitreApprentissage.getId() );
        maitreApprentissageDto.setVersion( maitreApprentissage.getVersion() );

        return maitreApprentissageDto;
    }

    @Override
    public CerfaDto cerfaToCerfaDto(Cerfa cerfa) {
        if ( cerfa == null ) {
            return null;
        }

        CerfaDto cerfaDto = new CerfaDto();

        cerfaDto.setId( cerfa.getId() );
        cerfaDto.setVersion( cerfa.getVersion() );
        cerfaDto.setModeContractuelApprentissage( cerfa.getModeContractuelApprentissage() );
        cerfaDto.setComplementEmployeur( cerfa.getComplementEmployeur() );
        cerfaDto.setEmployeurType( cerfa.getEmployeurType() );
        cerfaDto.setAssuranceChomage( cerfa.getAssuranceChomage() );
        cerfaDto.setComplementApprentit( cerfa.getComplementApprentit() );
        cerfaDto.setComplementRepresentant( cerfa.getComplementRepresentant() );
        cerfaDto.setEgilibiliteFonction( cerfa.getEgilibiliteFonction() );
        cerfaDto.setComplementResponsable( cerfa.getComplementResponsable() );
        cerfaDto.setFaitA( cerfa.getFaitA() );
        cerfaDto.setEmployeurPriveOuPublic( cerfa.getEmployeurPriveOuPublic() );
        cerfaDto.setNomEmployeur( cerfa.getNomEmployeur() );
        cerfaDto.setPrenomEmployeur( cerfa.getPrenomEmployeur() );
        cerfaDto.setAdresseEmployeur( adresseToAdresseDto( cerfa.getAdresseEmployeur() ) );
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
        cerfaDto.setAdresseApprenti( adresseToAdresseDto( cerfa.getAdresseApprenti() ) );
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
        cerfaDto.setAdresseRepresentant( adresseToAdresseDto( cerfa.getAdresseRepresentant() ) );
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
        cerfaDto.setRemuneration1( remunerationTORemunerationDto( cerfa.getRemuneration1() ) );
        cerfaDto.setRemuneration2( remunerationTORemunerationDto( cerfa.getRemuneration2() ) );
        cerfaDto.setRemuneration3( remunerationTORemunerationDto( cerfa.getRemuneration3() ) );
        cerfaDto.setRemuneration4( remunerationTORemunerationDto( cerfa.getRemuneration4() ) );
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
        cerfaDto.setAdresseResponsable( adresseToAdresseDto( cerfa.getAdresseResponsable() ) );
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
        cerfaDto.setEtudiant( etudiantToEtudiantDto( cerfa.getEtudiant() ) );

        return cerfaDto;
    }

    @Override
    public RemunerationDto remunerationTORemunerationDto(Remuneration remuneration) {
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
    public ActiviteTypeDto activiteTypeToActiviteTypeDto(ActiviteType activiteType) {
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
    public CompetenceProfessionnelleDto competenceProfessionnelleToCompetenceProfessionnelleDto(CompetenceProfessionnelle competenceProfessionnelle) {
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
        formation.setObjectif( formationDG2Dto.getObjectives() );
        formation.setPrerequis( formationDG2Dto.getPrerequisites() );
        formation.setId( formationDG2Dto.getId() );
        formation.setVersion( formationDG2Dto.getVersion() );
        formation.setSlug( formationDG2Dto.getSlug() );
        formation.setDuration( formationDG2Dto.getDuration() );
        formation.setPlan( formationDG2Dto.getPlan() );

        return formation;
    }

    @Override
    public Cursus cursusDG2DtoToCursus(CursusDG2Dto cursusDG2Dto) {
        if ( cursusDG2Dto == null ) {
            return null;
        }

        Cursus cursus = new Cursus();

        cursus.setIdDg2( cursusDG2Dto.getId() );
        cursus.setTitre( cursusDG2Dto.getTitle() );
        cursus.setDuree( cursusDG2Dto.getDuration() );
        cursus.setId( cursusDG2Dto.getId() );
        cursus.setVersion( cursusDG2Dto.getVersion() );
        cursus.setSlug( cursusDG2Dto.getSlug() );

        return cursus;
    }

    @Override
    public List<Cursus> lstCursusDG2DtoToListCursus(List<InterventionDG2Dto> lstCurusDto) {
        if ( lstCurusDto == null ) {
            return null;
        }

        List<Cursus> list = new ArrayList<Cursus>( lstCurusDto.size() );
        for ( InterventionDG2Dto interventionDG2Dto : lstCurusDto ) {
            list.add( interventionDG2DtoToCursus( interventionDG2Dto ) );
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
        centreFormation.setVersion( centreFormationDG2Dto.getVersion() );

        return centreFormation;
    }

    @Override
    public ExperienceProfessionnelleDto experienceProfessionnelleToExperienceProfessionnelleDto(ExperienceProfessionnelle experienceProfessionnelle) {
        if ( experienceProfessionnelle == null ) {
            return null;
        }

        ExperienceProfessionnelleDto experienceProfessionnelleDto = new ExperienceProfessionnelleDto();

        experienceProfessionnelleDto.setTacheRealisee( experienceProfessionnelle.getTacheRealisee() );
        experienceProfessionnelleDto.setMoyenUtilise( experienceProfessionnelle.getMoyenUtilise() );
        experienceProfessionnelleDto.setCollaborateur( experienceProfessionnelle.getCollaborateur() );
        experienceProfessionnelleDto.setContexte( experienceProfessionnelle.getContexte() );
        experienceProfessionnelleDto.setInformation( experienceProfessionnelle.getInformation() );
        experienceProfessionnelleDto.setId( experienceProfessionnelle.getId() );
        experienceProfessionnelleDto.setVersion( experienceProfessionnelle.getVersion() );

        return experienceProfessionnelleDto;
    }

    @Override
    public Utilisateur etudiantUtilisateurDG2DtoToUtilisateur(EtudiantUtilisateurDG2Dto eDG2) {
        if ( eDG2 == null ) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setIdDg2( eDG2.getPersonId() );
        utilisateur.setPrenom( eDG2.getFirstName() );
        utilisateur.setNom( eDG2.getLastName() );
        utilisateur.setLogin( eDG2.getEmail() );
        utilisateur.setTelephoneFixe( eDG2.getLandline() );
        utilisateur.setTelephone( eDG2.getMobile() );
        utilisateur.setVersion( eDG2.getVersion() );
        utilisateur.setCivilite( eDG2.getCivilite() );

        return utilisateur;
    }

    @Override
    public Utilisateur employeeDg2ToUtilisateur(EmployeeDG2Dto eDg2) {
        if ( eDg2 == null ) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();

        if ( eDg2.getPersonId() != null ) {
            utilisateur.setIdDg2( Long.parseLong( eDg2.getPersonId() ) );
        }
        utilisateur.setPrenom( eDg2.getFirstName() );
        utilisateur.setNom( eDg2.getLastName() );
        utilisateur.setLogin( eDg2.getEmail() );
        utilisateur.setId( eDg2.getId() );

        return utilisateur;
    }

    @Override
    public Adresse etudiantUtilisateurDG2DtoToAdresse(EtudiantUtilisateurDG2Dto eDG2) {
        if ( eDG2 == null ) {
            return null;
        }

        Adresse adresse = new Adresse();

        adresse.setCodePostal( eDG2.getPostcode() );
        adresse.setVille( eDG2.getCity() );
        adresse.setCountryCode( eDG2.getCountry() );
        adresse.setLibelle( eDG2.getStreet() );
        adresse.setVersion( eDG2.getVersion() );

        return adresse;
    }

    protected List<DossierProfessionnelDto> dossierProfessionnelListToDossierProfessionnelDtoList(List<DossierProfessionnel> list) {
        if ( list == null ) {
            return null;
        }

        List<DossierProfessionnelDto> list1 = new ArrayList<DossierProfessionnelDto>( list.size() );
        for ( DossierProfessionnel dossierProfessionnel : list ) {
            list1.add( dossierProfessionnelToDossierProfessionnelDto( dossierProfessionnel ) );
        }

        return list1;
    }

    protected List<DossierProjetDto> dossierProjetListToDossierProjetDtoList(List<DossierProjet> list) {
        if ( list == null ) {
            return null;
        }

        List<DossierProjetDto> list1 = new ArrayList<DossierProjetDto>( list.size() );
        for ( DossierProjet dossierProjet : list ) {
            list1.add( dossierProjetToDossierProjetDto( dossierProjet ) );
        }

        return list1;
    }

    protected Cursus interventionDG2DtoToCursus(InterventionDG2Dto interventionDG2Dto) {
        if ( interventionDG2Dto == null ) {
            return null;
        }

        Cursus cursus = new Cursus();

        cursus.setId( interventionDG2Dto.getId() );
        cursus.setVersion( interventionDG2Dto.getVersion() );
        cursus.setSlug( interventionDG2Dto.getSlug() );

        return cursus;
    }
}
