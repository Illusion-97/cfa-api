package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;
import java.util.List;

import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;

@SuppressWarnings("serial")
public class CompetenceDossierProDto implements Serializable {

    private long id;

    private String libelle;

    private int version;

    private List<ExperienceProfessionnelleDto> experienceProfessionnelles;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<ExperienceProfessionnelleDto> getExperienceProfessionnelles() {
        return experienceProfessionnelles;
    }

    public void setExperienceProfessionnelles(List<ExperienceProfessionnelleDto> experienceProfessionnelles) {
        this.experienceProfessionnelles = experienceProfessionnelles;
    }
}
