package fr.dawan.AppliCFABack.services;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.SupportCoursDto;

@Service
@Transactional
public class SupportCoursServiceImpl implements SupportCoursService {


	
	
	@Override
	public SupportCoursDto getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SupportCoursDto saveOrUpdate(SupportCoursDto tDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountDto count(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SupportCoursDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
