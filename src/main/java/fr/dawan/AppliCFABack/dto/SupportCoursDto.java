package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class SupportCoursDto implements Serializable{

	private long id;
	private int version;
	private String titre;
	private String pieceJointe;
	private List<Long> interventionsId;
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
	
}
