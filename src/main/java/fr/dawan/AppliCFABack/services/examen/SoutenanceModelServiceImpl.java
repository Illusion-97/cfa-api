package fr.dawan.AppliCFABack.services.examen;

import fr.dawan.AppliCFABack.dto.customdtos.SoutenanceModelDto;
import fr.dawan.AppliCFABack.entities.examen.SoutenanceModel;
import fr.dawan.AppliCFABack.mapper.SoutenanceModelMapper;
import fr.dawan.AppliCFABack.repositories.SoutenanceModelRepository;
import fr.dawan.AppliCFABack.services.generic.v2.GenericServiceImpl;

public class SoutenanceModelServiceImpl
        extends GenericServiceImpl<SoutenanceModel, SoutenanceModelRepository, SoutenanceModelDto, SoutenanceModelMapper>
        implements SoutenanceModelService{
    public SoutenanceModelServiceImpl(SoutenanceModelRepository repository, SoutenanceModelMapper mapper) {
        super(repository, mapper);
    }
}
