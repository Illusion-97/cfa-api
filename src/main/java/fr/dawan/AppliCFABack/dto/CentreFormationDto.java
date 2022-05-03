package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CentreFormationDto extends BaseEntityDto implements Serializable{
	private long idDg2;
	private String countryCode;
	private String nom;
	private AdresseDto adresseDto;
	private EntrepriseDto entrepriseDto;

	public CentreFormationDto() {
		super();
	}	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public AdresseDto getAdresseDto() {
		return adresseDto;
	}

	public void setAdresseDto(AdresseDto adresseDto) {
		this.adresseDto = adresseDto;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
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
}
