package fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel;

import java.io.Serializable;

public class GetPromotionDossierProDto implements Serializable {

    private long id;
    private GetCursusDossierProDto cursus;
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GetCursusDossierProDto getCursus() {
        return cursus;
    }

    public void setCursus(GetCursusDossierProDto cursus) {
        this.cursus = cursus;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
