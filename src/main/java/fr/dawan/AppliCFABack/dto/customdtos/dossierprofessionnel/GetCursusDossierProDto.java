package fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel;

import java.io.Serializable;
import java.util.Set;

public class GetCursusDossierProDto implements Serializable {

    private long id;
    private String titre;
    private Set<GetActiviteDossierProDto> activiteTypes;
    private DossierProEtudiantDto dossierProfessionnel;
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DossierProEtudiantDto getDossierProfessionnel() {
        return dossierProfessionnel;
    }

    public void setDossierProfessionnel(DossierProEtudiantDto dossierProfessionnel) {
        this.dossierProfessionnel = dossierProfessionnel;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Set<GetActiviteDossierProDto> getActiviteTypes() {
        return activiteTypes;
    }

    public void setActiviteTypes(Set<GetActiviteDossierProDto> activiteTypes) {
        this.activiteTypes = activiteTypes;
    }
}
