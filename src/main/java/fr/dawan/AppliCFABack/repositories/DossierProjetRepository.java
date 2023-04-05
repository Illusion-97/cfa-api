package fr.dawan.AppliCFABack.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.DossierProjet;

@Repository
public interface DossierProjetRepository extends JpaRepository<DossierProjet, Long> {

	Page<DossierProjet> findByNomContaining(String string, PageRequest of);

	@Query("SELECT d FROM DossierProjet d where nom = :nom")
	DossierProjet getByName(String nom);
}
