package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CerfaDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.Cerfa;
import fr.dawan.AppliCFABack.entities.Remuneration;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CerfaRepository;
import fr.dawan.AppliCFABack.repositories.RemunerationRepository;

@Service
@Transactional
public class CerfaServiceImpl implements CerfaService {

	@Autowired
	CerfaRepository cerfaRepo;
	@Autowired
	EtudiantService etudiantService;
	@Autowired 
	AdresseRepository adresseRepo;
	@Autowired
	RemunerationRepository remunerationRepo;
	
	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();
	
	@Override
	public CountDto count(String string) {
		
		return null;
	}

	@Override
	public List<CerfaDto> getAllByPage(int page, int size, String string) {
		
		return null;
	}
	
	/**
	 * Récupération des cerfa de  en fonction de l'id
	 * 
	 */

	@Override
	public CerfaDto getById(long id) {
		
		Optional<Cerfa> c = cerfaRepo.findById(id);
		CerfaDto cDto = mapper.CerfaToCerfaDto(c.get());
		cDto.setRemuneration1(mapper.RemunerationTORemunerationDto(c.get().getRemuneration1()));
		cDto.setRemuneration2(mapper.RemunerationTORemunerationDto(c.get().getRemuneration2()));
		cDto.setRemuneration3(mapper.RemunerationTORemunerationDto(c.get().getRemuneration3()));
		cDto.setRemuneration4(mapper.RemunerationTORemunerationDto(c.get().getRemuneration4()));
		cDto.setAdresseApprenti(mapper.AdresseToAdresseDto(c.get().getAdresseApprenti()));
		cDto.setAdresseEmployeur(mapper.AdresseToAdresseDto(c.get().getAdresseEmployeur()));
		cDto.setAdresseRepresentant(mapper.AdresseToAdresseDto(c.get().getAdresseRepresentant()));
		cDto.setAdresseResponsable(mapper.AdresseToAdresseDto(c.get().getAdresseResponsable()));
		cDto.setEtudiant(etudiantService.getById(c.get().getEtudiant().getId()));
		return cDto;
	}

	/**
	 * Récupération de la liste des cerfa
	 * 
	 * @return lstCerfaDto	Liste des objets cerfa
	 */
	
	@Override
	public List<CerfaDto> getAll() {
		List<Cerfa> lstCerfa = cerfaRepo.findAll();
		List<CerfaDto> lstCerfaDto = new ArrayList<>();
		
		for(Cerfa c : lstCerfa) {
			CerfaDto cDto = mapper.CerfaToCerfaDto(c);
			cDto.setRemuneration1(mapper.RemunerationTORemunerationDto(c.getRemuneration1()));
			cDto.setRemuneration2(mapper.RemunerationTORemunerationDto(c.getRemuneration2()));
			cDto.setRemuneration3(mapper.RemunerationTORemunerationDto(c.getRemuneration3()));
			cDto.setRemuneration4(mapper.RemunerationTORemunerationDto(c.getRemuneration4()));
			cDto.setAdresseApprenti(mapper.AdresseToAdresseDto(c.getAdresseApprenti()));
			cDto.setAdresseEmployeur(mapper.AdresseToAdresseDto(c.getAdresseEmployeur()));
			cDto.setAdresseRepresentant(mapper.AdresseToAdresseDto(c.getAdresseRepresentant()));
			cDto.setAdresseResponsable(mapper.AdresseToAdresseDto(c.getAdresseResponsable()));
			cDto.setEtudiant(etudiantService.getById(c.getEtudiant().getId()));
			lstCerfaDto.add(cDto);
		}
		return lstCerfaDto;
	}

	/**
	 * Suppression d'un cerfa
	 * 
	 * @param id	Id concernant un cerfa
	 */
	
	@Override
	public void deleteById(long id) {
		
		Cerfa c = cerfaRepo.getOne(id);
//		adresseRepo.deleteById(c.getAdresseApprenti().getId());
//		adresseRepo.deleteById(c.getAdresseEmployeur().getId());
//		adresseRepo.deleteById(c.getAdresseRepresentant().getId());
//		adresseRepo.deleteById(c.getAdresseResponsable().getId());
//		remunerationRepo.deleteById(c.getRemuneration1().getId());
//		remunerationRepo.deleteById(c.getRemuneration2().getId());
//		remunerationRepo.deleteById(c.getRemuneration3().getId());
//		remunerationRepo.deleteById(c.getRemuneration4().getId());
		cerfaRepo.deleteById(id);

	}

	/**
	 * Sauvegarde ou mise à jour d'un cerfa
	 * 
	 */
	
	@Override
	public CerfaDto saveOrUpdate(CerfaDto cDto) {
		Cerfa c = DtoTools.convert(cDto, Cerfa.class);
		
		 if (cDto.getRemuneration1() != null && cDto.getRemuneration1().getId() == 0) {
	            Remuneration r = DtoTools.convert(cDto.getRemuneration1(), Remuneration.class);
	            remunerationRepo.saveAndFlush(r);

	            Remuneration remunerationRepop = remunerationRepo.getOne(r.getId());
	            c.setRemuneration1(remunerationRepop);
	        }
		 if (cDto.getRemuneration2() != null && cDto.getRemuneration2().getId() == 0) {
	            Remuneration r = DtoTools.convert(cDto.getRemuneration2(), Remuneration.class);
	            remunerationRepo.saveAndFlush(r);

	            Remuneration remunerationRepop = remunerationRepo.getOne(r.getId());
	            c.setRemuneration2(remunerationRepop);
	        }
		 if (cDto.getRemuneration3() != null && cDto.getRemuneration3().getId() == 0) {
	            Remuneration r = DtoTools.convert(cDto.getRemuneration3(), Remuneration.class);
	            remunerationRepo.saveAndFlush(r);

	            Remuneration remunerationRepop = remunerationRepo.getOne(r.getId());
	            c.setRemuneration3(remunerationRepop);
	        }
		 if (cDto.getRemuneration4() != null && cDto.getRemuneration4().getId() == 0) {
	            Remuneration r = DtoTools.convert(cDto.getRemuneration4(), Remuneration.class);
	            remunerationRepo.saveAndFlush(r);

	            Remuneration remunerationRepop = remunerationRepo.getOne(r.getId());
	            c.setRemuneration4(remunerationRepop);
	        }
		
		 if (cDto.getAdresseApprenti() != null && cDto.getAdresseApprenti().getId() == 0) {
	            Adresse adresse = DtoTools.convert(cDto.getAdresseApprenti(), Adresse.class);
	            adresseRepo.saveAndFlush(adresse);

	            Adresse adresseRepop = adresseRepo.getOne(adresse.getId());
	            c.setAdresseApprenti(adresseRepop);
	        }
		 if (cDto.getAdresseRepresentant() != null && cDto.getAdresseRepresentant().getId() == 0) {
			 if(cDto.getAdresseApprenti().equals(cDto.getAdresseRepresentant())) {
				 c.setAdresseRepresentant(c.getAdresseApprenti());
			 } else {
	            Adresse adresse = DtoTools.convert(cDto.getAdresseRepresentant(), Adresse.class);
	            adresseRepo.saveAndFlush(adresse);

	            Adresse adresseRepop = adresseRepo.getOne(adresse.getId());
	            c.setAdresseRepresentant(adresseRepop);}
	        }
		 
		 if (cDto.getAdresseEmployeur() != null && cDto.getAdresseEmployeur().getId() == 0) {
	            Adresse adresse = DtoTools.convert(cDto.getAdresseEmployeur(), Adresse.class);
	            adresseRepo.saveAndFlush(adresse);

	            Adresse adresseRepop = adresseRepo.getOne(adresse.getId());
	            c.setAdresseEmployeur(adresseRepop);
	        }
		 
		 if (cDto.getAdresseResponsable() != null && cDto.getAdresseResponsable().getId() == 0) {
	            Adresse adresse = DtoTools.convert(cDto.getAdresseResponsable(), Adresse.class);
	            adresseRepo.saveAndFlush(adresse);

	            Adresse adresseRepop = adresseRepo.getOne(adresse.getId());
	            c.setAdresseResponsable(adresseRepop);
	        }
		cerfaRepo.saveAndFlush(c);
		return mapper.CerfaToCerfaDto(c);
	}

	/**
	 * Récupération d'un cerfa par rapport à étudiant
	 * 
	 * @param id 	Id d'un etudiant
	 * @return cerfa le ceraf de l'étudiant donné
	 */
	
	@Override
	public CerfaDto getByIdEtudiant(long id) {
		List<CerfaDto> lst = getAll();
		CerfaDto cerfa = new CerfaDto();
		for (CerfaDto c : lst) {
			if(c.getEtudiant().getId() == id) {
				cerfa = c;
			}
		}
		return cerfa;
	}

}
