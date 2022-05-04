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
	private CentreFormationDto centreFormationDto;
	private EntrepriseDto entrepriseDto;
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
	 * @return the centreFormationDto
	 */
	public CentreFormationDto getCentreFormationDto() {
		return centreFormationDto;
	}
	/**
	 * @param centreFormationDto the centreFormationDto to set
	 */
	public void setCentreFormationDto(CentreFormationDto centreFormationDto) {
		this.centreFormationDto = centreFormationDto;
	}
	/**
	 * @return the entrepriseDto
	 */
	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}
	/**
	 * @param entrepriseDto the entrepriseDto to set
	 */
	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}



}
