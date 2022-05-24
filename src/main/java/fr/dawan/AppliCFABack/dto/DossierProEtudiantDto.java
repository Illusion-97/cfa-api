package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class DossierProEtudiantDto implements Serializable {

    private long id;
    private String nom;
    private List<CursusDossierProDto> cursus;

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

    public List<CursusDossierProDto> getCursus() {
        return cursus;
    }

    public void setCursus(List<CursusDossierProDto> cursus) {
        this.cursus = cursus;
    }
}