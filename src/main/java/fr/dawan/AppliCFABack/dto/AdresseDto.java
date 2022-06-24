package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Adresse Entity
 */
@SuppressWarnings("serial")
public class AdresseDto extends BaseEntityDto implements Serializable {
	private String libelle;
	private String ville;
	private String codePostal;
	private long idDg2;

	public AdresseDto() {
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
