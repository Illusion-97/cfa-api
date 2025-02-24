package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.StatusConge;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
	
	/**
	 * Récupération de la liste des congés
	 * 
	 * @return lstDto	Liste des objets congés
	 */
	
	@Override
	public List<CongeDto> getAllConge() {
		List<Conge> lst = congeRepository.findAll();

		List<CongeDto> lstDto = new ArrayList<>();
		for (Conge c : lst) {
			lstDto.add(mapper.congeToCongeDto(c));
		}
		return lstDto;
	}

	/**
	 * Récupération des congés en fonction de l'id
	 * 
	 */
	
	@Override
	public CongeDto getById(long id) {
		Optional<Conge> f = congeRepository.findById(id);
		if (f.isPresent()) {
			CongeDto cDto = mapper.congeToCongeDto(f.get());
			cDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(f.get().getUtilisateur()));
			return cDto;
		}
		return null;		
	}
	
	/**
	 * Va permettre de récupérer tous les congés avec pagination
	 * et recherche
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts du congé
	 * @return LstDto Liste des objets congé
	 */
	
	@Override
	public List<CongeDto> getAllByPage(int page, int size, String search) {
		List<Conge> lst = congeRepository.findAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCase(search,search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<CongeDto> lstDto = new ArrayList<>();
		for (Conge c : lst) {
			CongeDto cDto = mapper.congeToCongeDto(c);
			UtilisateurDto uDto = mapper.utilisateurToUtilisateurDto(c.getUtilisateur());
			cDto.setUtilisateurDto(uDto);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	/**
	 * Sauvegarde ou mise à jour d'un congé
	 * 
	 */
	
	@Override
	public CongeDto saveOrUpdate(CongeDto cDto) {
		Conge c = DtoTools.convert(cDto, Conge.class);
		
		c = congeRepository.saveAndFlush(c);
		
		//Si on créer le congé et que le status est en attente
		//On envoir un mail au référent pour qu'il gère cette demande
		if(c.getId() == 0 && ( c.getStatus() == null || c.getStatus() == StatusConge.EN_ATTENTE)) {
			emailService.alertDemandeCongetoReferent(c);			
		}				
		
		return mapper.congeToCongeDto(c);
	}

	/**
	 * Suppression d'un congé
	 * 
	 * @param id	Id concernant le congé
	 */
	
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

	/**
	 * recuperation des congé restant / acquis par id user
	 * 
	 * @param id	Id concernant l'utilisateur
	 * @return result	congé acquis / restant de l'utilisateur
	 */
	
	@Override
	public double[] getAcquisDisponiblesRestantsByIdUtilisateur(long id) {
				
		double[] result = new double[3];
		
		result[0] = getAcquis(id);
		result[1] = getPris(id);
		result[2] = result[0]-result[1];
		
		return result;
	}

	/**
	 * recuperation des congés acquis avec l'id user
	 * 
	 * @param id	Id concernant l'utilisateur
	 * @return result	congé acquis de l'utilisateur
	 */
	
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
			if(date.isBefore(p.getDateDebut()))
				continue;
			
			// date fin < date.now => [dateDebut,dateFin]
			// dateDebut < date.now < dateFin => [dateDebut,date.now]
			if(date.isAfter(p.getDateFin()))
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

	/**
	 * recuperation des congés pris par id user
	 * 
	 * @param id	Id concernant l'utilisateur
	 * @return result	congé pris par l'utilisateur
	 */

	private double getPris(long id) {
		
		List<CongeDto> conges = utilisateurService.getAllCongesByIdUtilisateur(id);
		
		if(conges == null)
			return 0;
				
		double result = 0;
		
		for(CongeDto c : conges) {
			if(c.getStatus() != StatusConge.CONFIRME)
				continue;
			
			LocalDate temp = c.getDateDebut();
			
			while(!temp.isAfter(c.getDateFin())) {
				if(journeePlanningService.estJoursOuvrable(temp))
					result++;
				temp = temp.plusDays(1);
			}
		}
		
		return result;
	}
	
	/**
	 * Recherche d'un congé par nom ou prenom utilisateur
	 * 
	 * @param search recherche par prenom ou nom
	 */
	
	@Override
	public CountDto count(String search) {
		return new CountDto(congeRepository.countByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingIgnoringCase(search, search));
	}

	/**
	 * Récupération des congés en fonction de l'id user
	 * 
	 * @param id	id utilisateur
	 * @return result	Liste des congés concernant l'utilisateur
	 * 
	 */

	@Override
	public List<CongeDto> getAllByIdUtilisateur(long id) {
		List<CongeDto> result = new ArrayList<>();
		List<Conge> list = congeRepository.findAllByUtilisateurId(id);
		for(Conge c : list) {
			result.add(mapper.congeToCongeDto(c));
		}
		return result;
	}
}
