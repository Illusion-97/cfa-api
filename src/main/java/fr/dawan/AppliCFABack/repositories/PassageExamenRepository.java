package fr.dawan.AppliCFABack.repositories;


import fr.dawan.AppliCFABack.entities.PassageExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageExamenRepository extends JpaRepository<PassageExamen, Long> {

//	Page<PassageExamen> findAllByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(
//			String search, String search2, Pageable pageable);
//
//	long countByExamenEnonceContainingIgnoringCaseOrInterventionFormationTitreContainingIgnoringCase(String search,
//			String search2);
//
//	PassageExamen findByInterventionId(long id);
}
