package fr.dawan.AppliCFABack.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.repositories.DossierProjetRepository;

@Service
@Transactional
public class DossierProjetServiceImpl implements DossierProjetService {
	
	@Autowired
	DossierProjetRepository dossierProRepo;

}
