package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
 
@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService{

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Override
	public List<EtudiantDto> getAll() {
		List<Etudiant> lst = etudiantRepository.findAll();
		List<EtudiantDto> res = new ArrayList<EtudiantDto>();
		
		for(Etudiant c : lst) 
			res.add(DtoTools.convert(c, EtudiantDto.class));
		
		return res;
	}

	@Override
	public EtudiantDto getById(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);

		if (e.isPresent())
			return DtoTools.convert(e.get(), EtudiantDto.class);

		return null;
	}

	@Override
	public EtudiantDto saveOrUpdate(EtudiantDto e) {
		Etudiant etudiant = etudiantRepository.saveAndFlush(DtoTools.convert(e, Etudiant.class));
		return DtoTools.convert(etudiant, EtudiantDto.class);
	}

	@Override
	public void deleteById(long id) {
		etudiantRepository.deleteById(id);
	}

}
