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
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.GroupeEtudiantRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.NoteRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;

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

	// ##################################################
	// # CRUD #
	// ##################################################

	@Override
	public List<EtudiantDto> getAll() {
		List<Etudiant> lst = etudiantRepository.findAll();
		List<EtudiantDto> res = new ArrayList<EtudiantDto>();

		for (Etudiant e : lst) {
			EtudiantDto etuDto = DtoTools.convert(e, EtudiantDto.class);
			AdresseDto addrDto = DtoTools.convert(e.getAdresse(), AdresseDto.class);
			EntrepriseDto entDto = DtoTools.convert(e.getEntreprise(), EntrepriseDto.class);

			List<GroupeEtudiant> lstGrpEtu = e.getGroupes();
			List<GroupeEtudiantDto> lstGrpEtuDto = new ArrayList<GroupeEtudiantDto>();
			for (GroupeEtudiant grp : lstGrpEtu) {
				if (grp != null)
					lstGrpEtuDto.add(DtoTools.convert(grp, GroupeEtudiantDto.class));
			}

			List<Promotion> lstPromo = e.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(DtoTools.convert(promotion, PromotionDto.class));
			}

			UtilisateurDto refDto = DtoTools.convert(e.getFormateurReferent(), UtilisateurDto.class);

			etuDto.setAdresseDto(addrDto);
			etuDto.setGroupesDto(lstGrpEtuDto);
			etuDto.setEntrepriseDto(entDto);
			etuDto.setPromotionsDto(lstPromoDto);
			etuDto.setFormateurReferentDto(refDto);

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
		
		EtudiantDto eDto = DtoTools.convert(e.get(), EtudiantDto.class);
		AdresseDto addrDto = DtoTools.convert(e.get().getAdresse(), AdresseDto.class);		
		List<GroupeEtudiantDto> lst = new ArrayList<GroupeEtudiantDto>();
		for(GroupeEtudiant g : e.get().getGroupes()) {
			lst.add(DtoTools.convert(g, GroupeEtudiantDto.class));
		}
		
		eDto.setGroupesDto(lst);
		eDto.setAdresseDto(addrDto);

		return eDto;
	}

	@Override
	public EtudiantDto saveOrUpdate(EtudiantDto e) {
		Etudiant etudiant = etudiantRepository.saveAndFlush(DtoTools.convert(e, Etudiant.class));

		Path path = Paths.get("./src/main/resources/files/utilisateurs/" + etudiant.getId());

		try {
			Files.createDirectories(path);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return DtoTools.convert(etudiant, EtudiantDto.class);
	}

	@Override
	public void deleteById(long id) {

		Etudiant etudiant = getEtudiantById(id);

		if (etudiant == null)
			return;

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
				if (e.getId() == etudiant.getId()) {
					toDelete = e;
					break;
				}
			p.getEtudiants().remove(toDelete);
			promotionRepository.save(p);
		}

		for (GroupeEtudiant g : groupes) {
			for (Etudiant e : g.getEtudiants())
				if (e.getId() == etudiant.getId()) {
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

			UtilisateurDto referentPedagogique = DtoTools.convert(p.getReferentPedagogique(), UtilisateurDto.class);
			PromotionDto promotion = DtoTools.convert(p, PromotionDto.class);

			promotion.setReferentPedagogiqueDto(referentPedagogique);

			lstDto.add(promotion);
		}

		return lstDto;
	}

	@Override
	public List<GroupeEtudiantDto> getGroupesByIdEtudiant(long id) {
		List<GroupeEtudiant> lst = getEtudiantById(id).getGroupes();
		List<GroupeEtudiantDto> lstDto = new ArrayList<GroupeEtudiantDto>();
		for (GroupeEtudiant g : lst)
			lstDto.add(DtoTools.convert(g, GroupeEtudiantDto.class));

		return lstDto;
	}

	@Override
	public EntrepriseDto getEntrepriseByIdEtudiant(long id) {
		return DtoTools.convert(getEtudiantById(id).getEntreprise(), EntrepriseDto.class);
	}

	@Override
	public AdresseDto getAdresseByIdEtudiant(long id) {
		return DtoTools.convert(getEtudiantById(id).getAdresse(), AdresseDto.class);
	}

	// ##################################################
	// # 2eme Niveau #
	// ##################################################

	@Override
	public List<NoteDto> getNotesByIdEtudiant(long id, int page, int size) {
		List<Note> lst = noteRepository.getNotesByIdEtudiant(id, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<NoteDto> res = new ArrayList<NoteDto>();

		for (Note n : lst)
			res.add(DtoTools.convert(n, NoteDto.class));

		return res;
	}

	public List<NoteDto> getNotesByIdEtudiant(long id) {
		List<Note> lst = noteRepository.getNotesByIdEtudiant(id);
		List<NoteDto> res = new ArrayList<NoteDto>();

		for (Note n : lst)
			res.add(DtoTools.convert(n, NoteDto.class));

		return res;
	}

	@Override
	public List<AbsenceDto> getAbsencesByIdEtudiant(long id) {
		List<Absence> lst = absenceRepository.getAbsencesByIdEtudiant(id);
		List<AbsenceDto> res = new ArrayList<AbsenceDto>();

		for (Absence n : lst)
			res.add(DtoTools.convert(n, AbsenceDto.class));

		return res;
	}

	@Override
	public List<AbsenceDto> getAbsencesByIdEtudiant(long id, int page, int size) {
		List<Absence> lst = absenceRepository.getAbsencesByIdEtudiant(id, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<AbsenceDto> res = new ArrayList<AbsenceDto>();

		for (Absence n : lst)
			res.add(DtoTools.convert(n, AbsenceDto.class));

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
			res.add(DtoTools.convert(i, InterventionDto.class));

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

		int size = promotions.size();

		for (Promotion p : promotions)
			interventions.addAll(interventionRepository.getInterventionsByIdPromotion(p.getId()));

		for (Intervention i : interventions)
			result.addAll(journeePlanningService.getJourneePlanningFromIntervention(i));

		return result;
	}

	@Override
	public UtilisateurDto getFormateurReferentByIdEtudiant(long id) {
		return DtoTools.convert(getEtudiantById(id).getFormateurReferent(), UtilisateurDto.class);
	}

	@Override
	public UtilisateurDto getManagerByIdEtudiant(long id) {
		return DtoTools.convert(getEtudiantById(id).getManager(), UtilisateurDto.class);
	}

	@Override
	public List<DevoirDto> getDevoirsByIdEtudiant(long id, int page, int size) {

		return null;
//		List<Devoir> lst = devoirRepository.getDevoirsByIdEtudiant(id,  PageRequest.of(page, size)).get().collect(Collectors.toList());
//		List<DevoirDto> res = new ArrayList<DevoirDto>();
//		
//		for(Devoir n : lst)
//			res.add(DtoTools.convert(n, DevoirDto.class));
//		
//		return res;
	}

	@Override
	public CountDto count(String search) {
		// TODO Auto-generated method stub
		return new CountDto(etudiantRepository
				.countDistinctByPrenomContainingIgnoringCaseOrNomContainingOrPromotionsNomContainingOrGroupesNomContainingOrFormateurReferentNomContainingOrFormateurReferentPrenomContainingAllIgnoreCase(
						search, search, search, search, search, search));
	}

	@Override
	public List<EtudiantDto> getAllByPage(int page, int size, String search) {
		List<Etudiant> lstStud = etudiantRepository
				.findDistinctAllByPrenomContainingIgnoringCaseOrNomContainingOrPromotionsNomContainingOrGroupesNomContainingOrFormateurReferentNomContainingOrFormateurReferentPrenomContainingAllIgnoreCase(
						search, search, search, search, search, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		List<EtudiantDto> res = new ArrayList<EtudiantDto>();

		for (Etudiant e : lstStud) {
			EtudiantDto etuDto = DtoTools.convert(e, EtudiantDto.class);
			AdresseDto addrDto = DtoTools.convert(e.getAdresse(), AdresseDto.class);
			EntrepriseDto entDto = DtoTools.convert(e.getEntreprise(), EntrepriseDto.class);
			UtilisateurDto refDto = DtoTools.convert(e.getFormateurReferent(), UtilisateurDto.class);
			UtilisateurDto managDto = DtoTools.convert(e.getManager(), UtilisateurDto.class);

			List<GroupeEtudiant> lstGrpEtu = e.getGroupes();
			List<GroupeEtudiantDto> lstGrpEtuDto = new ArrayList<GroupeEtudiantDto>();
			for (GroupeEtudiant grp : lstGrpEtu) {
				if (grp != null)
					lstGrpEtuDto.add(DtoTools.convert(grp, GroupeEtudiantDto.class));
			}

			List<Promotion> lstPromo = e.getPromotions();
			List<PromotionDto> lstPromoDto = new ArrayList<PromotionDto>();
			for (Promotion promotion : lstPromo) {
				/** On convertis List<Promotion> en List<PromotionDto> **/
				if (promotion != null)
					lstPromoDto.add(DtoTools.convert(promotion, PromotionDto.class));
			}

			etuDto.setAdresseDto(addrDto);
			etuDto.setGroupesDto(lstGrpEtuDto);
			etuDto.setEntrepriseDto(entDto);
			etuDto.setPromotionsDto(lstPromoDto);
			etuDto.setFormateurReferentDto(refDto);
			etuDto.setManagerDto(managDto);
			res.add(etuDto);
		}

		return res;
	}

}
