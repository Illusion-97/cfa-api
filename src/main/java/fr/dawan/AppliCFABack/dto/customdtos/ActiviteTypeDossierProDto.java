package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;
import java.util.Set;

public class ActiviteTypeDossierProDto implements Serializable {

    private long id;
    private String libelle;
    private Set<CompetenceDossierProDto> competenceProfessionnelles;

    private int version;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
