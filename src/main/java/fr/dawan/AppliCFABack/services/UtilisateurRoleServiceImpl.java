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
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.UtilisateurRoleRepository;

@Service
@Transactional
public class UtilisateurRoleServiceImpl implements UtilisateurRoleService {

    @Autowired
    UtilisateurRoleRepository utilisateurRoleRepository;

    @Autowired
    private DtoMapper mapper = new DtoMapperImpl();

    //recuperation de la liste des roles user
    @Override
    public List<UtilisateurRoleDto> getAllUtilisateurRole() {
        List<UtilisateurRole> lst = utilisateurRoleRepository.findAll();

        List<UtilisateurRoleDto> lstDto = new ArrayList<UtilisateurRoleDto>();
        for (UtilisateurRole n : lst) {
            lstDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(n));
        }
        return lstDto;
    }

  //recuperation de la liste des roles user avec pagination et recherche
    @Override
    public List<UtilisateurRoleDto> getAllByPage(int page, int size, String search) {
        List<UtilisateurRole> lst = utilisateurRoleRepository.findAllByIntituleContainingIgnoringCase(search, PageRequest.of(page, size)).get().collect(Collectors.toList());

        // conversion vers Dto
        List<UtilisateurRoleDto> lstDto = new ArrayList<UtilisateurRoleDto>();
        for (UtilisateurRole c : lst) {
            UtilisateurRoleDto uDto = mapper.UtilisateurRoleToUtilisateurRoleDto(c);
            lstDto.add(uDto);
        }
        return lstDto;
    }

    //count search
    @Override
    public CountDto count(String search) {
        return new CountDto(utilisateurRoleRepository.countByIntituleContainingIgnoringCase(search));
    }

    //recuperation des roles user par id
    @Override
    public UtilisateurRoleDto getById(long id) {
        Optional<UtilisateurRole> e = utilisateurRoleRepository.findById(id);
        if (e.isPresent()) {
            UtilisateurRoleDto uDto = mapper.UtilisateurRoleToUtilisateurRoleDto(e.get());

            return uDto;
        }

        return null;
    }
    
    //methode d'ajout ou modification d'un role user
    @Override
    public UtilisateurRoleDto saveOrUpdate(UtilisateurRoleDto uDto) {
        UtilisateurRole u = DtoTools.convert(uDto, UtilisateurRole.class);

        u = utilisateurRoleRepository.saveAndFlush(u);

        return mapper.UtilisateurRoleToUtilisateurRoleDto(u);
    }

    //recuperartion par intitule
    @Override
    public UtilisateurRoleDto findByIntitule(String intitule) {
        UtilisateurRole role = utilisateurRoleRepository.findByIntituleContaining(intitule);
        return mapper.UtilisateurRoleToUtilisateurRoleDto(role);
    }

    //recuperartion par intitule bis ?
    @Override
    public UtilisateurRole findByIntituleBis(String intitule) {
        return utilisateurRoleRepository.findByIntituleContaining(intitule);
    }

    //methode de suppression d'un role user
    @Override
    public void deleteById(long id) {
        utilisateurRoleRepository.deleteById(id);

    }

}
