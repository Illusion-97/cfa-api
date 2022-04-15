package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SignatureDto implements Serializable {

	private long id;
	
	private int version;
	
	private String pieceJointe;
	
	private long utilisateurId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	public long getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
	
}
