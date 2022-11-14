package fr.dawan.AppliCFABack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.entities.Signature;
import org.springframework.data.jpa.repository.Query;

public interface SignatureRepository extends JpaRepository<Signature, Long>{

	Optional<Signature> getByUtilisateurId( long uId);

    @Query("SELECT s FROM Signature s JOIN Etudiant e ON e.id = :etudiantId JOIN Utilisateur u ON u.id = e.utilisateur.id WHERE s.utilisateur.id = u.id")
    Signature getSignatureByEtudiantId(long etudiantId);
}
