package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Devoir;
import fr.dawan.AppliCFABack.repositories.DevoirRepository;

@Service
@Transactional
public class DevoirServiceImpl implements DevoirService {

	@Autowired
	private DevoirRepository devoirRepository;

	@Override
	public List<DevoirDto> getAllDevoir() {
		List<Devoir> lst = devoirRepository.findAll();

		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			lstDto.add(DtoTools.convert(d, DevoirDto.class));
		}
		return lstDto;
	}

	@Override
	public List<DevoirDto> getAllDevoir(int page, int size) {
		List<Devoir> lst = devoirRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<DevoirDto> lstDto = new ArrayList<DevoirDto>();
		for (Devoir d : lst) {
			lstDto.add(DtoTools.convert(d, DevoirDto.class));
		}
		return lstDto;
	}

	@Override
	public DevoirDto getById(long id) {
		Optional<Devoir> d = devoirRepository.findById(id);
		if (d.isPresent())
			return DtoTools.convert(d.get(), DevoirDto.class);

		return null;
	}

	@Override
	public DevoirDto saveOrUpdate(DevoirDto dDto) {
		Devoir d = DtoTools.convert(dDto, Devoir.class);

		d = devoirRepository.saveAndFlush(d);

		return DtoTools.convert(d, DevoirDto.class);
	}

	@Override
	public void deleteById(long id) {
		devoirRepository.deleteById(id);

	}

	@Override
	public CountDto count() {
		// TODO Auto-generated method stub
		return new CountDto(devoirRepository.count());
	}

}
