package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CursusRepository;

@Transactional
@Service
public class CursusServiceImpl implements CursusService {

	@Autowired
	CursusRepository cursusRepo;
	
	@Autowired
	PromotionService promoService;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<CursusDto> getAll() {
		List<Cursus> lst = cursusRepo.findAll();
		List<CursusDto> lstDto = new ArrayList<CursusDto>();
		for (Cursus c : lst) {
			lstDto.add(mapper.CursusToCursusDto(c));
		}
		return lstDto;
	}

	@Override
	public List<CursusDto> getAllByPage(int page, int size, String search) {
		List<Cursus> lst = cursusRepo
				.findDistinctByTitreContainingIgnoringCaseOrFormationsTitreContainingIgnoringCase(search, search,
						PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		// conversion vers Dto
		List<CursusDto> lstDto = new ArrayList<CursusDto>();
		for (Cursus c : lst) {
			CursusDto cDto = mapper.CursusToCursusDto(c);
			List<Formation> lstForm = c.getFormations();
			List<FormationDto> lstFormDto = new ArrayList<FormationDto>();
			for (Formation form : lstForm) {
				if (form != null)
					lstFormDto.add(mapper.FormationToFormationDto(form));
			}
			cDto.setFormationsDto(lstFormDto);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(
				cursusRepo.countDistinctByTitreContainingIgnoringCaseOrFormationsTitreContainingIgnoringCase(search, search));
	}

	@Override
	public CursusDto saveOrUpdate(CursusDto cDto) {
		Cursus c = DtoTools.convert(cDto, Cursus.class);
		cursusRepo.saveAndFlush(c);
		return mapper.CursusToCursusDto(c);
	}

	@Override
	public void deleteById(long id) {
		cursusRepo.deleteById(id);

	}

	@Override
	public CursusDto getById(long id) {
		Optional<Cursus> c = cursusRepo.findById(id);
		if (c.isPresent()) {

			CursusDto cDto = mapper.CursusToCursusDto(c.get());
			List<FormationDto> lst = new ArrayList<FormationDto>();
			for (Formation f : c.get().getFormations()) {
				lst.add(mapper.FormationToFormationDto(f));
			}
			cDto.setFormationsDto(lst);
			return cDto;
		}
		return null;

	}

	@Override
	public CursusDto getByIdPromotion(long id) {
		// TODO Auto-generated method stub
		PromotionDto pDto = promoService.getById(id);
		CursusDto cDto = getById(pDto.getCursusDto().getId());
		return cDto;
	}

	@Override
	public List<PromotionDto> getPromotionsById(long id) {
		return promoService.getAllByCursusId(id);
	}


}
