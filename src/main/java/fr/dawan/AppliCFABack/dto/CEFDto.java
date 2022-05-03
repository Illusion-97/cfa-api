package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CEFDto extends BaseEntityDto implements Serializable{
	
	private UtilisateurDto utilisateurDto;
	private CentreFormationDto centreFormationDto;
	private EntrepriseDto entrepriseDto;

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
