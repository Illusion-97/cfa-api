package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Contrat;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.entities.DossierProfessionnel;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.ContratRepository;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.GroupeEtudiantRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.NoteRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.tools.HashTools;

@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	AbsenceRepository absenceRepository;

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
	ContratRepository contratRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	// ##################################################
	// # CRUD #
	// ##################################################

	@Override
	public List<EtudiantDto> getAll() {
		List<Etudiant> lst = etudiantRepository.findAll();
		List<EtudiantDto> res = new ArrayList<EtudiantDto>();

		for (Etudiant e : lst) {
			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(e);
			
			UtilisateurDto Utilisateur = mapper.UtilisateurToUtilisateurDto(e.getUtilisateur());
			etuDto.setUtilisateurDto(Utilisateur);
			
			AdresseDto addrDto = mapper.AdresseToAdresseDto(e.getUtilisateur().getAdresse());
//			EntrepriseDto entDto = mapper.EntrepriseToEntrepriseDto(e.getUtilisateur().getEntreprise());


			
			List<GroupeEtudiant> lstGrpEtu = e.getGroupes();
			List<GroupeEtudiantDto> lstGrpEtuDto = new ArrayList<GroupeEtudiantDto>();
			for (GroupeEtudiant grp : lstGrpEtu) {
				if (grp != null)
					lstGrpEtuDto.add(mapper.GroupeEtudiantToGroupEtudiantDto(grp));
			}

			List<Promotion> lstPromo = e.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(mapper.PromotionToPromotionDto(promotion));
			}
			List<DossierProjet> lstDossierProjet = e.getDossierProjet();
			List<DossierProjetDto> lstDossierProjetDto = new ArrayList<DossierProjetDto>();
			for (DossierProjet dp : lstDossierProjet) {
				DossierProjetDto dpdto = mapper.DossierProjetToDossierProjetDto(dp);
				dpdto.setProjet(mapper.ProjetToProjetDto(dp.getProjet()));
				lstDossierProjetDto.add(dpdto);
		
			}
			
			List<UtilisateurRoleDto> URDto = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole r : e.getUtilisateur().getRoles()) {
				URDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(r));
			}
			
			etuDto.getUtilisateurDto().setRolesDto(URDto);
			
			List<DossierProfessionnel>lstDossierProfessionnel = e.getDossierProfessionnel();
			List<DossierProfessionnelDto> lstDossierProfessionnelDto = new ArrayList<DossierProfessionnelDto>();
			for(DossierProfessionnel dp : lstDossierProfessionnel) {
				DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
				dpDto.setCursusDto(mapper.CursusToCursusDto(dp.getCursus()));
				lstDossierProfessionnelDto.add(dpDto);
			}
				

//			UtilisateurDto refDto = mapper.UtilisateurToUtilisateurDto(e.getFormateurReferent());

			etuDto.getUtilisateurDto().setAdresseDto(addrDto);
			etuDto.setGroupesDto(lstGrpEtuDto);
//			etuDto.getUtilisateurDto().setEntrepriseDto(entDto);
			etuDto.setPromotionsDto(lstPromoDto);
//			etuDto.setFormateurReferentDto(refDto);
			etuDto.setDossierProfessionnel(lstDossierProfessionnelDto);
			etuDto.setDossierProjet(lstDossierProjetDto);

			res.add(etuDto);
		}

		return res;
	}

//	@Override
//	public List<EtudiantDto> getAllByPage(int page, int size, String search) {
//		List<Etudiant> lst = etudiantRepository.findAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCase(search,search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());
//
//		// conversion vers Dto
//		List<EtudiantDto> lstDto = new ArrayList<EtudiantDto>();
//		for (Etudiant e : lst) {
//			EtudiantDto eDto = DtoTools.convert(e, EtudiantDto.class);
//			
//			List<PromotionDto> promotionsDto = new ArrayList<PromotionDto>();
//			for(Promotion p : e.getPromotions()) {
//				promotionsDto.add(DtoTools.convert(p, PromotionDto.class));
//			}
//			eDto.setPromotionsDto(promotionsDto);
//			lstDto.add(eDto);
//		}
//		return lstDto;
//	}
//
//	@Override
//	public CountDto count(String search) {
//		return new CountDto(etudiantRepository.countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCase(search, search, search));
//	}

	@Override
	public EtudiantDto getById(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);

		if (!e.isPresent()) return null;
		
		EtudiantDto eDto = mapper.EtudiantToEtudiantDto(e.get());
		
		eDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(e.get().getUtilisateur()));
		eDto.getUtilisateurDto().setAdresseDto(mapper.AdresseToAdresseDto(e.get().getUtilisateur().getAdresse()));
//		eDto.setFormateurReferentDto(mapper.UtilisateurToUtilisateurDto(e.get().getFormateurReferent()));
//		eDto.setManagerDto(mapper.UtilisateurToUtilisateurDto(e.get().getManager()));

		List<GroupeEtudiantDto> groupes = new ArrayList<GroupeEtudiantDto>();
		for(GroupeEtudiant g : e.get().getGroupes()) {
			groupes.add(mapper.GroupeEtudiantToGroupEtudiantDto(g));
		}		
		eDto.setGroupesDto(groupes);
		
		List<UtilisateurRoleDto> URDto = new ArrayList<UtilisateurRoleDto>();
		for (UtilisateurRole r : e.get().getUtilisateur().getRoles()) {
			URDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(r));
		}
		eDto.getUtilisateurDto().setRolesDto(URDto);
		
		List<PromotionDto> promotions = new ArrayList<PromotionDto>();
		for(Promotion p : e.get().getPromotions()) {
			PromotionDto pDto = mapper.PromotionToPromotionDto(p);
			List<InterventionDto> lstIDto = new ArrayList<>();
			for (Intervention i : p.getInterventions()) {
				InterventionDto iDto = mapper.InterventionToInterventionDto(i);
				lstIDto.add(iDto);
			}
			pDto.setInterventionsDto(lstIDto);
			promotions.add(pDto);
		}
		eDto.setPromotionsDto(promotions);
		List<DossierProjet> lstDossierProjet = e.get().getDossierProjet();
		List<DossierProjetDto> lstDossierProjetDto = new ArrayList<DossierProjetDto>();
		for (DossierProjet dp : lstDossierProjet) {
			DossierProjetDto dpdto = mapper.DossierProjetToDossierProjetDto(dp);
			dpdto.setProjet(mapper.ProjetToProjetDto(dp.getProjet()));
			lstDossierProjetDto.add(dpdto);
		}
		List<DossierProfessionnel>lstDossierProfessionnel = e.get().getDossierProfessionnel();
		List<DossierProfessionnelDto> lstDossierProfessionnelDto = new ArrayList<DossierProfessionnelDto>();
		for(DossierProfessionnel dp : lstDossierProfessionnel) {
			DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
			dpDto.setCursusDto(mapper.CursusToCursusDto(dp.getCursus()));
			lstDossierProfessionnelDto.add(dpDto);
		}
		eDto.setDossierProfessionnel(lstDossierProfessionnelDto);
		eDto.setDossierProjet(lstDossierProjetDto);
		
		
		return eDto;
	}

	@Override
	public EtudiantDto saveOrUpdate(EtudiantDto e) {
		Etudiant etudiant = DtoTools.convert(e, Etudiant.class);		

		if(etudiant.getUtilisateur() != null) {
			//HashTools throw Exception
			try {
				//Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
				if(etudiant.getUtilisateur().getId() == 0) {
					etudiant.getUtilisateur().setPassword(HashTools.hashSHA512(etudiant.getUtilisateur().getPassword()));
				}else {
					//Si on a modifié le mdp
					Etudiant etudiantInDB = etudiantRepository.getOne(etudiant.getId());
					if(!etudiantInDB.getUtilisateur().getPassword().equals(etudiant.getUtilisateur().getPassword())) {
						etudiant.getUtilisateur().setPassword(HashTools.hashSHA512(etudiant.getUtilisateur().getPassword()));
		            }
				}	
			}catch (Exception ex) {
	            ex.printStackTrace();
	        }
			
			//Pour le dossier
			Path path = Paths.get("./src/main/resources/files/utilisateurs/" + etudiant.getUtilisateur().getId());

			try {
				Files.createDirectories(path);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		etudiant = etudiantRepository.saveAndFlush(etudiant);
	
		

		return mapper.EtudiantToEtudiantDto(etudiant);
	}

	@Override
	public void deleteById(long id) {

		Etudiant etudiant = getEtudiantById(id);

		if (etudiant == null)
			return;
		
		Utilisateur Utilisateur = etudiant.getUtilisateur();
		etudiant.setUtilisateur(null);
		Utilisateur.setEtudiant(null);
		
		etudiantRepository.save(etudiant);
		utilisateurRepository.save(Utilisateur);

		// Etudiant
		// @ManyToMany Promotion promotions
		// @ManyToMany GroupeEtudiant groupes

		// On récupère ces objets, et on supprime les liens :

		List<Promotion> promotions = etudiant.getPromotions();
		List<GroupeEtudiant> groupes = etudiant.getGroupes();
		etudiant.setGroupes(null);
		etudiant.setPromotions(null);

		etudiantRepository.save(etudiant);

		Etudiant toDelete = new Etudiant();

		for (Promotion p : promotions) {
			for (Etudiant e : p.getEtudiants())
				if (e.getUtilisateur().getId() == etudiant.getUtilisateur().getId()) {
					toDelete = e;
					break;
				}
			p.getEtudiants().remove(toDelete);
			promotionRepository.save(p);
		}

		for (GroupeEtudiant g : groupes) {
			for (Etudiant e : g.getEtudiants())
				if (e.getUtilisateur().getId() == etudiant.getUtilisateur().getId()) {
					toDelete = e;
					break;
				}
			g.getEtudiants().remove(toDelete);
			groupeEtudiantRepository.save(g);
		}

		// Note
		// @ManyToOne Etudiant etudiant

		// Absence
		// @ManyToOne Etudiant etudiant

		// On récupère ces objets, et on supprime les liens :

		List<Note> notes = noteRepository.getNotesByIdEtudiant(id);
		List<Absence> absences = absenceRepository.getAbsencesByIdEtudiant(id);

		for (Note n : notes) {
			n.setEtudiant(null);
			noteRepository.save(n);
			noteRepository.delete(n);
		}

		for (Absence a : absences) {
			a.setEtudiant(null);
			absenceRepository.save(a);
			absenceRepository.delete(a);
		}

		// Les liens sont tous supprimés : on peut supprimé l'étudiant

		etudiantRepository.delete(etudiant);
	}

	// ##################################################
	// # 1er Niveau #
	// ##################################################

	@Override
	public List<PromotionDto> getPromotionsByIdEtudiant(long id) {
		List<Promotion> lst = getEtudiantById(id).getPromotions();
		List<PromotionDto> lstDto = new ArrayList<PromotionDto>();

		for (Promotion p : lst) {
			// Quand on convertit en Dto, on perd les objets contenu
			// On récupère le referent pedagogiue que l'on redonne a promotionDto

			UtilisateurDto referentPedagogique = mapper.UtilisateurToUtilisateurDto(p.getReferentPedagogique());
			PromotionDto promotion = mapper.PromotionToPromotionDto(p);

			promotion.setReferentPedagogiqueDto(referentPedagogique);

			lstDto.add(promotion);
		}

		return lstDto;
	}

	@Override
	public List<GroupeEtudiantDto> getGroupesByIdEtudiant(long id) {
		List<GroupeEtudiant> lst = getEtudiantById(id).getGroupes();
		List<GroupeEtudiantDto> lstDto = new ArrayList<GroupeEtudiantDto>();
		for (GroupeEtudiant g : lst) {
			GroupeEtudiantDto gDto = mapper.GroupeEtudiantToGroupEtudiantDto(g);
			List<EtudiantDto> eDtos = new ArrayList<EtudiantDto>();
			for(Etudiant e : g.getEtudiants()) {
				EtudiantDto etudiantDto = mapper.EtudiantToEtudiantDto(e);
				etudiantDto.setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(e.getUtilisateur()));
				eDtos.add(etudiantDto);
			}
			gDto.setEtudiants(eDtos);
			lstDto.add(gDto);
		}

		return lstDto;
	}

	@Override
	public EntrepriseDto getEntrepriseByIdEtudiant(long id) {
		//ATTENTION : L'etudiant a potentiellement une liste de contrat => une liste d'entreprise
		Contrat contrat = contratRepository.findByEtudiantId(id);
		return mapper.EntrepriseToEntrepriseDto(contrat.getMaitreApprentissage().getEntreprise());
	}

	@Override
	public AdresseDto getAdresseByIdEtudiant(long id) {
		return mapper.AdresseToAdresseDto(getEtudiantById(id).getUtilisateur().getAdresse());
	}

	// ##################################################
	// # 2eme Niveau #
	// ##################################################

	@Override
	public List<NoteDto> getNotesByIdEtudiant(long id, int page, int size) {
		List<Note> lst = noteRepository.getNotesByIdEtudiant(id, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<NoteDto> res = new ArrayList<NoteDto>();

		for (Note n : lst) {
			NoteDto nDto =mapper.NoteToNoteDto(n);
			nDto.setDevoirDto(mapper.DevoirToDevoirDto(n.getDevoir()));
			nDto.setExamenDto(mapper.PassageExamenToPassageExamenDto(n.getExamen()));
			nDto.getExamenDto().setExamenDto(mapper.ExamenToExamenDto(n.getExamen().getExamen()));
			
			
			res.add(nDto);
		}
		

		return res;
	}

	public List<NoteDto> getNotesByIdEtudiant(long id) {
		List<Note> lst = noteRepository.getNotesByIdEtudiant(id);
		List<NoteDto> res = new ArrayList<NoteDto>();

		for (Note n : lst)
			res.add(mapper.NoteToNoteDto(n));

		return res;
	}

	@Override
	public List<AbsenceDto> getAbsencesByIdEtudiant(long id) {
		List<Absence> lst = absenceRepository.getAbsencesByIdEtudiant(id);
		List<AbsenceDto> res = new ArrayList<AbsenceDto>();

		for (Absence n : lst)
			res.add(mapper.AbsenceToAbsenceDto(n));

		return res;
	}

	@Override
	public List<AbsenceDto> getAbsencesByIdEtudiant(long id, int page, int size) {
		List<Absence> lst = absenceRepository.getAbsencesByIdEtudiant(id, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<AbsenceDto> res = new ArrayList<AbsenceDto>();

		for (Absence n : lst)
			res.add(mapper.AbsenceToAbsenceDto(n));

		return res;
	}

	// ##################################################
	// # 3eme Niveau #
	// ##################################################

	@Override
	public List<InterventionDto> getIntervenionByIdEtudiant(long id) {
		List<Intervention> interventions = new ArrayList<Intervention>();
		List<InterventionDto> res = new ArrayList<InterventionDto>();

		for (Promotion p : getEtudiantById(id).getPromotions())
			interventions.addAll(interventionRepository.getInterventionsByIdPromotion(p.getId()));

		for (Intervention i : interventions)
			res.add(mapper.InterventionToInterventionDto(i));

		return res;
	}

	// ##################################################
	// # UTILE #
	// ##################################################

	private Etudiant getEtudiantById(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);

		if (e.isPresent()) 
			return e.get();

		return null;
	}

	@Override
	public List<JourneePlanningDto> getAllJourneePlanningByIdEtudiant(long id) {
		List<JourneePlanningDto> result = new ArrayList<JourneePlanningDto>();

		List<Intervention> interventions = new ArrayList<Intervention>();

		Etudiant e = getEtudiantById(id);

		List<Promotion> promotions = e.getPromotions();

//		int size = promotions.size();

		for (Promotion p : promotions)
			interventions.addAll(interventionRepository.getInterventionsByIdPromotion(p.getId()));

		for (Intervention i : interventions)
			result.addAll(journeePlanningService.getJourneePlanningFromIntervention(i));

		return result;
	}

	@Override
	public UtilisateurDto getFormateurReferentByIdEtudiant(long id) {
		Contrat contrat = contratRepository.findByEtudiantId(id);
		return mapper.UtilisateurToUtilisateurDto(contrat.getMaitreApprentissage().getUtilisateur());
	}

//	@Override
//	public UtilisateurDto getManagerByIdEtudiant(long id) {
//		return mapper.UtilisateurToUtilisateurDto(getEtudiantById(id).getManager());
//	}

	@Override
	public List<DevoirDto> getDevoirsByIdEtudiant(long id, int page, int size) {

//		List<DevoirDto> lstdDto =  new ArrayList<DevoirDto>();
//		EtudiantDto eDto = getById(id);
//		List<Devoir> lstD = devoirRepository.findAll();
//		
//		for (PromotionDto pDto : eDto.getPromotionsDto()) {
//			for (InterventionDto iDto : pDto.getInterventionsDto()) {
//				for (Devoir devoir : lstD) {
//					if (devoir.getIntervention().getId() == iDto.getId()) {
//						DevoirDto dDto = mapper.DevoirToDevoirDto(devoir);
//						dDto.setInterventionDto(iDto);
//						dDto.getInterventionDto().setFormationDto(mapper.FormationToFormationDto(devoir.getIntervention().getFormation()));
//						lstdDto.add(dDto);
//					}
//				}
//			}			
//		}
//		
//		return lstdDto;
		
		List<Devoir> lst = devoirRepository.findAllByInterventionPromotionsEtudiantsId(id, PageRequest.of(page, size)).get().collect(Collectors.toList());
		List<DevoirDto> res = new ArrayList<DevoirDto>();
		
		for(Devoir d : lst) {
			DevoirDto dDto = mapper.DevoirToDevoirDto(d);			
			dDto.setInterventionDto(mapper.InterventionToInterventionDto(d.getIntervention()));
			dDto.getInterventionDto().setFormationDto(mapper.FormationToFormationDto(d.getIntervention().getFormation()));			
			res.add(dDto);
		}
		
		return res;
	}

	@Override
	public CountDto count(String search) {
		// TODO Auto-generated method stub
		return new CountDto(etudiantRepository
				.countDistinctByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingOrPromotionsNomContainingOrGroupesNomContaining(
						search, search, search, search));
	}

	@Override
	public List<EtudiantDto> getAllByPage(int page, int size, String search) {
		List<Etudiant> lstStud = etudiantRepository
				.findDistinctAllByUtilisateurPrenomContainingIgnoringCaseOrUtilisateurNomContainingOrPromotionsNomContainingOrGroupesNomContaining(
						search, search, search, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		List<EtudiantDto> res = new ArrayList<EtudiantDto>();

		for (Etudiant e : lstStud) {
			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(e);
			UtilisateurDto pDto =  mapper.UtilisateurToUtilisateurDto(e.getUtilisateur());
			AdresseDto addrDto = mapper.AdresseToAdresseDto(e.getUtilisateur().getAdresse());
//			EntrepriseDto entDto =mapper.EntrepriseToEntrepriseDto(e.getUtilisateur().getEntreprise());
//			UtilisateurDto refDto = mapper.UtilisateurToUtilisateurDto(e.getFormateurReferent());
//			UtilisateurDto managDto = mapper.UtilisateurToUtilisateurDto(e.getManager());

			List<GroupeEtudiant> lstGrpEtu = e.getGroupes();
			List<GroupeEtudiantDto> lstGrpEtuDto = new ArrayList<GroupeEtudiantDto>();
			for (GroupeEtudiant grp : lstGrpEtu) {
				if (grp != null)
					lstGrpEtuDto.add(mapper.GroupeEtudiantToGroupEtudiantDto(grp));
			}

			List<Promotion> lstPromo = e.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(mapper.PromotionToPromotionDto(promotion));
			}
			List<DossierProjet> lstDossierProjet = e.getDossierProjet();
			List<DossierProjetDto> lstDossierProjetDto = new ArrayList<DossierProjetDto>();
			for (DossierProjet dp : lstDossierProjet) {
				DossierProjetDto dpdto = mapper.DossierProjetToDossierProjetDto(dp);
				dpdto.setProjet(mapper.ProjetToProjetDto(dp.getProjet()));
				lstDossierProjetDto.add(dpdto);
		
			}
			List<DossierProfessionnel>lstDossierProfessionnel = e.getDossierProfessionnel();
			List<DossierProfessionnelDto> lstDossierProfessionnelDto = new ArrayList<DossierProfessionnelDto>();
			for(DossierProfessionnel dp : lstDossierProfessionnel) {
				DossierProfessionnelDto dpDto = mapper.DossierProfessionnelToDossierProfessionnelDto(dp);
				dpDto.setCursusDto(mapper.CursusToCursusDto(dp.getCursus()));
				lstDossierProfessionnelDto.add(dpDto);
			}
			
			
			etuDto.setUtilisateurDto(pDto);
			etuDto.getUtilisateurDto().setAdresseDto(addrDto);
			etuDto.setGroupesDto(lstGrpEtuDto);
//			etuDto.getUtilisateurDto().setEntrepriseDto(entDto);
			etuDto.setPromotionsDto(lstPromoDto);
//			etuDto.setFormateurReferentDto(refDto);
//			etuDto.setManagerDto(managDto);
			etuDto.setDossierProfessionnel(lstDossierProfessionnelDto);
			etuDto.setDossierProjet(lstDossierProjetDto);
			res.add(etuDto);
		}

		return res;
	}

	

}
