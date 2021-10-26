package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class FormateurDto{
	private long id;
	private UtilisateurDto utilisateurDto;
	private List<InterventionDto> interventionsDto;
	private EntrepriseDto entrepriseDto;
	
	public FormateurDto() {
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
