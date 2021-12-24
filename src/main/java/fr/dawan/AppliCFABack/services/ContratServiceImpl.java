package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.ContratDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Contrat;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ContratRepository;

@Service
@Transactional
public class ContratServiceImpl implements ContratService{
	
	@Autowired
	ContratRepository contratRepository;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<ContratDto> getAllContrat() {
		List<Contrat> lst = contratRepository.findAll();
		
		List<ContratDto> lstDto = new ArrayList<ContratDto>();
		for (Contrat c : lst) {
			lstDto.add(mapper.ContratToContratDto(c));
		}
		return lstDto;
	}

	@Override
	public ContratDto getById(long id) {
		Optional<Contrat> c = contratRepository.findById(id);
		if (c.isPresent()) {
			ContratDto cDto = mapper.ContratToContratDto(c.get());
			return cDto;
		}
		return null;
	}

	@Override
	public List<ContratDto> getAllByPage(int page, int size, String search) {
		List<Contrat> lst = contratRepository.findAllByMaitreApprentissageUtilisateurPrenomContainingIgnoringCaseOrMaitreApprentissageUtilisateurNomContainingIgnoringCase(search,search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<ContratDto> lstDto = new ArrayList<ContratDto>();
		for (Contrat c : lst) {
			ContratDto cDto = mapper.ContratToContratDto(c);
			lstDto.add(cDto);
		}
		return lstDto;
	}

	@Override
	public ContratDto saveOrUpdate(ContratDto cDto) {
		Contrat c = DtoTools.convert(cDto, Contrat.class);
		contratRepository.saveAndFlush(c);
		return mapper.ContratToContratDto(c);
	}

	@Override
	public void deleteById(long id) {
		contratRepository.deleteById(id);
		
	}

	@Override
	public ContratDto count(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContratDto getByEtudiantId(long id) {
		Contrat c = contratRepository.findByEtudiantId(id);
	if (c != null) {
		ContratDto cDto = mapper.ContratToContratDto(c);
		return cDto;
	}
	return null;
	}

}
