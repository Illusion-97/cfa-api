package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;
import java.util.List;

import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;

@SuppressWarnings("serial")
public class DossierProEtudiantDto implements Serializable {

    private long id;
    private String nom;
    private CursusDossierProDto cursus;
    private List<ExperienceProfessionnelleDto> experienceProfessionnelles;
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CursusDossierProDto getCursus() {
        return cursus;
    }

    public void setCursus(CursusDossierProDto cursus) {
        this.cursus = cursus;
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