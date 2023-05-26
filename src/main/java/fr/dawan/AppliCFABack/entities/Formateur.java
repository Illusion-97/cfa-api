package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
@Entity
public class Formateur extends BaseEntity implements Serializable {

	@OneToOne
	@JoinColumn(name = "utilisateur_id")
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
