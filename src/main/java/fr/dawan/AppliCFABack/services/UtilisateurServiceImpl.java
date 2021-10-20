package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import fr.dawan.AppliCFABack.tools.HashTools;

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
	EmailService emailService;

	@Autowired
	private DtoMapper mapper;

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
		System.out.println("id : " + id);
		Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
		if (userOpt.isPresent()) {
			UtilisateurDto uDto = mapper.UtilisateurToUtilisateurDto(userOpt.get());

			if (userOpt.get().getAdresse() != null)
				uDto.setAdresseDto(mapper.AdresseToAdresseDto(userOpt.get().getAdresse()));
			if (userOpt.get().getEntreprise() != null)
				uDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(userOpt.get().getEntreprise()));
//			if(userOpt.get().getEntreprise().getAdresseSiege() != null) uDto.getEntrepriseDto().setAdresseSiegeDto(mapper.AdresseToAdresseDto(userOpt.get().getEntreprise().getAdresseSiege()));

			List<UtilisateurRoleDto> utilisateurRoleDto = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole ur : userOpt.get().getRoles()) {
				utilisateurRoleDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(ur));
			}
			uDto.setRolesDto(utilisateurRoleDto);
			
			if(userOpt.get().getEtudiant() != null) uDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(userOpt.get().getEtudiant()));
			if(userOpt.get().getFormateur() != null) uDto.setFormateurDto(mapper.FormateurToFormateurDto(userOpt.get().getFormateur()));
			if(userOpt.get().getCef() != null) uDto.setCefDto(mapper.CEFToCEFDto(userOpt.get().getCef()));

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
		
		utilisateurDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(user.getEtudiant()));
		utilisateurDto.setFormateurDto(mapper.FormateurToFormateurDto(user.getFormateur()));
		utilisateurDto.setCefDto(mapper.CEFToCEFDto(user.getCef()));

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

		// Refus d'insertion :
		// Si uDto n'est pas déjà en base (getId() == 0) => creation
		// Si un utilisateur a la même adresse mail => throw Exception

		if (uDto.getId() == 0 && utilisateurRepository.findByEmail(uDto.getLogin()) != null) {
			throw new Exception("Un utilisateur utilise déjà cette adresse mail login : " + uDto.getLogin()
					+ " findByEmail " + findByEmail(uDto.getLogin()).toString());
		}

		Utilisateur user = DtoTools.convert(uDto, Utilisateur.class);

		// HashTools throw Exception
		try {
			// Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
			if (user.getId() == 0) {
				if(user.getPassword() == null) {
					user.setPassword(HashTools.hashSHA512(generatePassword()));				
					emailService.newPassword(user.getLogin(), user.getPassword());		
				}else {
					user.setPassword(HashTools.hashSHA512(user.getPassword()));		
				}

			} else {
				// Si on a modifié le mdp
				Utilisateur userInDB = utilisateurRepository.getOne(user.getId());
				if (!userInDB.getPassword().equals(user.getPassword())) {
					user.setPassword(HashTools.hashSHA512(user.getPassword()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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

			// On save l'adresse avant de save l'entreprise
			if (uDto.getEntrepriseDto().getAdresseSiegeDto() != null) {
				Adresse adresseEntreprise = DtoTools.convert(uDto.getEntrepriseDto().getAdresseSiegeDto(),
						Adresse.class);
				adresseRepository.saveAndFlush(adresseEntreprise);

				Adresse adresseEntrepriseRepo = adresseRepository.getOne(adresseEntreprise.getId());
				entreprise.setAdresseSiege(adresseEntrepriseRepo);
			}
			Entreprise entrepriseRepo = entrepriseRepository.getOne(entreprise.getId());
			user.setEntreprise(entrepriseRepo);
		}
		
		//Changement de role, on créer l'entité associée 

		utilisateurRepository.saveAndFlush(user);

		filesService.createDirectory("utilisateurs/" + user.getId());
		filesService.createDirectory("utilisateurs/" + user.getId() + "/DossierProfessionnel");
		filesService.createDirectory("utilisateurs/" + user.getId() + "/DossierProjet");

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
				result.addAll(etudiantService.getAllJourneePlanningByIdEtudiant(userOpt.get().getEtudiant().getId()));
				break;
			case "FORMATEUR":
				result.addAll(formateurService.getAllJourneePlanningByIdFormateur(userOpt.get().getFormateur().getId()));
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
			
			utilisateurDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(utilisateur.getEtudiant()));
			utilisateurDto.setFormateurDto(mapper.FormateurToFormateurDto(utilisateur.getFormateur()));
			utilisateurDto.setCefDto(mapper.CEFToCEFDto(utilisateur.getCef()));

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
			
			utilisateurDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(utilisateur.getEtudiant()));
			utilisateurDto.setFormateurDto(mapper.FormateurToFormateurDto(utilisateur.getFormateur()));
			utilisateurDto.setCefDto(mapper.CEFToCEFDto(utilisateur.getCef()));

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

		if (result.size() != 0)
			return true;
		else
			return false;
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
	
	private String generatePassword() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		
		// Genere un mot de passe aleatoire de 0 à 9 et de A à Z(Majuscule/minuscule compris) en caractere ASCII
		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		// appendCodePoint => recupere le String en code ASCII pour le dechiffrer en charactere alphanumerique
		
		return generatedString;
	}
}
