package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.AppliCFABack.dto.CursusDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Cursus;
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
			lstDto.add(DtoTools.convert(c, CursusDto.class));
		}
		return lstDto;
	}

	@Override
	public CursusDto saveOrUpdate(CursusDto cDto) {
		// TODO Auto-generated method stub
		Cursus c = DtoTools.convert(cDto, Cursus.class);
		cursusRepo.saveAndFlush(c);
		return DtoTools.convert(c, CursusDto.class) ;
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
