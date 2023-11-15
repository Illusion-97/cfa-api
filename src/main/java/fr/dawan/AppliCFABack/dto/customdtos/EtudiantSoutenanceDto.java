package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class EtudiantSoutenanceDto extends BaseEntityDto implements Serializable {


    private UtilisateurDto utilisateurDto;
    private List<PromotionSoutenanceDto> promotionsDto;

    public EtudiantSoutenanceDto() {
    }

    public List<PromotionSoutenanceDto> getPromotionsDto() {
        return promotionsDto;
    }

    public void setPromotionsDto(List<PromotionSoutenanceDto> promotionsDto) {
        this.promotionsDto = promotionsDto;
    }

    public UtilisateurDto getUtilisateurDto() {
        return utilisateurDto;
    }

    public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
        this.utilisateurDto = utilisateurDto;
    }
}
