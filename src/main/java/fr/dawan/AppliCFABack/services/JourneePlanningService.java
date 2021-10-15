package fr.dawan.AppliCFABack.services;

import java.time.LocalDate;
import java.util.List;

import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.entities.Intervention;

public interface JourneePlanningService {

	List<JourneePlanningDto> getJourneePlanningFromIntervention(Intervention i);
	public boolean EstJoursOuvrable(LocalDate date);
}
