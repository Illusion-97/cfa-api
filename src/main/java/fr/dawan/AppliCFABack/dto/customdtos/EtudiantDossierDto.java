package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;
import java.util.List;

public class EtudiantDossierDto implements Serializable {

    private long id;
    private List<DossierProEtudiantDto> dossierProfessionnel;
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DossierProEtudiantDto> getDossierProfessionnel() {
        return dossierProfessionnel;
    }

    public void setDossierProfessionnel(List<DossierProEtudiantDto> dossierProfessionnel) {
        this.dossierProfessionnel = dossierProfessionnel;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
