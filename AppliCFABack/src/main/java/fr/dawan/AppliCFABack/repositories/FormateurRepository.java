package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.Formateur;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {
	
}
