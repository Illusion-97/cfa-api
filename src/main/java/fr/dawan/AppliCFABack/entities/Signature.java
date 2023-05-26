package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Set;
/***
 * 
 * @author Feres BG Valentin C.
 * @see Utilisateur
 * @since 1.0
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
@Entity
public class Signature extends BaseEntity implements Serializable {

	@Column(nullable = false, columnDefinition = "TEXT")
	private String pieceJointe;

	@OneToMany(mappedBy = "signature")
	private Set<Validation> validations;
	
	@OneToOne
	private Utilisateur utilisateur;

	public String getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	/**
	 * @return l'utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur l'utilisateur à affecter
	 
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}



}
