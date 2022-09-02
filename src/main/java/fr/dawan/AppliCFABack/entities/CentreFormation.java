package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class CentreFormation extends BaseEntity implements Serializable{

	@Column(nullable = true) // id dans dg2
	private long idDg2;
	
	
	@Column(nullable = true, length = 255) // name dans dg2
	private String nom;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Adresse adresse;

	@ManyToOne
	private Entreprise entreprise;

	public CentreFormation() {
		super();
	}

	public CentreFormation(long id, String nom, Adresse adresse, Entreprise entreprise) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.entreprise = entreprise;
	}

	public CentreFormation(long id, long idDg2, String countryCode, String nom, Adresse adresse,
			Entreprise entreprise) {
		super();
		this.id = id;
		this.idDg2 = idDg2;
		this.nom = nom;
		this.adresse = adresse;
		this.entreprise = entreprise;
	}
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public long getIdDg2() {
		return idDg2;
	}

	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + (int) (idDg2 ^ (idDg2 >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CentreFormation other = (CentreFormation) obj;
		if (idDg2 != other.idDg2)
			return false;
		if (nom == null) {
			return other.nom == null;
		} else return nom.equals(other.nom);
	}
	
	

}
