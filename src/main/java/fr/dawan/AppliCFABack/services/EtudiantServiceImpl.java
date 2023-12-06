package fr.dawan.AppliCFABack.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.dto.customdtos.AccueilEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantAbsencesDevoirsDto;
import fr.dawan.AppliCFABack.dto.customdtos.EtudiantInfoInterventionDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.EtudiantDossierDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.EtudiantDossierProjetDto;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.entities.LivretEvaluation.EtatLivertEval;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import fr.dawan.AppliCFABack.tools.HashTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	ProjetRepository projetRepository;
	@Autowired
	DossierProjetRepository dossierProRepo;
	
	@Autowired 
	DossierProfessionnelRepository dossierRepo;
	
	@Autowired
	TuteurRepository tuteurRepository;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	InterventionRepository interventionRepository;

	@Autowired
	GroupeEtudiantRepository groupeEtudiantRepository;

	@Autowired
	PromotionRepository promotionRepository;

	@Autowired
	FormateurRepository formateurRepository;

	@Autowired
	JourneePlanningService journeePlanningService;

	@Autowired
	DevoirRepository devoirRepository;

	@Autowired
	ExamenRepository examenRepository;
	@Autowired
	UtilisateurRoleRepository utilisateurRoleRepository;
	@Autowired
	private AbsenceRepository absenceRepository;

	@Autowired
	private DevoirEtudiantRepository devoirEtudiantRepository;

	@Autowired
	private PositionnementRepository positionnementRepository;

	@Autowired
	private BlocEvaluationRepository blocEvaluationRepository;
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Autowired
	private DtoTools mapperTools;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(EtudiantServiceImpl.class);

	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private LivretEvaluationRepository livretEvaluationRepository;
	
	@Value("${base_url_dg2}")
    private String baseUrl;
	
	// ##################################################
	// # CRUD #
	// ##################################################

	/**
	 * Récupération de la liste des etudiants
	 *
	 * @return res Liste des objets etudiants
	 */
	@Override
	public List<EtudiantDto> getAll(){
		List<Etudiant> lst = etudiantRepository.findAll();
		List<EtudiantDto> res = new ArrayList<>();

		for (Etudiant e : lst) {
			EtudiantDto etuDto = mapper.etudiantToEtudiantDto(e);
			UtilisateurDto utilisateur = mapper.utilisateurToUtilisateurDto(e.getUtilisateur());

			etuDto.setUtilisateurDto(utilisateur);

			if (e != null && e.getUtilisateur() != null && e.getUtilisateur().getAdresse() != null) {
			    AdresseDto addrDto = mapper.adresseToAdresseDto(e.getUtilisateur().getAdresse());
			
//			EntrepriseDto entDto = mapper.EntrepriseToEntrepriseDto(e.getUtilisateur().getEntreprise());

			List<GroupeEtudiant> lstGrpEtu = e.getGroupes();
			List<GroupeEtudiantDto> lstGrpEtuDto = new ArrayList<>();
			for (GroupeEtudiant grp : lstGrpEtu) {
				if (grp != null)
					lstGrpEtuDto.add(mapper.groupeEtudiantToGroupEtudiantDto(grp));
			}

			List<Promotion> lstPromo = e.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(mapper.promotionToPromotionDto(promotion));
			}
//			List<DossierProjet> dpEtu = dossierProRepo.findByIdEtudiant(id);
//			List<DossierProjetDto> lstDossierProjetDto = new ArrayList<>();
//			for (DossierProjet dp : dpEtu) {
//				DossierProjetDto dpdto = mapper.dossierProjetToDossierProjetDto(dp);
//				dpdto.setProjet(mapper.projetToProjetDto(dp.getProjet()));
//				lstDossierProjetDto.add(dpdto);

//			}

			List<UtilisateurRoleDto> uRDto = new ArrayList<>();
			for (UtilisateurRole r : e.getUtilisateur().getRoles()) {
				uRDto.add(mapper.utilisateurRoleToUtilisateurRoleDto(r));
			}

			etuDto.getUtilisateurDto().setRolesDto(uRDto);

			/*List<DossierProfessionnel> lstDossierProfessionnel = e.getDossierProfessionnel();
			List<DossierProfessionnelDto> lstDossierProfessionnelDto = new ArrayList<>();
			for (DossierProfessionnel dp : lstDossierProfessionnel) {
				DossierProfessionnelDto dpDto = mapper.dossierProfessionnelToDossierProfessionnelDto(dp);
				dpDto.setCursusDto(mapper.cursusToCursusDto(dp.getCursus()));
				lstDossierProfessionnelDto.add(dpDto);
			}*/

//			UtilisateurDto refDto = mapper.UtilisateurToUtilisateurDto(e.getFormateurReferent());

			etuDto.getUtilisateurDto().setAdresseDto(addrDto);
			etuDto.setGroupesDto(lstGrpEtuDto);
//			etuDto.getUtilisateurDto().setEntrepriseDto(entDto);
			etuDto.setPromotionsDto(lstPromoDto);
//			etuDto.setFormateurReferentDto(refDto);
			//etuDto.setDossierProfessionnel(lstDossierProfessionnelDto);
//			etuDto.setDossierProjet(lstDossierProjetDto);

			res.add(etuDto);
		}
		}
		for (Etudiant e : lst){
			res.add(mapper.etudiantToEtudiantDto(e));
		}
		return res;
	}

	/**
	 * Récupération des etudiants en fonction de l'id
	 *
	 * @param id id de l'étudiant
	 * @return eDto objet Etudiant
	 */

	@Override
	public EtudiantDto getById(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);

		if (!e.isPresent())
			return null;

		EtudiantDto eDto = mapper.etudiantToEtudiantDto(e.get());
		eDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(e.get().getUtilisateur()));
		eDto.getUtilisateurDto().setAdresseDto(mapper.adresseToAdresseDto(e.get().getUtilisateur().getAdresse()));
//		eDto.setFormateurReferentDto(mapper.UtilisateurToUtilisateurDto(e.get().getFormateurReferent()));
//		eDto.setManagerDto(mapper.UtilisateurToUtilisateurDto(e.get().getManager()));

		List<GroupeEtudiantDto> groupes = new ArrayList<>();
		for (GroupeEtudiant g : e.get().getGroupes()) {
			groupes.add(mapper.groupeEtudiantToGroupEtudiantDto(g));
		}
		eDto.setGroupesDto(groupes);

		List<UtilisateurRoleDto> uRDto = new ArrayList<>();
		for (UtilisateurRole r : e.get().getUtilisateur().getRoles()) {
			uRDto.add(mapper.utilisateurRoleToUtilisateurRoleDto(r));
		}
		eDto.getUtilisateurDto().setRolesDto(uRDto);

		List<PromotionDto> promotions = new ArrayList<>();
		for (Promotion p : e.get().getPromotions()) {
			PromotionDto pDto = mapper.promotionToPromotionDto(p);
			List<InterventionDto> lstIDto = new ArrayList<>();
			for (Intervention i : p.getInterventions()) {
				InterventionDto iDto = mapper.interventionToInterventionDto(i);
				lstIDto.add(iDto);
			}
			pDto.setInterventionsDto(lstIDto);
			promotions.add(pDto);
		}
		eDto.setPromotionsDto(promotions);
		List<DossierProjet> dpEtu = dossierProRepo.findByIdEtudiant(id);
		List<DossierProjetDto> lstDossierProjetDto = new ArrayList<>();
		for (DossierProjet dp : dpEtu) {
			DossierProjetDto dpdto = mapper.dossierProjetToDpDto(dp);
			lstDossierProjetDto.add(dpdto);
		}
		List<DossierProfessionnel> lstDossierProfessionnel = dossierRepo.findDossierProByEtudiantIdAndCursusId(id);
		List<DossierProfessionnelDto> lstDossierProfessionnelDto = new ArrayList<>();
		for (DossierProfessionnel dp : lstDossierProfessionnel) {
			DossierProfessionnelDto dpDto = mapper.dossierProfessionnelToDossierProfessionnelDto(dp);
			dpDto.setCursusDto(mapper.cursusToCursusDto(dp.getCursus()));
			lstDossierProfessionnelDto.add(dpDto);
		}
		eDto.setDossierProfessionnel(lstDossierProfessionnelDto);
		eDto.setDossierProjet(lstDossierProjetDto);

		return eDto;
	}

	/**
	 * Sauvegarde ou mise à jour d'un etudiant
	 *
	 */

	@Override
	public EtudiantDto saveOrUpdate(EtudiantDto e) {
		Etudiant etudiant = DtoTools.convert(e, Etudiant.class);

		if (etudiant.getUtilisateur() != null) {
			// HashTools throw Exception
			try {
				// Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
				if (etudiant.getUtilisateur().getId() == 0) {
					etudiant.getUtilisateur()
							.setPassword(HashTools.hashSHA512(etudiant.getUtilisateur().getPassword()));
				} else {
					// Si on a modifié le mdp
					Etudiant etudiantInDB = etudiantRepository.getOne(etudiant.getId());
					if (!etudiantInDB.getUtilisateur().getPassword().equals(etudiant.getUtilisateur().getPassword())) {
						etudiant.getUtilisateur()
								.setPassword(HashTools.hashSHA512(etudiant.getUtilisateur().getPassword()));
					}
				}
			} catch (Exception ex) {
				logger.warn("Error save etudiant", ex);
			}

			// Pour le dossier
			Path path = Paths.get("./src/main/resources/files/utilisateurs/" + etudiant.getUtilisateur().getId());

			try {
				Files.createDirectories(path);
			} catch (IOException ex) {
				logger.warn("Error creation du dossier", ex);
			}
		}

		etudiant = etudiantRepository.saveAndFlush(etudiant);

		return mapper.etudiantToEtudiantDto(etudiant);
	}

	/**
	 * Suppression d'un etudiant
	 *
	 * @param id Id concernant l'etudiant
	 */
	@Override
	public void deleteById(long id) {

//		Etudiant etudiant = getEtudiantById(id);

//		if (etudiant == null)
//			return;
//		
//		Utilisateur Utilisateur = etudiant.getUtilisateur();
//		etudiant.setUtilisateur(null);
//		Utilisateur.setEtudiant(null);
//		
//		etudiantRepository.save(etudiant);
//		utilisateurRepository.save(Utilisateur);
//
//		// Etudiant
//		// @ManyToMany Promotion promotions
//		// @ManyToMany GroupeEtudiant groupes
//
//		// On récupère ces objets, et on supprime les liens :
//
//		List<Promotion> promotions = etudiant.getPromotions();
//		List<GroupeEtudiant> groupes = etudiant.getGroupes();
//		etudiant.setGroupes(null);
//		etudiant.setPromotions(null);
//
//		etudiantRepository.save(etudiant);
//
//		Etudiant toDelete = new Etudiant();
//
//		for (Promotion p : promotions) {
//			for (Etudiant e : p.getEtudiants())
//				if (e.getUtilisateur().getId() == etudiant.getUtilisateur().getId()) {
//					toDelete = e;
//					break;
//				}
//			p.getEtudiants().remove(toDelete);
//			promotionRepository.save(p);
//		}
//
//		for (GroupeEtudiant g : groupes) {
//			for (Etudiant e : g.getEtudiants())
//				if (e.getUtilisateur().getId() == etudiant.getUtilisateur().getId()) {
//					toDelete = e;
//					break;
//				}
//			g.getEtudiants().remove(toDelete);
//			groupeEtudiantRepository.save(g);
//		}
//
//		// Note
//		// @ManyToOne Etudiant etudiant
//
//		// Absence
//		// @ManyToOne Etudiant etudiant
//
//		// On récupère ces objets, et on supprime les liens :
//
//		List<Note> notes = noteRepository.getNotesByIdEtudiant(id);
//		List<Absence> absences = absenceRepository.getAbsencesByIdEtudiant(id);
//
//		for (Note n : notes) {
////			n.setEtudiant(null);
//			noteRepository.save(n);
//			noteRepository.delete(n);
//		}
//
//		for (Absence a : absences) {
//			a.setEtudiant(null);
//			absenceRepository.save(a);
//			absenceRepository.delete(a);
//		}
//
//		// Les liens sont tous supprimés : on peut supprimé l'étudiant
//
		etudiantRepository.deleteById(id);

	}

	// ##################################################
	// # 1er Niveau #
	// ##################################################

	/**
	 * Récupération des promotions en fonction de l'id etudiant
	 *
	 * @param id id de l'etudiant
	 * @return lstDto liste des promotions de l'étudiant
	 */

	@Override
	public List<PromotionDto> getPromotionsByIdEtudiant(long id) {
		List<Promotion> lst = new ArrayList<>();
		try {
			lst = getEtudiantById(id).getPromotions();
		} catch (Exception e) {
			logger.error("getEtudiantById(id).getPromotions() failed", e);
		}
		List<PromotionDto> lstDto = new ArrayList<>();

		for (Promotion p : lst) {
			// Quand on convertit en Dto, on perd les objets contenu
			// On récupère le referent pedagogiue que l'on redonne a promotionDto

			UtilisateurDto referentPedagogique = mapper.utilisateurToUtilisateurDto(p.getReferentPedagogique());
			PromotionDto promotion = mapper.promotionToPromotionDto(p);

			promotion.setReferentPedagogiqueDto(referentPedagogique);

			lstDto.add(promotion);
		}

		return lstDto;
	}

	/**
	 * Récupération des groupes en fonction de l'id etudiant
	 *
	 * @param id id de l'etudiant
	 * @return lstDto liste des groupes de l'étudiant
	 */
	@Override
	public List<GroupeEtudiantDto> getGroupesByIdEtudiant(long id) {
		List<GroupeEtudiant> lst = new ArrayList<>();
		try {
			lst = getEtudiantById(id).getGroupes();
		} catch (Exception ex) {
			logger.error("getEtudiantById(id).getGroupes() failed", ex);
		}
		List<GroupeEtudiantDto> lstDto = new ArrayList<>();
		for (GroupeEtudiant g : lst) {
			GroupeEtudiantDto gDto = mapper.groupeEtudiantToGroupEtudiantDto(g);
			List<EtudiantDto> eDtos = new ArrayList<>();
			for (Etudiant e : g.getEtudiants()) {
				EtudiantDto etudiantDto = mapper.etudiantToEtudiantDto(e);
				etudiantDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(e.getUtilisateur()));
				eDtos.add(etudiantDto);
			}
			gDto.setEtudiantsDto(eDtos);
			lstDto.add(gDto);
		}

		return lstDto;
	}
	/**
	 * Récupération des projets affectés à l'étudiant
	 *
	 * @param id id de l'etudiant
	 */
	@Override
	public List<ProjetDto> getProjetByIdEtudiant(long id) {
		List<Projet> lstProjets = new ArrayList<>();
		List<GroupeEtudiantDto> lstGroupesEtuDto = getGroupesByIdEtudiant(id);
		for (GroupeEtudiantDto gpe : lstGroupesEtuDto) {
			GroupeEtudiant gp = mapper.groupeEtudiantDtoToGroupEtudiant(gpe);
			List<Projet> projets = projetRepository.findAllByGroupeId(gp.getId());
			lstProjets.addAll(projets);
		}
		return mapper.listProjettoListProjetDto(lstProjets);
	}
	/**
	 * Récupération de l'adresse de l'étudiant
	 *
	 * @param id id de l'etudiant
	 */

	@Override
	public AdresseDto getAdresseByIdEtudiant(long id) {
		return mapper.adresseToAdresseDto(getEtudiantById(id).getUtilisateur().getAdresse());
	}

	// ##################################################
	// # 2eme Niveau #
	// ##################################################

	// recuperation des notes par id etudiants + pagination
	@Override
	public List<NoteDto> getNotesByIdEtudiant(long id, int page, int size) {

//		List<Note> lst = noteRepository.getNotesByIdEtudiant(id, PageRequest.of(page, size)).get()
//				.collect(Collectors.toList());
//		List<NoteDto> res = new ArrayList<NoteDto>();
//
//		for (Note n : lst) {
//			NoteDto nDto =mapper.NoteToNoteDto(n);
////			nDto.setDevoirDto(mapper.DevoirToDevoirDto(n.getDevoir()));
////			nDto.setExamenDto(mapper.PassageExamenToPassageExamenDto(n.getExamen()));
////			nDto.getExamenDto().setExamenDto(mapper.ExamenToExamenDto(n.getExamen().getExamen()));
//
//
//			res.add(nDto);
//		}
//

		return null;
	}


	// ##################################################
	// # 3eme Niveau #
	// ##################################################

	/**
	 * Récupération des interventions en fonction de l'id etudiant
	 *
	 * @param id id de l'etudiant
	 * @return res liste des interventions de l'étudiant
	 */

	@Override
	public List<InterventionDto> getInterventionByIdEtudiant(long id) {
		List<Intervention> interventions = new ArrayList<>();
		List<InterventionDto> res = new ArrayList<>();

		for (Promotion p : getEtudiantById(id).getPromotions())
			interventions.addAll(interventionRepository.getInterventionsByIdPromotion(p.getId()));

		for (Intervention i : interventions)
			res.add(mapper.interventionToInterventionDto(i));

		return res;
	}

	// ##################################################
	// # UTILE #
	// ##################################################
	/**
	 * Récupération de l'etudiants en fonction de l'id etudiant
	 *
	 * @param id id de l'etudiant
	 */
	private Etudiant getEtudiantById(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);
		if (e.isPresent())
			return e.get();
		return null;
	}

	/**
	 * Récupération du planning en fonction de l'id etudiant
	 *
	 * @param id id de l'etudiant
	 * @return result planning de l'étudiant
	 */
	@Override
	public List<JourneePlanningDto> getAllJourneePlanningByIdEtudiant(long id) {
		List<JourneePlanningDto> result = new ArrayList<>();

		List<Intervention> interventions = new ArrayList<>();

		// On récupère l'étudiant
		Etudiant e = getEtudiantById(id);

		List<Promotion> promotions = e.getPromotions();

		// on récupère toutes les interventions de l'étudiant.
		for (Promotion p : promotions)
			interventions.addAll(interventionRepository.getInterventionsByIdPromotion(p.getId()));

		// pour chaque intervention, on récupère les JourneePlannignDto
		for (Intervention i : interventions)
			result.addAll(journeePlanningService.getJourneePlanningFromIntervention(i));
		return result;
	}

	/**
	 * Va permettre de récupérer tous les devoirs avec pagination
	 *
	 * @param id   id de l'étudiant
	 * @param page numero de la page
	 * @param size éléments sur la page
	 * @return res Liste des devoirs de l'étudiants
	 */
	@Override
	public List<DevoirDto> getDevoirsByIdEtudiant(long id, int page, int size) {

		List<Devoir> lst = devoirRepository.findAllByInterventionPromotionsEtudiantsId(id, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		List<DevoirDto> res = new ArrayList<>();

		for (Devoir d : lst) {
			DevoirDto dDto = DtoTools.convert(d, DevoirDto.class);

			res.add(dDto);
		}

		return res;
	}

	@Override
	public EtudiantDossierDto getByEtudiantIdForDossierPro(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);
		if (!e.isPresent())
			return null;
		return DtoTools.convert(e.get(), EtudiantDossierDto.class);
	}

	@Override
	public EtudiantDossierDto saveOrUpdateEtudiantDossier(EtudiantDossierDto e) {
		Etudiant etudiant = DtoTools.convert(e, Etudiant.class);

		if (etudiant.getUtilisateur() != null) {
			// HashTools throw Exception
			try {
				// Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
				if (etudiant.getUtilisateur().getId() == 0) {
					etudiant.getUtilisateur()
							.setPassword(HashTools.hashSHA512(etudiant.getUtilisateur().getPassword()));
				} else {
					// Si on a modifié le mdp
					Etudiant etudiantInDB = etudiantRepository.getOne(etudiant.getId());
					if (!etudiantInDB.getUtilisateur().getPassword().equals(etudiant.getUtilisateur().getPassword())) {
						etudiant.getUtilisateur()
								.setPassword(HashTools.hashSHA512(etudiant.getUtilisateur().getPassword()));
					}
				}
			} catch (Exception ex) {
				logger.warn("Error save dossier etudiant", ex);
			}

			// Pour le dossier
			Path path = Paths.get("./src/main/resources/files/utilisateurs/" + etudiant.getUtilisateur().getId());

			try {
				Files.createDirectories(path);
			} catch (IOException ex) {
				logger.warn("Error dossier create", ex);
			}
		}

		etudiant = etudiantRepository.saveAndFlush(etudiant);

		return DtoTools.convert(etudiant, EtudiantDossierDto.class);
	}

	/**
	 * Recherche d'un etudiant
	 *
	 * @param search recherche par prenom / nom / promotion / groupe
	 */

	@Override
	public CountDto count(String search) {
		return new CountDto(etudiantRepository
				.countDistinctByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingOrPromotionsNomContainingOrGroupesNomContaining(
						search, search, search, search));
	}

	/**
	 * Va permettre de récupérer tous les etudiants avec pagination recherche par
	 * nom / prenom / promo / groupe
	 *
	 * @param page   numero de la page
	 * @param size   éléments sur la page
	 * @param search éléments etudiant (nom,prenom,groupe,promo)
	 * @return res Liste des objets etudiant
	 */

	@Override
	public List<EtudiantDto> getAllByPage(int page, int size, String search) {
		List<Etudiant> lstStud = etudiantRepository
				.findDistinctAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingOrPromotionsNomContainingOrGroupesNomContaining(
						search, search, search, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		List<EtudiantDto> res = new ArrayList<>();

		for (Etudiant e : lstStud) {
			EtudiantDto etuDto = mapper.etudiantToEtudiantDto(e);
			UtilisateurDto pDto = mapper.utilisateurToUtilisateurDto(e.getUtilisateur());
			AdresseDto addrDto = mapper.adresseToAdresseDto(e.getUtilisateur().getAdresse());

			List<GroupeEtudiant> lstGrpEtu = e.getGroupes();
			List<GroupeEtudiantDto> lstGrpEtuDto = new ArrayList<>();
			for (GroupeEtudiant grp : lstGrpEtu) {
				if (grp != null)
					lstGrpEtuDto.add(mapper.groupeEtudiantToGroupEtudiantDto(grp));
			}

			List<Promotion> lstPromo = e.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(mapper.promotionToPromotionDto(promotion));
			}

			etuDto.setUtilisateurDto(pDto);
			etuDto.getUtilisateurDto().setAdresseDto(addrDto);
			etuDto.setGroupesDto(lstGrpEtuDto);

			etuDto.setPromotionsDto(lstPromoDto);

			res.add(etuDto);
		}

		return res;
	}

	@Override
	public List<EtudiantAbsencesDevoirsDto> getEtudiantsByInterventionId(long idIntervention, String search) {

		List<EtudiantAbsencesDevoirsDto> etudiantAbsencesDevoirsDtos = new ArrayList<>();
		List<Etudiant> etudiants = etudiantRepository.findAllDistinctByPromotionsInterventionsId(idIntervention,
				search);
		for (Etudiant etudiant : etudiants) {

			EtudiantAbsencesDevoirsDto etudiantAbsencesDevoirsDto = new EtudiantAbsencesDevoirsDto();

			Optional<Promotion> promotion = promotionRepository.getByEtudiantsIdAndInterventionsId(etudiant.getId(),
					idIntervention);

			EtudiantInfoInterventionDto aInfoIdto = DtoTools.convert(etudiant, EtudiantInfoInterventionDto.class);

			if (promotion.isPresent()) {

				aInfoIdto.setNomCentreFormation(promotion.get().getCentreFormation().getNom());
			}
			etudiantAbsencesDevoirsDto.setEtudiant(aInfoIdto);

			Optional<Positionnement> positionnement = positionnementRepository
					.getDistinctByInterventionIdAndEtudiantId(idIntervention, etudiant.getId());

			if (positionnement.isPresent()) {
				Positionnement.Niveau nDebut = positionnement.get().getNiveauDebut();
				Positionnement.Niveau nFin = positionnement.get().getNiveauFin();
				DtoTools mappeur = new DtoTools();
				etudiantAbsencesDevoirsDto.setPositionnement(DtoTools.convert(positionnement, PositionnementDto.class));
				etudiantAbsencesDevoirsDto.setNiveauDebut(mappeur.niveauToNiveauDto(nDebut));
				etudiantAbsencesDevoirsDto.setNiveauFin(mappeur.niveauToNiveauDto(nFin));
			}

			List<Absence> absences = absenceRepository.findAllByEtudiantIdAndInterventionId(etudiant.getId(),
					idIntervention);

			if (!absences.isEmpty() || absences != null) {
				List<AbsenceDto> absencesDto = new ArrayList<>();
				for (Absence absence : absences) {
					absencesDto.add(DtoTools.convert(absence, AbsenceDto.class));
				}
				etudiantAbsencesDevoirsDto.setAbsences(absencesDto);

			}
			List<DevoirEtudiant> devoirsEtudiant = devoirEtudiantRepository
					.getAllDevoirsEtudiantByInterventionId(idIntervention, etudiant.getId());

			if (!devoirsEtudiant.isEmpty() || devoirsEtudiant != null) {

				List<DevoirEtudiantDto> devoirsEDto = new ArrayList<>();

				for (DevoirEtudiant dE : devoirsEtudiant) {
					devoirsEDto.add(DtoTools.convert(dE, DevoirEtudiantDto.class));
				}
				etudiantAbsencesDevoirsDto.setDevoirs(devoirsEDto);
			}

			etudiantAbsencesDevoirsDtos.add(etudiantAbsencesDevoirsDto);

		}

		return etudiantAbsencesDevoirsDtos;
	}

	@Async("myTaskExecutor")
	public void fetchAllEtudiantDG2(String email, String password) throws Exception {
		List<Promotion> promotions = promotionRepository.findAll();

		for (Promotion promotion : promotions) {

			fetchAllEtudiantDG2ByIdPromotion(email, password, promotion.getIdDg2());
		}

	}

	@Async("myTaskExecutor")
	@Override
	public void fetchAllEtudiantDG2ByIdPromotion(String email, String password, long idPromotionDg2)
			throws FetchDG2Exception, JsonProcessingException, URISyntaxException {
		List<Etudiant> saved = new ArrayList<>();
		Optional<Promotion> optionnalPromotion = promotionRepository.findByIdDg2(idPromotionDg2);

		if (!optionnalPromotion.map(promotion -> {
			logger.info(">>>>>>>promo>>>>" + promotion.getIdDg2());
			logger.info("FetchDg2Etudiant >>> START");
			ObjectMapper objectMapper = new ObjectMapper();
			List<EtudiantUtilisateurDG2Dto> cResJson = new ArrayList<>();

			String url =  baseUrl + "sessions/" + idPromotionDg2 + "/registrations";

			HttpHeaders headers = new HttpHeaders();
			headers.add("x-auth-token", email + ":" + password);

			HttpEntity<String> httpEntity = new HttpEntity<>(headers);

			ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			logger.info("FetchDg2Etudiant >>> START /registration");

			if (repWs.getStatusCode() == HttpStatus.OK) {
				logger.info("FetchDg2Etudiant >>> START /registration OK");
				String json = repWs.getBody();
				// importUserFromJson(json, promotion);

				try {
					cResJson = objectMapper.readValue(json, new TypeReference<List<EtudiantUtilisateurDG2Dto>>() {
					});
				} catch (Exception ignored) {
				}
				for (EtudiantUtilisateurDG2Dto eDG2 : cResJson) {
					logger.info("FetchDg2Etudiant >>> START /for" + eDG2.getPersonId());

					Optional<Utilisateur> utiLisateurOptional = utilisateurRepository
							.findDistinctByIdDg2(eDG2.getPersonId());
					Etudiant etudiant = null;
					Utilisateur utilisateur = null;
					if (utiLisateurOptional.isPresent()) { //utilisateur existant, on cherche l'étudiant
						etudiant = etudiantRepository.findByUtilisateurId(utiLisateurOptional.get().getId());
						utilisateur = utiLisateurOptional.get();
					} else { //utilisateur non existant => on le crée
						utilisateur = mapper.etudiantUtilisateurDG2DtoToUtilisateur(eDG2);
						Adresse userAdresse = utilisateur.getAdresse();
						Adresse adresseDg2 = mapper.etudiantUtilisateurDG2DtoToAdresse(eDG2);
						if (userAdresse != null) {
							adresseDg2.setId(utilisateur.getAdresse().getId());
							adresseDg2.setVersion(utilisateur.getAdresse().getVersion());
							adresseRepository.saveAndFlush(adresseDg2);
						} else {
							adresseDg2 = adresseRepository.saveAndFlush(adresseDg2);
							utilisateur.setAdresse(adresseDg2);
						}
					}
					
					//modif du rôle
					List<UtilisateurRole> roles = utilisateur.getRoles() == null ? new ArrayList<>()
							: utilisateur.getRoles();
					if (roles.stream().noneMatch(r -> r.getId() == 1L)) {
						UtilisateurRole r = new UtilisateurRole();
						r.setId(1L);
						roles.add(r);
					}
					utilisateur.setRoles(roles);
					utilisateur.setActive(true);
					utilisateur.setPassword(null);
					utilisateurRepository.saveAndFlush(utilisateur);

					final Utilisateur util = utilisateur;
					if (etudiant == null) {
						etudiant = saved.stream().filter(e -> e.getUtilisateur().getIdDg2() == util.getIdDg2())
								.findFirst().orElse(null);
						if (etudiant == null) {
							etudiant = new Etudiant();
							etudiant.setUtilisateur(utilisateur);
							etudiant = etudiantRepository.saveAndFlush(etudiant);
							saved.add(etudiant);
						}
					} else {
						saved.add(etudiant);
					}

					long etudiantId = etudiant.getId();
					if (promotion.getEtudiants().stream().anyMatch(e -> e.getId() == etudiantId))
						continue;
					else {
						promotion.getEtudiants().add(etudiant);
						promotionRepository.save(promotion);
					}
					Optional<LivretEvaluation> evaOptional = livretEvaluationRepository
							.findByEtudiantIdAndTitreProfessionnelId(etudiant.getId(), promotion.getCursus().getId());
					if (!evaOptional.isPresent()) {
						LivretEvaluation livret = new LivretEvaluation();
						livret.setEtudiant(etudiant);
						livret.setTitreProfessionnel(promotion.getCursus());
						livret.setOrganismeFormation(promotion.getCentreFormation());
						livret.setEtat(EtatLivertEval.ENATTENTEDEVALIDATION);
						livret = livretEvaluationRepository.saveAndFlush(livret);
						final LivretEvaluation fLivret = livret;
						promotion.getCursus().getActiviteTypes().forEach(at -> {
							BlocEvaluation blocEvaluation = new BlocEvaluation();
							blocEvaluation.setLivretEvaluation(fLivret);
							blocEvaluation.setActiviteType(at);
							blocEvaluationRepository.saveAndFlush(blocEvaluation);
						});
					}

				}
				logger.info("FetchDg2Etudiant>>>>>>END");
				return true;
			} else {
				logger.error("FetchDg2Etudiant>>>>>>>>ERROR End failed");
				return false;
			}
		}).orElseThrow(() -> new FetchDG2Exception("Promotion Introuvable"))) {
			throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
		}
	}

	@Async("myTaskExecutor")
	public void importUserFromJson(String json, Optional<Promotion> promotion)
			throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<EtudiantUtilisateurDG2Dto> cResJson;

		cResJson = objectMapper.readValue(json, new TypeReference<List<EtudiantUtilisateurDG2Dto>>() {
		});

		for (EtudiantUtilisateurDG2Dto eDG2 : cResJson) {

			// Etudiant etudiantDg2 = mapper.etudiantUtilisateurDG2DtoToEtudiant(eDG2);
			Optional<Utilisateur> utiLisateurOptional = utilisateurRepository.findDistinctByIdDg2(eDG2.getPersonId());
			Utilisateur utilisateurDg2 = mapper.etudiantUtilisateurDG2DtoToUtilisateur(eDG2);
			System.out.println("DG2 " + utilisateurDg2.toString());
			Adresse adresseDg2 = mapper.etudiantUtilisateurDG2DtoToAdresse(eDG2);
			Etudiant etudiant = new Etudiant();
			if (utiLisateurOptional.isPresent()) {
				System.out.println(utiLisateurOptional.get());
				if (utiLisateurOptional.get().getEtudiant() != null) {
					etudiant = utiLisateurOptional.get().getEtudiant();
				}

				if (!adresseDg2.equals(utiLisateurOptional.get().getAdresse())) {
					if (utiLisateurOptional.get().getAdresse() != null) {
						adresseDg2.setId(utiLisateurOptional.get().getAdresse().getId());
						adresseDg2.setVersion(utiLisateurOptional.get().getAdresse().getVersion());
						adresseRepository.saveAndFlush(adresseDg2);
					} else {
						adresseDg2 = adresseRepository.saveAndFlush(adresseDg2);
						utiLisateurOptional.get().setAdresse(adresseDg2);
					}

				}

				utilisateurDg2.setPassword(utiLisateurOptional.get().getPassword());
				utilisateurDg2.setId(utiLisateurOptional.get().getId());
				utilisateurDg2.setVersion(utiLisateurOptional.get().getVersion());
				if (utilisateurDg2.equals(utiLisateurOptional.get())
						&& etudiant.getPromotions().contains(promotion.get())) {
					continue;
				} else {

					utilisateurDg2.setId(utiLisateurOptional.get().getId());
					utilisateurDg2.setVersion(utiLisateurOptional.get().getVersion());
					if (etudiant != null) {
						List<Etudiant> etudiants = new ArrayList<>();
						List<Promotion> promotions = new ArrayList<>();
						if (etudiant.getPromotions() != null) {
							promotions.addAll(etudiant.getPromotions());
						}
						if (!promotions.contains(promotion.get())) {
							promotions.add(promotion.get());
						}
						etudiant.setPromotions(promotions);
						if (promotion.get().getEtudiants() != null) {
							etudiants.addAll(promotion.get().getEtudiants());
						}
						if (!etudiants.contains(etudiant)) {
							etudiants.add(etudiant);
						}
						promotion.get().setEtudiants(etudiants);

					}
				}

				utilisateurDg2.setEtudiant(etudiant);
				etudiant.setUtilisateur(utilisateurDg2);

			} else {
				List<Promotion> promotions = new ArrayList<>();
				promotions.add(promotion.get());
				utilisateurDg2.setAdresse(adresseDg2);

				etudiant.setPromotions(promotions);
				List<Etudiant> etudiants = new ArrayList<>();
				etudiants.add(etudiant);
				if (promotion.get().getEtudiants() != null) {
					etudiants.addAll(promotion.get().getEtudiants());
				}
				promotion.get().setEtudiants(etudiants);
				try {
					utilisateurDg2.setPassword(HashTools.hashSHA512("password"));
				} catch (Exception e) {
					logger.error("setPassword failed", e);
				}

				List<UtilisateurRole> roles = new ArrayList<>();
				List<Utilisateur> utilisateurs = new ArrayList<>();
				UtilisateurRole etudiantRole = utilisateurRoleRepository.findByIntituleContaining("ETUDIANT");
				roles.add(etudiantRole);

				utilisateurs.add(utilisateurDg2);
				if (etudiantRole.getUtilisateurs() != null) {
					utilisateurs.addAll(etudiantRole.getUtilisateurs());

				}
				utilisateurDg2.setRoles(roles);
				etudiantRole.setUtilisateurs(utilisateurs);
				utilisateurDg2.setEtudiant(etudiant);
				etudiant.setUtilisateur(utilisateurDg2);

			}
			utilisateurRepository.saveAndFlush(utilisateurDg2);

			Etudiant etuSaved = etudiantRepository.saveAndFlush(etudiant);
			Optional<LivretEvaluation> evaOptional = livretEvaluationRepository
					.findByEtudiantIdAndTitreProfessionnelId(etuSaved.getId(), promotion.get().getCursus().getId());
			if (!evaOptional.isPresent()) {
				LivretEvaluation livert = new LivretEvaluation();
				livert.setEtudiant(etuSaved);
				livert.setTitreProfessionnel(promotion.get().getCursus());
				livert.setObservation("Cliquez ici pour taper du texte.");
				livert.setOrganismeFormation(promotion.get().getCentreFormation());
				livert.setEtat(EtatLivertEval.ENATTENTEDEVALIDATION);
				livert = livretEvaluationRepository.saveAndFlush(livert);
				Set<ActiviteType> activiteTypes = promotion.get().getCursus().getActiviteTypes();

				for (ActiviteType at : activiteTypes) {

					BlocEvaluation blocEvaluation = new BlocEvaluation();
					blocEvaluation.setLivretEvaluation(livert);
					blocEvaluation.setActiviteType(at);
					blocEvaluationRepository.saveAndFlush(blocEvaluation);

				}
			}

		}

	}

	@Override
	public AccueilEtudiantDto getAccueilEtudiant(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);
		if (e.isPresent()) {
			return mapperTools.etudiantToAccueilEtudiantDto(e.get());
		}
		return null;
	}

	@Override
	public List<EtudiantDto> getEtudiantByIdTuteurBySearch(long id, int page, int size, String search) {
		List<Etudiant> lstetud = etudiantRepository.findEtudiantBySearch(id, PageRequest.of(page, size), search).get()
				.collect(Collectors.toList());
		List<EtudiantDto> lstetudDto = new ArrayList<>();
		for (Etudiant etudiant : lstetud) {
			if (etudiant != null) {
				EtudiantDto etudDto = mapper.etudiantToEtudiantDto(etudiant);
				etudDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(etudiant.getUtilisateur()));
				lstetudDto.add(etudDto);
			}
		}
		return lstetudDto;
	}

	@Override
	public EtudiantDossierProjetDto getByEtudiantIdForDossierProjet(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);
		if (!e.isPresent())
			return null;
		return DtoTools.convert(e.get(), EtudiantDossierProjetDto.class);

	}
	
	@Override
	public List<EtudiantDto> getEtudiantByPromotion(long id, int page, int size, String search) {
		List<Etudiant> result = etudiantRepository.getEtudiantByPromotion(id, search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<EtudiantDto> res = new ArrayList<>();
		if (!result.isEmpty()) {
			for (Etudiant p: result) {
				EtudiantDto promotionDto = mapper.etudiantToEtudiantDto(p);
				res.add(promotionDto);
			}
		}
		return res;
	}

	@Override
	public CountDto countEtudiantByPromotion(long id, String search) {
		return new CountDto(etudiantRepository.countEtudiantByPromotion(id, search));
	}

	@Override
	public Etudiant savEtudiant(Utilisateur utilisateur) {
		Etudiant etudiant = new Etudiant();
		etudiant.setUtilisateur(utilisateur);
		
		return etudiantRepository.save(etudiant);
	}
	
	@Override
	public List<EtudiantDto> findAllByTuteurId(long tuteurId) {
		List<Etudiant> etudiants = etudiantRepository.findAllByTuteurId(tuteurId);
		List<EtudiantDto> etudiantDtos = new ArrayList<>();
		etudiants.forEach(e -> {
			EtudiantDto etudiantDto =  mapper.etudiantToEtudiantDto(e);
			etudiantDtos.add(etudiantDto);
		});
		return etudiantDtos;
	}
}
