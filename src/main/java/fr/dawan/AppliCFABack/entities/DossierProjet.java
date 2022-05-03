package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class DossierProjet extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String nom;

	@OneToOne
	private Projet projet;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

}
