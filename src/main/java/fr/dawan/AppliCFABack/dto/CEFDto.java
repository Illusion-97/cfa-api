package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-CEF Entity
 */
@SuppressWarnings("serial")
public class CEFDto extends BaseEntityDto implements Serializable{
	
	private UtilisateurDto utilisateurDto;

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



}
