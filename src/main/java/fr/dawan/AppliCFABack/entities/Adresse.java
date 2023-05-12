package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class Adresse extends BaseEntity implements Serializable {


	@Column(nullable = true, length = 255)
	private String libelle;

	@Column(nullable = false, length = 255)
	private String ville;

	@Column(nullable = false, length = 255)
	private String codePostal;
	
	@Column(nullable = false, length = 4) // country dans dg2
	private String countryCode;

	public Adresse() {
		super();
	}

	public Adresse(String libelle, String ville, String codePostal, String countryCode) {
		this.libelle = libelle;
		this.ville = ville;
		this.codePostal = codePostal;
		this.countryCode = countryCode;
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

	/**
	 * @return le countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode le countryCode à affecter
	 
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((codePostal== null) ? 0 : codePostal.hashCode());
		result = prime * result + ((libelle== null) ? 0 : libelle.hashCode());
		result = prime * result + ((ville== null) ? 0 : ville.hashCode());
		return result;}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adresse [libelle=");
		builder.append(libelle);
		builder.append(", ville=");
		builder.append(ville);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}


}
