package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CompetenceDossierProDto implements Serializable {

    private long id;

    private String libelle;

    private ExperienceProfessionnelleDto experienceProfessionnelle;

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

    public ExperienceProfessionnelleDto getExperienceProfessionnelle() {
        return experienceProfessionnelle;
    }

    public void setExperienceProfessionnelle(ExperienceProfessionnelleDto experienceProfessionnelle) {
        this.experienceProfessionnelle = experienceProfessionnelle;
    }
}
