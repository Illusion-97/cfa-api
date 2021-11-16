package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Cerfa;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CerfaRepository;

@Service
@Transactional
public class CerfaServiceImpl implements CerfaService {

	@Autowired
	CerfaRepository cerfaRepo;
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Override
	public CountDto count(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CerfaDto> getAllByPage(int page, int size, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CerfaDto getById(long id) {
		// TODO Auto-generated method stub
		Optional<Cerfa> c = cerfaRepo.findById(id);
		CerfaDto cDto = mapper.CerfaToCerfaDto(c.get());
		cDto.setRemuneration1(mapper.RemunerationTORemunerationDto(c.get().getRemuneration1()));
		cDto.setRemuneration2(mapper.RemunerationTORemunerationDto(c.get().getRemuneration2()));
		cDto.setRemuneration3(mapper.RemunerationTORemunerationDto(c.get().getRemuneration3()));
		cDto.setRemuneration4(mapper.RemunerationTORemunerationDto(c.get().getRemuneration4()));
		cDto.setEtudiant(etudiantService.getById(c.get().getEtudiant().getId()));
		return cDto;
	}

	@Override
	public List<CerfaDto> getAll() {
		// TODO Auto-generated method stub
		List<Cerfa> lstCerfa = cerfaRepo.findAll();
		List<CerfaDto> lstCerfaDto = new ArrayList<CerfaDto>();
		
		for(Cerfa c : lstCerfa) {
			CerfaDto cDto = mapper.CerfaToCerfaDto(c);
			cDto.setRemuneration1(mapper.RemunerationTORemunerationDto(c.getRemuneration1()));
			cDto.setRemuneration2(mapper.RemunerationTORemunerationDto(c.getRemuneration2()));
			cDto.setRemuneration3(mapper.RemunerationTORemunerationDto(c.getRemuneration3()));
			cDto.setRemuneration4(mapper.RemunerationTORemunerationDto(c.getRemuneration4()));
			cDto.setEtudiant(etudiantService.getById(c.getEtudiant().getId()));
			lstCerfaDto.add(cDto);
		}
		return lstCerfaDto;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		cerfaRepo.deleteById(id);

	}

	@Override
	public CerfaDto saveOrUpdate(CerfaDto cDto) {
		// TODO Auto-generated method stub
		Cerfa c = DtoTools.convert(cDto, Cerfa.class);
		cerfaRepo.saveAndFlush(c);
		return mapper.CerfaToCerfaDto(c);
	}

}
