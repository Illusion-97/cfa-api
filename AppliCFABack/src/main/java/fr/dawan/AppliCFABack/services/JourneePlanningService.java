package fr.dawan.AppliCFABack.services;

import java.util.List;

import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.entities.Intervention;

public interface JourneePlanningService {

	List<JourneePlanningDto> getJourneePlanningFromIntervention(Intervention i);
}
