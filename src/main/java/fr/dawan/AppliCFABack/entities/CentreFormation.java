package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CentreFormation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = true) // id dans dg2
	private long idDg2;
	
	@Column(nullable = false, length = 4) // country dans dg2
	private String countryCode;
	
	@Column(nullable = true, length = 255) // name dans dg2
	private String nom;

	@ManyToOne
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
		this.countryCode = countryCode;
		this.nom = nom;
		this.adresse = adresse;
		this.entreprise = entreprise;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
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
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (idDg2 != other.idDg2)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	

}
