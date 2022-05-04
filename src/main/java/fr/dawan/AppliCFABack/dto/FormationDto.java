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
	private long idDg2;
	private String duration;
	private String slug;

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

	/**
	 * @return the idDg2
	 */
	public long getIdDg2() {
		return idDg2;
	}

	/**
	 * @param idDg2 the idDg2 to set
	 */
	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

}
