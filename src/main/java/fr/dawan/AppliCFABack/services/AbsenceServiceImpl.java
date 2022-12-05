package fr.dawan.AppliCFABack.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.tools.JustificatifException;
import fr.dawan.AppliCFABack.tools.PdfTools;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 1.0
 * @return Implémentation de l'interface AbsenceService
 */
@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService {

	@Autowired
	AbsenceRepository absenceRepository;

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${app.storagefolder}")
	private String storageFolder;
	
	private static Logger logger = Logger.getGlobal();

	/**
	 * Récupération d'un absence par son ID
	 */
	@Override
	public AbsenceDto getById(long id) {
		Optional<Absence> absence = absenceRepository.findById(id);
		if (absence.isPresent()) {
			return DtoTools.convert(absence.get(), AbsenceDto.class);

		}
		return null;
	}

	/**
	 * Récupération de toutes les absences en fonction d'une intervention ID
	 */
	@Override
	public List<AbsenceDto> getAllByInterventionId(long id) {
		List<Absence> absences = absenceRepository.findAllByInterventionId(id);
		List<AbsenceDto> result = new ArrayList<>();
		for (Absence a : absences) {
			result.add(DtoTools.convert(a, AbsenceDto.class));
		}
		return result;
	}

	/**
	 * Sauvegade ou mise a jour d'une absence
	 */
	@Override
	public AbsenceDto saveOrUpdate(AbsenceDto aDto) {
		Absence abs = DtoTools.convert(aDto, Absence.class);
		Absence absDb = absenceRepository.saveAndFlush(abs);
		return DtoTools.convert(absDb, AbsenceDto.class);
	}

	/**
	 * Suppression d'une absence
	 */

	@Override
	public void delete(long id) {
		absenceRepository.deleteById(id);
	}

	/**
	 * Récupération de toutes les absences en fonctions d'un EtudiantId
	 */
	@Override
	public List<AbsenceDto> getAllByEtudiantId(long id) {
		List<Absence> absences = absenceRepository.findAllByEtudiantId(id);
		List<AbsenceDto> result = new ArrayList<>();
		for (Absence a : absences) {
			result.add(DtoTools.convert(a, AbsenceDto.class));
		}
		return result;
	}

	/**
	 * Récupération de toutes les absences dont les noms ou prénoms des étudiants
	 * contiennent le champs de recherche
	 */
	@Override
	public List<AbsenceDto> getAllByNomPrenomContaining(String search) {
		List<Absence> absences = absenceRepository
				.findByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(
						"%" + search + "%");
		List<AbsenceDto> result = new ArrayList<>();
		for (Absence a : absences) {
			result.add(DtoTools.convert(a, AbsenceDto.class));
		}
		return result;
	}

//	@Override
//	public List<AbsenceDto> getAllByPromotionId(long id) {
//		List<Absence> absences = absenceRepository.findAllByPromotionId(id);
//		List<AbsenceDto> result = new ArrayList<AbsenceDto>();
//		for(Absence a : absences) {
//			result.add(DtoTools.convert(a, AbsenceDto.class));
//		}
//		return result;
//	}

	/**
	 * Récupère le nom d'occurences du champs de recherche
	 */
	@Override
	public CountDto count(String search) {
		return new CountDto(absenceRepository.countByUtilisateurEtudiantNomOrPrenomContaining(search));
	}
	
	@Override
	public String getJustificatifByAbsenceId(long idAbsence) throws JustificatifException{
		Optional<Absence> absenceOpt = absenceRepository.findByAbsenceId(idAbsence);
		Optional<Etudiant> etudiantOpt = etudiantRepository.findById(absenceOpt.get().getEtudiant().getId());
		//absenceOpt.get().get
		if(!etudiantOpt.isPresent())
			throw new JustificatifException("Etudiant non trouvé");
		
		String outputPdf = storageFolder + "justificatifAbsenceEtudiant/" + 
				absenceOpt.get().getJustificatif();

		return outputPdf;
	}

//	@Autowired
//	private DtoMapper mapper = new DtoMapperImpl();
//
//	//recuperation de la liste des absences
//	@Override
//	public List<AbsenceDto> getAllAbsence() {
//		List<Absence> lst = absenceRepository.findAll();
//
//		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
//		for (Absence a : lst) {
//			AbsenceDto absDto = mapper.AbsenceToAbsenceDto(a);
//			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(a.getEtudiant());
//			absDto.setEtudiantDto(etuDto);
//			lstDto.add(absDto);
//
//		}
//		return lstDto;
//	}
//
//	//recuperation des absences par id
//	@Override
//	public AbsenceDto getById(long id) {
//		Optional<Absence> a = absenceRepository.findById(id);
//		if (!a.isPresent()) return null;
//		
//		AbsenceDto aDto =mapper.AbsenceToAbsenceDto(a.get());
//		aDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(a.get().getEtudiant()));
//		aDto.getEtudiantDto().setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(a.get().getEtudiant().getUtilisateur()));
//		
//		return aDto;
//	}
//
//	//methode d'ajout ou modification d'une absence
//	@Override
//	public AbsenceDto saveOrUpdate(AbsenceDto aDto) {
//		Absence a = DtoTools.convert(aDto, Absence.class);
//
//		a = absenceRepository.saveAndFlush(a);
//
//		return mapper.AbsenceToAbsenceDto(a);
//	}
//
//	//methode de suppression d'une absence
//	@Override
//	public void deleteById(long id) {
//		absenceRepository.deleteById(id);
//
//	}
//
//	//methode count
//	@Override
//	public CountDto count(String search) {
//		return new CountDto(
//				absenceRepository.countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(search, search));
//	}
//
//	//recuperation de la liste des absences avec pagination et recherche
//	@Override
//	public List<AbsenceDto> getAllAbsence(int page, int size, String search) {
//		List<Absence> lst = absenceRepository.findByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContainingAllIgnoreCase(search,
//				search, PageRequest.of(page, size)).get().collect(Collectors.toList());
//		List<AbsenceDto> lstDto = new ArrayList<AbsenceDto>();
//		for (Absence a : lst) {
//			AbsenceDto absDto = mapper.AbsenceToAbsenceDto(a);
//			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(a.getEtudiant());
//////			UtilisateurDto ref = DtoTools.convert(a.getEtudiant().getFormateurReferent(), UtilisateurDto.class);
//////			etuDto.setFormateurReferentDto(ref);
////			List<Promotion> lsPromotions = a.getEtudiant().getPromotions();
////			List<PromotionDto> lstPromotionDtos = new ArrayList<PromotionDto>();
////			for (Promotion p : lsPromotions) {
////				PromotionDto promoDto = DtoTools.convert(p, PromotionDto.class);
////				lstPromotionDtos.add(promoDto);
////				etuDto.setPromotionsDto(lstPromotionDtos);
////			}
//
//			absDto.setEtudiantDto(etuDto);
//			lstDto.add(absDto);
//		}
//		return lstDto;
//	}
//
//	//recuperation des absences par etudiant id
//	@Override
//	public List<AbsenceDto> getAllByIdEtudiant(long id) {
//		List<AbsenceDto> result = new ArrayList<AbsenceDto>();
//		List<Absence> list = absenceRepository.findAllByEtudiantId(id);
//		for(Absence a : list) {
//			AbsenceDto absence = mapper.AbsenceToAbsenceDto(a);
//			absence.setEtudiantDto(mapper.EtudiantToEtudiantDto(etudiantRepository.getOne(id)));
//			absence.getEtudiantDto().setUtilisateurDto(mapper.UtilisateurToUtilisateurDto(etudiantRepository.getOne(id).getUtilisateur()));
//			result.add(absence);
//		}
//		return result;
//	}

}
