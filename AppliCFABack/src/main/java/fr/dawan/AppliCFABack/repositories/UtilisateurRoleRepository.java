package fr.dawan.AppliCFABack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.AppliCFABack.entities.UtilisateurRole;

@Repository
public interface UtilisateurRoleRepository extends JpaRepository<UtilisateurRole, Long>{

}
