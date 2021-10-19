package fr.dawan.AppliCFABack.dto;

public class EntrepriseDto {
	private long id;
	private String raisonSociale;
	private String siret;
	private String naf;
	private String effectifTotal;
	private String employeurType;
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

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getNaf() {
		return naf;
	}

	public void setNaf(String naf) {
		this.naf = naf;
	}

	public String getEffectifTotal() {
		return effectifTotal;
	}

	public void setEffectifTotal(String effectifTotal) {
		this.effectifTotal = effectifTotal;
	}

	public String getEmployeurType() {
		return employeurType;
	}

	public void setEmployeurType(String employeurType) {
		this.employeurType = employeurType;
	}
	
}
