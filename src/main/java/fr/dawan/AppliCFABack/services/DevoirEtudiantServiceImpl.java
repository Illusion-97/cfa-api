package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirEtudiantDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.DevoirEtudiant;
import fr.dawan.AppliCFABack.repositories.DevoirEtudiantRepository;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 * 
 * @author Feres BG Valentin C.
 * @see fr.dawan.AppliCFABack.repositories.DevoirEtudiantRepository
 * @see fr.dawan.AppliCFABack.dto.DevoirEtudiantDto
 * @since 1.0
 * @version 1.0
 *
 */
@Service
@Transactional
public class DevoirEtudiantServiceImpl implements DevoirEtudiantService {

	@Autowired
	private DevoirEtudiantRepository devoirEtudiantRepository;

	/**
	 * Récupération du DevoirEtudiant en fonction de son id
	 * 
	 * @param id DevoirEtudiant
	 * @return DevoirEtudiant DTO
	 * 
	 */
	@Override
	public DevoirEtudiantDto getById(long id) {
		Optional<DevoirEtudiant> dE = devoirEtudiantRepository.findById(id);
		return dE.map(devoirEtudiant -> DtoTools.convert(devoirEtudiant, DevoirEtudiantDto.class))
				.orElseThrow(() -> new NoSuchElementException("DevoirEtudiant non trouvé avec l'ID : " + id));
	}


	/**
	 * Sauvegarde ou mise à jour du DevoirEtudiant
	 * 
	 * @param DevoirEtudiant DTO
	 * @return DevoirEtudiant DTO
	 */
	@Override
	public DevoirEtudiantDto saveOrUpdate(DevoirEtudiantDto tDto) throws SaveInvalidException {
		if (tDto.getDevoirId() == 0 || tDto.getEtudiantId() == 0)
			throw new SaveInvalidException("Inmposible de persister etudiant ou devoir invalide");
		DevoirEtudiant dE = DtoTools.convert(tDto, DevoirEtudiant.class);
		dE = devoirEtudiantRepository.saveAndFlush(dE);

		return DtoTools.convert(dE, DevoirEtudiantDto.class);
	}

	/***
	 * Compte le nombre de DevoirEtudiant en fonction de l'élément de la recherche
	 * 
	 * @param Élément de la recherche
	 * @return Count DTO
	 */
	@Override
	public CountDto count(String search) {
		long nb = devoirEtudiantRepository
				.countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContaining(search, search);

		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	/**
	 * Suppression du DevoirEtudiant
	 * 
	 * @param id DevoirEtudiant
	 */
	@Override
	public void delete(long id) {
		devoirEtudiantRepository.deleteById(id);
	}

	/***
	 * Récupération des DevoirEtudiant en fonction de l'id de l'étudiant
	 * 
	 * @param id Etudiant
	 * @return DevoirEtudiantDto Liste des objets DevoirEtudiantDto
	 */
	@Override
	public List<DevoirEtudiantDto> getAllByEtudiantId(long id) {
		List<DevoirEtudiant> devoirsEtudiants = devoirEtudiantRepository.getAllByEtudiantId(id);
		return devoirsEtudiants.stream()
				.map(devoirEtudiant -> DtoTools.convert(devoirEtudiant, DevoirEtudiantDto.class))
				.collect(Collectors.toList());
	}

	/***
	 * Récupération des DevoirEtudiant en fonction de l'id du devoir
	 * 
	 * @param id Devoir
	 * @return DevoirEtudiantDto Liste des objets DevoirEtudiantDto
	 */
	@Override
	public List<DevoirEtudiantDto> getAllByDevoirId(long id) {
		List<DevoirEtudiant> devoirsEtudiants = devoirEtudiantRepository.getAllByDevoirId(id);
		return devoirsEtudiants.stream()
				.map(devoirEtudiant -> DtoTools.convert(devoirEtudiant, DevoirEtudiantDto.class))
				.collect(Collectors.toList());
	}

}
