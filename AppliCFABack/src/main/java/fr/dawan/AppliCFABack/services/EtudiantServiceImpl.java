package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;

@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	PromotionRepository promotionRepository;

	// ##################################################
	// # 					CRUD 						#
	// ##################################################

	@Override
	public List<EtudiantDto> getAll() {
		List<Etudiant> lst = etudiantRepository.findAll();

		List<EtudiantDto> res = new ArrayList<EtudiantDto>();

		for (Etudiant c : lst) {

			EtudiantDto etuDto = DtoTools.convert(c, EtudiantDto.class);

//			etuDto.setEntrepriseDto(DtoTools.convert(c.getEntreprise(), EntrepriseDto.class));
//			etuDto.setGroupesDto(getGroupesDto(c));

			res.add(etuDto);
		}

		return res;
	}

	@Override
	public EtudiantDto getById(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);

		if (e.isPresent()) {
			EtudiantDto etuDto = DtoTools.convert(e.get(), EtudiantDto.class);

//			etuDto.setEntrepriseDto(DtoTools.convert(e.get().getEntreprise(), EntrepriseDto.class));
//			etuDto.setGroupesDto(getGroupesEtudiantDto(e.get()));
			
			return etuDto;
		}
			

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
		
		List<Promotion> lst = promotionRepository.findAll();
		List<PromotionDto> res = new ArrayList<PromotionDto>();
		
		for(Promotion p : lst) 
			for(Etudiant e : p.getEtudiants()) 
				if(e.getId() == id) 
					res.add(DtoTools.convert(p, PromotionDto.class));
		
		return res;
	}

//	@Override
//	public List<EntrepriseDto> getEntrepriseByIdEtudiant(long id) {
//		return null;
//	}

//	@Override
//	public List<PersonneDto> getPersonneByIdEtudiant(long id) {
//		return null;
//	}
	
//	@Override
//	public List<NoteDto> getNotesByIdEtudiant(long id) {
//		return null;
//	}

	@Override
	public List<GroupeEtudiantDto> getGroupesByIdEtudiant(long id) {
		Optional<Etudiant> e = etudiantRepository.findById(id);
		
		if (!e.isPresent())
			return null;
		
		return getGroupesEtudiantDto(e.get());
	}

//	@Override
//	public List<AbsenceDto> getAbsencesByIdEtudiant(long id) {
//		return null;
//	}

	// ##################################################
	// # 			     2eme Niveau 					#
	// ##################################################
	
//	@Override
//	public List<ProgrammeCoursDto> getProgrammeCoursByIdEtudiant(long id) {
//		return null;
//	}

//	@Override
//	public List<ProjetDto> getProjetByIdEtudiant(long id) {
//		return null;
//	}

//	@Override
//	public List<AdresseDto> getAdresseByIdEtudiant(long id) {
//		return null;
//	}

	// ##################################################
	// # 			     3eme Niveau 					#
	// ##################################################
	
//	@Override
//	public List<FormateurDto> getFormateursByIdEtudiant(long id) {
//		return null;
//	}

//	@Override
//	public List<DevoirDto> getDevoirsByIdEtudiant(long id) {
//		return null;
//	}

//	@Override
//	public List<ExamenDto> getExamensByIdEtudiant(long id) {
//		return null;
//	}
	
	// ##################################################
	// # 					UTILE 						#
	// ##################################################

	private List<GroupeEtudiantDto> getGroupesEtudiantDto(Etudiant e) {
		List<GroupeEtudiant> lst = e.getGroupes();
		List<GroupeEtudiantDto> lstDto = new ArrayList<GroupeEtudiantDto>();
		for (GroupeEtudiant g : lst) 
			lstDto.add(DtoTools.convert(g, GroupeEtudiantDto.class));
		
		return lstDto;
	}
}
