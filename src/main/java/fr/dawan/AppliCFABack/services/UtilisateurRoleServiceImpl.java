package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
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
    
    private static Logger logger = Logger.getGlobal();

    /**
	 * Récupération de la liste des roles utilisateurs
	 * 
	 * @return lstDto	Liste des objets role utilisateur
	 */
    
    @Override
    public List<UtilisateurRoleDto> getAllUtilisateurRole() {
        List<UtilisateurRole> lst = utilisateurRoleRepository.findAll();

        List<UtilisateurRoleDto> lstDto = new ArrayList<>();
        for (UtilisateurRole n : lst) {
            lstDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(n));
        }
        return lstDto;
    }

    /**
	 * Va permettre de récupérer tous les roles utilisateur avec pagination
	 * et recherche
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	élément role utilisateur (intitule)
	 * @return lstDto Liste des objets roles utilisateur
	 */
	
    @Override
    public List<UtilisateurRoleDto> getAllByPage(int page, int size, String search) {
        List<UtilisateurRole> lst = utilisateurRoleRepository.findAllByIntituleContainingIgnoringCase(search, PageRequest.of(page, size)).get().collect(Collectors.toList());

        // conversion vers Dto
        List<UtilisateurRoleDto> lstDto = new ArrayList<>();
        for (UtilisateurRole c : lst) {
            UtilisateurRoleDto uDto = mapper.UtilisateurRoleToUtilisateurRoleDto(c);
            lstDto.add(uDto);
        }
        return lstDto;
    }

    /**
	 * Recherche de role utilisateur / nb
	 * 
	 * @param search recherche par intitule
	 */
    
    @Override
    public CountDto count(String search) {
        return new CountDto(utilisateurRoleRepository.countByIntituleContainingIgnoringCase(search));
    }

    /**
	 * Récupération des roles utilisateur en fonction de l'id
	 * 
	 * @param id	id du role utilisateur
	 */
	
    @Override
    public UtilisateurRoleDto getById(long id) {
        Optional<UtilisateurRole> e = utilisateurRoleRepository.findById(id);
        if (e.isPresent()) {
            //UtilisateurRoleDto uDto = mapper.UtilisateurRoleToUtilisateurRoleDto(e.get());

            return mapper.UtilisateurRoleToUtilisateurRoleDto(e.get());
        }

        return null;
    }
    
	/**
	 * Sauvegarde ou mise à jour d'un role utilisateur
	 * 
	 */
	
    @Override
    public UtilisateurRoleDto saveOrUpdate(UtilisateurRoleDto uDto) {
        UtilisateurRole u = DtoTools.convert(uDto, UtilisateurRole.class);

        u = utilisateurRoleRepository.saveAndFlush(u);

        return mapper.UtilisateurRoleToUtilisateurRoleDto(u);
    }

	/**
	 * Recuperation du role utilisateur par intitule
	 * 
	 * @param Intitule	intitule du role
	 * 
	 */
	
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

    /**
	 * Suppression d'un utilisateur role
	 * 
	 * @param id	Id concernant un utilisateur role
	 */
    
    @Override
    public void deleteById(long id) {
        utilisateurRoleRepository.deleteById(id);

    }

}
