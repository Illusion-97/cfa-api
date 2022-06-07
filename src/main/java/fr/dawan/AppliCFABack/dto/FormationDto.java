package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Formation Entity
 */
@SuppressWarnings("serial")
public class FormationDto extends BaseEntityDto implements Serializable {

	private String titre;
	private String contenu;
	private List<CursusDto> cursusLstDto;

	public FormationDto() {
		super();
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}

	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	/**
	 * @return the cursusLstDto
	 */
	public List<CursusDto> getCursusLstDto() {
		return cursusLstDto;
	}

	/**
	 * @param cursusLstDto the cursusLstDto to set
	 */
	public void setCursusLstDto(List<CursusDto> cursusLstDto) {
		this.cursusLstDto = cursusLstDto;
	}

}
