package fr.dawan.AppliCFABack.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;

@Service
public class JourneePlanningServiceImpl implements JourneePlanningService{

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<JourneePlanningDto> getJourneePlanningFromIntervention(Intervention i) {
		List<JourneePlanningDto> result = new ArrayList<JourneePlanningDto>();
		
		LocalDate compteur = i.getDateDebut();
		
		while(compteur.compareTo(i.getDateFin()) <= 0) {
			if(EstJoursOuvrable(compteur)) {
				
				List<FormateurDto> formateurs = new ArrayList<FormateurDto>();
				for(Formateur f : i.getFormateurs()) 
					formateurs.add(mapper.FormateurToFormateurDto(f));
				
				Formation f = i.getFormation();
				
				FormationDto fDto = mapper.FormationToFormationDto(f);
				
				JourneePlanningDto journee = new JourneePlanningDto(compteur, fDto, formateurs);
				journee.setIdIntervention(i.getId());
                
				result.add(journee);
			}
				
			compteur = compteur.plusDays(1);
		}
		
		return result;
	}
	
	
	//Pour déterminer si la date donnée en argulent est un jours ouvrable
	@Override
	public boolean EstJoursOuvrable(LocalDate date)
    {
        return !(EstFerie(date) || EstWeekEnd(date));
    }
	
	//Pour déterminer sila date donnée en argulent est un jours férié
    private boolean EstFerie(LocalDate date)
    {
        List<LocalDate> JoursFeries = new ArrayList<LocalDate>();
        JoursFeries.add(LocalDate.of(date.getYear(),1,1));// 01 Janvier
        JoursFeries.add(LocalDate.of(date.getYear(), 5, 1));// 01 Mai
        JoursFeries.add(LocalDate.of(date.getYear(), 5, 8));// 08 Mai
        JoursFeries.add(LocalDate.of(date.getYear(), 7, 14));// 14 Juillet
        JoursFeries.add(LocalDate.of(date.getYear(), 8, 15));// 15 Aout
        JoursFeries.add(LocalDate.of(date.getYear(), 11, 1));// 01 Novembre
        JoursFeries.add(LocalDate.of(date.getYear(), 11, 11));// 11 Novembre
        JoursFeries.add(LocalDate.of(date.getYear(), 12, 25));// Noël

        //  --  Calcul de pâque
        //  --  Trouver sur internet : 
        JoursFeries.addAll(CalculPaque(date));

        for(LocalDate d : JoursFeries)
            //date.compareTo(date2) : j'ai peur d'avoir des erreurs avec d'éventuelle heures ou autres ?
        	//ça ne devrait pas arriver, mais dans le doute ...
            if (date.getDayOfYear() == d.getDayOfYear()) 
            	return true;

        return false;
    }

	
	//Pour déterminer si week end
    private boolean EstWeekEnd(LocalDate date)
    {
    	//getValue() : @return the day-of-week, from 1 (Monday) to 7 (Sunday)
        if ((int)date.getDayOfWeek().getValue() == 6 || (int)date.getDayOfWeek().getValue()  == 7) 
        	return true;
        else 
        	return false;
    }
	
	//calcul des jours fériés liès à Pâques
    //Méthode trouvée sur Internet
    private List<LocalDate> CalculPaque(LocalDate date)
    {
        //Calcul du jour de pâques (algorithme de Oudin (1940))
        //Calcul du nombre d'or - 1
        int intGoldNumber = (int)(date.getYear() % 19);
        // Année divisé par cent
        int intAnneeDiv100 = (int)(date.getYear() / 100);
        // intEpacte est = 23 - Epacte (modulo 30)
        int intEpacte = (int)((intAnneeDiv100 - intAnneeDiv100 / 4 - (8 * intAnneeDiv100 + 13) / 25 + (
        19 * intGoldNumber) + 15) % 30);
        //Le nombre de jours à partir du 21 mars pour atteindre la pleine lune Pascale
        int intDaysEquinoxeToMoonFull = (int)(intEpacte - (intEpacte / 28) * (1 - (intEpacte / 28) * (29 / (intEpacte + 1)) * ((21 - intGoldNumber) / 11)));
        //Jour de la semaine pour la pleine lune Pascale (0=dimanche)
        int intWeekDayMoonFull = (int)((date.getYear() + date.getYear() / 4 + intDaysEquinoxeToMoonFull +
              2 - intAnneeDiv100 + intAnneeDiv100 / 4) % 7);
        // Nombre de jours du 21 mars jusqu'au dimanche de ou 
        // avant la pleine lune Pascale (un nombre entre -6 et 28)
        int intDaysEquinoxeBeforeFullMoon = intDaysEquinoxeToMoonFull - intWeekDayMoonFull;
        // mois de pâques
        int intMonthPaques = (int)(3 + (intDaysEquinoxeBeforeFullMoon + 40) / 44);
        // jour de pâques
        int intDayPaques = (int)(intDaysEquinoxeBeforeFullMoon + 28 - 31 * (intMonthPaques / 4));
        // lundi de pâques
//        LocalDate dtMondayPaques = new LocalDate(date.getYear(), intMonthPaques, intDayPaques + 1);
        LocalDate dtMondayPaques = LocalDate.of(date.getYear(), intMonthPaques, intDayPaques + 1);
        // Ascension
        LocalDate dtAscension = dtMondayPaques.plusDays(38);
        //Pentecote
        LocalDate dtMondayPentecote = dtMondayPaques.plusDays(49);

        List<LocalDate> JoursPaque = new ArrayList<LocalDate>();
        JoursPaque.add(dtMondayPaques);
        JoursPaque.add(dtAscension);
        JoursPaque.add(dtMondayPentecote);

        return JoursPaque;
    }

}
