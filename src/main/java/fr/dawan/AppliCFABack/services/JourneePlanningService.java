package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.entities.Intervention;

import java.time.LocalDate;
import java.util.List;

public interface JourneePlanningService {

	List<JourneePlanningDto> getJourneePlanningFromIntervention(Intervention i);
	/**
	 * Détermination si la date donnée en argument est un jour ouvrable
	 * 
	 */
	boolean estJoursOuvrable(LocalDate date);
}
