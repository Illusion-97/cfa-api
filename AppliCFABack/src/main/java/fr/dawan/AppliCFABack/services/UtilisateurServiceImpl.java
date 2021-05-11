package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	CongeRepository congeRepository;
	
	@Value("${app.storagefolder}")
	private String PARENT_DIRECTORY;
	
	@Override
	public List<UtilisateurDto> getAll() {
		List<Utilisateur> users = utilisateurRepository.findAll();
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
				
		for (Utilisateur u : users) {
			
			List<UtilisateurRoleDto> test = new ArrayList<UtilisateurRoleDto>();
			for(UtilisateurRole r : u.getRoles()) {
				test.add(DtoTools.convert(r, UtilisateurRoleDto.class));
			}
			
			UtilisateurDto user = DtoTools.convert(u, UtilisateurDto.class);
			user.setRolesDto(test);
			res.add(user);
		}
		return res;
	}

	@Override
	public List<UtilisateurDto> getAll(int page, int size) {
		List<Utilisateur> users = utilisateurRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(DtoTools.convert(u, UtilisateurDto.class));
		}
		return res;
	}

	@Override
	public UtilisateurDto getById(long id) {
		Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
		if (userOpt.isPresent())
			return DtoTools.convert(userOpt.get(), UtilisateurDto.class);
		return null;
	}

	@Override
	public UtilisateurDto getName(String name) {
		Utilisateur user = utilisateurRepository.findByName(name);
		if (user != null)
			return DtoTools.convert(user, UtilisateurDto.class);
		return null;
	}

	@Override
	public UtilisateurDto insertUpdate(UtilisateurDto uDto) {
		Utilisateur user = DtoTools.convert(uDto, Utilisateur.class);
		
		utilisateurRepository.saveAndFlush(user);
		
		Path path = Paths.get(PARENT_DIRECTORY + "utilisateurs/" + user.getId());
		
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return DtoTools.convert(user, UtilisateurDto.class);
	}

	@Override
	public void deleteById(long id) {
		utilisateurRepository.deleteById(id);
	}

	@Override
	public List<UtilisateurDto> findByAdresse(String ville) {
		List<Utilisateur> users = utilisateurRepository.findByAdresse(ville);
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(DtoTools.convert(u, UtilisateurDto.class));
		}
		return res;
	}

	@Override
	public List<UtilisateurDto> findByEntreprise(long idEntreprise) {
		List<Utilisateur> users = utilisateurRepository.findByEntreprise(idEntreprise);
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(DtoTools.convert(u, UtilisateurDto.class));
		}
		return res;
	}

	@Override
	public List<JourneePlanningDto> getAllJourneePlanningByIdUtilisateur(long id) {
		List<JourneePlanningDto> result = new ArrayList<JourneePlanningDto>();
		
		Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
		if (!userOpt.isPresent())
			return null;
			
		for(UtilisateurRole role : userOpt.get().getRoles()) {
			switch(role.getIntitule()) {
			case "ETUDIANT":
			default:
				result.addAll(etudiantService.getAllJourneePlanningByIdEtudiant(id));
				break;
			case "FORMATEUR":
				break;
			case "ADMIN":
				break;
			case "CEF":
				break;
				
			}
		}
		
		return result;
	}

	@Override
	public List<CongeDto> getAllCongesByIdUtilisateur(long id) {
		List<CongeDto> result = new ArrayList<CongeDto>();
		
		List<Conge> conges = congeRepository.findByIdUtilisateur(id);
		
		for(Conge c : conges) {
			result.add(DtoTools.convert(c, CongeDto.class));
		}
		
		return result;
	}

	@Override
	public AdresseDto getAdresseByIdUtilisateur(long id) {
		return DtoTools.convert(getUtilisateurById(id).getAdresse(), AdresseDto.class);
	}
	
	// ##################################################
	// # 					UTILE 						#
	// ##################################################
	
	private Utilisateur getUtilisateurById(long id) {
		Optional<Utilisateur> e = utilisateurRepository.findById(id);

		if (e.isPresent())
			return e.get();
		
		return null;
	}

	@Override
	public List<UtilisateurDto> getAllWithObject() {
		List<Utilisateur> lstUsr = utilisateurRepository.findAll();
		List<UtilisateurDto> lstUsrDto = new ArrayList<UtilisateurDto>();

		for (Utilisateur utilisateur : lstUsr) {
			UtilisateurDto utilisateurDto = DtoTools.convert(utilisateur, UtilisateurDto.class);

			AdresseDto adresseDto = DtoTools.convert(utilisateur.getAdresse(), AdresseDto.class);
			utilisateurDto.setAdresseDto(adresseDto);

			EntrepriseDto entrepriseDto = DtoTools.convert(utilisateur.getEntreprise(), EntrepriseDto.class);
			utilisateurDto.setEntrepriseDto(entrepriseDto);

			List<UtilisateurRole> lstUsrRole = utilisateur.getRoles();
			List<UtilisateurRoleDto> lstUsrRoleDto = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole utilisateurRole : lstUsrRole) {
				if (utilisateurRole != null)
					lstUsrRoleDto.add(DtoTools.convert(utilisateurRole, UtilisateurRoleDto.class));
			}
			utilisateurDto.setRolesDto(lstUsrRoleDto);

			lstUsrDto.add(utilisateurDto);
		}

		return lstUsrDto;

	}

}
