package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CentreFormationDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CentreFormationServiceImpl implements CentreFormationService {

	private final CentreFormationRepository centreFormationRepository;


	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	public CentreFormationServiceImpl(
			@Autowired CentreFormationRepository centreFormationRepository
	) {
		this.centreFormationRepository = centreFormationRepository;
	}

	/**
	 * Récupération de la liste des centres de formation
	 *
	 * @return lstDto	Liste des objets centre de formation
	 */

	@Override
	public List<CentreFormationDto> getAllCentreFormation() {
		List<CentreFormation> lst = centreFormationRepository.findAll();
		return lst.stream()
				.map(cf -> {
					CentreFormationDto cDto = mapper.centreFormationToCentreFormationDto(cf);
					cDto.setEntrepriseDto(mapper.entrepriseToEntrepriseDto(cf.getEntreprise()));
					cDto.setAdresseDto(mapper.adresseToAdresseDto(cf.getAdresse()));
					return cDto;
				})
				.collect(Collectors.toList());
	}

	/**
	 * Va permettre de récupérer tous les centres de formations avec pagination
	 *
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @return LstDto Liste des objets centre de formation
	 */

	@Override
	public List<CentreFormationDto> getAllCentreFormation(int page, int size) {
		List<CentreFormation> lst = centreFormationRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());

		// conversion vers Dto
		return convertCentreFormationToDto(lst);
	}

	/**
	 * Récupération des centres de formation en fonction de l'id
	 *
	 */

	@Override
	public CentreFormationDto getById(long id) {
		Optional<CentreFormation> cf = centreFormationRepository.findById(id);
		if (!cf.isPresent()) return null;

        return mapper.centreFormationToCentreFormationDto(cf.get());
	}

	/**
	 * Sauvegarde ou mise à jour d'un centre de formation
	 *
	 */

	@Override
	public CentreFormationDto saveOrUpdate(CentreFormationDto cfDto) {
		CentreFormation cf = DtoTools.convert(cfDto, CentreFormation.class);

		cf = centreFormationRepository.saveAndFlush(cf);

		return mapper.centreFormationToCentreFormationDto(cf);
	}

	/**
	 * Suppression d'un centre de formation
	 *
	 * @param id	Id concernant un centre de formation
	 */

	@Override
	public void deleteById(long id) {
		centreFormationRepository.deleteById(id);

	}

	/**
	 * Recherche d'un centre de foramtion
	 *
	 * @param search recherche par nom
	 */

	@Override
	public CountDto count(String search) {

		return new CountDto(centreFormationRepository.countByNomContaining(search));
	}

	/**
	 * Va permettre de récupérer tous les centres de formation avec pagination
	 *
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléments centres de formation
	 * @return LstDto Liste des objets centre de formation
	 */

	@Override
	public List<CentreFormationDto> getAllCentreFormations(int page, int size, String search) {
		List<CentreFormation> cf = centreFormationRepository.findAllByNomContaining(search, PageRequest.of(page, size)).get().collect(Collectors.toList());
		return convertCentreFormationToDto(cf);
	}

	private List<CentreFormationDto> convertCentreFormationToDto(List<CentreFormation> cf) {
        return cf.stream().map(c -> {
			CentreFormationDto cfDto = mapper.centreFormationToCentreFormationDto(c);
			cfDto.setEntrepriseDto(mapper.entrepriseToEntrepriseDto(c.getEntreprise()));
			cfDto.setAdresseDto(mapper.adresseToAdresseDto(c.getAdresse()));
			return cfDto;
		}).collect(Collectors.toList());
	}

}
