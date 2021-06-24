package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.GroupeEtudiantDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;
import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.GroupeEtudiant;
import fr.dawan.AppliCFABack.entities.Projet;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.GroupeEtudiantRepository;

@Service
@Transactional
public class GroupeEtudiantServiceImpl implements GroupeEtudiantService{

	@Autowired
	private GroupeEtudiantRepository groupeEtudiantRepository;
	
	@Override
	public List<GroupeEtudiantDto> getAllGroupeEtudiant() {
		List<GroupeEtudiant> lst = groupeEtudiantRepository.findAll();
		
		List<GroupeEtudiantDto> lstDto = new ArrayList<GroupeEtudiantDto>();
		for (GroupeEtudiant g : lst) {
			lstDto.add(DtoTools.convert(g, GroupeEtudiantDto.class));
		}
		return lstDto;
	}
	
	@Override
	public List<GroupeEtudiantDto> getAllByPage(int page, int size, String search) {
		List<GroupeEtudiant> lst = groupeEtudiantRepository.findAllByNomContainingIgnoringCaseOrEtudiantsNomContainingIgnoringCaseOrEtudiantsPrenomContainingIgnoringCase(search,search, search, PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<GroupeEtudiantDto> lstDto = new ArrayList<GroupeEtudiantDto>();
		for (GroupeEtudiant g : lst) {
			GroupeEtudiantDto gDto = DtoTools.convert(g, GroupeEtudiantDto.class);
			List<EtudiantDto> etudiantsDto = new ArrayList<EtudiantDto>();
			for(Etudiant e : g.getEtudiants()) {
				EtudiantDto eDto = DtoTools.convert(e, EtudiantDto.class);
				List<PromotionDto> pDtos = new ArrayList<PromotionDto>();
				for(Promotion p : e.getPromotions()) {
					pDtos.add(DtoTools.convert(p, PromotionDto.class));
				}
				eDto.setPromotionsDto(pDtos);
				etudiantsDto.add(eDto);
			}
			gDto.setEtudiants(etudiantsDto);
			lstDto.add(gDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(groupeEtudiantRepository.countByNomContainingIgnoringCaseOrEtudiantsNomContainingIgnoringCaseOrEtudiantsPrenomContainingIgnoringCase(search, search, search));
	}

	@Override
	public GroupeEtudiantDto getById(long id) {
		Optional<GroupeEtudiant> g = groupeEtudiantRepository.findById(id);
		
		if(!g.isPresent()) return null;
		
		GroupeEtudiantDto gDto = DtoTools.convert(g.get(), GroupeEtudiantDto.class);
		List<EtudiantDto> etudiantsDto = new ArrayList<EtudiantDto>();
		for(Etudiant e : g.get().getEtudiants()) {
			EtudiantDto eDto = DtoTools.convert(e, EtudiantDto.class);
			List<PromotionDto> pDtos = new ArrayList<PromotionDto>();
			for(Promotion p : e.getPromotions()) {
				pDtos.add(DtoTools.convert(p, PromotionDto.class));
			}
			eDto.setPromotionsDto(pDtos);
			etudiantsDto.add(eDto);
		}
		gDto.setEtudiants(etudiantsDto);
		
		return gDto;
	}

	@Override
	public GroupeEtudiantDto saveOrUpdate(GroupeEtudiantDto gDto) {
		System.out.println("GroupeEtudiantDto saveOrUpdate");
		GroupeEtudiant g = groupeEtudiantRepository.saveAndFlush(DtoTools.convert(gDto, GroupeEtudiant.class));
		return DtoTools.convert(g, GroupeEtudiantDto.class);
	}

	@Override
	public void deleteById(long id) {
		groupeEtudiantRepository.deleteById(id);
		
	}

	@Override
	public List<EtudiantDto> getEtudiantsByGroupeId(long id) {
		Optional<GroupeEtudiant> g = groupeEtudiantRepository.findById(id);
		if(!g.isPresent())
			return null;
		
		List<EtudiantDto> result = new ArrayList<EtudiantDto>();
		for(Etudiant e : g.get().getEtudiants()) {
			EtudiantDto eDto = DtoTools.convert(e, EtudiantDto.class);
			List<PromotionDto> pDtos = new ArrayList<PromotionDto>();
			for(Promotion p : e.getPromotions()) {
				pDtos.add(DtoTools.convert(p, PromotionDto.class));
			}
			eDto.setPromotionsDto(pDtos);
			result.add(eDto);
		}		
		
		return result;
		
	}

	

}
