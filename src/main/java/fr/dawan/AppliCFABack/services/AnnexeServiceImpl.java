package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.AnnexeDto;
import fr.dawan.AppliCFABack.entities.Annexe;
import fr.dawan.AppliCFABack.repositories.AnnexeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;

@Service
@Transactional
public class AnnexeServiceImpl extends GenericServiceImpl<Annexe, AnnexeDto> implements AnnexeService{

	@Autowired
	AnnexeRepository annexeRepo;

	@Value("src/main/resources/files/")
	private String storageFolder2;

	@Autowired
	public AnnexeServiceImpl(AnnexeRepository repository) {
		super(repository, AnnexeDto.class, Annexe.class);
		this.annexeRepo = repository;
	}

	/**
	 * Supprime une annexe par son identifiant.
	 *
	 * @param annexeId L'identifiant unique de l'annexe à supprimer.
	 * @return true si la suppression est réussie, false sinon.
	 */
	@Override
	public boolean deleteAnnexe(Long annexeId) {

		Annexe annexe = annexeRepo.findById(annexeId).orElse(null);
		if (annexe == null) {
			return false; 
		}

		String path = storageFolder2 + "DossierProfessionnel" + "/" ;
		if (path != null) {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
		}
		annexeRepo.delete(annexe);
		return true;

	}
	
}
