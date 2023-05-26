package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JourneePlanningServiceImpl implements JourneePlanningService{

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la journee de planning en fonction de l'intervention
	 * 
	 * @param i	objet Intervention
	 * @return result	return la journee de planning
	 */
	
	//recuperation de la journée de planning en fonction de l'intervention
	@Override
	public List<JourneePlanningDto> getJourneePlanningFromIntervention(Intervention i) {
		List<JourneePlanningDto> result = new ArrayList<>();
		
		LocalDate compteur = i.getDateDebut();
		
		while(compteur.compareTo(i.getDateFin()) <= 0) {
			if(estJoursOuvrable(compteur)) {
				
								
				FormateurDto formDto = mapper.formateurToFormateurDto(i.getFormateur());
				formDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(i.getFormateur().getUtilisateur()));
				FormateurDto formateur = formDto;
				
				Formation f = i.getFormation();
				
				FormationDto fDto = mapper.formationToFormationDto(f);
				
				JourneePlanningDto journee = new JourneePlanningDto(compteur, fDto, formateur);
				journee.setIdIntervention(i.getId());
                
				result.add(journee);
			}
				
			compteur = compteur.plusDays(1);
		}
		
		return result;
	}
	
	/**
	 * Détermination si la date donnée en argument est un jour ouvrable
	 * 
	 */
	
	@Override
	public boolean estJoursOuvrable(LocalDate date)
    {
        return !(estFerie(date) || estWeekEnd(date));
    }
	
	/**
	 * Détermination si la date donnée en argument est un jours férié
	 * 
	 */
	
    private boolean estFerie(LocalDate date)
    {
        List<LocalDate> joursFeries = new ArrayList<>();
        joursFeries.add(LocalDate.of(date.getYear(),1,1));// 01 Janvier
        joursFeries.add(LocalDate.of(date.getYear(), 5, 1));// 01 Mai
        joursFeries.add(LocalDate.of(date.getYear(), 5, 8));// 08 Mai
        joursFeries.add(LocalDate.of(date.getYear(), 7, 14));// 14 Juillet
        joursFeries.add(LocalDate.of(date.getYear(), 8, 15));// 15 Aout
        joursFeries.add(LocalDate.of(date.getYear(), 11, 1));// 01 Novembre
        joursFeries.add(LocalDate.of(date.getYear(), 11, 11));// 11 Novembre
        joursFeries.add(LocalDate.of(date.getYear(), 12, 25));// Noël

        //  --  Calcul de pâque
        //  --  Trouver sur internet : 
        joursFeries.addAll(calculPaque(date));

        for(LocalDate d : joursFeries)
            //date.compareTo(date2) : j'ai peur d'avoir des erreurs avec d'éventuelle heures ou autres ?
        	//ça ne devrait pas arriver, mais dans le doute ...
            if (date.getDayOfYear() == d.getDayOfYear()) 
            	return true;

        return false;
    }

    /**
	 * Déterminination si c'est le week end
	 * 
	 */
    
	//Pour déterminer si week end
    private boolean estWeekEnd(LocalDate date)
    {
    	//getValue() : @return the day-of-week, from 1 (Monday) to 7 (Sunday)
		return date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7;
    }
	
    /**
	 * Calcul des jours fériés de Pâques
	 * 
	 * @param date	objet date
	 * @return JourPaque	Retourne les jours de pâque férier
	 */
    //Méthode trouvée sur Internet
    private List<LocalDate> calculPaque(LocalDate date)
    {
        //Calcul du jour de pâques (algorithme de Oudin (1940))
        //Calcul du nombre d'or - 1
        int intGoldNumber = date.getYear() % 19;
        // Année divisé par cent
        int intAnneeDiv100 = date.getYear() / 100;
        // intEpacte est = 23 - Epacte (modulo 30)
        int intEpacte = (intAnneeDiv100 - intAnneeDiv100 / 4 - (8 * intAnneeDiv100 + 13) / 25 + (
        19 * intGoldNumber) + 15) % 30;
        //Le nombre de jours à partir du 21 mars pour atteindre la pleine lune Pascale
        int intDaysEquinoxeToMoonFull = intEpacte - (intEpacte / 28) * (1 - (intEpacte / 28) * (29 / (intEpacte + 1)) * ((21 - intGoldNumber) / 11));
        //Jour de la semaine pour la pleine lune Pascale (0=dimanche)
        int intWeekDayMoonFull = (date.getYear() + date.getYear() / 4 + intDaysEquinoxeToMoonFull +
              2 - intAnneeDiv100 + intAnneeDiv100 / 4) % 7;
        // Nombre de jours du 21 mars jusqu'au dimanche de ou 
        // avant la pleine lune Pascale (un nombre entre -6 et 28)
        int intDaysEquinoxeBeforeFullMoon = intDaysEquinoxeToMoonFull - intWeekDayMoonFull;
        // mois de pâques
        int intMonthPaques = 3 + (intDaysEquinoxeBeforeFullMoon + 40) / 44;
        // jour de pâques
        int intDayPaques = intDaysEquinoxeBeforeFullMoon + 28 - 31 * (intMonthPaques / 4);
        // lundi de pâques
        LocalDate dtMondayPaques = LocalDate.of(date.getYear(), intMonthPaques, intDayPaques + 1);
        // Ascension
        LocalDate dtAscension = dtMondayPaques.plusDays(38);
        //Pentecote
        LocalDate dtMondayPentecote = dtMondayPaques.plusDays(49);

        List<LocalDate> joursPaque = new ArrayList<>();
        joursPaque.add(dtMondayPaques);
        joursPaque.add(dtAscension);
        joursPaque.add(dtMondayPentecote);

        return joursPaque;
    }

}
