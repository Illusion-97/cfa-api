package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import java.io.Serializable;
import java.util.List;

public class EtudiantDossierProjetDto implements Serializable {

    private long id;
    private List<DossierProjetEtudiantDto> dossierProjet;
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DossierProjetEtudiantDto> getDossierProjet() {
        return dossierProjet;
    }

    public void setDossierProjet(List<DossierProjetEtudiantDto> dossierProjet) {
        this.dossierProjet = dossierProjet;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

  
}
