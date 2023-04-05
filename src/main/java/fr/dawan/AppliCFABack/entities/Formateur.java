package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Formateur extends BaseEntity implements Serializable {

	@OneToOne(cascade = CascadeType.ALL)
	private Utilisateur utilisateur;

	public Formateur() {
		super();
	}

	public Formateur(long id, Utilisateur utilisateur, Entreprise entreprise) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;

	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
