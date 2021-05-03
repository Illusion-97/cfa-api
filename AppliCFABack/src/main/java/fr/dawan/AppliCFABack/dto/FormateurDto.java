package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class FormateurDto extends UtilisateurDto{
	private long id;
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
	
	
}
