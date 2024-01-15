package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CursusServiceImpl implements CursusService {

	private final CursusRepository cursusRepo;

	private final PromotionRepository promoRepo;
	private final PromotionService promoService;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();


	public CursusServiceImpl(
			@Autowired CursusRepository cursusRepo,
			@Autowired PromotionRepository promoRepo,
			@Autowired PromotionService promoService
	) {
		this.cursusRepo = cursusRepo;
		this.promoRepo = promoRepo;
		this.promoService = promoService;
	}



	/**
	 * Récupération de la liste des cursus
	 *
	 * @return lstDto	Liste des objets cursus
	 */

	@Override
	public List<CursusDto> getAll() {
		return cursusRepo.findAll().stream()
				.map(mapper::cursusToCursusDto)
				.collect(Collectors.toList());
	}

	public Page<PromotionDto> getByIdPromotionAndByPage(long idCursus, int page, int size){

		return promoRepo.getAllPageablePromotionByCursusId(idCursus, PageRequest.of(page, size))
				.map(promo -> mapper.promotionToPromotionDto(promo));
	}

	@Override
	public CountDto countPromotion(long id) {
		return new CountDto(
				promoRepo.countPromotionByCursusId(id));
	}

	/**
	 * Va permettre de récupérer tous les cursus avec pagination
	 * recherche par titre ou formation
	 *
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts du cursus
	 * @return LstDto Liste des objets cursus
	 */

	@Override
	public List<CursusDto> getAllByPage(int page, int size, String search) {
		List<Cursus> lst = cursusRepo.findAllByTitre(search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<CursusDto> lstDto = new ArrayList<>();
		for (Cursus c : lst) {
			CursusDto cDto = mapper.cursusToCursusDto(c);
			List<Formation> lstForm = c.getFormations();
			List<FormationDto> lstFormDto = new ArrayList<>();
			for (Formation form : lstForm) {
				if (form != null)
					lstFormDto.add(mapper.formationToFormationDto(form));
			}
			cDto.setFormationsDto(lstFormDto);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	/**
	 * Recherche d'un cursus
	 *
	 * @param search recherche par titre ou titre d'une formation
	 */

	@Override
	public CountDto count(String search) {
		return new CountDto(
				cursusRepo.countDistinctByTitreContainingIgnoringCaseOrFormationsTitreContainingIgnoringCase(search, search));
	}

	/**
	 * Sauvegarde ou mise à jour d'un cursus
	 *
	 */

	@Override
	public CursusDto saveOrUpdate(CursusDto cDto) {
		Cursus c = DtoTools.convert(cDto, Cursus.class);
		cursusRepo.saveAndFlush(c);
		return mapper.cursusToCursusDto(c);
	}

	/**
	 * Suppression d'un cursus
	 *
	 * @param id	Id concernant le cursus
	 */

	@Override
	public void deleteById(long id) {
		cursusRepo.deleteById(id);

	}

	/**
	 * Récupération des cursus en fonction de l'id
	 *
	 */

	@Override
	public CursusDto getById(long id) {
		Optional<Cursus> c = cursusRepo.findById(id);
		if (c.isPresent()) {

			CursusDto cDto = mapper.cursusToCursusDto(c.get());
			List<FormationDto> lst = new ArrayList<>();
			for (Formation f : c.get().getFormations()) {
				lst.add(mapper.formationToFormationDto(f));
			}
			cDto.setFormationsDto(lst);
			return cDto;
		}
		return null;

	}

	/**
	 * Récupération des cursus en fonction de l'id de la promotion
	 *
	 * @param id	id de la promotion
	 * @return cDto	objet cursus
	 */
	@Override
	public CursusDto getByIdPromotion(long id) {
		PromotionDto pDto = promoService.getById(id);
		return getById(pDto.getCursusDto().getId());
	}

	/**
	 * Récupération des promo en fonction de l'id du cursus
	 *
	 * @param id Id du cursus
	 */
	//recuperation des promo par id cursus
	@Override
	public List<PromotionDto> getPromotionsById(long id) {
		return promoService.getAllByCursusId(id);
	}
}
