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
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.EntrepriseRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRoleRepository;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	UtilisateurRoleRepository utilisateurRoleRepository;
	@Autowired
	AdresseRepository adresseRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	CongeRepository congeRepository;
	@Autowired
	AbsenceRepository absenceRepository;
	@Autowired
	PromotionRepository promotionRespository;

	@Autowired
	EtudiantService etudiantService;
	@Autowired
	FilesService filesService;
	@Autowired
	FormateurService formateurService;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<UtilisateurDto> getAll() {
		List<Utilisateur> users = utilisateurRepository.findAll();
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();

		for (Utilisateur u : users) {

			List<UtilisateurRoleDto> role = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole r : u.getRoles()) {
				role.add(mapper.UtilisateurRoleToUtilisateurRoleDto(r));
			}

			UtilisateurDto user = mapper.UtilisateurToUtilisateurDto(u);
			user.setRolesDto(role);
			res.add(user);
		}
		return res;
	}

	@Override
	public List<UtilisateurDto> getAllUtilisateurs(int page, int size, String search) {

		List<Utilisateur> users = utilisateurRepository
				.findAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseRueContainingIgnoringCaseOrEntrepriseRaisonSocialeContainingIgnoringCase(
						search, search, search, search, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			UtilisateurDto uDto = mapper.UtilisateurToUtilisateurDto(u);
			uDto.setAdresseDto(mapper.AdresseToAdresseDto(u.getAdresse()));
			uDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(u.getEntreprise()));
			List<UtilisateurRoleDto> utilisateurRoleDto = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole ur : u.getRoles()) {
				utilisateurRoleDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(ur));
			}
			uDto.setRolesDto(utilisateurRoleDto);

			res.add(uDto);

		}
		return res;
	}

	@Override
	public CountDto count(String search) {
		return new CountDto(utilisateurRepository
				.countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseRueContainingIgnoringCaseOrEntrepriseRaisonSocialeContainingIgnoringCase(
						search, search, search, search, search));
	}

	@Override
	public UtilisateurDto getById(long id) {
		Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
		if (userOpt.isPresent()) {
			UtilisateurDto uDto = mapper.UtilisateurToUtilisateurDto(userOpt.get());
			uDto.setAdresseDto(mapper.AdresseToAdresseDto(userOpt.get().getAdresse()));
			uDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(userOpt.get().getEntreprise()));
			uDto.getEntrepriseDto().setAdresseSiegeDto(mapper.AdresseToAdresseDto(userOpt.get().getEntreprise().getAdresseSiege()));

			List<UtilisateurRoleDto> utilisateurRoleDto = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole ur : userOpt.get().getRoles()) {
				utilisateurRoleDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(ur));
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

		UtilisateurDto utilisateurDto = mapper.UtilisateurToUtilisateurDto(user);

		AdresseDto adresseDto = mapper.AdresseToAdresseDto(user.getAdresse());
		utilisateurDto.setAdresseDto(adresseDto);

		EntrepriseDto entrepriseDto = mapper.EntrepriseToEntrepriseDto(user.getEntreprise());
		utilisateurDto.setEntrepriseDto(entrepriseDto);

		List<UtilisateurRole> lstUsrRole = user.getRoles();
		List<UtilisateurRoleDto> lstUsrRoleDto = new ArrayList<UtilisateurRoleDto>();
		for (UtilisateurRole utilisateurRole : lstUsrRole) {
			if (utilisateurRole != null)
				lstUsrRoleDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(utilisateurRole));
		}
		utilisateurDto.setRolesDto(lstUsrRoleDto);

		return utilisateurDto;
	}

	@Override
	public UtilisateurDto getName(String name) {
		Utilisateur user = utilisateurRepository.findByName(name);
		if (user != null)
			return mapper.UtilisateurToUtilisateurDto(user);
		return null;
	}

	@Override
	public UtilisateurDto insertUpdate(UtilisateurDto uDto) throws Exception {

		// refus d'insertion :
		// Si uDto n'est pas déjà en base (getId() == 0) => creation
		// Si un utilisateur a la même adresse mail => throw Exception
		if (uDto.getId() == 0 && findByEmail(uDto.getLogin()) != null) {
			throw new Exception("Un utilisateur utilise déjà cette adresse mail");
		}

		Utilisateur user = DtoTools.convert(uDto, Utilisateur.class);

		// On save l'addresse avant de save l'utilisateur
		if (uDto.getAdresseDto() != null) {
			Adresse adresse = DtoTools.convert(uDto.getAdresseDto(), Adresse.class);
			adresseRepository.saveAndFlush(adresse);

			Adresse adresseRepop = adresseRepository.getOne(adresse.getId());
			user.setAdresse(adresseRepop);
		}

		// On save l'entreprise avant de save l'utilisateur
		if (uDto.getEntrepriseDto() != null) {
			Entreprise entreprise = DtoTools.convert(uDto.getEntrepriseDto(), Entreprise.class);
			entrepriseRepository.saveAndFlush(entreprise);
						
			//On save l'adresse avant de save l'entreprise
			if(uDto.getEntrepriseDto().getAdresseSiegeDto() != null) {
				Adresse adresseEntreprise = DtoTools.convert(uDto.getEntrepriseDto().getAdresseSiegeDto(), Adresse.class);
				adresseRepository.saveAndFlush(adresseEntreprise);
				
				Adresse adresseEntrepriseRepo = adresseRepository.getOne(adresseEntreprise.getId());
				entreprise.setAdresseSiege(adresseEntrepriseRepo);
			}	
			Entreprise entrepriseRepo = entrepriseRepository.getOne(entreprise.getId());
			user.setEntreprise(entrepriseRepo);
		}

		utilisateurRepository.saveAndFlush(user);

		filesService.createDirectory("utilisateurs/" + user.getId());

		UtilisateurDto result = mapper.UtilisateurToUtilisateurDto(utilisateurRepository.getOne(user.getId()));

		return result;
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
			res.add(mapper.UtilisateurToUtilisateurDto(u));
		}
		return res;
	}

	@Override
	public List<UtilisateurDto> findByEntreprise(long idEntreprise) {
		List<Utilisateur> users = utilisateurRepository.findByEntreprise(idEntreprise);
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(mapper.UtilisateurToUtilisateurDto(u));
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
				result.addAll(etudiantService.getAllJourneePlanningByIdEtudiant(id));
				break;
			case "FORMATEUR":
				result.addAll(formateurService.getAllJourneePlanningByIdFormateur(id));
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
			result.add(mapper.CongeToCongeDto(c));
		}

		return result;
	}

	@Override
	public AdresseDto getAdresseByIdUtilisateur(long id) {
		return mapper.AdresseToAdresseDto(getUtilisateurById(id).getAdresse());
	}

	// ##################################################
	// # UTILE #
	// ##################################################

	private Utilisateur getUtilisateurById(long id) {
		Optional<Utilisateur> e = utilisateurRepository.findById(id);
				
		if (e.isPresent())
			return e.get();

		return null;
		
//		Utilisateur e = utilisateurRepository.getOne(id);
//
//		return e;
	}

	@Override
	public List<UtilisateurDto> getAllWithObject() {
		List<Utilisateur> lstUsr = utilisateurRepository.findAll();
		List<UtilisateurDto> lstUsrDto = new ArrayList<UtilisateurDto>();

		for (Utilisateur utilisateur : lstUsr) {
			UtilisateurDto utilisateurDto = mapper.UtilisateurToUtilisateurDto(utilisateur);

			AdresseDto adresseDto = mapper.AdresseToAdresseDto(utilisateur.getAdresse());
			utilisateurDto.setAdresseDto(adresseDto);

			EntrepriseDto entrepriseDto = mapper.EntrepriseToEntrepriseDto(utilisateur.getEntreprise());
			utilisateurDto.setEntrepriseDto(entrepriseDto);

			List<UtilisateurRole> lstUsrRole = utilisateur.getRoles();
			List<UtilisateurRoleDto> lstUsrRoleDto = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole utilisateurRole : lstUsrRole) {
				if (utilisateurRole != null)
					lstUsrRoleDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(utilisateurRole));
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
			UtilisateurDto utilisateurDto = mapper.UtilisateurToUtilisateurDto(utilisateur);

			AdresseDto adresseDto = mapper.AdresseToAdresseDto(utilisateur.getAdresse());
			utilisateurDto.setAdresseDto(adresseDto);

			EntrepriseDto entrepriseDto = mapper.EntrepriseToEntrepriseDto(utilisateur.getEntreprise());
			utilisateurDto.setEntrepriseDto(entrepriseDto);

			List<UtilisateurRole> lstUsrRole = utilisateur.getRoles();
			List<UtilisateurRoleDto> lstUsrRoleDto = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole utilisateurRole : lstUsrRole) {
				if (utilisateurRole != null)
					lstUsrRoleDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(utilisateurRole));
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
			AbsenceDto absDto = mapper.AbsenceToAbsenceDto(abs);
			EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(abs.getEtudiant());
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
	public List<UtilisateurDto> findAllByRoleByPage(int page, int size, String role, String search) {
		List<Utilisateur> users = utilisateurRepository
				.findAllByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
						role, search, role, search, role, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(mapper.UtilisateurToUtilisateurDto(u));
		}
		return res;
	}

	@Override
	public CountDto countByRole(String role, String search) {
		return new CountDto(utilisateurRepository
				.countByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
						role, search, role, search, role, search));
	}

	@Override
	public Boolean isReferent(long id) {
		List<Promotion> result = promotionRespository.findAllByReferentPedagogiqueId(id);
		
		if(result.size() != 0) return true;
		else return false;
	}

}
