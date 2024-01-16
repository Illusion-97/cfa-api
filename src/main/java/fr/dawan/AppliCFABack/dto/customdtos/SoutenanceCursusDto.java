package fr.dawan.AppliCFABack.dto.customdtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.dawan.AppliCFABack.entities.Cursus}
 */
@Value
public class SoutenanceCursusDto implements Serializable {
    long id;
    String titre;
    int niveau;
    String sigle;
    String millesime;
    String codeTitre;
}
