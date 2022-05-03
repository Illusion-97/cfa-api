package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class FormateurDto extends BaseEntityDto implements Serializable {

	private UtilisateurDto utilisateurDto;
	private List<InterventionDto> interventionsDto;
	private EntrepriseDto entrepriseDto;

	public FormateurDto() {
	}

	public List<InterventionDto> getInterventionsDto() {
		return interventionsDto;
	}

	public void setInterventionsDto(List<InterventionDto> interventionsDto) {
		this.interventionsDto = interventionsDto;
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
