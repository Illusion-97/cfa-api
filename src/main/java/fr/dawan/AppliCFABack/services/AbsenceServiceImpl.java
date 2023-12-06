package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.tools.JustificatifException;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author Nico
 * @see fr.dawan.appliCFABack.service
 * @since 1.0
 * @version 2.0
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

	private static final Logger logger = Logger.getGlobal();

	/**
	 * Récupération d'un absence par son ID
	 */
	@Override
	public AbsenceDto getById(long id) {
		return absenceRepository.findById(id)
				.map(absence -> DtoTools.convert(absence, AbsenceDto.class))
				.orElse(null);
	}


	/**
	 * Récupération de toutes les absences en fonction d'une intervention ID
	 */
	@Override
	public List<AbsenceDto> getAllByInterventionId(long id) {
		List<Absence> absences = absenceRepository.findAllByInterventionId(id);
		List<AbsenceDto> result = new ArrayList<>(absences.size());
		for (Absence absence : absences) {
			result.add(DtoTools.convert(absence, AbsenceDto.class));
		}
		return result;
	}


	/**
	 * Sauvegarde ou mise a jour d'une absence
	 */
	@Override
	public AbsenceDto saveOrUpdate(AbsenceDto aDto) {
		Absence abs = absenceRepository.save(DtoTools.convert(aDto, Absence.class));
		return DtoTools.convert(abs, AbsenceDto.class);
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
		if(!etudiantOpt.isPresent())
			throw new JustificatifException("Etudiant non trouvé");

		String outputPdf = storageFolder + "justificatifAbsenceEtudiant/" + 
				absenceOpt.get().getJustificatif();

		return outputPdf;
	}




}
