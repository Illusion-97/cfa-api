package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Formateur extends BaseEntity implements Serializable {

	@OneToOne
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Formateur)) return false;
		Formateur formateur = (Formateur) obj;
		return   Objects.equals(getUtilisateur(), formateur.getUtilisateur());
	}
	
	

}
