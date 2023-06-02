package fr.dawan.AppliCFABack.mapper;

import fr.dawan.AppliCFABack.dto.ActiviteTypeDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.AnnexeDto;
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
import fr.dawan.AppliCFABack.dto.EvaluationFormationDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;
import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.FacultatifDto;
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
import fr.dawan.AppliCFABack.dto.TuteurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.ActiviteTypeDossierProDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.CompetenceDossierProDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.CursusDossierProDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.DossierProEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.CompetenceCouvertesDossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.EtudiantDossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.ProjetDossierProjetDto;
import fr.dawan.AppliCFABack.entities.ActiviteType;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.Annexe;
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
import fr.dawan.AppliCFABack.entities.Remuneration;
import fr.dawan.AppliCFABack.entities.Tuteur;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-01T16:07:11+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class DtoMapperImpl implements DtoMapper {

    @Override
    public EvaluationFormationDto toEvaluationFormationDto(EvaluationFormation entity) {
        if ( entity == null ) {
            return null;
        }

        EvaluationFormationDto evaluationFormationDto = new EvaluationFormationDto();

        evaluationFormationDto.setId( entity.getId() );
        evaluationFormationDto.setVersion( entity.getVersion() );
        evaluationFormationDto.setContenu( entity.getContenu() );
        evaluationFormationDto.setDateEvaluation( entity.getDateEvaluation() );

        return evaluationFormationDto;
    }

    @Override
    public EvaluationFormation toEvaluationFormationEntity(EvaluationFormationDto dto) {
        if ( dto == null ) {
            return null;
        }

        EvaluationFormation evaluationFormation = new EvaluationFormation();

        evaluationFormation.setId( dto.getId() );
        evaluationFormation.setVersion( dto.getVersion() );
        evaluationFormation.setContenu( dto.getContenu() );
        evaluationFormation.setDateEvaluation( dto.getDateEvaluation() );

        return evaluationFormation;
    }

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
        cursusDto.setNiveau( cursus.getNiveau() );
        cursusDto.setSigle( cursus.getSigle() );
        cursusDto.setMillesime( cursus.getMillesime() );
        cursusDto.setCodeTitre( cursus.getCodeTitre() );

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

        etudiantDto.setTuteurDto( tuteurTotuteurDto( etudiant.getTuteur() ) );
        etudiantDto.setId( etudiant.getId() );
        etudiantDto.setVersion( etudiant.getVersion() );
        etudiantDto.setDossierProfessionnel( dossierProfessionnelListToDossierProfessionnelDtoList( etudiant.getDossierProfessionnel() ) );

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
    public TuteurDto tuteurTotuteurDto(Tuteur tuteur) {
        if ( tuteur == null ) {
            return null;
        }

        TuteurDto tuteurDto = new TuteurDto();

        tuteurDto.setId( tuteur.getId() );
        tuteurDto.setVersion( tuteur.getVersion() );

        return tuteurDto;
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
        promotionDto.setIdDg2( promotion.getIdDg2() );

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
        utilisateurDto.setActive( utilisateur.isActive() );
        utilisateurDto.setExternalAccount( utilisateur.isExternalAccount() );
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
        dossierProfessionnelDto.setNom( dossierProfessionnel.getNom() );
        dossierProfessionnelDto.setCursusDto( cursusToCursusDto( dossierProfessionnel.getCursus() ) );
        dossierProfessionnelDto.setExperienceProfessionnelleDtos( experienceProfessionnelleToExperienceProfessionnelleDto( dossierProfessionnel.getExperienceProfessionnelles() ) );
        dossierProfessionnelDto.setAnnexeDtos( annexeToAnnexeDto( dossierProfessionnel.getAnnexes() ) );
        dossierProfessionnelDto.setFacultatifDto( facultatifToFacultatifDto( dossierProfessionnel.getFacultatifs() ) );
        dossierProfessionnelDto.setVersion( dossierProfessionnel.getVersion() );
        dossierProfessionnelDto.setFileImport( dossierProfessionnel.getFileImport() );

        return dossierProfessionnelDto;
    }

    @Override
    public DossierProfessionnel dossierProfessionnelDtoToDossierProfessionnel(DossierProEtudiantDto dosierProEtudiant) {
        if ( dosierProEtudiant == null ) {
            return null;
        }

        DossierProfessionnel dossierProfessionnel = new DossierProfessionnel();

        dossierProfessionnel.setId( dosierProEtudiant.getId() );
        dossierProfessionnel.setNom( dosierProEtudiant.getNom() );
        dossierProfessionnel.setCursus( cursusDossierProDtoToCursus( dosierProEtudiant.getCursusDto() ) );
        dossierProfessionnel.setExperienceProfessionnelles( experienceProfessionnelleDtoListToExperienceProfessionnelleList1( dosierProEtudiant.getExperienceProfessionnelleDtos() ) );
        dossierProfessionnel.setAnnexes( annexeDtoListToAnnexeList( dosierProEtudiant.getAnnexeDtos() ) );
        dossierProfessionnel.setFacultatifs( facultatifDtoListToFacultatifList( dosierProEtudiant.getFacultatifDto() ) );
        dossierProfessionnel.setVersion( dosierProEtudiant.getVersion() );
        dossierProfessionnel.setFileImport( dosierProEtudiant.getFileImport() );

        return dossierProfessionnel;
    }

    @Override
    public DossierProEtudiantDto dossierProfessionnelToDossierProEtudiantDto(DossierProfessionnel dossierPro) {
        if ( dossierPro == null ) {
            return null;
        }

        DossierProEtudiantDto dossierProEtudiantDto = new DossierProEtudiantDto();

        dossierProEtudiantDto.setId( dossierPro.getId() );
        dossierProEtudiantDto.setNom( dossierPro.getNom() );
        dossierProEtudiantDto.setCursusDto( cursusToCursusDossierProDto( dossierPro.getCursus() ) );
        dossierProEtudiantDto.setExperienceProfessionnelleDtos( experienceProfessionnelleToExperienceProfessionnelleDto( dossierPro.getExperienceProfessionnelles() ) );
        dossierProEtudiantDto.setAnnexeDtos( annexeToAnnexeDto( dossierPro.getAnnexes() ) );
        dossierProEtudiantDto.setFacultatifDto( facultatifToFacultatifDto( dossierPro.getFacultatifs() ) );
        dossierProEtudiantDto.setFileImport( dossierPro.getFileImport() );
        dossierProEtudiantDto.setVersion( dossierPro.getVersion() );

        return dossierProEtudiantDto;
    }

    @Override
    public DossierProjetDto dossierProjetToDpDto(DossierProjet dossierProjet) {
        if ( dossierProjet == null ) {
            return null;
        }

        DossierProjetDto dossierProjetDto = new DossierProjetDto();

        dossierProjetDto.setNom( dossierProjet.getNom() );
        dossierProjetDto.setId( dossierProjet.getId() );
        dossierProjetDto.setDossierImport( dossierProjet.getDossierImport() );
        dossierProjetDto.setProjet( projetToProjetDossierProjetDto( dossierProjet.getProjet() ) );
        dossierProjetDto.setEtudiant( etudiantToEtudiantDossierProjetDto( dossierProjet.getEtudiant() ) );
        List<String> list = dossierProjet.getAnnexeDossierProjets();
        if ( list != null ) {
            dossierProjetDto.setAnnexeDossierProjets( new ArrayList<String>( list ) );
        }
        List<String> list1 = dossierProjet.getInfoDossierProjets();
        if ( list1 != null ) {
            dossierProjetDto.setInfoDossierProjets( new ArrayList<String>( list1 ) );
        }
        List<String> list2 = dossierProjet.getContenuDossierProjets();
        if ( list2 != null ) {
            dossierProjetDto.setContenuDossierProjets( new ArrayList<String>( list2 ) );
        }
        List<String> list3 = dossierProjet.getResumeDossierProjets();
        if ( list3 != null ) {
            dossierProjetDto.setResumeDossierProjets( new ArrayList<String>( list3 ) );
        }
        dossierProjetDto.setVersion( dossierProjet.getVersion() );

        return dossierProjetDto;
    }

    @Override
    public DossierProjetDto dossierProjetToDossierProjetDto(DossierProjet dossierProjet) {
        if ( dossierProjet == null ) {
            return null;
        }

        DossierProjetDto dossierProjetDto = new DossierProjetDto();

        dossierProjetDto.setCompetenceProfessionnelleIds( competenceProToId( dossierProjet.getCompetenceProfessionnelles() ) );
        dossierProjetDto.setNom( dossierProjet.getNom() );
        dossierProjetDto.setId( dossierProjet.getId() );
        dossierProjetDto.setDossierImport( dossierProjet.getDossierImport() );
        dossierProjetDto.setProjet( projetToProjetDossierProjetDto( dossierProjet.getProjet() ) );
        dossierProjetDto.setEtudiant( etudiantToEtudiantDossierProjetDto( dossierProjet.getEtudiant() ) );
        List<String> list1 = dossierProjet.getAnnexeDossierProjets();
        if ( list1 != null ) {
            dossierProjetDto.setAnnexeDossierProjets( new ArrayList<String>( list1 ) );
        }
        List<String> list2 = dossierProjet.getInfoDossierProjets();
        if ( list2 != null ) {
            dossierProjetDto.setInfoDossierProjets( new ArrayList<String>( list2 ) );
        }
        List<String> list3 = dossierProjet.getContenuDossierProjets();
        if ( list3 != null ) {
            dossierProjetDto.setContenuDossierProjets( new ArrayList<String>( list3 ) );
        }
        List<String> list4 = dossierProjet.getResumeDossierProjets();
        if ( list4 != null ) {
            dossierProjetDto.setResumeDossierProjets( new ArrayList<String>( list4 ) );
        }
        dossierProjetDto.setVersion( dossierProjet.getVersion() );

        return dossierProjetDto;
    }

    @Override
    public DossierProjet dossierProjetDtoToDossierProjet(DossierProjetDto dpDto) {
        if ( dpDto == null ) {
            return null;
        }

        DossierProjet dossierProjet = new DossierProjet();

        dossierProjet.setCompetenceProfessionnelles( idToCompetencePro( dpDto.getCompetenceProfessionnelleIds() ) );
        dossierProjet.setId( dpDto.getId() );
        dossierProjet.setVersion( dpDto.getVersion() );
        dossierProjet.setDossierImport( dpDto.getDossierImport() );
        dossierProjet.setNom( dpDto.getNom() );
        dossierProjet.setProjet( projetDossierProjetDtoToProjet( dpDto.getProjet() ) );
        dossierProjet.setEtudiant( etudiantDossierProjetDtoToEtudiant( dpDto.getEtudiant() ) );
        List<String> list1 = dpDto.getAnnexeDossierProjets();
        if ( list1 != null ) {
            dossierProjet.setAnnexeDossierProjets( new ArrayList<String>( list1 ) );
        }
        List<String> list2 = dpDto.getInfoDossierProjets();
        if ( list2 != null ) {
            dossierProjet.setInfoDossierProjets( new ArrayList<String>( list2 ) );
        }
        List<String> list3 = dpDto.getContenuDossierProjets();
        if ( list3 != null ) {
            dossierProjet.setContenuDossierProjets( new ArrayList<String>( list3 ) );
        }
        List<String> list4 = dpDto.getResumeDossierProjets();
        if ( list4 != null ) {
            dossierProjet.setResumeDossierProjets( new ArrayList<String>( list4 ) );
        }

        return dossierProjet;
    }

    @Override
    public AnnexeDto AnnexeToAnnexeDto(Annexe annexe) {
        if ( annexe == null ) {
            return null;
        }

        AnnexeDto annexeDto = new AnnexeDto();

        annexeDto.setId( annexe.getId() );
        annexeDto.setLibelleAnnexe( annexe.getLibelleAnnexe() );
        annexeDto.setPieceJointe( annexe.getPieceJointe() );
        annexeDto.setVersion( annexe.getVersion() );

        return annexeDto;
    }

    @Override
    public FacultatifDto FacultatifToFacultatifDto(Facultatif facultatif) {
        if ( facultatif == null ) {
            return null;
        }

        FacultatifDto facultatifDto = new FacultatifDto();

        facultatifDto.setId( facultatif.getId() );
        facultatifDto.setVersion( facultatif.getVersion() );
        facultatifDto.setOrganisme( facultatif.getOrganisme() );
        facultatifDto.setIntitule( facultatif.getIntitule() );
        facultatifDto.setDate( facultatif.getDate() );

        return facultatifDto;
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
    public List<CompetenceCouvertesDossierProjetDto> competenceProfessionnelleToCompetenceCouvertesDto(List<CompetenceProfessionnelle> competenceProfessionnelle) {
        if ( competenceProfessionnelle == null ) {
            return null;
        }

        List<CompetenceCouvertesDossierProjetDto> list = new ArrayList<CompetenceCouvertesDossierProjetDto>( competenceProfessionnelle.size() );
        for ( CompetenceProfessionnelle competenceProfessionnelle1 : competenceProfessionnelle ) {
            list.add( competenceProfessionnelleToCompetenceCouvertesDossierProjetDto( competenceProfessionnelle1 ) );
        }

        return list;
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

        return adresse;
    }

    @Override
    public List<AnnexeDto> annexeToAnnexeDto(List<Annexe> annexes) {
        if ( annexes == null ) {
            return null;
        }

        List<AnnexeDto> list = new ArrayList<AnnexeDto>( annexes.size() );
        for ( Annexe annexe : annexes ) {
            list.add( AnnexeToAnnexeDto( annexe ) );
        }

        return list;
    }

    @Override
    public List<FacultatifDto> facultatifToFacultatifDto(List<Facultatif> facultatifs) {
        if ( facultatifs == null ) {
            return null;
        }

        List<FacultatifDto> list = new ArrayList<FacultatifDto>( facultatifs.size() );
        for ( Facultatif facultatif : facultatifs ) {
            list.add( FacultatifToFacultatifDto( facultatif ) );
        }

        return list;
    }

    @Override
    public List<ExperienceProfessionnelleDto> experienceProfessionnelleToExperienceProfessionnelleDto(List<ExperienceProfessionnelle> experienceProfessionnelles) {
        if ( experienceProfessionnelles == null ) {
            return null;
        }

        List<ExperienceProfessionnelleDto> list = new ArrayList<ExperienceProfessionnelleDto>( experienceProfessionnelles.size() );
        for ( ExperienceProfessionnelle experienceProfessionnelle : experienceProfessionnelles ) {
            list.add( experienceProfessionnelleToExperienceProfessionnelleDto( experienceProfessionnelle ) );
        }

        return list;
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

    protected ExperienceProfessionnelle experienceProfessionnelleDtoToExperienceProfessionnelle(ExperienceProfessionnelleDto experienceProfessionnelleDto) {
        if ( experienceProfessionnelleDto == null ) {
            return null;
        }

        ExperienceProfessionnelle experienceProfessionnelle = new ExperienceProfessionnelle();

        experienceProfessionnelle.setId( experienceProfessionnelleDto.getId() );
        experienceProfessionnelle.setVersion( experienceProfessionnelleDto.getVersion() );
        experienceProfessionnelle.setTacheRealisee( experienceProfessionnelleDto.getTacheRealisee() );
        experienceProfessionnelle.setMoyenUtilise( experienceProfessionnelleDto.getMoyenUtilise() );
        experienceProfessionnelle.setCollaborateur( experienceProfessionnelleDto.getCollaborateur() );
        experienceProfessionnelle.setContexte( experienceProfessionnelleDto.getContexte() );
        experienceProfessionnelle.setInformation( experienceProfessionnelleDto.getInformation() );

        return experienceProfessionnelle;
    }

    protected List<ExperienceProfessionnelle> experienceProfessionnelleDtoListToExperienceProfessionnelleList(List<ExperienceProfessionnelleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ExperienceProfessionnelle> list1 = new ArrayList<ExperienceProfessionnelle>( list.size() );
        for ( ExperienceProfessionnelleDto experienceProfessionnelleDto : list ) {
            list1.add( experienceProfessionnelleDtoToExperienceProfessionnelle( experienceProfessionnelleDto ) );
        }

        return list1;
    }

    protected CompetenceProfessionnelle competenceDossierProDtoToCompetenceProfessionnelle(CompetenceDossierProDto competenceDossierProDto) {
        if ( competenceDossierProDto == null ) {
            return null;
        }

        CompetenceProfessionnelle competenceProfessionnelle = new CompetenceProfessionnelle();

        competenceProfessionnelle.setId( competenceDossierProDto.getId() );
        competenceProfessionnelle.setVersion( competenceDossierProDto.getVersion() );
        competenceProfessionnelle.setLibelle( competenceDossierProDto.getLibelle() );
        competenceProfessionnelle.setNumeroFiche( competenceDossierProDto.getNumeroFiche() );
        competenceProfessionnelle.setExperienceProfessionnelles( experienceProfessionnelleDtoListToExperienceProfessionnelleList( competenceDossierProDto.getExperienceProfessionnelles() ) );

        return competenceProfessionnelle;
    }

    protected Set<CompetenceProfessionnelle> competenceDossierProDtoSetToCompetenceProfessionnelleSet(Set<CompetenceDossierProDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<CompetenceProfessionnelle> set1 = new HashSet<CompetenceProfessionnelle>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CompetenceDossierProDto competenceDossierProDto : set ) {
            set1.add( competenceDossierProDtoToCompetenceProfessionnelle( competenceDossierProDto ) );
        }

        return set1;
    }

    protected ActiviteType activiteTypeDossierProDtoToActiviteType(ActiviteTypeDossierProDto activiteTypeDossierProDto) {
        if ( activiteTypeDossierProDto == null ) {
            return null;
        }

        ActiviteType activiteType = new ActiviteType();

        activiteType.setId( activiteTypeDossierProDto.getId() );
        activiteType.setVersion( activiteTypeDossierProDto.getVersion() );
        activiteType.setLibelle( activiteTypeDossierProDto.getLibelle() );
        activiteType.setNumeroFiche( activiteTypeDossierProDto.getNumeroFiche() );
        activiteType.setCompetenceProfessionnelles( competenceDossierProDtoSetToCompetenceProfessionnelleSet( activiteTypeDossierProDto.getCompetenceProfessionnelles() ) );

        return activiteType;
    }

    protected Set<ActiviteType> activiteTypeDossierProDtoSetToActiviteTypeSet(Set<ActiviteTypeDossierProDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ActiviteType> set1 = new HashSet<ActiviteType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ActiviteTypeDossierProDto activiteTypeDossierProDto : set ) {
            set1.add( activiteTypeDossierProDtoToActiviteType( activiteTypeDossierProDto ) );
        }

        return set1;
    }

    protected Cursus cursusDossierProDtoToCursus(CursusDossierProDto cursusDossierProDto) {
        if ( cursusDossierProDto == null ) {
            return null;
        }

        Cursus cursus = new Cursus();

        cursus.setId( cursusDossierProDto.getId() );
        cursus.setVersion( cursusDossierProDto.getVersion() );
        cursus.setTitre( cursusDossierProDto.getTitre() );
        cursus.setActiviteTypes( activiteTypeDossierProDtoSetToActiviteTypeSet( cursusDossierProDto.getActiviteTypes() ) );

        return cursus;
    }

    protected List<ExperienceProfessionnelle> experienceProfessionnelleDtoListToExperienceProfessionnelleList1(List<ExperienceProfessionnelleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ExperienceProfessionnelle> list1 = new ArrayList<ExperienceProfessionnelle>( list.size() );
        for ( ExperienceProfessionnelleDto experienceProfessionnelleDto : list ) {
            list1.add( experienceProfessionnelleDtoToExperienceProfessionnelle( experienceProfessionnelleDto ) );
        }

        return list1;
    }

    protected Annexe annexeDtoToAnnexe(AnnexeDto annexeDto) {
        if ( annexeDto == null ) {
            return null;
        }

        Annexe annexe = new Annexe();

        annexe.setId( annexeDto.getId() );
        annexe.setVersion( annexeDto.getVersion() );
        annexe.setLibelleAnnexe( annexeDto.getLibelleAnnexe() );
        annexe.setPieceJointe( annexeDto.getPieceJointe() );

        return annexe;
    }

    protected List<Annexe> annexeDtoListToAnnexeList(List<AnnexeDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Annexe> list1 = new ArrayList<Annexe>( list.size() );
        for ( AnnexeDto annexeDto : list ) {
            list1.add( annexeDtoToAnnexe( annexeDto ) );
        }

        return list1;
    }

    protected Facultatif facultatifDtoToFacultatif(FacultatifDto facultatifDto) {
        if ( facultatifDto == null ) {
            return null;
        }

        Facultatif facultatif = new Facultatif();

        facultatif.setId( facultatifDto.getId() );
        facultatif.setVersion( facultatifDto.getVersion() );
        facultatif.setOrganisme( facultatifDto.getOrganisme() );
        facultatif.setIntitule( facultatifDto.getIntitule() );
        facultatif.setDate( facultatifDto.getDate() );

        return facultatif;
    }

    protected List<Facultatif> facultatifDtoListToFacultatifList(List<FacultatifDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Facultatif> list1 = new ArrayList<Facultatif>( list.size() );
        for ( FacultatifDto facultatifDto : list ) {
            list1.add( facultatifDtoToFacultatif( facultatifDto ) );
        }

        return list1;
    }

    protected CompetenceDossierProDto competenceProfessionnelleToCompetenceDossierProDto(CompetenceProfessionnelle competenceProfessionnelle) {
        if ( competenceProfessionnelle == null ) {
            return null;
        }

        CompetenceDossierProDto competenceDossierProDto = new CompetenceDossierProDto();

        competenceDossierProDto.setId( competenceProfessionnelle.getId() );
        competenceDossierProDto.setLibelle( competenceProfessionnelle.getLibelle() );
        competenceDossierProDto.setVersion( competenceProfessionnelle.getVersion() );
        competenceDossierProDto.setExperienceProfessionnelles( experienceProfessionnelleToExperienceProfessionnelleDto( competenceProfessionnelle.getExperienceProfessionnelles() ) );
        competenceDossierProDto.setNumeroFiche( competenceProfessionnelle.getNumeroFiche() );

        return competenceDossierProDto;
    }

    protected Set<CompetenceDossierProDto> competenceProfessionnelleSetToCompetenceDossierProDtoSet(Set<CompetenceProfessionnelle> set) {
        if ( set == null ) {
            return null;
        }

        Set<CompetenceDossierProDto> set1 = new HashSet<CompetenceDossierProDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CompetenceProfessionnelle competenceProfessionnelle : set ) {
            set1.add( competenceProfessionnelleToCompetenceDossierProDto( competenceProfessionnelle ) );
        }

        return set1;
    }

    protected ActiviteTypeDossierProDto activiteTypeToActiviteTypeDossierProDto(ActiviteType activiteType) {
        if ( activiteType == null ) {
            return null;
        }

        ActiviteTypeDossierProDto activiteTypeDossierProDto = new ActiviteTypeDossierProDto();

        activiteTypeDossierProDto.setId( activiteType.getId() );
        activiteTypeDossierProDto.setLibelle( activiteType.getLibelle() );
        activiteTypeDossierProDto.setCompetenceProfessionnelles( competenceProfessionnelleSetToCompetenceDossierProDtoSet( activiteType.getCompetenceProfessionnelles() ) );
        activiteTypeDossierProDto.setVersion( activiteType.getVersion() );
        activiteTypeDossierProDto.setNumeroFiche( activiteType.getNumeroFiche() );

        return activiteTypeDossierProDto;
    }

    protected Set<ActiviteTypeDossierProDto> activiteTypeSetToActiviteTypeDossierProDtoSet(Set<ActiviteType> set) {
        if ( set == null ) {
            return null;
        }

        Set<ActiviteTypeDossierProDto> set1 = new HashSet<ActiviteTypeDossierProDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ActiviteType activiteType : set ) {
            set1.add( activiteTypeToActiviteTypeDossierProDto( activiteType ) );
        }

        return set1;
    }

    protected CursusDossierProDto cursusToCursusDossierProDto(Cursus cursus) {
        if ( cursus == null ) {
            return null;
        }

        CursusDossierProDto cursusDossierProDto = new CursusDossierProDto();

        cursusDossierProDto.setTitre( cursus.getTitre() );
        cursusDossierProDto.setActiviteTypes( activiteTypeSetToActiviteTypeDossierProDtoSet( cursus.getActiviteTypes() ) );
        cursusDossierProDto.setId( cursus.getId() );
        cursusDossierProDto.setVersion( cursus.getVersion() );

        return cursusDossierProDto;
    }

    protected ProjetDossierProjetDto projetToProjetDossierProjetDto(Projet projet) {
        if ( projet == null ) {
            return null;
        }

        ProjetDossierProjetDto projetDossierProjetDto = new ProjetDossierProjetDto();

        projetDossierProjetDto.setId( projet.getId() );
        projetDossierProjetDto.setNom( projet.getNom() );
        projetDossierProjetDto.setVersion( projet.getVersion() );

        return projetDossierProjetDto;
    }

    protected EtudiantDossierProjetDto etudiantToEtudiantDossierProjetDto(Etudiant etudiant) {
        if ( etudiant == null ) {
            return null;
        }

        EtudiantDossierProjetDto etudiantDossierProjetDto = new EtudiantDossierProjetDto();

        etudiantDossierProjetDto.setId( etudiant.getId() );
        etudiantDossierProjetDto.setVersion( etudiant.getVersion() );

        return etudiantDossierProjetDto;
    }

    protected Projet projetDossierProjetDtoToProjet(ProjetDossierProjetDto projetDossierProjetDto) {
        if ( projetDossierProjetDto == null ) {
            return null;
        }

        Projet projet = new Projet();

        projet.setId( projetDossierProjetDto.getId() );
        projet.setVersion( projetDossierProjetDto.getVersion() );
        projet.setNom( projetDossierProjetDto.getNom() );

        return projet;
    }

    protected Etudiant etudiantDossierProjetDtoToEtudiant(EtudiantDossierProjetDto etudiantDossierProjetDto) {
        if ( etudiantDossierProjetDto == null ) {
            return null;
        }

        Etudiant etudiant = new Etudiant();

        etudiant.setId( etudiantDossierProjetDto.getId() );
        etudiant.setVersion( etudiantDossierProjetDto.getVersion() );

        return etudiant;
    }

    protected CompetenceCouvertesDossierProjetDto competenceProfessionnelleToCompetenceCouvertesDossierProjetDto(CompetenceProfessionnelle competenceProfessionnelle) {
        if ( competenceProfessionnelle == null ) {
            return null;
        }

        CompetenceCouvertesDossierProjetDto competenceCouvertesDossierProjetDto = new CompetenceCouvertesDossierProjetDto();

        competenceCouvertesDossierProjetDto.setId( competenceProfessionnelle.getId() );
        competenceCouvertesDossierProjetDto.setVersion( competenceProfessionnelle.getVersion() );
        competenceCouvertesDossierProjetDto.setLibelle( competenceProfessionnelle.getLibelle() );
        competenceCouvertesDossierProjetDto.setNumeroFiche( competenceProfessionnelle.getNumeroFiche() );

        return competenceCouvertesDossierProjetDto;
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
