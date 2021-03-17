package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PersonneDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
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

	// ##################################################
	// # 			     1er Niveau 					#
	// ##################################################
	
	@Override
	public List<PromotionDto> getPromotionsByIdEtudiant(long id) {
		return etudiantRepository.getPromotionsByIdEtudiant(id);
	}

	@Override
	public List<EntrepriseDto> getEntrepriseByIdEtudiant(long id) {
		return etudiantRepository.getEntrepriseByIdEtudiant(id);
	}

	@Override
	public List<PersonneDto> getPersonneByIdEtudiant(long id) {
		return etudiantRepository.getPersonneByIdEtudiant(id);
	}
	
	@Override
	public List<NoteDto> getNotesByIdEtudiant(long id) {
		return etudiantRepository.getNotesByIdEtudiant(id);
	}

	@Override
	public List<PromotionDto> getGroupesByIdEtudiant(long id) {
		return etudiantRepository.getGroupesByIdEtudiant(id);
	}

	@Override
	public List<PromotionDto> getAbsencesByIdEtudiant(long id) {
		return etudiantRepository.getAbsencesByIdEtudiant(id);
	}

	// ##################################################
	// # 			     2eme Niveau 					#
	// ##################################################
}
