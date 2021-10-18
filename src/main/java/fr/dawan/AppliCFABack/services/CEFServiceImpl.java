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
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CEFRepository;
import fr.dawan.AppliCFABack.tools.HashTools;

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
		if (c.isPresent()) {
			CEFDto cefDto = mapper.CEFToCEFDto(c.get());
			cefDto.setPersonneDto(mapper.UtilisateurToUtilisateurDto(c.get().getPersonne()));
			return cefDto;
		}
			
		return null;
	}

	@Override
	public CEFDto saveOrUpdate(CEFDto cDto) {
		CEF c = DtoTools.convert(cDto, CEF.class);
		
		//HashTools throw Exception
		try {
			//Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
			if(c.getPersonne().getId() == 0) {
				c.getPersonne().setPassword(HashTools.hashSHA512(c.getPersonne().getPassword()));
			}else {
				//Si on a modifié le mdp
				CEF cefInDB = cefRepository.getOne(c.getPersonne().getId());
				if(!cefInDB.getPersonne().getPassword().equals(c.getPersonne().getPassword())) {
	                c.getPersonne().setPassword(HashTools.hashSHA512(c.getPersonne().getPassword()));
	            }
			}	
		}catch (Exception e) {
            e.printStackTrace();
        }

		c = cefRepository.saveAndFlush(c);

		return mapper.CEFToCEFDto(c);
	}

	@Override
	public void deleteById(long id) {
		cefRepository.deleteById(id);

	}

}
