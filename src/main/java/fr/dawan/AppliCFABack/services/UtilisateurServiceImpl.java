package fr.dawan.AppliCFABack.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CompetenceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DevoirEtudiantDto;
import fr.dawan.AppliCFABack.dto.DossierProfessionnelDto;
import fr.dawan.AppliCFABack.dto.DossierProjetDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EmployeeDG2Dto;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.LivretEvaluationDto;
import fr.dawan.AppliCFABack.dto.LoginDto;
import fr.dawan.AppliCFABack.dto.LoginResponseDto;
import fr.dawan.AppliCFABack.dto.PositionnementDto;
import fr.dawan.AppliCFABack.dto.ResetResponse;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Entreprise;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Tuteur;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CEFRepository;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.EntrepriseRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.MaitreApprentissageRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.repositories.TuteurRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRoleRepository;
import fr.dawan.AppliCFABack.tools.EmailResetPasswordException;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import fr.dawan.AppliCFABack.tools.FileException;
import fr.dawan.AppliCFABack.tools.HashTools;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import javassist.NotFoundException;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	UtilisateurRepository utilisateurRepository;	
	@Autowired
	AdresseRepository adresseRepository;
	@Autowired
	CongeRepository congeRepository;
	@Autowired
	PromotionRepository promotionRespository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	FormateurRepository formateurRepository;
	@Autowired
	CentreFormationRepository centreFormationRepository;
	@Autowired
	TuteurRepository tuteurRepository;
	@Autowired
	UtilisateurRoleRepository utilisateurRoleRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	CEFRepository cefRepository;
	@Autowired
	MaitreApprentissageRepository maitreApprentissageRepository;
	@Autowired
	DossierProjetService DossierProjetService;
	@Autowired
	CompetenceProfessionnelleService competenceProfessionnelleService;
	@Autowired
	EtudiantService etudiantService;
	@Autowired
	FilesService filesService;
	@Autowired
	FormateurService formateurService;
	@Autowired
	LivretEvaluationService livretEvaluationService;
	@Autowired
	EmailService emailService;
	@Autowired
	TuteurService tuteurService;
	@Autowired
	UtilisateurRoleService utilisateurRoleService;
	@Autowired
	DevoirEtudiantService devoirEtudiantService;
	@Autowired
	InterventionService interventionService;
	@Autowired
	PositionnementService positionnementService;
	@Autowired
	DossierProfessionnelService dosiDossierProfessionnelService;

	@Autowired
	private DtoMapper mapper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpServletRequest request;

	@Value("${app.ldap.url}")
	private String ldapUrl;

	@Value("${app.ldap.protocol}")
	private String ldapProtocol;

	@Value("${app.ldap.technical.dn}")
	private String ldapTechnicalAccDN;

	@Value("${app.ldap.technical.pwd}")
	private String ldapTechnicalAccPwd;
	
	@Value("${base_url_dg2}")
    private String baseUrl;
	
	@Value("${domain.dawan}")
    private String domainDawan;

    @Value("${domain.jehann}")
    private String domainJehann;
    
    @PersistenceContext
    private EntityManager entityManager;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getLdapUrl() {
		return ldapUrl;
	}

	public void setLdapUrl(String ldapUrl) {
		this.ldapUrl = ldapUrl;
	}

	public String getLdapProtocol() {
		return ldapProtocol;
	}

	public void setLdapProtocol(String ldapProtocol) {
		this.ldapProtocol = ldapProtocol;
	}

	public String getLdapTechnicalAccDN() {
		return ldapTechnicalAccDN;
	}

	public void setLdapTechnicalAccDN(String ldapTechnicalAccDN) {
		this.ldapTechnicalAccDN = ldapTechnicalAccDN;
	}

	public String getLdapTechnicalAccPwd() {
		return ldapTechnicalAccPwd;
	}

	public void setLdapTechnicalAccPwd(String ldapTechnicalAccPwd) {
		this.ldapTechnicalAccPwd = ldapTechnicalAccPwd;
	}

	private static Logger logger = LoggerFactory.getLogger(UtilisateurRoleServiceImpl.class);

	/**
	 * Récupération de la liste des utilisateurs
	 * 
	 * @return res Liste des objets utilisateurs
	 */
	@Override
	public List<UtilisateurDto> getAll() {
		List<Utilisateur> users = utilisateurRepository.findAll();
		List<UtilisateurDto> res = new ArrayList<>();

		for (Utilisateur u : users) {

			List<UtilisateurRoleDto> role = new ArrayList<>();
			for (UtilisateurRole r : u.getRoles()) {
				role.add(mapper.utilisateurRoleToUtilisateurRoleDto(r));
			}

			UtilisateurDto user = mapper.utilisateurToUtilisateurDto(u);
			user.setRolesDto(role);
			res.add(user);
		}
		return res;
	}

	/**
	 * Va permettre de récupérer tous les utilisateurs avec pagination et recherche
	 * 
	 * @param page   numero de la page
	 * @param size   éléments sur la page
	 * @param search éléments utilisateurs (nom,prenom,login,adresse)
	 * @return res Liste des objets utilisateurs
	 */

	@Override
	public List<UtilisateurDto> getAllUtilisateurs(int page, int size, String search) {

		List<Utilisateur> users = utilisateurRepository
				.findAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseLibelleContainingIgnoringCase(
						search, search, search, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		List<UtilisateurDto> res = new ArrayList<>();
		for (Utilisateur u : users) {
			UtilisateurDto uDto = mapper.utilisateurToUtilisateurDto(u);
			uDto.setAdresseDto(mapper.adresseToAdresseDto(u.getAdresse()));
			List<UtilisateurRoleDto> utilisateurRoleDto = new ArrayList<>();
			for (UtilisateurRole ur : u.getRoles()) {
				utilisateurRoleDto.add(mapper.utilisateurRoleToUtilisateurRoleDto(ur));
			}
			uDto.setRolesDto(utilisateurRoleDto);

			res.add(uDto);

		}
		return res;
	}

	/**
	 * Recherche d'un utilisateur / nb
	 * 
	 * @param search recherche par prenom / nom / login / adresse
	 */

	@Override
	public CountDto count(String search) {
		return new CountDto(utilisateurRepository
				.countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseLibelleContainingIgnoringCase(
						search, search, search, search));
	}

	/**
	 * Récupération des utilisateurs en fonction de l'id
	 * 
	 * @param id id de l'utilisateur
	 * @return uDto objet utilsateur
	 */

	@Override
	public UtilisateurDto getById(long id) {
		logger.info("id : ", id);
		Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
		if (userOpt.isPresent()) {
			UtilisateurDto uDto = mapper.utilisateurToUtilisateurDto(userOpt.get());

			if (userOpt.get().getAdresse() != null)
				uDto.setAdresseDto(mapper.adresseToAdresseDto(userOpt.get().getAdresse()));

//			if(userOpt.get().getEntreprise().getAdresseSiege() != null) uDto.getEntrepriseDto().setAdresseSiegeDto(mapper.AdresseToAdresseDto(userOpt.get().getEntreprise().getAdresseSiege()));

			List<UtilisateurRoleDto> utilisateurRoleDto = new ArrayList<>();
			for (UtilisateurRole ur : userOpt.get().getRoles()) {
				utilisateurRoleDto.add(mapper.utilisateurRoleToUtilisateurRoleDto(ur));
			}
			uDto.setRolesDto(utilisateurRoleDto);

			if (userOpt.get().getEtudiant() != null)
				uDto.setEtudiantDto(mapper.etudiantToEtudiantDto(userOpt.get().getEtudiant()));
			if (userOpt.get().getFormateur() != null)
				uDto.setFormateurDto(mapper.formateurToFormateurDto(userOpt.get().getFormateur()));
			if (userOpt.get().getCef() != null)
				uDto.setCefDto(mapper.cefToCEFDto(userOpt.get().getCef()));
			if (userOpt.get().getTuteur() != null)
				uDto.setTuteurDto(mapper.tuteurTotuteurDto(userOpt.get().getTuteur()));

			return uDto;
		}

		return null;
	}

	/**
	 * Récupération des utilisateurs en fonction de l'email
	 * 
	 * @param email email utilisateur
	 * @return utilisateurDto objet utilisateur
	 */

	@Override
	public UtilisateurDto findByEmail(String email) {
		Utilisateur user = utilisateurRepository.findByEmail(email);

		if (user == null)
			return null;

		UtilisateurDto utilisateurDto = mapper.utilisateurToUtilisateurDto(user);

		AdresseDto adresseDto = mapper.adresseToAdresseDto(user.getAdresse());
		utilisateurDto.setAdresseDto(adresseDto);

		List<UtilisateurRole> lstUsrRole = user.getRoles();
		List<UtilisateurRoleDto> lstUsrRoleDto = new ArrayList<>();
		for (UtilisateurRole utilisateurRole : lstUsrRole) {
			if (utilisateurRole != null)
				lstUsrRoleDto.add(mapper.utilisateurRoleToUtilisateurRoleDto(utilisateurRole));
		}
		utilisateurDto.setRolesDto(lstUsrRoleDto);

		utilisateurDto.setEtudiantDto(mapper.etudiantToEtudiantDto(user.getEtudiant()));
		utilisateurDto.setFormateurDto(mapper.formateurToFormateurDto(user.getFormateur()));
		utilisateurDto.setCefDto(mapper.cefToCEFDto(user.getCef()));
		utilisateurDto.setTuteurDto(mapper.tuteurTotuteurDto(user.getTuteur()));

		return utilisateurDto;
	}

	/**
	 * Récupération des utilisateurs en fonction du nom
	 * 
	 * @param name nom de l'utilisateur
	 */

	@Override
	public UtilisateurDto getName(String name) {
		Utilisateur user = utilisateurRepository.findByName(name);
		if (user != null)
			return mapper.utilisateurToUtilisateurDto(user);
		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'un utilisateur
	 * 
	 * @param uDto objet utilisateur
	 * @return result objet utilisateur (nouveau ou modifier)
	 * @throws SaveInvalidException
	 * 
	 */

	@Override
	public UtilisateurDto insertUpdate(UtilisateurDto uDto) throws SaveInvalidException {

		// Refus d'insertion :
		// Si uDto n'est pas déjà en base (getId() == 0) => creation
		// Si un utilisateur a la même adresse mail => throw Exception

		if (uDto.getId() == 0 && utilisateurRepository.findByEmail(uDto.getLogin()) != null) {
			throw new SaveInvalidException("Un utilisateur utilise déjà cette adresse mail login : " + uDto.getLogin()
					+ " findByEmail " + findByEmail(uDto.getLogin()).toString());
		}

		Utilisateur user = DtoTools.convert(uDto, Utilisateur.class);

		// HashTools throw Exception
		try {
			// Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
			if (user.getId() == 0) {
				if (user.getPassword() == null || user.getPassword().equals("")) {
					user.setPassword(generatePassword());
					// emailService.newPassword(user.getLogin(), user.getPassword());
					user.setPassword(HashTools.hashSHA512(user.getPassword()));

				} else {
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
			logger.error("hashPwd failed", e);
		}

		// On save l'addresse avant de save l'utilisateur
		if (uDto.getAdresseDto() != null && uDto.getAdresseDto().getId() == 0) {
			Adresse adresse = DtoTools.convert(uDto.getAdresseDto(), Adresse.class);
			adresseRepository.saveAndFlush(adresse);

			Adresse adresseRepop = adresseRepository.getOne(adresse.getId());
			user.setAdresse(adresseRepop);
		}
		// On save l'entreprise avant de save l'utilisateur
		if (uDto.getEntrepriseDto() != null && uDto.getEntrepriseDto().getId() == 0) {
			Entreprise entreprise = DtoTools.convert(uDto.getEntrepriseDto(), Entreprise.class);
			entrepriseRepository.saveAndFlush(entreprise);

			Entreprise entrepriseRepop = entrepriseRepository.getOne(entreprise.getId());
			user.setEntreprise(entrepriseRepop);
		}

		// On save les roles
		// Changement de role, on créer l'entité associée
		Boolean isEtudiant = false;
		Boolean isFormateur = false;
		Boolean isCEF = false;
		Boolean isTuteur = false;
		if (user.getRoles() != null) {

			for (UtilisateurRole role : user.getRoles()) {
				if (role.getIntitule().equals("ETUDIANT")) {
					isEtudiant = true;
				}
				if (role.getIntitule().equals("FORMATEUR")) {
					isFormateur = true;
				}
				if (role.getIntitule().equals("CEF")) {
					isCEF = true;
				}
				if (role.getIntitule().equals("TUTEUR")) {
					isTuteur = true;
				}
			}
		}

		user = utilisateurRepository.saveAndFlush(user);

		// Si on ajoute un role
		if (isEtudiant && user.getEtudiant() == null) {
			Etudiant etudiant = new Etudiant();
			etudiant = etudiantRepository.saveAndFlush(etudiant);
			user.setEtudiant(etudiant);
			etudiant.setUtilisateur(user);

			etudiantRepository.saveAndFlush(etudiant);

		}
		if (isFormateur && user.getFormateur() == null) {
			Formateur formateur = new Formateur();
			formateur = formateurRepository.saveAndFlush(formateur);
			user.setFormateur(formateur);
			formateur.setUtilisateur(user);

			formateurRepository.saveAndFlush(formateur);
		}
		if (isCEF && user.getCef() == null) {
			CEF cef = new CEF();
			cef = cefRepository.saveAndFlush(cef);
			user.setCef(cef);
			cef.setUtilisateur(user);

			cefRepository.saveAndFlush(cef);
		}
		if (isTuteur && isTuteur.getClass() == null) {
			Tuteur tuteur = new Tuteur();
			tuteur = tuteurRepository.saveAndFlush(tuteur);
			user.setTuteur(tuteur);
			tuteur.setUtilisateur(user);

		}

		// Si on supprime un role
		if (!isEtudiant && user.getEtudiant() != null) {
			Etudiant etudiant = etudiantRepository.getOne(user.getEtudiant().getId());
			etudiant.setUtilisateur(null);
			user.setEtudiant(null);

			etudiantRepository.saveAndFlush(etudiant);

			// On delete l'etudiant ?
//			etudiantService.deleteById(etudiant.getId());			
		}
		if (!isFormateur && user.getFormateur() != null) {
			Formateur formateur = formateurRepository.getOne(user.getFormateur().getId());
			formateur.setUtilisateur(null);
			user.setFormateur(null);

			formateurRepository.saveAndFlush(formateur);

			// On delete l'etudiant ?
//			formateurService.deleteById(formateur.getId());		
		}
		if (!isCEF && user.getCef() != null) {
			CEF cef = cefRepository.getOne(user.getCef().getId());
			cef.setUtilisateur(null);
			user.setCef(null);

			cefRepository.saveAndFlush(cef);

			// On delete l'etudiant ?
//			cefService.deleteById(cef.getId());	
		}
		if (!isTuteur && isTuteur.getClass() != null) {
			Tuteur tuteur = tuteurRepository.getOne(user.getTuteur().getId());
			tuteur.setUtilisateur(null);
			user.setTuteur(null);

			tuteurRepository.saveAndFlush(tuteur);

			// On delete l'etudiant ?
//			cefService.deleteById(cef.getId());	
		}

		user = utilisateurRepository.saveAndFlush(user);

		// On créer les dossier pour l'utilisateur dans le shared
		filesService.createDirectory("utilisateurs/" + user.getId());
		filesService.createDirectory("utilisateurs/" + user.getId() + "/DossierProfessionnel");
		filesService.createDirectory("utilisateurs/" + user.getId() + "/DossierProjet");

		UtilisateurDto result = mapper.utilisateurToUtilisateurDto(utilisateurRepository.getOne(user.getId()));
		result.setEtudiantDto(mapper.etudiantToEtudiantDto(user.getEtudiant()));
		result.setFormateurDto(mapper.formateurToFormateurDto(user.getFormateur()));
		result.setCefDto(mapper.cefToCEFDto(user.getCef()));
		result.setTuteurDto(mapper.tuteurTotuteurDto(user.getTuteur()));

		return result;
	}	
	
	@Override
	public UtilisateurDto insertTuteur(UtilisateurDto uDto) throws SaveInvalidException {
		
		if (uDto.getId() == 0 && utilisateurRepository.findByEmail(uDto.getLogin()) != null) {
			throw new SaveInvalidException("Un utilisateur utilise déjà cette adresse mail login : " + uDto.getLogin());
		}
		
		try {
			// Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
			if (uDto.getId() == 0) {
				if (uDto.getPassword() == null || uDto.getPassword().equals("")) {
					uDto.setPassword(generatePassword());
					uDto.setPassword(HashTools.hashSHA512(uDto.getPassword()));

				} else {
					uDto.setPassword(HashTools.hashSHA512(uDto.getPassword()));
				}

			} else {
				// Si on a modifié le mdp
				Utilisateur userInDB = utilisateurRepository.getOne(uDto.getId());
				if (!userInDB.getPassword().equals(uDto.getPassword())) {
					uDto.setPassword(HashTools.hashSHA512(uDto.getPassword()));
				}
			}
		} catch (Exception e) {
			logger.error("hashPwd failed", e);
		}
		
		Utilisateur user = mapper.utilisateurDtoToUtilisateur(uDto);
		UtilisateurRole tuteurRole = utilisateurRoleRepository.findByIntituleContaining("TUTEUR");
		//Entreprise optEntreprise = entrepriseRepository.findById(user.getEntreprise().getId()).get();
		if (user.getEntreprise() != null) {
	        Entreprise optEntreprise = entrepriseRepository.findById(user.getEntreprise().getId()).orElse(null);
	        user.setEntreprise(optEntreprise);
	    }
		// Gestion de l'adresse
	    if (user.getAdresse() != null) {
	        Adresse adresse = adresseRepository.findById(user.getAdresse().getId()).orElse(null);
	        user.setAdresse(adresse);
	    }

	    // Gestion du centre de formation
	    if (user.getCentreFormation() != null) {
	        CentreFormation centreFormation = centreFormationRepository.findById(user.getCentreFormation().getId()).orElse(null);
	        user.setCentreFormation(centreFormation);
	    }
//		Adresse adresse = adresseRepository.findById(user.getAdresse().getId()).get();
//		CentreFormation centreFormation = centreFormationRepository.findById(user.getCentreFormation().getId()).get();
	    
		List<UtilisateurRole> utilisateurRoles = new ArrayList<>();
		utilisateurRoles.add(tuteurRole);
		user.setRoles(utilisateurRoles);
		//user.setEntreprise(optEntreprise);
		//user.setAdresse(adresse);
		//user.setCentreFormation(centreFormation);
		
		user.setActive(false);//par defaut a false comme il y l'inscription du tuteur sera a modifier avec l'interface admin 

		user.setExternalAccount(true);		
		
		user = utilisateurRepository.save(user);
		
		Tuteur tuteur = new Tuteur();
		tuteur.setUtilisateur(user);
		tuteur = tuteurRepository.save(tuteur);
		
		if (uDto.getEtudiantDto() != null) {
			Long idEtudiant = uDto.getEtudiantDto().getId();
			Optional<Etudiant> etuOpt = etudiantRepository.findById(idEtudiant);
			if (etuOpt.isPresent()) {
				Etudiant etudiant = etuOpt.get();
				etudiant.setTuteur(tuteur);
				etudiantRepository.save(etudiant);
			} else {
				throw new EntityNotFoundException("Étudiant non trouvé avec l'ID : " + idEtudiant);
			} 
		}
		user.setTuteur(tuteur);
		user = utilisateurRepository.saveAndFlush(user);
		
		UtilisateurDto userDto = mapper.utilisateurToUtilisateurDto(user);
		
		return userDto;
	}

	/**
	 * Suppression d'un utilisateur
	 * 
	 * @param id Id concernant un utilisateur
	 */

	@Override
	public void deleteById(long id) {

		Utilisateur utilisateur = utilisateurRepository.getOne(id);

		if (utilisateur.getEtudiant() != null) {
			Etudiant etudiant = etudiantRepository.getOne(utilisateur.getEtudiant().getId());
			etudiant.setUtilisateur(null);
			utilisateur.setEtudiant(null);
//    		etudiantRepository.save(etudiant);
			etudiantRepository.delete(etudiant);
		}
		if (utilisateur.getFormateur() != null) {
			Formateur formateur = formateurRepository.getOne(utilisateur.getFormateur().getId());
			formateur.setUtilisateur(null);
			utilisateur.setFormateur(null);
//    		formateurRepository.save(formateur);
			formateurRepository.delete(formateur);
		}
		if (utilisateur.getCef() != null) {
			CEF cef = cefRepository.getOne(utilisateur.getCef().getId());
			cef.setUtilisateur(null);
			utilisateur.setCef(null);
//    		cefRepository.save(cef);
			cefRepository.delete(cef);
		}
//		if (utilisateur.getMaitreApprentissage() != null) {
//			MaitreApprentissage maitreApprentissage = maitreApprentissageRepository
//					.getOne(utilisateur.getMaitreApprentissage().getId());
//			maitreApprentissage.setUtilisateur(null);
//			utilisateur.setMaitreApprentissage(null);
////    		maitreApprentissageRepository.save(maitreApprentissage);
//			maitreApprentissageRepository.delete(maitreApprentissage);
//		}

		utilisateurRepository.deleteById(id);

		filesService.deleteDirectoryWithContent("utilisateurs/" + id);
	}

	// recuperation des user par adresse id
	/**
	 * Récupération des utilisateurs par adresse en fonction de la ville
	 * 
	 * @param ville adresse etudiant
	 * @return res Liste utilisateur
	 */

	@Override
	public List<UtilisateurDto> findByAdresse(String ville) {
		List<Utilisateur> users = utilisateurRepository.findByAdresseVille(ville);
		List<UtilisateurDto> res = new ArrayList<>();
		for (Utilisateur u : users) {
			res.add(mapper.utilisateurToUtilisateurDto(u));
		}
		return res;
	}

//	@Override
//	public List<UtilisateurDto> findByEntreprise(long idEntreprise) {
//		List<Utilisateur> users = utilisateurRepository.findByEntreprise(idEntreprise);
//		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
//		for (Utilisateur u : users) {
//			res.add(mapper.UtilisateurToUtilisateurDto(u));
//		}
//		return res;
//	}

	/**
	 * Planning de l'utilisateur
	 * 
	 * @param id id de l'utilisateur
	 * @return result Liste journee planning de l'utilisateur
	 */
	@Override
	public List<JourneePlanningDto> getAllJourneePlanningByIdUtilisateur(long id) {
		List<JourneePlanningDto> result = new ArrayList<>();

		Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
		if (!userOpt.isPresent())
			return null;

		for (UtilisateurRole role : userOpt.get().getRoles()) {
			switch (role.getIntitule()) {
			case "ETUDIANT":
				result.addAll(etudiantService.getAllJourneePlanningByIdEtudiant(userOpt.get().getEtudiant().getId()));
				break;
			case "FORMATEUR":
				result.addAll(
						formateurService.getAllJourneePlanningByIdFormateur(userOpt.get().getFormateur().getId()));
				break;
			}
		}

		return result;
	}

	/**
	 * Congé de l'utilisateur
	 * 
	 * @param id id de l'utilisateur
	 * @return result liste des objets conge de l'utilisateur
	 */

	@Override
	public List<CongeDto> getAllCongesByIdUtilisateur(long id) {
		List<CongeDto> result = new ArrayList<>();

		List<Conge> conges = congeRepository.findByIdUtilisateur(id);

		for (Conge c : conges) {
			result.add(mapper.congeToCongeDto(c));
		}

		return result;
	}

	/**
	 * Récupération de l'adresse en fonction de l'id de l'utilisateur
	 * 
	 * @param id id de l'utilisateur
	 * @return l'adresse utilisateur
	 */

	@Override
	public AdresseDto getAdresseByIdUtilisateur(long id) {
		return mapper.adresseToAdresseDto(getUtilisateurById(id).getAdresse());
	}

	@Override
	public List<UtilisateurDto> getAllWithObject() {
		List<Utilisateur> lstUsr = utilisateurRepository.findAll();
		List<UtilisateurDto> lstUsrDto = new ArrayList<>();

		for (Utilisateur utilisateur : lstUsr) {
			UtilisateurDto utilisateurDto = mapper.utilisateurToUtilisateurDto(utilisateur);

			AdresseDto adresseDto = mapper.adresseToAdresseDto(utilisateur.getAdresse());
			utilisateurDto.setAdresseDto(adresseDto);

			List<UtilisateurRole> lstUsrRole = utilisateur.getRoles();
			List<UtilisateurRoleDto> lstUsrRoleDto = new ArrayList<>();
			for (UtilisateurRole utilisateurRole : lstUsrRole) {
				if (utilisateurRole != null)
					lstUsrRoleDto.add(mapper.utilisateurRoleToUtilisateurRoleDto(utilisateurRole));
			}
			utilisateurDto.setRolesDto(lstUsrRoleDto);

			utilisateurDto.setEtudiantDto(mapper.etudiantToEtudiantDto(utilisateur.getEtudiant()));
			utilisateurDto.setFormateurDto(mapper.formateurToFormateurDto(utilisateur.getFormateur()));
			utilisateurDto.setCefDto(mapper.cefToCEFDto(utilisateur.getCef()));

			lstUsrDto.add(utilisateurDto);
		}

		return lstUsrDto;

	}

	@Override
	public UtilisateurDto getByIdWithObject(long id) {
		Utilisateur utilisateur = getUtilisateurById(id);

		if (utilisateur != null) {
			UtilisateurDto utilisateurDto = mapper.utilisateurToUtilisateurDto(utilisateur);

			AdresseDto adresseDto = mapper.adresseToAdresseDto(utilisateur.getAdresse());
			utilisateurDto.setAdresseDto(adresseDto);

			List<UtilisateurRole> lstUsrRole = utilisateur.getRoles();
			List<UtilisateurRoleDto> lstUsrRoleDto = new ArrayList<>();
			for (UtilisateurRole utilisateurRole : lstUsrRole) {
				if (utilisateurRole != null)
					lstUsrRoleDto.add(mapper.utilisateurRoleToUtilisateurRoleDto(utilisateurRole));
			}
			utilisateurDto.setRolesDto(lstUsrRoleDto);

			utilisateurDto.setEtudiantDto(mapper.etudiantToEtudiantDto(utilisateur.getEtudiant()));
			utilisateurDto.setFormateurDto(mapper.formateurToFormateurDto(utilisateur.getFormateur()));
			utilisateurDto.setCefDto(mapper.cefToCEFDto(utilisateur.getCef()));

			return utilisateurDto;
		}

		return null;
	}

	/**
	 * Récupération des utilisateur pa role
	 * 
	 * @param idRole objet utilisateur role
	 * @return resfinal liste des utilisateurs en fonction du role
	 */

	@Override
	public List<UtilisateurDto> findByRole(long idRole) {
		List<UtilisateurDto> res = getAll();
		List<UtilisateurDto> resfinal = new ArrayList<>();

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

//  //recuperation des user par etudiant id, referent
//    @Override
//    public List<AbsenceDto> findAllByEtudiantPromotionsReferentPedagogiqueId(long id) {
//        List<Absence> lstAbs = absenceRepository.findDistinctByEtudiantPromotionsReferentPedagogiqueId(id);
//        List<AbsenceDto> lstAbsDto = new ArrayList<AbsenceDto>();
//        for (Absence abs : lstAbs) {
//            AbsenceDto absDto = mapper.AbsenceToAbsenceDto(abs);
//            EtudiantDto etuDto = mapper.EtudiantToEtudiantDto(abs.getEtudiant());
//            absDto.setEtudiantDto(etuDto);
//            lstAbsDto.add(absDto);
//            return lstAbsDto;
//        }
//        return null;
//    }

//    //methode count
//    @Override
//    public CountDto countEtudiantPromotionsReferentPedagogiqueId(long id) {
//        return new CountDto(absenceRepository.countDistinctByEtudiantPromotionsReferentPedagogiqueId(id));
//    }

	// recuperation des user par role + pagination + recherche
	/**
	 * Va permettre de récupérer tous utilisateurs par role avec pagination et
	 * recherche
	 * 
	 * @param page   numero de la page
	 * @param size   éléments sur la page
	 * @param search éléments role utilisateur (prenom, nom, login)
	 * @return res liste des utilisateurs du role concerné
	 */
	@Override
	public List<UtilisateurDto> findAllByRoleByPage(int page, int size, String role, String search) {
		List<Utilisateur> users = utilisateurRepository
				.findAllByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
						role, search, role, search, role, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());

		List<UtilisateurDto> res = new ArrayList<>();
		for (Utilisateur u : users) {
			UtilisateurDto uDto = mapper.utilisateurToUtilisateurDto(u);

			List<UtilisateurRoleDto> userRolesDto = new ArrayList<>();
			for (UtilisateurRole ur : u.getRoles()) {
				userRolesDto.add(mapper.utilisateurRoleToUtilisateurRoleDto(ur));
			}
			uDto.setRolesDto(userRolesDto);
			res.add(uDto);
		}
		return res;
	}

	/**
	 * Recherche par role / nombre
	 * 
	 * @param role   objet role utilisateur
	 * @param search recherche par nom / prenom / login
	 */

	@Override
	public CountDto countByRole(String role, String search) {
		return new CountDto(utilisateurRepository
				.countByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
						role, search, role, search, role, search));
	}

	/**
	 * Utilisateur referent ou non
	 * 
	 * @param id id referent
	 */

	@Override
	public Boolean isReferent(long id) {
		List<Promotion> result = promotionRespository.findAllByReferentPedagogiqueId(id);

		return result.size() != 0;
	}

	/**
	 * File upload
	 * 
	 * @throws FileException IOException
	 * 
	 */

	@Override
	public void uploadFile(MultipartFile file, long idUser) throws FileException, IOException {
// J'ai 'renforcé' la secu en mappant l'id de l'utilisateur. Seul l'admin pourra uplaod des fichier. A voir si je laisse
		Utilisateur user = getUtilisateurById(idUser);
		for (UtilisateurRole role : user.getRoles()) {

			if (role.getIntitule().equals("ADMIN")) { // Verifie si l'utilisateur a un role admin

				if (file.isEmpty()) { // Si aucun fichier n'a été importé
					throw new FileException("Fichier introuvable");
				}

				InputStream inputStream = file.getInputStream(); // recupere le stream du fichier pour lire son contenu

				String filename = file.getOriginalFilename();
				String[] fileSplited = filename.split("\\.");

				if (fileSplited[1].equals("csv")) { // si l'extension du fichier == csv
					CsvParserSettings settings = new CsvParserSettings();
					settings.setHeaderExtractionEnabled(true); // definit si oui ou non on extrait les en-tetes lors du
																// parsing
					settings.detectFormatAutomatically(); // detecte automatiquement le separateur

					CsvParser parser = new CsvParser(settings);
					List<Record> records = parser.parseAllRecords(inputStream);
					records.forEach(item -> {
						Utilisateur utilisateur = new Utilisateur();

						// on definit les valeurs par rapport au nom de l'entete
						utilisateur.setCivilite(item.getString("civilite"));
						utilisateur.setPrenom(item.getString("prenom"));
						utilisateur.setNom(item.getString("nom"));
						utilisateur.setLogin(item.getString("login"));
						utilisateur.setDateDeNaissance(LocalDate.parse(item.getString("date_de_naissance")));
						utilisateur.setTelephone(item.getString("telephone"));
						utilisateur.setPassword(item.getString("password")); // TODO: commenter cette ligne plus tard

						// on convertit l'utilisateur en Dto puis on appelle la methode insertUpdate
						UtilisateurDto utilisateurDto = mapper.utilisateurToUtilisateurDto(utilisateur);
						List<UtilisateurRoleDto> roleDtoList = new ArrayList<>();
						// On cherche un role par son intitule
						UtilisateurRoleDto rDto = utilisateurRoleService.findByIntitule(item.getString("rolesDto"));
						// On ajoute ce role dans une liste de role
						roleDtoList.add(rDto);
						// on ajoute la liste à l'utilisateur
						utilisateurDto.setRolesDto(roleDtoList);

						try {
							// On appelle la methode pour inserer un utilisateur
							insertUpdate(utilisateurDto);
						} catch (Exception e) {
							logger.error("insertUpdate failed", e);
						}
					});
				} else { // sinon => ERROR
					throw new FileException("Extension de fichier incorrecte");
				}
			} else {
				throw new FileException("Accès refusé : Vous n'avez pas les autorisations requises");
			}
		}
	}

	// ##################################################
	// # UTILE #
	// ##################################################

	/**
	 * Récupération de l'utilisateur en fonction de l'id
	 * 
	 * @param id id de l'utilisateur
	 */
	private Utilisateur getUtilisateurById(long id) {
		Optional<Utilisateur> e = utilisateurRepository.findById(id);

		if (e.isPresent())
			return e.get();

		return null;

	}

	/**
	 * Génération du mot de passe
	 * 
	 * @return generatedString mot de passe généré
	 */
	// generation pwd
	public String generatePassword() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		// Random random = new Random();
		SecureRandom random = new SecureRandom();

		// Genere un mot de passe aleatoire de 0 à 9 et de A à Z(Majuscule/minuscule
		// compris) en caractere ASCII
		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		// appendCodePoint => recupere le String en code ASCII pour le dechiffrer en
		// charactere alphanumerique

		return generatedString;
	}

	/**
	 * Reset du mot de passe
	 * 
	 * @throws EmailResetPasswordException
	 * 
	 */
	// reset pwd
	@Override
	public boolean resetPassword(ResetResponse reset) throws EmailResetPasswordException {
		String hashedPwd = null;
		try {
			hashedPwd = HashTools.hashSHA512(reset.getPassword());
		} catch (Exception e) {
			throw new EmailResetPasswordException("1 : Hashage failed");
		}
		String email = jwtTokenUtil.getUsernameFromToken(reset.getToken());

		Utilisateur u = utilisateurRepository.findByEmail(email);
		String currentPwd = "";

		if (u != null)
			try {
				currentPwd = HashTools.hashSHA512(u.getPassword());
			} catch (Exception e) {
				throw new EmailResetPasswordException("2 : Hashage failed");
			}

		if (u != null && !currentPwd.equals(hashedPwd)) {

			u.setPassword(hashedPwd);
			utilisateurRepository.saveAndFlush(u);

			return true;
		} else if (u != null && currentPwd.equals(hashedPwd)) {
			// same password
			return false;
		} else {
			// if user == null
			return false;
		}

	}

	/**
	 * Enregistre en base de données le employees récupéré de DG2
	 * 
	 * @param email , password
	 * @return void
	 */
	@Override
	public void fetchAllDG2Employees(String email, String password) throws FetchDG2Exception, URISyntaxException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<EmployeeDG2Dto> cResJson;
		
		//url dg2 qui concerne la recupération des locations
		URI url = new URI(baseUrl + "employees");
		
		//recupérartion des headers / email / password dg2
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-auth-token", email + ":" + password);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		if (repWs.getStatusCode() == HttpStatus.OK) {
			String json = repWs.getBody();
			//recuperation des values en json et lecture
			cResJson = objectMapper.readValue(json, new TypeReference<List<EmployeeDG2Dto>>() {
			});
			//boucle pour récupérer toute la liste
			for (EmployeeDG2Dto eDG2 : cResJson) {
				Utilisateur utilisateurImport = mapper.employeeDg2ToUtilisateur(eDG2);
				Optional<CentreFormation> centreFormationOpt = centreFormationRepository.findByIdDg2(eDG2.getLocationId());
				Optional<Utilisateur> optUtlisateur = utilisateurRepository.findByIdDg2(utilisateurImport.getIdDg2());
				if (centreFormationOpt.isPresent()) {
					utilisateurImport.setCentreFormation(centreFormationOpt.get());
				}
				else {
					logger.error("SaveAndFlush failed","Centre de formation Introuvable");
					throw new FetchDG2Exception("Centre de formation Introuvable");
				}
				
				if (optUtlisateur.isPresent()) {
					
					if (optUtlisateur.get().equals(utilisateurImport) && optUtlisateur.get().getCentreFormation().getId() == centreFormationOpt.get().getId()) {
						continue;}
					else  {
						if (utilisateurImport != null) {
							utilisateurImport.setPassword(optUtlisateur.get().getPassword());
							utilisateurImport.setId(optUtlisateur.get().getId());
							utilisateurImport.setVersion(optUtlisateur.get().getVersion());
							utilisateurImport.setActive(true);
	
						}
					}
					try {
					utilisateurRepository.saveAndFlush(utilisateurImport);

					} catch (Exception e) {
						logger.error("SaveAndFlush failed", e);

					}
				} else {
					
					try {
						
						utilisateurImport.setPassword(null);
						utilisateurImport.setActive(true);
					    utilisateurRepository.saveAndFlush(utilisateurImport);


					} catch (Exception e) {
						logger.error("SaveAndFlush failed", e);

					
					}
				}
			}
		} else {
			throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
		}
	}
	
	@Override
	public LoginResponseDto checkLogin(LoginDto loginDto) throws Exception {

		if (loginDto.getLogin().contains(domainDawan) 
				|| loginDto.getLogin().contains(domainJehann)) {

			// login via LDAP
			try {
				Hashtable<String, String> environment = new Hashtable<String, String>();
				environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				environment.put(Context.PROVIDER_URL, ldapUrl);
				environment.put(Context.SECURITY_PROTOCOL, ldapProtocol);
				environment.put(Context.SECURITY_AUTHENTICATION, "simple");

				String organizationUnit = "Dawan";
				String uid = loginDto.getLogin().substring(0, loginDto.getLogin().indexOf('@'));
				// les utilisateurs jehann se loggent avec un compte : xxxx-jehann
				if (loginDto.getLogin().contains("@jehann.fr")) {
					uid = uid + "-jehann";
					organizationUnit = "jehann";
				}
				environment.put(Context.SECURITY_PRINCIPAL,
						"uid=" + uid + ",ou=" + organizationUnit + ",ou=Utilisateurs,dc=dawan,dc=fr");
				environment.put(Context.SECURITY_CREDENTIALS, loginDto.getPassword());
				DirContext ctx = new InitialDirContext(environment);
				logger.info("Login of user " + loginDto.getLogin() + " from : " + request.getRemoteAddr());
				ctx.close();

				// check if user exist in application Db
				Utilisateur u = utilisateurRepository.findByEmail(loginDto.getLogin());
				if (u != null) { // user found in DB
					if (u.isActive())
						return createTokenFromUser(u);
					else
						throw new Exception("Error : Blocked Account. Contact administrator !");
				} else { // user authenticated via LDAP but no present in DB
					// recherche des infos
					// on passe sur le compte technique pour pouvoir lire des infos
					environment.put(Context.SECURITY_PRINCIPAL, ldapTechnicalAccDN);
					environment.put(Context.SECURITY_CREDENTIALS, ldapTechnicalAccPwd);

					try {
						ctx = new InitialDirContext(environment);
						SearchControls searchCtls = new SearchControls();
						searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

						String searchFilter = "objectClass=inetOrgPerson";
						NamingEnumeration<?> namingEnum = ctx.search(
								"uid=" + uid + ",ou=" + organizationUnit + ",ou=Utilisateurs,dc=dawan,dc=fr",
								searchFilter, searchCtls);
						while (namingEnum.hasMore()) {
							SearchResult result = (SearchResult) namingEnum.next();
							Attributes userAttr = result.getAttributes();
							// System.out.println("uId = " + userAttr.get("uid"));
							// System.out.println("Mail = " + userAttr.get("mail").get(0).toString());
							// System.out.println("adFirstName =
							// "+userAttr.get("givenName").get(0).toString());
							// System.out.println("adLastName = "+userAttr.get("sn").get(0).toString());
							// System.out.println("name = "+userAttr.get("cn").get(0).toString());

							Utilisateur newUser = new Utilisateur();
							// newUser.setRole(Utilisateur.Role.BASIC_USER); //TODO ROLE TUTEUR EXTERNE PAR
							// DEFAUT
							newUser.setActive(true);
							newUser.setLogin(loginDto.getLogin());
							newUser.setPassword(HashTools.hashSHA512("defaultUs43%_T!_bA0uT(&2"));
							newUser.setExternalAccount(true); // pas de stockage du password

							try {
								newUser.setNom(userAttr.get("sn").get(0).toString());
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								newUser.setPrenom(userAttr.get("givenName").get(0).toString());
							} catch (Exception e) {
								e.printStackTrace();
							}
							newUser = utilisateurRepository.saveAndFlush(newUser);
							return createTokenFromUser(newUser);
						}
						namingEnum.close();
						ctx.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new Exception("Error : Directory search Error !");
					}
				}

			} catch (Exception ex) {
				// ex.printStackTrace(); // TODO comment before prod
				throw new Exception("Error : invalid credentials !");
			}
		} else { // login via Db
					// ================================================================================
			Utilisateur u = utilisateurRepository.findByEmail(loginDto.getLogin());
			if (u != null && u.getPassword().equals(HashTools.hashSHA512(loginDto.getPassword())) && u.isActive()) {
				return createTokenFromUser(u);
			} else {
				logger.info("Login failed of" + loginDto.getLogin() + " from " + request.getRemoteAddr());
				throw new Exception("Error : invalid credentials !");
			}
		}
		throw new Exception("Error : invalid credentials !");

	}

	private LoginResponseDto createTokenFromUser(Utilisateur u) throws Exception {
		LoginResponseDto result = DtoTools.convert(u, LoginResponseDto.class);
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("user_id", u.getId());
		claims.put("user_fullName", u.getPrenom() + " " + u.getNom());
		claims.put("user_role", u.getRolesStr());

		String token = jwtTokenUtil.doGenerateToken(claims, u.getLogin());
		fr.dawan.AppliCFABack.interceptors.TokenSaver.tokensByEmail.put(u.getLogin(), token);
		result.setToken(token);

		logger.info("Login of user " + u.getId() + " from : " + request.getRemoteAddr());

		return result;
	}
	
	public void modifierRolesUtilisateur(long utilisateurId, List<Long> nouveauRolesIds) throws NotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        List<UtilisateurRole> nouveauxRoles = utilisateurRoleRepository.findAllById(nouveauRolesIds);

        
        // ajout de rôle
        for (UtilisateurRole role : nouveauxRoles) {
			if (!utilisateur.getRolesStr().contains(role.getIntitule())) {
				switch (role.getIntitule()) {
				case "ETUDIANT":
					utilisateur.setEtudiant(etudiantService.savEtudiant(utilisateur));
					break;
				case "FORMATEUR":
					utilisateur.setFormateur(formateurService.saFormateur(utilisateur));
					break;
				case "TUTEUR":					
					utilisateur.setTuteur(tuteurService.saveTuteur(utilisateur));
					break;
				default:
					break;
				}
			}		
		}
        utilisateur.modifierRoles(nouveauxRoles);
        
        // suppression de rôle
        for (UtilisateurRoleDto role : utilisateurRoleService.getAllUtilisateurRole()) {
			if (!utilisateur.getRolesStr().contains(role.getIntitule())) {
				switch (role.getIntitule()) {
				case "ETUDIANT":
					try {						
						long etudiantId = utilisateur.getEtudiant().getId();
						//suppression de l'etudiant dans la promotion
						//TODO Modifier promotionRespository par promotionService
						List<Promotion> promotions = promotionRespository.getByEtudiantId(etudiantId);
						if (!promotions.isEmpty()) {
							promotions.forEach(promotion -> promotion.getEtudiants().remove(utilisateur.getEtudiant()));
							promotionRespository.saveAll(promotions);							
						}							
						//suppression du dossierProjet
						List<DossierProjetDto> dossierProjets = DossierProjetService.getByIdEtudiant(etudiantId);
						if (!dossierProjets.isEmpty()) {							
							for (DossierProjetDto dossierProjet : dossierProjets) {
								List<CompetenceProfessionnelleDto> competenceProfessionnellesDtos = competenceProfessionnelleService.getAllByDossierProjets(dossierProjet.getId());
								if (!competenceProfessionnellesDtos.isEmpty()) {
									competenceProfessionnellesDtos.forEach(cp -> competenceProfessionnelleService.deleteById(cp.getId()));									
								}								
								DossierProjetService.deleteById(dossierProjet.getId());							
							}
						}
						// suppression des devoirs de l'étudiant
						List<DevoirEtudiantDto> devoirsEtudiant = devoirEtudiantService.getAllByEtudiantId(etudiantId);
						if (!devoirsEtudiant.isEmpty()) {							
							devoirsEtudiant.forEach(devoirEtudiant -> devoirEtudiantService.delete(devoirEtudiant.getId()));				
						}
						//suppression des dossierProfessionnel de l'étudiant
						List<DossierProfessionnelDto> dosiDossierProfessionnelDtos = dosiDossierProfessionnelService.getByIdEtudiant(etudiantId);
						if (!dosiDossierProfessionnelDtos.isEmpty()) {
							dosiDossierProfessionnelDtos.forEach(dp -> dosiDossierProfessionnelService.delete(dp.getId()));							
						}
						//suppression des livretEvaluation de l'étudiant
						List<LivretEvaluationDto> livretEvaluations = livretEvaluationService.getByEtudiantId(etudiantId);
						if (!livretEvaluations.isEmpty()) {
							livretEvaluations.forEach(le -> livretEvaluationService.delete(le.getId()));							
						}
						//suppression des positionnement de l'étudiant
						PositionnementDto positionnement = positionnementService.getByIdEtudiant(etudiantId);
						if (positionnement != null) {
							positionnementService.delete(positionnement.getId());						
						}
						
						etudiantService.deleteById(etudiantId);		
						utilisateur.setEtudiant(null);
					} catch (Exception e) {
						System.out.println("l'utilisateur na pas d'étudiant");
					}
					break;
					
				case "FORMATEUR":
					try {									
						long formateurId = utilisateur.getFormateur().getId();
						//suppression des intervention liée au formateur
						List<InterventionDto> interventions = interventionService.findAllByFormateurId(formateurId);
						if (!interventions.isEmpty()) {
							interventionService.deleteLstIntervention(interventions);
						}
						formateurService.deleteById(formateurId);
					} catch (Exception e) {
						System.out.println("l'utilisateur na pas de formateur");
					}
					break;
					
				case "TUTEUR":					
					try {
						long tuteurId = utilisateur.getTuteur().getId();
						//suppression du tuteur dans l'étudiant
						List<EtudiantDto> etudiants = etudiantService.findAllByTuteurId(tuteurId);
						if (!etudiants.isEmpty()) {
							etudiants.forEach(etudiant -> {
								etudiant.setTuteurDto(null);
								etudiantService.saveOrUpdate(etudiant);
							});
						}
						tuteurService.delete(tuteurId);
						utilisateur.setTuteur(null);
					} catch (Exception e) {
						System.out.println("l'utilisateur na pas de tuteur");
					}
					break;
					
				default:
					break;
				}
			}
		}       
        
        utilisateurRepository.save(utilisateur);
    }

}
