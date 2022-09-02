package fr.dawan.AppliCFABack.dto.customdtos;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PromotionEtudiantDto {

    private String cursusTitre;

    private String cursusDuree;

    private String nom;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private List<PlanningEtudiantDto> planningsEtudiantDto;

    public PromotionEtudiantDto() {
    }

    public PromotionEtudiantDto(String cursusTitre, String cursusDescription, String cursusDuree, String nom, LocalDate dateDebut, LocalDate dateFin, List<PlanningEtudiantDto> planningsEtudiantDto) {
        this.cursusTitre = cursusTitre;
        this.cursusDescription = cursusDescription;
        this.cursusDuree = cursusDuree;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.planningsEtudiantDto = planningsEtudiantDto;
    }

    public String getCursusTitre() {
        return cursusTitre;
    }

    public void setCursusTitre(String cursusTitre) {
        this.cursusTitre = cursusTitre;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromotionEtudiantDto)) return false;
        PromotionEtudiantDto that = (PromotionEtudiantDto) o;
        return Objects.equals(getCursusTitre(), that.getCursusTitre()) && Objects.equals(getCursusDescription(), that.getCursusDescription()) && Objects.equals(getCursusDuree(), that.getCursusDuree()) && Objects.equals(getNom(), that.getNom()) && Objects.equals(getDateDebut(), that.getDateDebut()) && Objects.equals(getDateFin(), that.getDateFin()) && Objects.equals(getPlanningsEtudiantDto(), that.getPlanningsEtudiantDto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCursusTitre(), getCursusDescription(), getCursusDuree(), getNom(), getDateDebut(), getDateFin(), getPlanningsEtudiantDto());
    }
}