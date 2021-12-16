package fr.dawan.AppliCFABack.services;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import fr.dawan.AppliCFABack.dto.*;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.tools.HashTools;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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
    AbsenceRepository absenceRepository;
    @Autowired
    PromotionRepository promotionRespository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    FormateurRepository formateurRepository;
    @Autowired
    CEFRepository cefRepository;
    @Autowired
    MaitreApprentissageRepository maitreApprentissageRepository;

    @Autowired
    EtudiantService etudiantService;
    @Autowired
    FilesService filesService;
    @Autowired
    FormateurService formateurService;
    @Autowired
    EmailService emailService;
    @Autowired
    UtilisateurRoleService utilisateurRoleService;
    @Autowired
    private DtoMapper mapper;
    
    @Autowired
	private JwtTokenUtil jwtTokenUtil;


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
                .findAllByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseRueContainingIgnoringCase(
                        search, search, search, search, PageRequest.of(page, size))
                .get().collect(Collectors.toList());

        List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
        for (Utilisateur u : users) {
            UtilisateurDto uDto = mapper.UtilisateurToUtilisateurDto(u);
            uDto.setAdresseDto(mapper.AdresseToAdresseDto(u.getAdresse()));
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
                .countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseRueContainingIgnoringCase(
                        search, search, search, search));
    }

    @Override
    public UtilisateurDto getById(long id) {
        System.out.println("id : " + id);
        Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
        if (userOpt.isPresent()) {
            UtilisateurDto uDto = mapper.UtilisateurToUtilisateurDto(userOpt.get());

            if (userOpt.get().getAdresse() != null)
                uDto.setAdresseDto(mapper.AdresseToAdresseDto(userOpt.get().getAdresse()));

//			if(userOpt.get().getEntreprise().getAdresseSiege() != null) uDto.getEntrepriseDto().setAdresseSiegeDto(mapper.AdresseToAdresseDto(userOpt.get().getEntreprise().getAdresseSiege()));

            List<UtilisateurRoleDto> utilisateurRoleDto = new ArrayList<UtilisateurRoleDto>();
            for (UtilisateurRole ur : userOpt.get().getRoles()) {
                utilisateurRoleDto.add(mapper.UtilisateurRoleToUtilisateurRoleDto(ur));
            }
            uDto.setRolesDto(utilisateurRoleDto);

            if (userOpt.get().getEtudiant() != null)
                uDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(userOpt.get().getEtudiant()));
            if (userOpt.get().getFormateur() != null)
                uDto.setFormateurDto(mapper.FormateurToFormateurDto(userOpt.get().getFormateur()));
            if (userOpt.get().getCef() != null) uDto.setCefDto(mapper.CEFToCEFDto(userOpt.get().getCef()));

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
                if (user.getPassword().equals("") || user.getPassword() == null) {
                    user.setPassword(generatePassword());
                    emailService.newPassword(user.getLogin(), user.getPassword());
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
            e.printStackTrace();
        }


        // On save l'addresse avant de save l'utilisateur
        if (uDto.getAdresseDto() != null && uDto.getAdresseDto().getId() == 0) {
            Adresse adresse = DtoTools.convert(uDto.getAdresseDto(), Adresse.class);
            adresseRepository.saveAndFlush(adresse);

            Adresse adresseRepop = adresseRepository.getOne(adresse.getId());
            user.setAdresse(adresseRepop);
        }

        //On save les roles
        //Changement de role, on créer l'entité associée
        Boolean isEtudiant = false;
        Boolean isFormateur = false;
        Boolean isCEF = false;
        Boolean isMaitreApprentissage = false;
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
                if (role.getIntitule().equals("MAITREAPPRENTISSAGE")) {
                    isMaitreApprentissage = true;
                }
            }
        }

        user = utilisateurRepository.saveAndFlush(user);

        //Si on ajoute un role
        if (isEtudiant && user.getEtudiant() == null) {
            Etudiant etudiant = new Etudiant();
            etudiant = etudiantRepository.saveAndFlush(etudiant);
            user.setEtudiant(etudiant);
            etudiant.setUtilisateur(user);

            etudiant = etudiantRepository.saveAndFlush(etudiant);

        }
        if (isFormateur && user.getFormateur() == null) {
            Formateur formateur = new Formateur();
            formateur = formateurRepository.saveAndFlush(formateur);
            user.setFormateur(formateur);
            formateur.setUtilisateur(user);

            formateur = formateurRepository.saveAndFlush(formateur);
        }
        if (isCEF && user.getCef() == null) {
            CEF cef = new CEF();
            cef = cefRepository.saveAndFlush(cef);
            user.setCef(cef);
            cef.setUtilisateur(user);

            cef = cefRepository.saveAndFlush(cef);
        }
        if (isMaitreApprentissage && user.getMaitreApprentissage() == null) {
            MaitreApprentissage MaApp = new MaitreApprentissage();
            MaApp = maitreApprentissageRepository.saveAndFlush(MaApp);
            user.setMaitreApprentissage(MaApp);
            MaApp.setUtilisateur(user);

            MaApp = maitreApprentissageRepository.saveAndFlush(MaApp);
        }


        //Si on supprime un role
        if (!isEtudiant && user.getEtudiant() != null) {
            Etudiant etudiant = etudiantRepository.getOne(user.getEtudiant().getId());
            etudiant.setUtilisateur(null);
            user.setEtudiant(null);

            etudiant = etudiantRepository.saveAndFlush(etudiant);

            //On delete l'etudiant ?
//			etudiantService.deleteById(etudiant.getId());			
        }
        if (!isFormateur && user.getFormateur() != null) {
            Formateur formateur = formateurRepository.getOne(user.getFormateur().getId());
            formateur.setUtilisateur(null);
            user.setFormateur(null);

            formateur = formateurRepository.saveAndFlush(formateur);

            //On delete l'etudiant ?
//			formateurService.deleteById(formateur.getId());		
        }
        if (!isCEF && user.getCef() != null) {
            CEF cef = cefRepository.getOne(user.getCef().getId());
            cef.setUtilisateur(null);
            user.setCef(null);

            cef = cefRepository.saveAndFlush(cef);

            //On delete l'etudiant ?
//			cefService.deleteById(cef.getId());	
        }
        if (!isMaitreApprentissage && user.getMaitreApprentissage() != null) {
            MaitreApprentissage maitreApprentissage = maitreApprentissageRepository.getOne(user.getMaitreApprentissage().getUtilisateur().getId());
            maitreApprentissage.setUtilisateur(null);
            user.setMaitreApprentissage(null);

            maitreApprentissage = maitreApprentissageRepository.saveAndFlush(maitreApprentissage);

            //On delete l'etudiant ?
//			maitreApprentissageService.deleteById(maitreApprentissage.getId());	
        }

        user = utilisateurRepository.saveAndFlush(user);

        //On créer les dossier pour l'utilisateur dans le shared
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

//	@Override
//	public List<UtilisateurDto> findByEntreprise(long idEntreprise) {
//		List<Utilisateur> users = utilisateurRepository.findByEntreprise(idEntreprise);
//		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
//		for (Utilisateur u : users) {
//			res.add(mapper.UtilisateurToUtilisateurDto(u));
//		}
//		return res;
//	}

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

    @Override
    public void uploadFile(MultipartFile file, long idUser) throws Exception {
// J'ai 'renforcé' la secu en mappant l'id de l'utilisateur. Seul l'admin pourra uplaod des fichier. A voir si je laisse
        Utilisateur user = getUtilisateurById(idUser);
        for (UtilisateurRole role : user.getRoles()) {

            if (role.getIntitule().equals("ADMIN")) { // Verifie si l'utilisateur a un role admin

                if (file.isEmpty()) { // Si aucun fichier n'a été importé
                    throw new Exception("Fichier introuvable");
                }

                InputStream inputStream = file.getInputStream(); // recupere le stream du fichier pour lire son contenu

                String filename = file.getOriginalFilename();
                String[] fileSplited = filename.split("\\.");

                if (fileSplited[1].equals("csv")) { // si l'extension du fichier == csv
                    CsvParserSettings settings = new CsvParserSettings();
                    settings.setHeaderExtractionEnabled(true); // definit si oui ou non on extrait les en-tetes lors du parsing
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
                        UtilisateurDto utilisateurDto = mapper.UtilisateurToUtilisateurDto(utilisateur);
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
                            e.printStackTrace();
                        }
                    });
                } else { // sinon => ERROR
                    throw new Exception("Extension de fichier incorrecte");
                }
            } else {
                throw new Exception("Acces refusé : Vous n'avez pas les autorisations requises");
            }
        }
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

	@Override
	public boolean resetPassword(ResetResponse reset) throws Exception {
		String hashedPwd = HashTools.hashSHA512(reset.getPassword());
		String email = jwtTokenUtil.getUsernameFromToken(reset.getToken());

		Utilisateur u = utilisateurRepository.findByEmail(email);
		String currentPwd = "";

		if (u != null)
			currentPwd = HashTools.hashSHA512(u.getPassword());

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
}
