package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-utilisateur role Entity
 */
@SuppressWarnings("serial")
public class UtilisateurRoleDto extends BaseEntityDto implements Serializable {

	private String intitule;
	private List<UtilisateurDto> utilisateursDto;

	/**
	 * @return the intitule
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * @param intitule the intitule to set
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * @return the utilisateursDto
	 */
	public List<UtilisateurDto> getUtilisateursDto() {
		return utilisateursDto;
	}

	/**
	 * @param utilisateursDto the utilisateursDto to set
	 */
	public void setUtilisateursDto(List<UtilisateurDto> utilisateursDto) {
		this.utilisateursDto = utilisateursDto;
	}

	@Override
	public String toString() {
		return "UtilisateurRoleDto{" + "id=" + id + ", intitule='" + intitule + '\'' + ", utilisateursDto="
				+ utilisateursDto + '}';
	}
}
