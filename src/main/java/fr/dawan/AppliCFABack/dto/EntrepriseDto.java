package fr.dawan.AppliCFABack.dto;

public class EntrepriseDto {
	private long id;
	private String raisonSociale;
	private AdresseDto adresseSiegeDto;

	public EntrepriseDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public AdresseDto getAdresseSiegeDto() {
		return adresseSiegeDto;
	}

	public void setAdresseSiegeDto(AdresseDto adresseSiegeDto) {
		this.adresseSiegeDto = adresseSiegeDto;
	}

}
