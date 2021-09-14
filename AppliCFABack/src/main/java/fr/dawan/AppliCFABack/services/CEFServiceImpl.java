package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CEFRepository;

@Service
@Transactional
public class CEFServiceImpl implements CEFService {

	@Autowired
	CEFRepository cefRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<CEFDto> getAllCef() {
		List<CEF> lst = cefRepository.findAll();

		List<CEFDto> lstDto = new ArrayList<CEFDto>();
		for (CEF c : lst) {
			lstDto.add(mapper.CEFToCEFDto(c));
		}
		return lstDto;
	}

	@Override
	public List<CEFDto> getAllCef(int page, int size) {
		List<CEF> lst = cefRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<CEFDto> lstDto = new ArrayList<CEFDto>();
		for (CEF c : lst) {
			lstDto.add(mapper.CEFToCEFDto(c));
		}
		return lstDto;
	}

	@Override
	public CEFDto getById(long id) {
		Optional<CEF> c = cefRepository.findById(id);
		if (c.isPresent())
			return mapper.CEFToCEFDto(c.get());

		return null;
	}

	@Override
	public CEFDto saveOrUpdate(CEFDto cDto) {
		CEF c = DtoTools.convert(cDto, CEF.class);

		c = cefRepository.saveAndFlush(c);

		return mapper.CEFToCEFDto(c);
	}

	@Override
	public void deleteById(long id) {
		cefRepository.deleteById(id);

	}

}
