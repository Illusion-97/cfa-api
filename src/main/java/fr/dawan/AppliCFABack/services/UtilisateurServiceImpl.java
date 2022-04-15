package fr.dawan.AppliCFABack.services;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.AdresseDto;
import fr.dawan.AppliCFABack.dto.CongeDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.JourneePlanningDto;
import fr.dawan.AppliCFABack.dto.ResetResponse;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Absence;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CEF;
import fr.dawan.AppliCFABack.entities.Conge;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.MaitreApprentissage;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.AbsenceRepository;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CEFRepository;
import fr.dawan.AppliCFABack.repositories.CongeRepository;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.MaitreApprentissageRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.tools.HashTools;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;

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


    //recuperation de la liste des user
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

    //recuperation de la liste des user avec pagination et recherche
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

    //count search
    @Override
    public CountDto count(String search) {
        return new CountDto(utilisateurRepository
                .countByPrenomContainingIgnoringCaseOrNomContainingIgnoringCaseOrLoginContainingIgnoringCaseOrAdresseRueContainingIgnoringCase(
                        search, search, search, search));
    }

    //recuperation des user par id
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

    //recuperation des user par email
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

    //recuperation des user par name
    @Override
    public UtilisateurDto getName(String name) {
        Utilisateur user = utilisateurRepository.findByName(name);
        if (user != null)
            return mapper.UtilisateurToUtilisateurDto(user);
        return null;
    }

    //methode d'ajout ou modification d'un user
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
                    //emailService.newPassword(user.getLogin(), user.getPassword());
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
        result.setEtudiantDto(mapper.EtudiantToEtudiantDto(user.getEtudiant()));
        result.setFormateurDto(mapper.FormateurToFormateurDto(user.getFormateur()));
        result.setCefDto(mapper.CEFToCEFDto(user.getCef()));
        result.setMaitreApprentissageDto(mapper.MaitreApprentissageToMaitreApprentissageDto(user.getMaitreApprentissage()));

        return result;
    }

    //methode de suppression d'un user
    @Override
    public void deleteById(long id) {
    	
    	
    	Utilisateur utilisateur = utilisateurRepository.getOne(id);
    	
    	if(utilisateur.getEtudiant() != null) {
    		Etudiant etudiant = etudiantRepository.getOne(utilisateur.getEtudiant().getId());
    		etudiant.setUtilisateur(null);
    		utilisateur.setEtudiant(null);
//    		etudiantRepository.save(etudiant);
    		etudiantRepository.delete(etudiant);
    	}
		if(utilisateur.getFormateur() != null) {
			Formateur formateur = formateurRepository.getOne(utilisateur.getFormateur().getId());
			formateur.setUtilisateur(null);
    		utilisateur.setFormateur(null);
//    		formateurRepository.save(formateur);
    		formateurRepository.delete(formateur);
		}
		if(utilisateur.getCef() != null) {
			CEF cef = cefRepository.getOne(utilisateur.getCef().getId());
    		cef.setUtilisateur(null);
    		utilisateur.setCef(null);
//    		cefRepository.save(cef);
    		cefRepository.delete(cef);
		}
		if(utilisateur.getMaitreApprentissage() != null) {
			MaitreApprentissage maitreApprentissage = maitreApprentissageRepository.getOne(utilisateur.getMaitreApprentissage().getId());
			maitreApprentissage.setUtilisateur(null);
    		utilisateur.setMaitreApprentissage(null);
//    		maitreApprentissageRepository.save(maitreApprentissage);
    		maitreApprentissageRepository.delete(maitreApprentissage);
		}
		
    	
        utilisateurRepository.deleteById(id);        
        
        
        filesService.deleteDirectoryWithContent("utilisateurs/" + id);
    }

    //recuperation des user par adresse id
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

    //planning de l'user
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

    //congé de l'user
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

    //recuperation des user par role
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

    //recuperation des user par role + pagination + recherche
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

    // count par role comme plusieurs role
    @Override
    public CountDto countByRole(String role, String search) {
        return new CountDto(utilisateurRepository
                .countByRolesIntituleIgnoringCaseAndPrenomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndNomContainingIgnoringCaseOrRolesIntituleIgnoringCaseAndLoginContainingIgnoringCase(
                        role, search, role, search, role, search));
    }

    //user referent ou non
    @Override
    public Boolean isReferent(long id) {
        List<Promotion> result = promotionRespository.findAllByReferentPedagogiqueId(id);

        if (result.size() != 0)
            return true;
        else
            return false;
    }

    //file upload
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

    //generation pwd
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

    //reset pwd
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
	
	//import des users DG2 en prevoyance des necessité d'ajouts pour le projet
//		@Override
//		public void fetchAllDG2User(String email, String password) throws Exception {
//			ObjectMapper objectMapper = new ObjectMapper();
//			List<UserDG2Dto> cResJson;
//			
//			//url dg2 qui concerne la recupération des locations
//			URI url = new URI("https://dawan.org/api2/cfa/users");
//			
//			//recupérartion des headers / email / password dg2
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("x-auth-token", email + ":" + password);
//
//			HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//
//			ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
//
//			if (repWs.getStatusCode() == HttpStatus.OK) {
//				String json = repWs.getBody();
//				//recuperation des values en json et lecture
//				cResJson = objectMapper.readValue(json, new TypeReference<List<UtilisateurDG2Dto>>() {
//				});
//				//boucle pour récupérer toute la liste
//				for (UtilisateurDG2Dto uDG2 : uResJson) {
//					Utilisateur userImport = mapper.utilisateurDG2DtoToUtilisateur(uDG2);
//					Optional<Utilisateur> optUser = utilisateurRepository.findByIdDg2(userImport.getIdDg2());
//
//					if (optUser.isPresent()) {
//						if (optUser.get().equals(userImport))
//							continue;
//						else if (!optUser.get().equals(userImport)) {
//							userImport.setId(optUser.get().getId());
//						}
//						utilisateurRepository.saveAndFlush(userImport);
//					} else {
//						utilisateurRepository.saveAndFlush(userImport);
//					}
//				}
//			} else {
//				throw new Exception("ResponseEntity from the webservice WDG2 not correct");
//			}
//		}
}
