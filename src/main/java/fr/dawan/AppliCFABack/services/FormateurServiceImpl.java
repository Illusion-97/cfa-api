package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.tools.HashTools;

@Service
@Transactional
public class FormateurServiceImpl implements FormateurService {
	
	@Autowired
	FormateurRepository formateurRepository;
	@Autowired
	InterventionRepository interventionRepository;
	@Autowired
	JourneePlanningService journeePlanningService;
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	/**
	 * Récupération de la liste des formateurs
	 * 
	 * @return lstDto	Liste des objets formateur
	 */
	
	@Override
	public List<FormateurDto> getAll() {
		List<Formateur> lst = formateurRepository.findAll();
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();
		for (Formateur f : lst) {
			lstDto.add(mapper.FormateurToFormateurDto(f));
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer tous les formateurs avec pagination
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @return LstDto Liste des objets formateurs
	 */
	
	
	@Override
	public List<FormateurDto> getAllByPage(int page, int size) {
		List<Formateur> lst = formateurRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();
		for (Formateur f : lst) {
			lstDto.add(mapper.FormateurToFormateurDto(f));
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer tous les formateurs avec pagination
	 * recherche par bom ou prenom
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts de l'utilisateur formateur
	 * @return LstDto Liste des objets formteurs
	 */
	
	
	@Override
	public List<FormateurDto> getAllByPageWithKeyword(int page, int size, String search) {
		List<Formateur> lstFor = formateurRepository
				.findAllByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(search, search, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();

		for (Formateur f : lstFor) {
			lstDto.add(mapper.FormateurToFormateurDto(f));
		}
		return lstDto;
	}

	/**
	 * Récupération des formateurs en fonction de l'id
	 * 
	 * @param id	id du formateur
	 */
	
	@Override
	public FormateurDto getById(long id) {
		Optional<Formateur> f = formateurRepository.findById(id);
		if (f.isPresent()) {
			FormateurDto fDto = mapper.FormateurToFormateurDto(f.get());
			fDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(f.get().getUtilisateur()));
			return fDto;
		}

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'un formateur
	 * 
	 */
	
	@Override
	public FormateurDto saveOrUpdate(FormateurDto fDto) {
		Formateur formateur = DtoTools.convert(fDto, Formateur.class);
		
		if(formateur.getUtilisateur() != null) {
			//HashTools throw Exception
			try {
				//Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
				if(formateur.getUtilisateur().getId() == 0) {
					formateur.getUtilisateur().setPassword(HashTools.hashSHA512(formateur.getUtilisateur().getPassword()));
				}else {
					//Si on a modifié le mdp
					Formateur formateurInDB = formateurRepository.getOne(formateur.getId());
					if(!formateurInDB.getUtilisateur().getPassword().equals(formateur.getUtilisateur().getPassword())) {
						formateur.getUtilisateur().setPassword(HashTools.hashSHA512(formateur.getUtilisateur().getPassword()));
		            }
				}	
			}catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		
		formateur = formateurRepository.saveAndFlush(formateur);
		return mapper.FormateurToFormateurDto(formateur);
	}

	/**
	 * Suppression d'un formateur
	 * 
	 * @param id	Id concernant le formateur
	 */
	
	@Override
	public void deleteById(long id) {
		Optional<Formateur> formateur = formateurRepository.findById(id);
		List<Intervention> lstInterventions = formateur.get().getInterventions();

		if (formateur.isPresent())
			for (Intervention interv : lstInterventions) {
				for (Formateur form : interv.getFormateurs()) {
					if (form.getId() == formateur.get().getId()) {

					}
				}
			}
		formateurRepository.deleteById(id);

	}

	@Override
	public List<FormateurDto> getAllWithObject() {
		List<Formateur> lstFor = formateurRepository.findAll();
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();

		for (Formateur formateur : lstFor) {

			FormateurDto formateurDto = mapper.FormateurToFormateurDto(formateur);

			List<Intervention> lstInter = formateur.getInterventions();
			List<InterventionDto> lstInterDto = new ArrayList<InterventionDto>();
			for (Intervention intervention : lstInter) {
				if (intervention != null)
					lstInterDto.add(mapper.InterventionToInterventionDto(intervention));
			}
			formateurDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(formateur.getUtilisateur()));
			formateurDto.setInterventionsDto(lstInterDto);
			lstDto.add(formateurDto);
		}
		return lstDto;
	}

	/**
	 * Recherche d'un formateur
	 * 
	 * @param search recherche par nom ou prenom
	 */
	
	@Override // nb de formateur
	public CountDto count(String search) {
		return new CountDto(formateurRepository.countByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(search, search));
	}

//	// Recupere les interventions par rapport a l'id du formateur
//	@Override
//	public FormateurDto getInterventionByIdFormateur(long id) {
//		// methode findById dans le repository
//		Optional<Formateur> i = formateurRepository.findById(id);
//		if (i.isPresent()) {
//			// On convertis un Optional<Formateur> en Formateur
//			FormateurDto formateurDto = DtoTools.convert(i.get(), FormateurDto.class);
//
//			// ici je recupere les intervention par rapport au formateur
//			List<Intervention> lstInt = i.get().getInterventions();
//			/**
//			 * les interventions sont de type Intervention. Comme on passe par les Dto on
//			 * doit convertir lstInt qui est de type Intervention en InterventionDto
//			 **/
//			List<InterventionDto> lstIntDto = new ArrayList<InterventionDto>();
//			for (Intervention inter : lstInt) {
//				/**
//				 * La boucle va me permettre de recup les interventionDto du formateur. J'ai
//				 * besoin aussi de recup les formation lié aux intervention du formateur. Pareil
//				 * que par rapport aux intervention, je convertis la Formation en FormationDto
//				 * 
//				 */
//				if (inter != null) {
//					Formation formation = inter.getFormation();
//					FormationDto formationDto = DtoTools.convert(formation, FormationDto.class);
//					InterventionDto interDto = DtoTools.convert(inter, InterventionDto.class);
//					interDto.setFormationDto(formationDto);
//					lstIntDto.add(interDto);
//				}
//			}
//
//			List<UtilisateurRole> lstRole = i.get().getRoles();
//			List<UtilisateurRoleDto> lstRoleDto = new ArrayList<UtilisateurRoleDto>();
//			for (UtilisateurRole role : lstRole) {
//				if (role != null)
//					lstRoleDto.add(DtoTools.convert(role, UtilisateurRoleDto.class));
//			}
//			formateurDto.setRolesDto(lstRoleDto);
//			formateurDto.setInterventionsDto(lstIntDto);
//			return formateurDto;
//		}
//
//		return null;
//	}

	/**
	 * Recuperation des interventions du formateur avec pagination
	 * 
	 * @param id id du formateur
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @return lstInDto Liste des objets intervention
	 */
	
	/** ++++++++++++++ INTERVENTION FORMATEUR ++++++++++++++ **/
	@Override // affiche toute les interventions du formateur
	public List<InterventionDto> getAllInterventionsByFormateurIdPerPage(long id, int page, int size) {
		List<Intervention> lstIn = interventionRepository.findAllByFormateursId(id, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<InterventionDto> lstInDto = new ArrayList<InterventionDto>();
		for (Intervention intervention : lstIn) {
			if (intervention != null) {
				InterventionDto interDto = mapper.InterventionToInterventionDto(intervention);

				FormationDto formDto = mapper.FormationToFormationDto(intervention.getFormation());

				interDto.setFormationDto(formDto);
				lstInDto.add(interDto);
			}
		}
		return lstInDto;
	}

	/**
	 * Recuperation des interventions du formateur avec pagination
	 * plus recherche 
	 * 
	 * @param id id du formateur
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléments de l'intervention
	 * @return lstInDto Liste des objets intervention
	 */
	
	@Override // affiche toute les interventions du formateur + recherche par mot clé
	public List<InterventionDto> getAllInterventionsByFormateurIdPerPageByKeyword(long id, int page, int size,
			String search) {
		List<Intervention> lstIn = interventionRepository
				.findByFormateursIdAndFormationTitreContainingAllIgnoreCase(id, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		List<InterventionDto> lstInDto = new ArrayList<InterventionDto>();
		for (Intervention intervention : lstIn) {
			if (intervention != null) {
				InterventionDto interDto = mapper.InterventionToInterventionDto(intervention);

				FormationDto formDto = mapper.FormationToFormationDto(intervention.getFormation());

				interDto.setFormationDto(formDto);
				lstInDto.add(interDto);
			}
		}
		return lstInDto;
	}

	/**
	 * Nombre d'intervention du formateur avec recherche par mot clé
	 * 
	 * @param id 	id du formateur
	 * @param search	recherche par id / titre de la formation
	 */
	
	@Override
	public CountDto countInterventionById(long id, String search) {
		return new CountDto(
				formateurRepository.countByIdAndInterventionsFormationTitreContainingAllIgnoreCase(id, search));
	}

	/**
	 * Nombre d'intervention du formateur
	 * 
	 * @param id	id du formateur
	 */
	
	@Override // nb interventions du formateur
	public CountDto countInterventionById(long id) {
		return new CountDto(interventionRepository.countByFormateursId(id));
	}

	/**
	 * Planning du formateur
	 * 
	 * @param id	id du formateur
	 * @return journeeDto	liste des objets planning du formateur
	 */
	//planning du formateur
	@Override
	public List<JourneePlanningDto> getAllJourneePlanningByIdFormateur(long id) {
		List<JourneePlanningDto> journeeDto = new ArrayList<JourneePlanningDto>();
		List<Intervention> interventions = new ArrayList<Intervention>();

		interventions.addAll(interventionRepository.findAllByFormateursId(id));
		for (Intervention i : interventions)
			journeeDto.addAll(journeePlanningService.getJourneePlanningFromIntervention(i));

		return journeeDto;
	}

}
