package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-groupe etudiant Entity
 */
@SuppressWarnings("serial")
public class GroupeEtudiantDto extends BaseEntityDto implements Serializable {

	private String nom;
	private List<EtudiantDto> etudiantsDto;

	public GroupeEtudiantDto() {
		super();
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the etudiantsDto
	 */
	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
	}

	/**
	 * @param etudiantsDto the etudiantsDto to set
	 */
	public void setEtudiantsDto(List<EtudiantDto> etudiantsDto) {
		this.etudiantsDto = etudiantsDto;
	}

}
