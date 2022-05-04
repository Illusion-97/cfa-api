package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-support de cours Entity
 */
@SuppressWarnings("serial")
public class SupportCoursDto extends BaseEntityDto implements Serializable {

	private String titre;
	private String pieceJointe;
	private List<Long> interventionsId;

	public SupportCoursDto() {
		super();
	}

	public SupportCoursDto(long id, int version, String titre, String pieceJointe, List<Long> interventionsId) {
		super();
		this.id = id;
		this.version = version;
		this.titre = titre;
		this.pieceJointe = pieceJointe;
		this.interventionsId = interventionsId;
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
	 * @return the pieceJointe
	 */
	public String getPieceJointe() {
		return pieceJointe;
	}

	/**
	 * @param pieceJointe the pieceJointe to set
	 */
	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	/**
	 * @return the interventionsId
	 */
	public List<Long> getInterventionsId() {
		return interventionsId;
	}

	/**
	 * @param interventionsId the interventionsId to set
	 */
	public void setInterventionsId(List<Long> interventionsId) {
		this.interventionsId = interventionsId;
	}

	
}
