package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Adresse extends BaseEntity implements Serializable {


	@Column(nullable = true) // id dans dg2
	private long idDg2;
	
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

	/**
	 * @return le idDg2
	 */
	public long getIdDg2() {
		return idDg2;
	}

	/**
	 * @param idDg2 le idDg2 à affecter
	 
	 */
	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}
	

}
