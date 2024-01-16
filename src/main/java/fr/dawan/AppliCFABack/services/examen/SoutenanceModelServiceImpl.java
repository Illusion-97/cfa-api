package fr.dawan.AppliCFABack.services.examen;

import fr.dawan.AppliCFABack.dto.customdtos.SoutenanceModelDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.examen.SoutenanceModel;
import fr.dawan.AppliCFABack.event.cursus.CursusCreatedEvent;
import fr.dawan.AppliCFABack.mapper.SoutenanceModelMapper;
import fr.dawan.AppliCFABack.repositories.SoutenanceModelRepository;
import fr.dawan.AppliCFABack.services.generic.v2.GenericServiceImpl;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SoutenanceModelServiceImpl
        extends GenericServiceImpl<SoutenanceModel, SoutenanceModelRepository, SoutenanceModelDto, SoutenanceModelMapper>
        implements SoutenanceModelService{
    public SoutenanceModelServiceImpl(SoutenanceModelRepository repository, SoutenanceModelMapper mapper) {
        super(repository, mapper);
    }

    @EventListener(CursusCreatedEvent.class)
    public void saveBaseModel(CursusCreatedEvent event) {
        repository.saveAndFlush(SoutenanceModel.getBaseModel(event.getCreated()));
    }
}
