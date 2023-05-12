package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import java.io.Serializable;

public class EtudiantDossierProjetDto implements Serializable {

    private long id;
    private int version;

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
        this.version = ++version;
    }

  
}
