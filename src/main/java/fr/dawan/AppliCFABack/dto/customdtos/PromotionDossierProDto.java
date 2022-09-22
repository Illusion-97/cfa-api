package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;

public class PromotionDossierProDto implements Serializable {

    private long id;
    private CursusPromotionDossierProDto cursus;
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CursusPromotionDossierProDto getCursus() {
        return cursus;
    }

    public void setCursus(CursusPromotionDossierProDto cursus) {
        this.cursus = cursus;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
