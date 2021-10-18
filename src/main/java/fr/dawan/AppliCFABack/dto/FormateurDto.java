package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class FormateurDto{
	private long id;
	private UtilisateurDto personneDto;
	private List<InterventionDto> interventionsDto;
	
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

	public UtilisateurDto getPersonneDto() {
		return personneDto;
	}

	public void setPersonneDto(UtilisateurDto personneDto) {
		this.personneDto = personneDto;
	}
	
	
}
