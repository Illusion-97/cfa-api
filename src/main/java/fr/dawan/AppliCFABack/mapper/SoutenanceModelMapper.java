package fr.dawan.AppliCFABack.mapper;

import fr.dawan.AppliCFABack.dto.customdtos.SoutenanceModelDto;
import fr.dawan.AppliCFABack.entities.examen.SoutenanceModel;
import org.mapstruct.Mapper;

@Mapper
public interface SoutenanceModelMapper extends GenericMapper<SoutenanceModel, SoutenanceModelDto> {
}
