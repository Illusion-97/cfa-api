package fr.dawan.AppliCFABack.mapper;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
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
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PassageExamenDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Conge;
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
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.PassageExamen;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-22T16:44:43+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
public class DtoMapperImpl implements DtoMapper {

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

        cEFDto.setLogin( cef.getLogin() );
        cEFDto.setPassword( cef.getPassword() );
        cEFDto.setPrenom( cef.getPrenom() );
        cEFDto.setNom( cef.getNom() );
        cEFDto.setCivilite( cef.getCivilite() );
        cEFDto.setDateDeNaissance( cef.getDateDeNaissance() );
        cEFDto.setTelephone( cef.getTelephone() );
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

        return entrepriseDto;
    }

    @Override
    public EtudiantDto EtudiantToEtudiantDto(Etudiant etudiant) {
        if ( etudiant == null ) {
            return null;
        }

        EtudiantDto etudiantDto = new EtudiantDto();

        etudiantDto.setLogin( etudiant.getLogin() );
        etudiantDto.setPassword( etudiant.getPassword() );
        etudiantDto.setPrenom( etudiant.getPrenom() );
        etudiantDto.setNom( etudiant.getNom() );
        etudiantDto.setCivilite( etudiant.getCivilite() );
        etudiantDto.setDateDeNaissance( etudiant.getDateDeNaissance() );
        etudiantDto.setTelephone( etudiant.getTelephone() );
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
        examenDto.setEnonce( examen.getEnonce() );

        return examenDto;
    }

    @Override
    public FormateurDto FormateurToFormateurDto(Formateur formateur) {
        if ( formateur == null ) {
            return null;
        }

        FormateurDto formateurDto = new FormateurDto();

        formateurDto.setLogin( formateur.getLogin() );
        formateurDto.setPassword( formateur.getPassword() );
        formateurDto.setPrenom( formateur.getPrenom() );
        formateurDto.setNom( formateur.getNom() );
        formateurDto.setCivilite( formateur.getCivilite() );
        formateurDto.setDateDeNaissance( formateur.getDateDeNaissance() );
        formateurDto.setTelephone( formateur.getTelephone() );
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
        noteDto.setNoteObtenu( note.getNoteObtenu() );
        noteDto.setObservations( note.getObservations() );

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
