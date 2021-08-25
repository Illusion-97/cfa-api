package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EntrepriseDto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRoleRepository;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	UtilisateurRoleRepository utilisateurRoleRepository;

	@Autowired
	AdresseRepository adresseRepository;
	
	@Autowired
	EtudiantService etudiantService;

	@Autowired
	CongeRepository congeRepository;

	@Autowired
	FilesService filesService;


	@Autowired
	AbsenceRepository absenceRepository;
	@Override
	public List<UtilisateurDto> getAll() {
		List<Utilisateur> users = utilisateurRepository.findAll();
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();

		for (Utilisateur u : users) {

			List<UtilisateurRoleDto> test = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole r : u.getRoles()) {
				test.add(DtoTools.convert(r, UtilisateurRoleDto.class));
			}

			UtilisateurDto user = DtoTools.convert(u, UtilisateurDto.class);
			user.setRolesDto(test);
			res.add(user);
		}
		return res;
	}

	@Override
	public List<UtilisateurDto> getAllUtilisateurs(int page, int size, String search) {

		List<Utilisateur> users = utilisateurRepository
				.findAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseRueContainingIgnoringCaseOrEntrepriseRaisonSocialeContainingIgnoringCase(search,
						search, search, search, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			UtilisateurDto uDto = DtoTools.convert(u, UtilisateurDto.class);
			uDto.setAdresseDto(DtoTools.convert(u.getAdresse(), AdresseDto.class));
			uDto.setEntrepriseDto(DtoTools.convert(u.getEntreprise(), EntrepriseDto.class));
			List<UtilisateurRoleDto> utilisateurRoleDto = new ArrayList<UtilisateurRoleDto>();
			for(UtilisateurRole ur : u.getRoles()) {
				utilisateurRoleDto.add(DtoTools.convert(ur, UtilisateurRoleDto.class));
			}
			uDto.setRolesDto(utilisateurRoleDto);
			
			res.add(uDto);
			
		}
		return res;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(utilisateurRepository
				.countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseRueContainingIgnoringCaseOrEntrepriseRaisonSocialeContainingIgnoringCase(search,
						search, search, search, search));
	}

	@Override
	public UtilisateurDto getById(long id) {
		Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
		if (userOpt.isPresent()) {
			UtilisateurDto uDto = DtoTools.convert(userOpt.get(), UtilisateurDto.class);
			uDto.setAdresseDto(DtoTools.convert(userOpt.get().getAdresse(), AdresseDto.class));
			uDto.setEntrepriseDto(DtoTools.convert(userOpt.get().getEntreprise(), EntrepriseDto.class));
			List<UtilisateurRoleDto> utilisateurRoleDto = new ArrayList<UtilisateurRoleDto>();
			for(UtilisateurRole ur : userOpt.get().getRoles()) {
				utilisateurRoleDto.add(DtoTools.convert(ur, UtilisateurRoleDto.class));
			}
			uDto.setRolesDto(utilisateurRoleDto);
			
			return uDto;
		}
			
			
		return null;
	}

	@Override
	public UtilisateurDto findByEmail(String email) {
		Utilisateur user = utilisateurRepository.findByEmail(email);

		if (user == null)
			return null;

		UtilisateurDto utilisateurDto = DtoTools.convert(user, UtilisateurDto.class);

		AdresseDto adresseDto = DtoTools.convert(user.getAdresse(), AdresseDto.class);
		utilisateurDto.setAdresseDto(adresseDto);

		EntrepriseDto entrepriseDto = DtoTools.convert(user.getEntreprise(), EntrepriseDto.class);
		utilisateurDto.setEntrepriseDto(entrepriseDto);

		List<UtilisateurRole> lstUsrRole = user.getRoles();
		List<UtilisateurRoleDto> lstUsrRoleDto = new ArrayList<UtilisateurRoleDto>();
		for (UtilisateurRole utilisateurRole : lstUsrRole) {
			if (utilisateurRole != null)
				lstUsrRoleDto.add(DtoTools.convert(utilisateurRole, UtilisateurRoleDto.class));
		}
		utilisateurDto.setRolesDto(lstUsrRoleDto);

		return utilisateurDto;
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
		System.out.println("uDto : " + uDto.toString());
		System.out.println("uDto.getAdresseDto() : " + uDto.getAdresseDto().toString());
		Utilisateur user = DtoTools.convert(uDto, Utilisateur.class);
		
		if(uDto.getAdresseDto() != null) {
			Adresse adresse = DtoTools.convert(uDto.getAdresseDto(), Adresse.class);
			System.out.println("adresse : " + adresse.toString());
			adresseRepository.saveAndFlush(adresse);
			
			Adresse adresseRepop = adresseRepository.getOne(adresse.getId());
			System.out.println("adresseRepop : " + adresseRepop.toString());
			user.setAdresse(adresseRepop);
		}				

		System.out.println("user : " + user.toString());
		
		utilisateurRepository.saveAndFlush(user);

		filesService.createDirectory("utilisateurs/" + user.getId());

		return DtoTools.convert(user, UtilisateurDto.class);
	}

	@Override
	public void deleteById(long id) {
		utilisateurRepository.deleteById(id);
		filesService.deleteDirectoryWithContent("utilisateurs/" + id);
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

		for (UtilisateurRole role : userOpt.get().getRoles()) {
			switch (role.getIntitule()) {
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

		for (Conge c : conges) {
			result.add(DtoTools.convert(c, CongeDto.class));
		}

		return result;
	}

	@Override
	public AdresseDto getAdresseByIdUtilisateur(long id) {
		return DtoTools.convert(getUtilisateurById(id).getAdresse(), AdresseDto.class);
	}

	// ##################################################
	// # UTILE #
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

	@Override
	public UtilisateurDto getByIdWithObject(long id) {
		Utilisateur utilisateur = getUtilisateurById(id);

		if (utilisateur != null) {
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

			return utilisateurDto;
		}

		return null;
	}

	@Override
	public List<UtilisateurDto> findByRole(long idRole) {
		List<UtilisateurDto> res = getAll();
		List<UtilisateurDto> resfinal = new ArrayList<UtilisateurDto>();
		
		for (UtilisateurDto u : res) {
			List<UtilisateurRoleDto> userRole = u.getRolesDto();
			
			for (UtilisateurRoleDto ur : userRole) {
				if (ur.getId() == idRole) {
					resfinal.add(u);
				}
			}			
		}
		
		return resfinal;
	}
	
	@Override
	public List<AbsenceDto> findAllByEtudiantPromotionsReferentPedagogiqueId(long id) {
		List<Absence> lstAbs = absenceRepository.findDistinctByEtudiantPromotionsReferentPedagogiqueId(id);
		List<AbsenceDto> lstAbsDto = new ArrayList<AbsenceDto>();
		for (Absence abs : lstAbs) {
			AbsenceDto absDto = DtoTools.convert(abs, AbsenceDto.class);
			EtudiantDto etuDto = DtoTools.convert(abs.getEtudiant(), EtudiantDto.class);
			absDto.setEtudiantDto(etuDto);
			lstAbsDto.add(absDto);
			return lstAbsDto;
		}
		return null;
	}

	@Override
	public CountDto countEtudiantPromotionsReferentPedagogiqueId(long id) {
		return new CountDto(absenceRepository.countDistinctByEtudiantPromotionsReferentPedagogiqueId(id));
	}

	@Override
	public List<UtilisateurDto> findAllByRoleByPage(int page, int size,String role, String search) {
		List<Utilisateur> users = utilisateurRepository
				.findAllByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
						role, search, role, search, role, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
				
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(DtoTools.convert(u, UtilisateurDto.class));
		}
		return res;
	}

	@Override
	public CountDto countByRole(String role, String search) {
		return new CountDto(utilisateurRepository
				.countByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
						role, search, role, search, role, search));
	}

}
