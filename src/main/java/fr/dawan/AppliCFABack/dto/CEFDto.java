package fr.dawan.AppliCFABack.dto;

public class CEFDto{
	private long id;
	private UtilisateurDto utilisateurDto;
	private CentreFormationDto centreFormationDto;
	private EntrepriseDto entrepriseDto;

	public CEFDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CentreFormationDto getCentreFormationDto() {
		return centreFormationDto;
	}

	public void setCentreFormationDto(CentreFormationDto centreFormationDto) {
		this.centreFormationDto = centreFormationDto;
	}

	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

}
