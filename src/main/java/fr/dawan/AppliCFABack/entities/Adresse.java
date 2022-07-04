package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Adresse extends BaseEntity implements Serializable {
	
	@Column(nullable = false, length = 255)
	private int numero;

	@Column(nullable = false, length = 255)
	private String rue;


	@Column(nullable = false, length = 255)
	private String libelle;

	@Column(nullable = false, length = 255)
	private String ville;

	@Column(nullable = false, length = 255)
	private String codePostal;

	public Adresse() {
		super();
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public Adresse(int numero, String rue, String ville, String codePostal) {
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setLibelle(String rue) {
		this.libelle = rue;
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
