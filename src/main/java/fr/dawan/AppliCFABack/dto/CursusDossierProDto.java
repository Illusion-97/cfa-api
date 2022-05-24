package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class CursusDossierProDto {

    private long id;
    private String titre;
    private List<ActiviteTypeDossierProDto> activites;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<ActiviteTypeDossierProDto> getActivites() {
        return activites;
    }

    public void setActivites(List<ActiviteTypeDossierProDto> activites) {
        this.activites = activites;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
