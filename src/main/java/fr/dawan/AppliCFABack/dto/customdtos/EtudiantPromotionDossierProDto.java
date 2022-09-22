package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;
import java.util.List;

public class EtudiantPromotionDossierProDto implements Serializable {

    private long id;
    private List<PromotionDossierProDto> promotions;
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PromotionDossierProDto> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<PromotionDossierProDto> promotions) {
        this.promotions = promotions;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
