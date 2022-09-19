package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FicheEntrepriseDto;
import fr.dawan.AppliCFABack.entities.FicheEntreprise;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.FicheEntrepriseRepository;

@Service
@Transactional
public class FicheEntrepriseImpl implements FicheEntrepriseService {

	@Autowired
	FicheEntrepriseRepository ficheEntrepriseRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	/**
	 * Récupération de la liste des fiches entreprises
	 * 
	 * @return lstDto	Liste des objets fiches entreprises
	 */
	
	@Override
	public List<FicheEntrepriseDto> getAllFicheEntreprise() {
		List<FicheEntreprise> lst = ficheEntrepriseRepository.findAll();

		List<FicheEntrepriseDto> lstDto = new ArrayList<>();
		for (FicheEntreprise n : lst) {
			FicheEntrepriseDto fDto = mapper.ficheEntrepriseToFicheEntrepriseDto(n);
			fDto.setEtudiantDto(mapper.etudiantToEtudiantDto(n.getEtudiant()));
			fDto.setEntrepriseDto(mapper.entrepriseToEntrepriseDto(n.getEntreprise()));

			fDto.getEntrepriseDto().setAdresseSiegeDto(mapper.adresseToAdresseDto(n.getEntreprise().getAdresseSiege()));
			lstDto.add(fDto);
		}
		return lstDto;
	}

	/**
	 * Va permettre de récupérer toutes les fiches entreprises avec pagination
	 * et recherche
	 * 
	 * @param page	numero de la page
	 * @param size	éléments sur la page
	 * @param search	éléments fiche entreprises 
	 * @return lstDto Liste des objets fiches entreprises
	 */
	
	@Override
	public List<FicheEntrepriseDto> getAllByPage(int page, int size, String search) {
		List<FicheEntreprise> lst = ficheEntrepriseRepository
				.findAll();

		// conversion vers Dto
		List<FicheEntrepriseDto> lstDto = new ArrayList<>();
		for (FicheEntreprise c : lst) {
			FicheEntrepriseDto fDto = mapper.ficheEntrepriseToFicheEntrepriseDto(c);
			fDto.setEntrepriseDto(mapper.entrepriseToEntrepriseDto(c.getEntreprise()));
			fDto.getEntrepriseDto().setAdresseSiegeDto(mapper.adresseToAdresseDto(c.getEntreprise().getAdresseSiege()));
			lstDto.add(fDto);
		}
		return lstDto;
	}

	/**
	 * Récupération des fiches entreprises en fonction de l'id
	 * 
	 * @param id	id de la fiche entreprise
	 */
	
	@Override
	public FicheEntrepriseDto getById(long id) {
		Optional<FicheEntreprise> e = ficheEntrepriseRepository.findById(id);
		if (e.isPresent()) {
			
			FicheEntrepriseDto fDto = mapper.ficheEntrepriseToFicheEntrepriseDto(e.get());

			fDto.setEntrepriseDto(mapper.entrepriseToEntrepriseDto(e.get().getEntreprise()));
			fDto.setEtudiantDto(mapper.etudiantToEtudiantDto(e.get().getEtudiant()));	

			fDto.getEntrepriseDto().setAdresseSiegeDto(mapper.adresseToAdresseDto(e.get().getEntreprise().getAdresseSiege()));
			return fDto;
		}			

		return null;
	}

	/**
	 * Sauvegarde ou mise à jour d'une fiche entreprise
	 * 
	 */
	
	@Override
	public FicheEntrepriseDto saveOrUpdate(FicheEntrepriseDto fDto) {
		FicheEntreprise u = DtoTools.convert(fDto, FicheEntreprise.class);
		
		u = ficheEntrepriseRepository.saveAndFlush(u);
		
		return mapper.ficheEntrepriseToFicheEntrepriseDto(u);
	}

	/**
	 * Suppression d'une fiche entreprise
	 * 
	 * @param id	Id concernant la fiche entreprise
	 */
	
	@Override
	public void deleteById(long id) {
		ficheEntrepriseRepository.deleteById(id);

	}

	/**
	 * Récupération des fiches entreprises en fonction de l'id de l'étudiant
	 * 
	 * @param id	id de l'etudiant
	 * @return f	fiche entreprise de l'étudiant
	 */
	
	@Override
	public FicheEntrepriseDto getByIdEtudiant(long id) {
		List<FicheEntrepriseDto> lst = getAllFicheEntreprise();
		FicheEntrepriseDto f = new FicheEntrepriseDto();
		for (FicheEntrepriseDto ficheEntrepriseDto : lst) {
			if(ficheEntrepriseDto.getEtudiantDto().getId() == id) {
				f = ficheEntrepriseDto;
			}
		}
		return f;
	}

}
