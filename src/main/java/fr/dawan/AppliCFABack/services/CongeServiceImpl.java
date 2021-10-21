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
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.StatusConge;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
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
	
	@Autowired
	EmailService emailService;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	@Override
	public List<CongeDto> getAllConge() {
		List<Conge> lst = congeRepository.findAll();

		List<CongeDto> lstDto = new ArrayList<CongeDto>();
		for (Conge c : lst) {
			lstDto.add(mapper.CongeToCongeDto(c));
		}
		return lstDto;
	}

	@Override
	public CongeDto getById(long id) {
		Optional<Conge> f = congeRepository.findById(id);
		if (f.isPresent()) {
			CongeDto cDto = mapper.CongeToCongeDto(f.get());
			cDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(f.get().getUtilisateur()));
			return cDto;
		}
		return null;		
	}

	@Override
	public List<CongeDto> getAllByPage(int page, int size, String search) {
		List<Conge> lst = congeRepository.findAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCase(search,search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<CongeDto> lstDto = new ArrayList<CongeDto>();
		for (Conge c : lst) {
			CongeDto cDto = mapper.CongeToCongeDto(c);
			UtilisateurDto uDto = mapper.UtilisateurToUtilisateurDto(c.getUtilisateur());
			cDto.setUtilisateurDto(uDto);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	@Override
	public CongeDto saveOrUpdate(CongeDto cDto) {
		Conge c = DtoTools.convert(cDto, Conge.class);
		
		c = congeRepository.saveAndFlush(c);
		
		//Si on créer le congé et que le status est en attente
		//On envoir un mail au référent pour qu'il gère cette demande
		if(c.getId() == 0 && ( c.getStatus() == null || c.getStatus() == StatusConge.EN_ATTENTE)) {
			emailService.alertDemandeCongetoReferent(c);			
		}				
		
		return mapper.CongeToCongeDto(c);
	}

	@Override
	public void deleteById(long id) {
		//n récupère le conge
		Optional<Conge> c = congeRepository.findById(id);		
		if (!c.isPresent()) return;
		Conge conge = c.get();
		
		//on supprime le lien
		conge.setUtilisateur(null);
		congeRepository.save(conge);
		
		//on delete
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
		
		UtilisateurDto utilisateur = utilisateurService.getById(id);
		
		List<PromotionDto> promos = etudiantService.getPromotionsByIdEtudiant(utilisateur.getEtudiantDto().getId());
		
		if(promos == null)
			return 0;
		
		double result = 0;
		
		for(PromotionDto p : promos) {
			LocalDate date = LocalDate.now();
			
			//On enleve 1 jours car on a pas cotisé le jours courant
			date = date.minusDays(1);
			
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

	@Override
	public CountDto count(String search) {
		return new CountDto(congeRepository.countByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCase(search, search));
	}

	@Override
	public List<CongeDto> getAllByIdUtilisateur(long id) {
		List<CongeDto> result = new ArrayList<CongeDto>();
		List<Conge> list = congeRepository.findAllByUtilisateurId(id);
		for(Conge c : list) {
			result.add(mapper.CongeToCongeDto(c));
		}
		return result;
	}
}
