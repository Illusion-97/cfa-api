package fr.dawan.AppliCFABack.dto.customdtos;

import java.time.LocalDate;
import java.util.List;

public class PromotionEtudiantDto {

    private String cursusTitre;

    private String cursusDescription;

    private String cursusDuree;

    private String nom;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private List<PlanningEtudiantDto> planningsEtudiantDto;

    public String getCursusTitre() {
        return cursusTitre;
    }

    public void setCursusTitre(String cursusTitre) {
        this.cursusTitre = cursusTitre;
    }

    public String getCursusDescription() {
        return cursusDescription;
    }

    public void setCursusDescription(String cursusDescription) {
        this.cursusDescription = cursusDescription;
    }

    public String getCursusDuree() {
        return cursusDuree;
    }

    public void setCursusDuree(String cursusDuree) {
        this.cursusDuree = cursusDuree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<PlanningEtudiantDto> getPlanningsEtudiantDto() {
        return planningsEtudiantDto;
    }

    public void setPlanningsEtudiantDto(List<PlanningEtudiantDto> planningsEtudiantDto) {
        this.planningsEtudiantDto = planningsEtudiantDto;
    }
}