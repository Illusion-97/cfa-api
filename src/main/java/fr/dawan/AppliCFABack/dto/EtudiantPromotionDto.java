package fr.dawan.AppliCFABack.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EtudiantPromotionDto {
    private long id;
    private String nom;
    private LocalDate dateDebut;
}
