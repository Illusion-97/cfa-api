package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.DossierProfessionnel;

@Repository
public interface DossierProfessionnelRepository extends JpaRepository<DossierProfessionnel, Long>{

	Page<DossierProfessionnel> findByNomContaining(String string, PageRequest of);
	
//	@Query("SELECT dp FROM DossierProfessionnel dp WHERE dp.etudiant.id = :id")
//	List<DossierProfessionnel> findByIdEtudiant(long id);

}
