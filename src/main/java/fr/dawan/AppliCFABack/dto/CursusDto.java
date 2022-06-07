package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Cursus Entity
 */
@SuppressWarnings("serial")
public class CursusDto extends BaseEntityDto implements Serializable {

	private String titre;
	private List<FormationDto> formationsDto;
	private String description;
	private String duree;
	private String slug;
	private long idDg2;

	public CursusDto() {
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
	 * @return the formationsDto
	 */
	public List<FormationDto> getFormationsDto() {
		return formationsDto;
	}

	/**
	 * @param formationsDto the formationsDto to set
	 */
	public void setFormationsDto(List<FormationDto> formationsDto) {
		this.formationsDto = formationsDto;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the duree
	 */
	public String getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public long getIdDg2() {
		return idDg2;
	}

	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

}
