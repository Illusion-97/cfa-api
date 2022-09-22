package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;

public class CursusPromotionDossierProDto implements Serializable {

    private long id;

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
}
