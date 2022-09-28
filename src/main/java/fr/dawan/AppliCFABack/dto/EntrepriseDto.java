package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import fr.dawan.AppliCFABack.entities.Entreprise.EmployeurType;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Entreprise Entity
 */
@SuppressWarnings("serial")
public class EntrepriseDto extends BaseEntityDto implements Serializable {

	private String raisonSociale;
	private String siret;
	private String naf;
	private int effectifTotal;
	private EmployeurType employeurType;
	private AdresseDto adresseSiegeDto;

	public EntrepriseDto() {
		super();
	}

	/**
	 * @return the raisonSociale
	 */
	public String getRaisonSociale() {
		return raisonSociale;
	}

	/**
	 * @param raisonSociale the raisonSociale to set
	 */
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	/**
	 * @return the siret
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * @param siret the siret to set
	 */
	public void setSiret(String siret) {
		this.siret = siret;
	}

	/**
	 * @return the naf
	 */
	public String getNaf() {
		return naf;
	}

	/**
	 * @param naf the naf to set
	 */
	public void setNaf(String naf) {
		this.naf = naf;
	}


	/**
	 * @return le effectifTotal
	 */
	public int getEffectifTotal() {
		return effectifTotal;
	}

	/**
	 * @param effectifTotal le effectifTotal à affecter
	 
	 */
	public void setEffectifTotal(int effectifTotal) {
		this.effectifTotal = effectifTotal;
	}

	/**
	 * @return le employeurType
	 */
	public EmployeurType getEmployeurType() {
		return employeurType;
	}

	/**
	 * @param employeurType le employeurType à affecter
	 
	 */
	public void setEmployeurType(EmployeurType employeurType) {
		this.employeurType = employeurType;
	}

	/**
	 * @return the adresseSiegeDto
	 */
	public AdresseDto getAdresseSiegeDto() {
		return adresseSiegeDto;
	}

	/**
	 * @param adresseSiegeDto the adresseSiegeDto to set
	 */
	public void setAdresseSiegeDto(AdresseDto adresseSiegeDto) {
		this.adresseSiegeDto = adresseSiegeDto;
	}

}
