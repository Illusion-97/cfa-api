package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-CentreFormation Entity
 */
@SuppressWarnings("serial")
public class CentreFormationDto extends BaseEntityDto implements Serializable{
	private long idDg2;
	private String countryCode;
	private String nom;
	private AdresseDto adresseDto;
	private EntrepriseDto entrepriseDto;
	/**
	 * @return the idDg2
	 */
	public long getIdDg2() {
		return idDg2;
	}
	/**
	 * @param idDg2 the idDg2 to set
	 */
	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the adresseDto
	 */
	public AdresseDto getAdresseDto() {
		return adresseDto;
	}
	/**
	 * @param adresseDto the adresseDto to set
	 */
	public void setAdresseDto(AdresseDto adresseDto) {
		this.adresseDto = adresseDto;
	}
	/**
	 * @return the entrepriseDto
	 */
	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}
	/**
	 * @param entrepriseDto the entrepriseDto to set
	 */
	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

	
}
