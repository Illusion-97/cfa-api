package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")

public class DevoirEtudiantDto implements Serializable {


	private long id;
	
	private long devoirId;
	
	private long etudiantId;
	
	private LocalDateTime dateRendu;
	
	private String pieceJointe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
