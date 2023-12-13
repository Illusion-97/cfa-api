package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.EtudiantDto;
import fr.dawan.AppliCFABack.dto.TuteurDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Tuteur;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.EtudiantRepository;
import fr.dawan.AppliCFABack.repositories.TuteurRepository;
import fr.dawan.AppliCFABack.tools.HashTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
@Transactional
public class TuteurServiceImpl extends GenericServiceImpl<Tuteur, TuteurDto> implements TuteurService{

	@Autowired
	TuteurRepository tuteurRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Autowired
	public TuteurServiceImpl(TuteurRepository tuteurRepository)
	{
		super(tuteurRepository, TuteurDto.class, Tuteur.class);
		this.tuteurRepository = tuteurRepository;
	}
	
	private static final Logger logger = Logger.getGlobal();
	
	/**
	 * Récupération de la liste des tuteurs
	 *
	 * @return lstTDto	Liste des objets Tuteur
	 */
	@Override
	public List<TuteurDto> getAll() {
		List<Tuteur> lst = tuteurRepository.findAll();
		List<TuteurDto> lstTDto = new ArrayList<>();
		for (Tuteur t : lst) {
			lstTDto.add(mapper.tuteurTotuteurDto(t));
		}
		return lstTDto;
	
	}
	
	/**
	 * Va permettre de récupérer tous les Tuteurs avec pagination
	 *
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @return LstDto Liste des objets tuteurs
	 */
	@Override
	public List<TuteurDto> getAllByPage(int page, int size) {
		List<Tuteur> lst = tuteurRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<TuteurDto> lstTDto = new ArrayList<>();
		for (Tuteur t : lst) {
			lstTDto.add(mapper.tuteurTotuteurDto(t));
		}
		return lstTDto;
	}

	/**
	 * Va permettre de récupérer tous les tuteurs avec pagination
	 * recherche par nom ou prenom
	 *
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search éléménts de l'utilisateur tuteur
	 * @return LstDto Liste des objets tuteirs
	 */
	@Override
	public List<TuteurDto> getAllByPageWithKeyword(int page, int size, String search) {
		List<Tuteur> lst = tuteurRepository
				.findAllByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(search, search, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<TuteurDto> lstTDto = new ArrayList<>();
		for (Tuteur t : lst) {
			lstTDto.add(mapper.tuteurTotuteurDto(t));
		}
		return lstTDto;
	}
	
	/**
	 * Récupération des tuteurs en fonction de l'id
	 *
	 * @param id	id du tuteur
	 */
	@Override
	public TuteurDto getById(long id) {
		Optional<Tuteur> t= tuteurRepository.findById(id);
		if (t.isPresent()) {
			TuteurDto tuteurDto = mapper.tuteurTotuteurDto(t.get());
			tuteurDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(t.get().getUtilisateur()));
			return tuteurDto;
		}

		return null;
	}


	/**
	 * Sauvegarde ou mise à jour d'un tuteur
	 *
	 */

	@Override
	public TuteurDto saveOrUpdate(TuteurDto tuteurDto) {
		
	Tuteur tuteur = DtoTools.convert(tuteurDto, Tuteur.class);
		
		if(tuteur.getUtilisateur() != null) {
			//HashTools throw Exception
			try {
				//Si l'utilisateur n'est pas déjà en base, il faut hasher son mdp
				if(tuteur.getUtilisateur().getId() == 0) {
					tuteur.getUtilisateur().setPassword(HashTools.hashSHA512(tuteur.getUtilisateur().getPassword()));
				}else {
					//Si on a modifié le mdp
					Tuteur tuteurInDB = tuteurRepository.getOne(tuteur.getId());
					if(!tuteurInDB.getUtilisateur().getPassword().equals(tuteur.getUtilisateur().getPassword())) {
						tuteur.getUtilisateur().setPassword(HashTools.hashSHA512(tuteur.getUtilisateur().getPassword()));
		            }
				}	
			}catch (Exception e) {
	            logger.log(Level.WARNING, "save or update failed", e);
	        }
		}
		
		
		tuteur = tuteurRepository.saveAndFlush(tuteur);
		return mapper.tuteurTotuteurDto(tuteur);
	}

	/**
	 * Recherche tous les étudiants associés à un tuteur par son identifiant.
	 *
	 * @param id L'identifiant unique du tuteur.
	 * @return Une liste d'objets EtudiantDto représentant les étudiants associés au tuteur.
	 */
	@Override
	public List<EtudiantDto> findAllByTuteurId(long id) {
		List<Etudiant> lstetud= tuteurRepository.findAllByTuteurId(id);
				List<EtudiantDto> lstetudDto = new ArrayList<>();
				for (Etudiant etudiant : lstetud) 
				{
					if (etudiant != null) {
						EtudiantDto etudDto = mapper.etudiantToEtudiantDto(etudiant);

						TuteurDto tuteurDto = mapper.tuteurTotuteurDto(etudiant.getTuteur());

						etudDto.setTuteurDto(tuteurDto);
						lstetudDto.add(etudDto);
				}
				}
				return lstetudDto;
	}

	/**
	 * Récupère une liste paginée d'étudiants associés à un tuteur par son identifiant.
	 *
	 * @param id   L'identifiant unique du tuteur.
	 * @param page Le numéro de la page à récupérer (commence à 0).
	 * @param size Le nombre d'éléments par page.
	 * @return Une liste d'objets EtudiantDto représentant les étudiants associés au tuteur pour la page spécifiée.
	 */
	@Override
	public List<EtudiantDto> getAllEtudiantsByTuteurIdPerPage(long id, int page, int size) {
		List<Etudiant> lstetud= etudiantRepository.findAllByTuteurId(id, PageRequest.of(page, size))
				.get()
				.collect(Collectors.toList());
				List<EtudiantDto> lstetudDto = new ArrayList<>();
				for (Etudiant etudiant : lstetud) 
				{
					if (etudiant != null) {
						EtudiantDto etudDto = mapper.etudiantToEtudiantDto(etudiant);
						etudDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(etudiant.getUtilisateur()));	
						lstetudDto.add(etudDto);
				}
				}
				return lstetudDto;
	}
	/**
	 * Récupère une liste paginée d'étudiants associés à un tuteur par son identifiant et en fonction d'une recherche.
	 *
	 * @param id    L'identifiant unique du tuteur.
	 * @param page  Le numéro de la page à récupérer (commence à 0).
	 * @param size  Le nombre d'éléments par page.
	 * @param search Le terme de recherche pour filtrer les résultats.
	 * @return Une liste d'objets EtudiantDto représentant les étudiants associés au tuteur et correspondant à la recherche pour la page spécifiée.
	 */
	@Override
	public List<EtudiantDto> getEtudiantByTuteurAndBySearch(long id, int page, int size, String search) {
		List<Etudiant> lstetud= tuteurRepository.findEtudiantBySearch(id, PageRequest.of(page, size), search)
				.get()
				.collect(Collectors.toList());
				List<EtudiantDto> lstetudDto = new ArrayList<>();
				for (Etudiant etudiant : lstetud) 
				{
					if (etudiant != null) {
						EtudiantDto etudDto = mapper.etudiantToEtudiantDto(etudiant);
						etudDto.setUtilisateurDto(mapper.utilisateurToUtilisateurDto(etudiant.getUtilisateur()));	
						lstetudDto.add(etudDto);
					}
				}
				return lstetudDto;

	}

	/**
	 * Compte le nombre total d'étudiants associés à un tuteur par son identifiant.
	 *
	 * @param id L'identifiant unique du tuteur.
	 * @return Un objet CountDto contenant le nombre d'étudiants associés au tuteur.
	 */
	@Override
	public CountDto countEtudiantByIdTuteur(long id) {
		return new CountDto(etudiantRepository.countByTuteurId(id));
	}
	/**
	 * Compte le nombre total d'étudiants associés à un tuteur par son identifiant en fonction d'une recherche.
	 *
	 * @param id     L'identifiant unique du tuteur.
	 * @param search Le terme de recherche pour filtrer les résultats.
	 * @return Un objet CountDto contenant le nombre d'étudiants associés au tuteur correspondant à la recherche.
	 */
	@Override
	public CountDto countEtudiantByIdTuteurSearch(long id, String search) {
		return new CountDto(tuteurRepository.countByIdAndEtudiantsUtilisateurNomContainingAllIgnoringCase(id, search));
	}

	/**
	 * Compte le nombre total d'étudiants en fonction d'une recherche sur les noms et prénoms.
	 *
	 * @param search Le terme de recherche pour filtrer les résultats.
	 * @return Un objet CountDto contenant le nombre d'étudiants correspondant à la recherche.
	 */
	@Override
	public CountDto count(String search) {
		long nb = tuteurRepository.countByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(search, search);
		CountDto result =  new CountDto();
		result.setNb(nb);
		return result;
	}

	/**
	 * Supprime un tuteur par son identifiant.
	 *
	 * @param id L'identifiant unique du tuteur à supprimer.
	 */
	@Override
	public void delete(long id) {
		Optional<Tuteur> opt = tuteurRepository.findById(id);
		if(opt.isPresent())
		{
			Tuteur t = opt.get();
			t.setEtudiants(null);
			t.setUtilisateur(null);
			tuteurRepository.delete(t);
			
		}
		
	}

	/**
	 * Enregistre un nouveau tuteur avec l'utilisateur spécifié.
	 *
	 * @param utilisateur L'objet Utilisateur associé au nouveau tuteur.
	 * @return L'objet Tuteur enregistré.
	 */
	@Override
	public Tuteur saveTuteur(Utilisateur utilisateur) {
		Tuteur tuteur = new Tuteur();
		tuteur.setUtilisateur(utilisateur);
		
		return tuteurRepository.save(tuteur);
	}

	
	
	

	

	
}
