package fr.dawan.AppliCFABack.services.dg2Imports;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDG2Dto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.repositories.FormationRepository;
import fr.dawan.AppliCFABack.services.FormationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DG2ImportsFormation extends DG2ImportTools {

    @Autowired
    private FormationRepository formationRepository;

    private static final Logger logger = LoggerFactory.getLogger(FormationServiceImpl.class);

    private void handleDG2Formation(FormationDG2Dto fDtoDG2, Cursus cursusDb, List<Formation> result) {

        Optional<Formation> formationDb = formationRepository.findByIdDg2(fDtoDG2.getId());
        Formation formationImported = new Formation();

        try {
            formationImported = DtoTools.convert(fDtoDG2, Formation.class);
        } catch (Exception e) {
            logger.warn("mapper failed", e);
        }

        if (formationDb.isPresent()) {
            if (formationDb.get().equals(formationImported)) {
                return;
            }

            formationImported.setId(formationDb.get().getId());
            formationImported.setVersion(formationDb.get().getVersion());
        }

        // Sauvegarder la formation importée dans la base de données
        formationImported = formationRepository.saveAndFlush(formationImported);

        // Ajouter la formation importée à la liste de formations du cursus
        cursusDb.getFormations().add(formationImported);

        result.add(formationImported);
    }
}
