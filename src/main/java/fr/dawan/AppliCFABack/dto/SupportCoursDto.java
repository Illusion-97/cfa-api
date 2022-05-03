package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

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

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	public List<Long> getInterventionsId() {
		return interventionsId;
	}

	public void setInterventionsId(List<Long> interventionsId) {
		this.interventionsId = interventionsId;
	}

}
