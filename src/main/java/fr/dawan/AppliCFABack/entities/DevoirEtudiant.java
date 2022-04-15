package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class DevoirEtudiant implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Devoir devoir;
	
	@ManyToOne
	private Etudiant etudiant;
	
	private LocalDateTime dateRendu;
	
	private String pieceJointe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Devoir getDevoir() {
		return devoir;
	}

	public void setDevoir(Devoir devoir) {
		this.devoir = devoir;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
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
