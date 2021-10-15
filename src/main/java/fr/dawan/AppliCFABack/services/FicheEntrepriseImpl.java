package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
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

	@Override
	public List<FicheEntrepriseDto> getAllFicheEntreprise() {
		List<FicheEntreprise> lst = ficheEntrepriseRepository.findAll();

		List<FicheEntrepriseDto> lstDto = new ArrayList<FicheEntrepriseDto>();
		for (FicheEntreprise n : lst) {
			FicheEntrepriseDto fDto = mapper.FicheEntrepriseToFicheEntrepriseDto(n);
			fDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(n.getEtudiant()));
			fDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(n.getEntreprise()));

			fDto.getEntrepriseDto().setAdresseSiegeDto(mapper.AdresseToAdresseDto(n.getEntreprise().getAdresseSiege()));
			lstDto.add(fDto);
		}
		return lstDto;
	}

	@Override
	public List<FicheEntrepriseDto> getAllByPage(int page, int size, String search) {
		List<FicheEntreprise> lst = ficheEntrepriseRepository
				.findAll();

		// conversion vers Dto
		List<FicheEntrepriseDto> lstDto = new ArrayList<FicheEntrepriseDto>();
		for (FicheEntreprise c : lst) {
			FicheEntrepriseDto fDto = mapper.FicheEntrepriseToFicheEntrepriseDto(c);
			fDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(c.getEntreprise()));
			//fDto.getEntrepriseDto().setRaisonSociale(mapper.EntrepriseToEntrepriseDto(c.getEntreprise().getRaisonSociale()));
			fDto.getEntrepriseDto().setAdresseSiegeDto(mapper.AdresseToAdresseDto(c.getEntreprise().getAdresseSiege()));
			lstDto.add(fDto);
		}
		return lstDto;
	}

//	@Override
//	public CountDto count(String search) {
//		// TODO Auto-generated method stub
//		return new CountDto(ficheEntrepriseRepository.countByNomContainingIgnoringCase(search));
//	}

	@Override
	public FicheEntrepriseDto getById(long id) {
		Optional<FicheEntreprise> e = ficheEntrepriseRepository.findById(id);
		if (e.isPresent()) {
			
			FicheEntrepriseDto fDto = mapper.FicheEntrepriseToFicheEntrepriseDto(e.get());

			fDto.setEntrepriseDto(mapper.EntrepriseToEntrepriseDto(e.get().getEntreprise()));
			fDto.setEtudiantDto(mapper.EtudiantToEtudiantDto(e.get().getEtudiant()));	

			fDto.getEntrepriseDto().setAdresseSiegeDto(mapper.AdresseToAdresseDto(e.get().getEntreprise().getAdresseSiege()));
			return fDto;
		}			

		return null;
	}

	@Override
	public FicheEntrepriseDto saveOrUpdate(FicheEntrepriseDto fDto) {
		FicheEntreprise u = DtoTools.convert(fDto, FicheEntreprise.class);
		
		u = ficheEntrepriseRepository.saveAndFlush(u);
		
		return mapper.FicheEntrepriseToFicheEntrepriseDto(u);
	}

	@Override
	public void deleteById(long id) {
		ficheEntrepriseRepository.deleteById(id);

	}

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
