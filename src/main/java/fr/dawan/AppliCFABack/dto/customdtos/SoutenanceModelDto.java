package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.entities.examen.SoutenanceModel;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link SoutenanceModel}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class SoutenanceModelDto extends BaseEntityDto implements Serializable {
    SoutenanceCursusDto cursus;
    List<ModuleDto> modules;
}
