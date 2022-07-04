package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Adresse extends BaseEntity implements Serializable {


	@Column(nullable = false, length = 255)
	private String libelle;

	@Column(nullable = false, length = 255)
	private String ville;

	@Column(nullable = false, length = 255)
	private String codePostal;

	public Adresse() {
		super();
	}

	public Adresse(String libelle, String ville, String codePostal) {
		this.libelle = libelle;
		this.ville = ville;
		this.codePostal = codePostal;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}




}
