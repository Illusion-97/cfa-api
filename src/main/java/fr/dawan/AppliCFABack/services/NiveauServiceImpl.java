package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.NiveauDto;
import fr.dawan.AppliCFABack.entities.Positionnement.Niveau;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NiveauServiceImpl implements  NiveauService {

	@Override
	public List<NiveauDto> getAllNiveaux() {
		List<NiveauDto> niveauxDto = new ArrayList<>();
		DtoTools mappeur = new DtoTools();
		Niveau[] niveaux = 	Niveau.values();
		
		for (Niveau niveau : niveaux) {
			niveauxDto.add(mappeur.niveauToNiveauDto(niveau));
		}
	
		return niveauxDto;
	}

	
}
