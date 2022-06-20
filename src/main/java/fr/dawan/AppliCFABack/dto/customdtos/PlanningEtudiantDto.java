package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.entities.Formateur;

import java.time.LocalDate;
import java.util.List;

public class PlanningEtudiantDto {

    private LocalDate interventionDateDebut;

    private LocalDate interventionDateFin;

    private String formationTitre;

    private String formateurNom;


    public PlanningEtudiantDto() {
    }

    public PlanningEtudiantDto(LocalDate interventionDateDebut, LocalDate interventionDateFin, String formationTitre, String formateurNom) {
        this.interventionDateDebut = interventionDateDebut;
        this.interventionDateFin = interventionDateFin;
        this.formationTitre = formationTitre;
        this.formateurNom = formateurNom;
    }

    public PlanningEtudiantDto(LocalDate dateDebut, LocalDate dateFin, String titre, List<Formateur> formateurs) {
    }

    public LocalDate getInterventionDateDebut() {
        return interventionDateDebut;
    }

    public void setInterventionDateDebut(LocalDate interventionDateDebut) {
        this.interventionDateDebut = interventionDateDebut;
    }

    public LocalDate getInterventionDateFin() {
        return interventionDateFin;
    }

    public void setInterventionDateFin(LocalDate interventionDateFin) {
        this.interventionDateFin = interventionDateFin;
    }

    public String getFormationTitre() {
        return formationTitre;
    }

    public void setFormationTitre(String formationTitre) {
        this.formationTitre = formationTitre;
    }

    public String getFormateurNom() {
        return formateurNom;
    }

    public void setFormateurNom(String formateurNom) {
        this.formateurNom = formateurNom;
    }


}