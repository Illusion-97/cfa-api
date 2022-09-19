package fr.dawan.AppliCFABack.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.RemunerationDto;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.RemunerationRepository;

@Service
@Transactional
public class RemunerationServiceImpl implements RemunerationService {
	
	@Autowired
	RemunerationRepository remunerationRepo;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public CountDto count(String string) {
		
		return null;
	}

	@Override
	public List<RemunerationDto> getAllByPage(int page, int size, String string) {
		
		return null;
	}

	@Override
	public RemunerationDto getById(long id) {
		
		return null;
	}

	@Override
	public List<RemunerationDto> getAll() {
	
		return null;
	}

	@Override
	public void deleteById(long id) {
		remunerationRepo.deleteById(id);
	}

	@Override
	public RemunerationDto saveOrUpdate(RemunerationDto rDto) {
	
		return null;
	}

}
