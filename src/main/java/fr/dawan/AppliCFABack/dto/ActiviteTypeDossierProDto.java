package fr.dawan.AppliCFABack.dto;

import java.util.Set;

public class ActiviteTypeDossierProDto {

    private long id;
    private String libelle;
    private Set<CompetenceDossierProDto> competenceProfessionnelles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<CompetenceDossierProDto> getCompetenceProfessionnelles() {
        return competenceProfessionnelles;
    }

    public void setCompetenceProfessionnelles(Set<CompetenceDossierProDto> competenceProfessionnelles) {
        this.competenceProfessionnelles = competenceProfessionnelles;
    }
}
