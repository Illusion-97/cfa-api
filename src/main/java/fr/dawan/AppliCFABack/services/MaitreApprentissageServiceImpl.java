package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.MaitreApprentissageDto;
import fr.dawan.AppliCFABack.entities.MaitreApprentissage;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.MaitreApprentissageRepository;
import fr.dawan.AppliCFABack.tools.HashTools;

@Service
@Transactional
public class MaitreApprentissageServiceImpl implements MaitreApprentissageService {

	@Autowired
	MaitreApprentissageRepository maitreApprentissageRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Override
	public List<MaitreApprentissageDto> getAllMaitreApprentissage() {
		List<MaitreApprentissage> lst = maitreApprentissageRepository.findAll();

		List<MaitreApprentissageDto> lstDto = new ArrayList<MaitreApprentissageDto>();
		for (MaitreApprentissage ma : lst) {
			lstDto.add(mapper.MaitreApprentissageToMaitreApprentissageDto(ma));
		}
		return lstDto;
	}

	@Override
	public List<MaitreApprentissageDto> getAllMaitreApprentissage(int page, int size) {
		List<MaitreApprentissage> lst = maitreApprentissageRepository.findAll(PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<MaitreApprentissageDto> lstDto = new ArrayList<MaitreApprentissageDto>();
		for (MaitreApprentissage ma : lst) {
			lstDto.add(mapper.MaitreApprentissageToMaitreApprentissageDto(ma));
		}
		return lstDto;
	}

	@Override
	public MaitreApprentissageDto getById(long id) {
		Optional<MaitreApprentissage> c = maitreApprentissageRepository.findById(id);
		if (c.isPresent())
			return mapper.MaitreApprentissageToMaitreApprentissageDto(c.get());

		return null;
	}

	@Override
	public MaitreApprentissageDto saveOrUpdate(MaitreApprentissageDto maDto) {
		MaitreApprentissage ma = DtoTools.convert(maDto, MaitreApprentissage.class);
		
		//HashTools throw Exception
		try {
			//Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
			if(ma.getUtilisateur().getId() == 0) {
				ma.getUtilisateur().setPassword(HashTools.hashSHA512(ma.getUtilisateur().getPassword()));
			}else {
				//Si on a modifié le mdp
				MaitreApprentissage maInDB = maitreApprentissageRepository.getOne(ma.getUtilisateur().getId());
				if(!maInDB.getUtilisateur().getPassword().equals(ma.getUtilisateur().getPassword())) {
					ma.getUtilisateur().setPassword(HashTools.hashSHA512(ma.getUtilisateur().getPassword()));
	            }
			}	
		}catch (Exception e) {
            e.printStackTrace();
        }

		ma = maitreApprentissageRepository.saveAndFlush(ma);

		return mapper.MaitreApprentissageToMaitreApprentissageDto(ma);
	}

	@Override
	public void deleteById(long id) {
		maitreApprentissageRepository.deleteById(id);
		
	}

}
