package fr.dawan.AppliCFABack.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EtudiantUtilisateurDto {
    private List<EtudiantPromotionDto> promotions;

}
