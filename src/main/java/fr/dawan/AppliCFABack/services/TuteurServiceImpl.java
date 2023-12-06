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
	
	@Override
	public List<TuteurDto> getAll() {
		List<Tuteur> lst = tuteurRepository.findAll();
		List<TuteurDto> lstTDto = new ArrayList<>();
		for (Tuteur t : lst) {
			lstTDto.add(mapper.tuteurTotuteurDto(t));
		}
		return lstTDto;
	
	}
	
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

	@Override
	public List<EtudiantDto> getEtudiantBySearch(long id, int page, int size, String search) {
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

	@Override
	public CountDto countEtudiantByIdTuteur(long id) {
		return new CountDto(etudiantRepository.countByTuteurId(id));
	}

	@Override
	public CountDto countEtudiantByIdTuteurSearch(long id, String search) {
		return new CountDto(tuteurRepository.countByIdAndEtudiantsUtilisateurNomContainingAllIgnoringCase(id, search));
	}

	@Override
	public CountDto count(String search) {
		long nb = tuteurRepository.countByUtilisateurPrenomContainingOrUtilisateurNomContainingAllIgnoreCase(search, search);
		CountDto result =  new CountDto();
		result.setNb(nb);
		return result;
	}

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

	@Override
	public Tuteur saveTuteur(Utilisateur utilisateur) {
		Tuteur tuteur = new Tuteur();
		tuteur.setUtilisateur(utilisateur);
		
		return tuteurRepository.save(tuteur);
	}
	
	

	

	
}
