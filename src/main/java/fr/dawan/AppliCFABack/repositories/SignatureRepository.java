package fr.dawan.AppliCFABack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.entities.Signature;

public interface SignatureRepository extends JpaRepository<Signature, Long>{

	Optional<Signature> getByUtilisateurId( long uId);
}
