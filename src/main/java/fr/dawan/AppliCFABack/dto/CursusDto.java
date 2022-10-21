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
	private String duree;
	private String slug;
	private long idDg2;
	private int niveau ;
	private String sigle;
	private String millesime;
	private String codeTitre;

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

	/**
	 * @return le niveau
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau le niveau à affecter
	 
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return le sigle
	 */
	public String getSigle() {
		return sigle;
	}

	/**
	 * @param sigle le sigle à affecter
	 
	 */
	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	/**
	 * @return le millesime
	 */
	public String getMillesime() {
		return millesime;
	}

	/**
	 * @param millesime 
	 * le millesime à affecter
	 
	 */
	public void setMillesime(String millesime) {
		this.millesime = millesime;
	}

	/**
	 * @return le codeTitre
	 */
	
	public String getCodeTitre() {
		return codeTitre;
	}

	/**
	 * @param codeTitre 
	 * le codeTitre à affecter
	 
	 */
	
	public void setCodeTitre(String codeTitre) {
		this.codeTitre = codeTitre;
	}
	
	
	

}
