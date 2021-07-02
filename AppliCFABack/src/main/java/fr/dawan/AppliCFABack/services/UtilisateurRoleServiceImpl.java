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
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.repositories.UtilisateurRoleRepository;

@Service
@Transactional
public class UtilisateurRoleServiceImpl implements UtilisateurRoleService{

	@Autowired
	UtilisateurRoleRepository utilisateurRoleRepository;
	
	@Override
	public List<UtilisateurRoleDto> getAllUtilisateurRole() {
		List<UtilisateurRole> lst = utilisateurRoleRepository.findAll();

		List<UtilisateurRoleDto> lstDto = new ArrayList<UtilisateurRoleDto>();
		for (UtilisateurRole n : lst) {
			lstDto.add(DtoTools.convert(n, UtilisateurRoleDto.class));
		}
		return lstDto;
	}

	@Override
	public List<UtilisateurRoleDto> getAllByPage(int page, int size, String search) {
		List<UtilisateurRole> lst = utilisateurRoleRepository.findAllByIntituleContainingIgnoringCase(search,PageRequest.of(page, size)).get().collect(Collectors.toList());

		// conversion vers Dto
		List<UtilisateurRoleDto> lstDto = new ArrayList<UtilisateurRoleDto>();
		for (UtilisateurRole c : lst) {
			UtilisateurRoleDto uDto = DtoTools.convert(c, UtilisateurRoleDto.class);
			lstDto.add(uDto);
		}
		return lstDto;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(utilisateurRoleRepository.countByIntituleContainingIgnoringCase(search));
	}

	@Override
	public UtilisateurRoleDto getById(long id) {
		Optional<UtilisateurRole> e = utilisateurRoleRepository.findById(id);
		if (e.isPresent()) {
			UtilisateurRoleDto uDto = DtoTools.convert(e.get(), UtilisateurRoleDto.class);
						
			return uDto;
		}			

		return null;
	}

	@Override
	public UtilisateurRoleDto saveOrUpdate(UtilisateurRoleDto uDto) {
		UtilisateurRole u = DtoTools.convert(uDto, UtilisateurRole.class);
		
		u = utilisateurRoleRepository.saveAndFlush(u);
		
		return DtoTools.convert(u, UtilisateurRoleDto.class);
	}

	@Override
	public void deleteById(long id) {
		utilisateurRoleRepository.deleteById(id);
		
	}

}
