package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.NoteDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;
import fr.dawan.AppliCFABack.repositories.NoteRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;

@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	AbsenceRepository absenceRepository;
	
	@Autowired 
	InterventionRepository interventionRepository;
	
	// ##################################################
	// # 					CRUD 						#
	// ##################################################

	@Override
	public List<EtudiantDto> getAll() {
		List<Etudiant> lst = etudiantRepository.findAll();
		List<EtudiantDto> res = new ArrayList<EtudiantDto>();

		for (Etudiant e : lst) 
			res.add(DtoTools.convert(e, EtudiantDto.class));
		
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
		List<Promotion> lst = getEtudiantById(id).getPromotions();
		List<PromotionDto> lstDto = new ArrayList<PromotionDto>();
		for (Promotion g : lst) 
			lstDto.add(DtoTools.convert(g, PromotionDto.class));
		
		return lstDto;
	}

	@Override
	public List<GroupeEtudiantDto> getGroupesByIdEtudiant(long id) {
		List<GroupeEtudiant> lst = getEtudiantById(id).getGroupes();
		List<GroupeEtudiantDto> lstDto = new ArrayList<GroupeEtudiantDto>();
		for (GroupeEtudiant g : lst) 
			lstDto.add(DtoTools.convert(g, GroupeEtudiantDto.class));
		
		return lstDto;
	}
	
	@Override
	public EntrepriseDto getEntrepriseByIdEtudiant(long id) {
		return DtoTools.convert(getEtudiantById(id).getEntreprise(), EntrepriseDto.class);
	}
	
	@Override
	public AdresseDto getAdresseByIdEtudiant(long id) {
		return DtoTools.convert(getEtudiantById(id).getAdresse(), AdresseDto.class);
	}
	
	// ##################################################
	// # 				2eme Niveau 					#
	// ##################################################
	
	@Override
	public List<NoteDto> getNotesByIdEtudiant(long id) {
		List<Note> lst = noteRepository.getNotesByIdEtudiant(id);
		List<NoteDto> res = new ArrayList<NoteDto>();
		
		for(Note n : lst)
			res.add(DtoTools.convert(n, NoteDto.class));
		
		return res;
	}


	@Override
	public List<AbsenceDto> getAbsencesByIdEtudiant(long id) {
		List<Absence> lst = absenceRepository.getAbsencesByIdEtudiant(id);
		List<AbsenceDto> res = new ArrayList<AbsenceDto>();
		
		for(Absence n : lst)
			res.add(DtoTools.convert(n, AbsenceDto.class));
		
		return res;
	}

	// ##################################################
	// # 			     3eme Niveau 					#
	// ##################################################
	
	@Override
	public List<InterventionDto> getIntervenionByIdEtudiant(long id) {	
		List<Intervention> interventions = new ArrayList<Intervention>();
		List<InterventionDto> res = new ArrayList<InterventionDto>();
		
		for(Promotion p : getEtudiantById(id).getPromotions())
			interventions.addAll(interventionRepository.getInterventionsByIdPromotion(p.getId()));
		
		for(Intervention i : interventions)
			res.add(DtoTools.convert(i, InterventionDto.class));
		
		return res;
	}
	
	@Override
	public List<UtilisateurDto> getFormateursByIdEtudiant(long id) {
		return null;
	}
	
	// ##################################################
	// # 					UTILE 						#
	// ##################################################
	
	private Etudiant getEtudiantById(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);

		if (e.isPresent())
			return e.get();
		
		return null;
	}
}
