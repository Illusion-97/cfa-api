package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-maitre apprentissage Entity
 */
@SuppressWarnings("serial")
public class MaitreApprentissageDto extends BaseEntityDto implements Serializable {

	private EntrepriseDto entrepriseDto;
	private UtilisateurDto utilisateurDto;

	public MaitreApprentissageDto() {
		super();
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
