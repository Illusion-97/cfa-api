package fr.dawan.AppliCFABack.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sifa extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "user_id")
    Utilisateur utilisateur;
    private String nationalite;
    //private int numeroTelephoneJeune;
    //@Email
    //private String adresseMailJeune;
    //private Date dateNaissanceJeune;
    //private int sexeJeune;
    //private String premierPrenomJeune;
    //private String nomFamilleJeune;
    @OneToOne
    @JoinColumn(name = "promo_id")
    Promotion promotion;
    private int situationOuClasseAnneeDerniere;
    private int dureeMoisFormationTheorique;
    private int dureeMoisFormationReelle;
    //private Date dateEntreeCfa;
    //private Date dateDebutContratApprentissage;
    //private Date dateRuptureContrat;
    @OneToOne
    @JoinColumn(name = "adresseUser_id")
    Adresse adresseUser;
    //private String adressePostaleJeune;
    //private String codePostalResidenceJeune;
    //private String codeCommuneResidenceJeune;
    private String codeCommuneNaissanceJeune;
    @OneToOne
    @JoinColumn(name = "entreprise_id")
    Entreprise entreprise;
    //private int codeNafEtablissementEmployeur;
    //private int nombreSalariesEntrepriseEmployeur;
    //private int numeroSiretEntrepriseEmployeur;
    //private int codeCommuneEtablissementEmployeur;
    private int typeEmployeur;

    private String numeroUaiEtablissement;
    private int organismeGestionnaireEtablissement;
    private String numeroUaiSiteFormation;
    private int numeroUaiFormation;
    private String natureStructureJuridique;
    private String statutJeune;
    private String diplomeOuTitrePrepare;// Numéro Scolarité ou diplôme en lien avec l'état voir peut être dg2
    private String situationFormation;
    private String nomUsageJeune;
    private String deuxiemePrenomJeune;
    private String troisiemePrenomJeune;
    private int numeroTelephonePersonnelResponsable1;
    private int numeroTelephoneProfessionnelResponsable1;
    private int numeroTelephonePersonnelResponsable2;
    private int numeroTelephoneProfessionnelResponsable2;
    @Email
    private String adresseMailResponsable1;
    @Email
    private String adresseMailResponsable2;
    private String regimeScolaireJeune;
    private String pcsRepresentantLegalJeune;
    private boolean apprentisReconnaissanceTravailleurHandicape;
    private String situationAvantPremiereAnneeApprentissage;
    private String dernierDiplomeObtenu;
    private String numeroUaiEtablissementDerniereAnnee;
    private Etat etat;
    public enum Etat {
        VALIDE,
        INVALIDE
    }
}
