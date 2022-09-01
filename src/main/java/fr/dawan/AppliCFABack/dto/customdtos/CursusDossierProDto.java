package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
public class CursusDossierProDto implements Serializable {

    private long id;
    private String titre;
    private Set<ActiviteTypeDossierProDto> activiteTypes;

    private int version;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Set<ActiviteTypeDossierProDto> getActiviteTypes() {
        return activiteTypes;
    }

    public void setActiviteTypes(Set<ActiviteTypeDossierProDto> activiteTypes) {
        this.activiteTypes = activiteTypes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
