package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Formateur Entity
 */
@SuppressWarnings("serial")
public class FormateurDto extends BaseEntityDto implements Serializable {

	private UtilisateurDto utilisateurDto;
	private List<InterventionDto> interventionsDto;

	public FormateurDto() {
		super();
	}

	/**
	 * @return the utilisateurDto
	 */
	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	/**
	 * @param utilisateurDto the utilisateurDto to set
	 */
	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}

	/**
	 * @return the interventionsDto
	 */
	public List<InterventionDto> getInterventionsDto() {
		return interventionsDto;
	}

	/**
	 * @param interventionsDto the interventionsDto to set
	 */
	public void setInterventionsDto(List<InterventionDto> interventionsDto) {
		this.interventionsDto = interventionsDto;
	}


	
}
