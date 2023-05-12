package fr.dawan.AppliCFABack.repositories;

import fr.dawan.AppliCFABack.entities.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SignatureRepository extends JpaRepository<Signature, Long>{

	Optional<Signature> getByUtilisateurId( long uId);

    @Query("SELECT s FROM Signature s JOIN Etudiant e ON e.id = :etudiantId JOIN Utilisateur u ON u.id = e.utilisateur.id WHERE s.utilisateur.id = u.id")
    Signature getSignatureByEtudiantId(long etudiantId);
}
