package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Sifa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SifaDto {
    private long id;
    private int version;
    private String numeroUaiEtablissement;
    private int organismeGestionnaireEtablissement;
    private String numeroUaiSiteFormation;
    private int numeroUaiFormation;
    private String natureStructureJuridique;
    private String statutJeune;
    private String diplomeOuTitrePrepare;
    private int dureeMoisFormationTheorique;
    private int dureeMoisFormationReelle;
    private String situationFormation;
    private String nomFamilleJeune;
    private String nomUsageJeune;
    private String premierPrenomJeune;
    private String deuxiemePrenomJeune;
    private String troisiemePrenomJeune;
    private String adressePostaleJeune;
    private String codePostalResidenceJeune;
    private String codeCommuneResidenceJeune;
    private int numeroTelephoneJeune;
    private int numeroTelephonePersonnelResponsable1;
    private int numeroTelephoneProfessionnelResponsable1;
    private int numeroTelephonePersonnelResponsable2;
    private int numeroTelephoneProfessionnelResponsable2;
    @Email
    private String adresseMailJeune;
    @Email
    private String adresseMailResponsable1;
    @Email
    private String adresseMailResponsable2;
    private LocalDate dateNaissanceJeune;
    private String codeCommuneNaissanceJeune;
    private int sexeJeune;
    private String regimeScolaireJeune;
    private String pcsRepresentantLegalJeune;
    private boolean apprentisReconnaissanceTravailleurHandicape;
    private String nationalite;
    private String situationAvantPremiereAnneeApprentissage;
    private String dernierDiplomeObtenu;
    private int situationOuClasseAnneeDerniere;
    private String numeroUaiEtablissementDerniereAnnee;
    private int typeEmployeur;
    private LocalDate dateEntreeCfa;
    private LocalDate dateDebutContratApprentissage;
    private LocalDate dateRuptureContrat;
    private int codeCommuneEtablissementEmployeur;
    private int codeNafEtablissementEmployeur;
    private int nombreSalariesEntrepriseEmployeur;
    private int numeroSiretEntrepriseEmployeur;
    private Sifa.Etat etat;
}
