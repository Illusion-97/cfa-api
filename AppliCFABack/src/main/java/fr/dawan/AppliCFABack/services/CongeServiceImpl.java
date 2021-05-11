package fr.dawan.AppliCFABack.services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.StatusConge;
import fr.dawan.AppliCFABack.repositories.CongeRepository;

@Service
@Transactional
public class CongeServiceImpl implements CongeService {

	@Autowired
	CongeRepository congeRepository;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	UtilisateurService utilisateurService;

	@Autowired 
	JourneePlanningService journeePlanningService;
	
	@Override
	public List<CongeDto> getAllConge() {
		List<Conge> lst = congeRepository.findAll();

		List<CongeDto> lstDto = new ArrayList<CongeDto>();
		for (Conge c : lst) {
			lstDto.add(DtoTools.convert(c, CongeDto.class));
		}
		return lstDto;
	}

	@Override
	public CongeDto getById(long id) {
		Optional<Conge> f = congeRepository.findById(id);
		if (f.isPresent())
			return DtoTools.convert(f.get(), CongeDto.class);

		return null;
	}

	@Override
	public List<CongeDto> getAllConge(int page, int size) {
		List<Conge> lst = congeRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<CongeDto> lstDto = new ArrayList<CongeDto>();
		for (Conge c : lst) {
			lstDto.add(DtoTools.convert(c, CongeDto.class));
		}
		return lstDto;
	}

	@Override
	public CongeDto saveOrUpdate(CongeDto cDto) {
		Conge c = DtoTools.convert(cDto, Conge.class);
		
		c = congeRepository.saveAndFlush(c);
		
		return DtoTools.convert(c, CongeDto.class);
	}

	@Override
	public void deleteById(long id) {
		congeRepository.deleteById(id);
		
	}

	@Override
	public double[] getAcquisDisponiblesRestantsByIdUtilisateur(long id) {
				
		double[] result = new double[3];
		
		result[0] = getAcquis(id);
		result[1] = getPris(id);
		result[2] = result[0]-result[1];
		
		return result;
	}

	private double getAcquis(long id) {		
		
		List<PromotionDto> promos = etudiantService.getPromotionsByIdEtudiant(id);
		
		if(promos == null)
			return 0;
		
		double result = 0;
		
		for(PromotionDto p : promos) {
			LocalDate date = LocalDate.now();
			
			//On enleve 1 jours car on a pas cotisé le jours courant
			date.plusDays(-1);
			
			//date.now < date debut => pas de calcul
			if(date.compareTo(p.getDateDebut()) < 0)
				continue;
			
			// date fin < date.now => [dateDebut,dateFin]
			// dateDebut < date.now < dateFin => [dateDebut,date.now]
			if(date.compareTo(p.getDateFin()) > 0)
				date = p.getDateFin();
			
				
			//ATTENTION : month-of-year, from 1 to 12
			// acquis = (fin.month-debut.month + 1) * 2.08	
			result += (date.getMonthValue()-p.getDateDebut().getMonthValue() + 1 ) * 2.08;
						
			//si debut.day != first day of month ie si le premier mois n'est pas complet
			//		acquis -= 2.08	on enleve 1 mois du result
			//		acquis += (2.08/nbJoursDansMois)*(dernierjours-datedebut)	on ajoute les jours cotisé
			//			-> distinction jours ouvrés ??
			if(p.getDateDebut().getDayOfMonth() != 1) 
				result -= 2.08 - (2.08/YearMonth.of(p.getDateDebut().getYear(), p.getDateDebut().getMonthValue()).lengthOfMonth()) * (YearMonth.of(p.getDateDebut().getYear(), p.getDateDebut().getMonthValue()).lengthOfMonth()-p.getDateDebut().getDayOfMonth());
			
			//si fin.day != lastday of month	le dernier mois n'est pas complet
			//		acquis -= 2.08	on enleve 1 mois
			//		acquis += (2.08/nbJoursDansMois)*dateFin	on ajoute les jours cotisé
			if(date.getDayOfMonth() != YearMonth.of(date.getYear(), date.getMonthValue()).lengthOfMonth()) 
				result -= 2.08 - (2.08/YearMonth.of(date.getYear(), date.getMonthValue()).lengthOfMonth())*date.getDayOfMonth();			
			
		}
		
		
		return result;
	}

	private double getPris(long id) {
		
		List<CongeDto> conges = utilisateurService.getAllCongesByIdUtilisateur(id);
		
		if(conges == null)
			return 0;
				
		double result = 0;
		
		for(CongeDto c : conges) {
			if(c.getStatus() != StatusConge.CONFIRME)
				continue;
			
			LocalDate temp = c.getDateDebut();
			
			while(temp.compareTo(c.getDateFin()) <= 0) {
				if(journeePlanningService.EstJoursOuvrable(temp))
					result++;
				temp = temp.plusDays(1);
			}
		}
		
		return result;
	}
}
