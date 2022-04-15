package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirEtudiantDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.DevoirEtudiant;
import fr.dawan.AppliCFABack.repositories.DevoirEtudiantRepository;
@Service
@Transactional
public class DevoirEtudiantServiceImpl implements DevoirEtudiantService {

	@Autowired
	private DevoirEtudiantRepository devoirEtudiantRepository ;
	@Override
	public DevoirEtudiantDto getById(long id) {

		Optional<DevoirEtudiant> dE = devoirEtudiantRepository.findById(id) ;
		if (dE.isPresent()) 
			return DtoTools.convert(dE.get(), DevoirEtudiantDto.class);
		
		return null;
	}

	@Override
	public DevoirEtudiantDto saveOrUpdate(DevoirEtudiantDto tDto) throws Exception {
		if (tDto.getDevoirId() == 0 || tDto.getEtudiantId() == 0) 
			throw new Exception("Inmposible de persister etudiant ou devoir invalide");
		DevoirEtudiant dE = DtoTools.convert(tDto, DevoirEtudiant.class);
		dE = devoirEtudiantRepository.saveAndFlush(dE);
		
		return DtoTools.convert(dE, DevoirEtudiantDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb =  devoirEtudiantRepository.countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContaining(search, search);
  
		CountDto d = new CountDto();
		d.setNb(nb);
	return d;
	}

	@Override
	public void delete(long id) {
		devoirEtudiantRepository.deleteById(id);		
	}

	@Override
	public List<DevoirEtudiantDto> getAllByEtudiantId(long id) {

		List<DevoirEtudiant> devoirsEtudiants = devoirEtudiantRepository.getAllByEtudiantId(id);
		List<DevoirEtudiantDto> result = new ArrayList<DevoirEtudiantDto>();

		for (DevoirEtudiant devoirEtudiant : devoirsEtudiants) {
			result.add(DtoTools.convert(devoirEtudiant, DevoirEtudiantDto.class));
			
		}
		return result;
	}

	@Override
	public List<DevoirEtudiantDto> getAllByDevoirId(long id) {
		List<DevoirEtudiant> devoirsEtudiants = devoirEtudiantRepository.getAllByDevoirId(id);
		List<DevoirEtudiantDto> result = new ArrayList<DevoirEtudiantDto>();

		for (DevoirEtudiant devoirEtudiant : devoirsEtudiants) {
			result.add(DtoTools.convert(devoirEtudiant, DevoirEtudiantDto.class));
			
		}
		return result;
	}

}
