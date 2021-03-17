package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.AppliCFABack.dto.CoursDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Cours;
import fr.dawan.AppliCFABack.repositories.CoursRepository;

@Service
@Transactional
public class CoursServiceImpl implements CoursService {

	@Autowired
	 CoursRepository coursRepo;
	
	@Override
	public List<CoursDto> getAll() {
		// TODO Auto-generated method stub
		List<Cours> lst = coursRepo.findAll();
		
		List<CoursDto> lstDto = new ArrayList<CoursDto>();
		for (Cours cours : lst) {
			lstDto.add(DtoTools.convert(cours, CoursDto.class));
		}
		return lstDto;
	}

}
