package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Etudiant,Utilisateur
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"utilisateur_id", "etudiant_id" })  )
public class MaitreApprentissage extends BaseEntity implements Serializable {

	@ManyToOne
	private Etudiant etudiant;
	@ManyToOne
	private Utilisateur utilisateur;
	
	/**
	 * @return le etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}
	/**
	 * @param etudiant le etudiant à affecter
	 
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	/**
	 * @return le utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	/**
	 * @param utilisateur le utilisateur à affecter
	 
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
