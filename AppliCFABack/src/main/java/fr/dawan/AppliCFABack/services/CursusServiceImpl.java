package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.repositories.CursusRepository;

@Transactional
@Service
public class CursusServiceImpl implements CursusService {

	@Autowired
	CursusRepository cursusRepo;

	@Override
	public List<CursusDto> getAll() {
		List<Cursus> lst = cursusRepo.findAll();
		List<CursusDto> lstDto = new ArrayList<CursusDto>();
		for (Cursus c : lst) {
			CursusDto cursusDto = DtoTools.convert(c, CursusDto.class);
			List<Formation> lstForm = c.getFormations();
			List<FormationDto> lstFormDto = new ArrayList<FormationDto>();
			for (Formation formation : lstForm) {
				if (formation != null)
					lstFormDto.add(DtoTools.convert(formation, FormationDto.class));
			}
			cursusDto.setFormationsDto(lstFormDto);
			lstDto.add(cursusDto);
		}
		return lstDto;
	}

	@Override
	public CursusDto saveOrUpdate(CursusDto cDto) {
		// TODO Auto-generated method stub
		Cursus c = DtoTools.convert(cDto, Cursus.class);
		cursusRepo.saveAndFlush(c);
		return DtoTools.convert(c, CursusDto.class);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		cursusRepo.deleteById(id);

	}

	@Override
	public CursusDto getById(long id) {
		// TODO Auto-generated method stub
		Optional<Cursus> c = cursusRepo.findById(id);
		if (c.isPresent()) {

			CursusDto cDto = DtoTools.convert(c, CursusDto.class);
			return cDto;
		}
		return null;

	}

}
