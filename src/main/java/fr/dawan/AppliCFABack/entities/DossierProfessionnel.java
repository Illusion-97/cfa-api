package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class DossierProfessionnel extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String nom;

	@OneToOne
	private Cursus cursus;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}

}
