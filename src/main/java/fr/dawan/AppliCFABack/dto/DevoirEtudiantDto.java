package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")

public class DevoirEtudiantDto extends BaseEntityDto implements Serializable {

	private long devoirId;
	
	private long etudiantId;
	
	private LocalDateTime dateRendu;
	
	private String pieceJointe;

	public long getDevoirId() {
		return devoirId;
	}

	public void setDevoirId(long devoirId) {
		this.devoirId = devoirId;
	}

	public long getEtudiantId() {
		return etudiantId;
	}

	public void setEtudiantId(long etudiantId) {
		this.etudiantId = etudiantId;
	}

	public LocalDateTime getDateRendu() {
		return dateRendu;
	}

	public void setDateRendu(LocalDateTime dateRendu) {
		this.dateRendu = dateRendu;
	}

	public String getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}
	
	
}
