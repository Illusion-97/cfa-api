package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PositionnementDto;
import fr.dawan.AppliCFABack.entities.Positionnement;
import fr.dawan.AppliCFABack.repositories.PositionnementRepository;
@Service
@Transactional
public class PositionnementServiceImpl implements PositionnementService {

	@Autowired
	private PositionnementRepository positionnementRepository;
	
	
	@Override
	public PositionnementDto getById(long id) {
		Optional<Positionnement> p= positionnementRepository.findById(id) ;
		if (p.isPresent()) 
			return DtoTools.convert(p.get(), PositionnementDto.class);
		
		return null;
	}

	@Override
	public PositionnementDto saveOrUpdate(PositionnementDto tDto) throws Exception {
		if (tDto.getEtudiantId() == 0 || tDto.getInterventionId() == 0)
			throw new Exception("Inmposible de persister etudiant ou intervention invalide");
		
		Positionnement p = DtoTools.convert(tDto, Positionnement.class);
		p = positionnementRepository.saveAndFlush(p);
		return DtoTools.convert(p, PositionnementDto.class);
	}

	@Override
	public CountDto count(String search) {
		long nb = positionnementRepository.countByEtudiantUtilisateurNomContainingOrEtudiantUtilisateurPrenomContaining(search, search);
		CountDto d = new CountDto();
		d.setNb(nb);
		return d;
	}

	@Override
	public void delete(long id) {
		positionnementRepository.deleteById(id);
		
	}

	@Override
	public List<PositionnementDto> getAllByPromotionId(long idPromotion) {
		List<Positionnement> positionnements = positionnementRepository.getAllByPromotionId(idPromotion);
		List<PositionnementDto> result = new ArrayList<PositionnementDto>();
		for (Positionnement p : positionnements) {
			result.add(DtoTools.convert(p, PositionnementDto.class));
		}
		return result;
	}

	@Override
	public List<PositionnementDto> getAllByInterventionId(long idIntervention) {
		List<Positionnement> positionnements = positionnementRepository.getAllByInterventionId(idIntervention);
		List<PositionnementDto> result = new ArrayList<PositionnementDto>();
		for (Positionnement p : positionnements) {
			result.add(DtoTools.convert(p, PositionnementDto.class));
		}
		return result;
	}

}
